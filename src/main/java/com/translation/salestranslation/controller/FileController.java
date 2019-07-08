package com.translation.salestranslation.controller;


import com.translation.salestranslation.model.Message;
import com.translation.salestranslation.model.UploadFile;
import com.translation.salestranslation.model.UploadFileRepository;
import com.translation.salestranslation.model.TextRepository;
import com.translation.salestranslation.service.FileProcessService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
// * CORS issues if crossorigin is not set, use CorsFilter to instead
//@CrossOrigin(origins = "http://localhost:4201")
@RequestMapping(path = "/file")
public class FileController {
    // generate log
    private static final Logger logger = LogManager.getLogger(FileController.class);
    // file path. * Value is from beans.factory.annotation
    @Value("${upload.path}")
    private String filePath;
    @Value("${generated.path}")
    private String generatePath;
    @Value("${python.path}")
    private String pythonPath;

    // * autowired to pack interface
    @Autowired
    private FileProcessService fileProcessService;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    private TextRepository textRepository;

    @RequestMapping("/testUpload")
    public Message testUpload(MultipartHttpServletRequest request) {
        System.out.println("get req from: " + request);
        Message msg = new Message();
        return msg;
    }

    @GetMapping("/getPDFs/{folderId}")
    public Iterable<UploadFile> getAllPDFs(@PathVariable Integer folderId) {
        logger.info("Return UploadFile files in " + folderId);
        if (folderId == 0)
            return uploadFileRepository.findAllByOrderByIdDesc();
        else
            return uploadFileRepository.findByFolderIdOrderByIdDesc(folderId);
    }

    @DeleteMapping("/{folderId}")
    public Message deleteAllPDFs(@PathVariable Integer folderId) {
        Message message = new Message();
        try {
            if (folderId == 0) {
                uploadFileRepository.deleteAll();
                textRepository.deleteAll();
            }
            else {
                uploadFileRepository.deleteByFolderId(folderId);
                textRepository.deleteByFolderId(folderId);
            }
            message.setStatus_code(200);
            logger.info("All data in folder" + folderId + "has been deleted");
        } catch (Exception e) {
            logger.info("Delete failed for folder " + folderId);
            message.setStatus_code(-1);
        }
        return message;
    }


    @PostMapping(path = "/uploadpdf1")
    public Message testUploadPDFs(MultipartHttpServletRequest upPDFs) {
        Message msg = new Message();

        List<MultipartFile> files = upPDFs.getFiles("upFiles");

        logger.info("files  : " + files.size());

        msg.setMessage("test message");

        return msg;
    }

    // * (@RequestPart List<MultipartFile> upPDFs) cannot be used directly to get
    //   uploaded filelist
    // * method to get request : RequestParam, HttpServletRequest etc.
    // * RequestParam(key) can also be used to get params
    @PostMapping(path = "/upload")
    public Iterable<Message> uploadPDFs(MultipartHttpServletRequest upPDFs) {
        Integer fileType = Integer.parseInt(upPDFs.getParameter("type"));
        logger.info("The type of uploaded file is " + fileType);
        // get folder id for data storing
        Integer folderId = Integer.parseInt(upPDFs.getParameter("folder"));
        // set request list to local list
//        The file contents are either stored in memory or temporarily on disk.
//        In either case, the user is responsible for copying file contents to a
//        session-level or persistent store as and if desired. The temporary storage
//        will be cleared at the end of request processing
        List<MultipartFile> files = upPDFs.getFiles("upFiles");
        // generate message list for checking of each file uploading
        ArrayList<Message> msgList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(files)) {
            files.forEach( file -> fileProcessService.uploadPDFs(fileType, file, folderId, msgList));
        }
        return msgList;
    }
}
