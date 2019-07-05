package com.translation.salestranslation.controller;


import com.translation.salestranslation.model.Message;
import com.translation.salestranslation.model.PDF;
import com.translation.salestranslation.model.PDFRepository;
import com.translation.salestranslation.model.TextRepository;
import com.translation.salestranslation.service.FileProcessService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.DataInput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
// * CORS issues if crossorigin is not set, use CorsFilter to instead
//@CrossOrigin(origins = "http://localhost:4201")
@RequestMapping(path = "/file")
public class FileController {
    // generate log
    private static final Logger logger = LogManager.getLogger(FileController.class);
    // file path. * Value is from beans.factory.annotation
    @Value("${pdf.path}")
    private String pdfPath;
    @Value("${file.path} + generate")
    private String generatePath;
    @Value("${file.path} + python")
    private String pythonPath;

    // * autowired to pack interface
    @Autowired
    private FileProcessService fileProcessService;

    @Autowired
    private PDFRepository pdfRepository;

    @Autowired
    private TextRepository textRepository;

    @RequestMapping("/testUpload")
    public Message testUpload(MultipartHttpServletRequest request) {
        System.out.println("get req from: " + request);
        Message msg = new Message();
        return msg;
    }

    @GetMapping("/getPDFs/{folderId}")
    public Iterable<PDF> getAllPDFs(@PathVariable Integer folderId) {
        logger.info("Return PDF files in " + folderId);
        if (folderId == 0)
            return pdfRepository.findAllByOrderByIdDesc();
        else
            return pdfRepository.findByFolderIdOrderByIdDesc(folderId);
    }

    @DeleteMapping("/{folderId}")
    public Message deleteAllPDFs(@PathVariable Integer folderId) {
        Message message = new Message();
        try {
            if (folderId == 0) {
                pdfRepository.deleteAll();
                textRepository.deleteAll();
            }
            else {
                pdfRepository.deleteByFolderId(folderId);
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
    @PostMapping(path = "/uploadpdf")
    public Message uploadPDFs(MultipartHttpServletRequest upPDFs) {
        Message msg = new Message();
//        String[] filename = new String[upPDFs.size()];
        logger.info("The type of uploaded file is PDF");
        // get folder id for data storing
        Integer folderId = Integer.parseInt(upPDFs.getParameter("folder"));
        // set request list to local list
//        The file contents are either stored in memory or temporarily on disk.
//        In either case, the user is responsible for copying file contents to a
//        session-level or persistent store as and if desired. The temporary storage
//        will be cleared at the end of request processing
        List<MultipartFile> files = upPDFs.getFiles("upFiles");

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
        String upTime = dateFormat.format(date);

        if (!CollectionUtils.isEmpty(files)) {
            try {
                files.forEach( file -> {
                    PDF pdf = new PDF();
                    String name = file.getOriginalFilename();
                    logger.info("Get file : " + name);
                    // time stamps to uploaded files to avoid conflict with same-named files
                    String filepath = pdfPath + System.currentTimeMillis() + "_" + name;

                    pdf.setFilename(name);
                    pdf.setFilepath(filepath);
                    pdf.setStatus(1);
                    pdf.setSavetime(upTime);
                    pdf.setFolderId(folderId);

                    pdfRepository.save(pdf);
                    logger.info("Start to upload pdf file asynchronously!");
                    // uploading files
                    try {
                        fileProcessService.uploadPDFs();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                // return entity in repository by descend id order
                // because latest uploaded file should be at first
                msg.setStatus_code(200);
                // no need to return right now, call the page to re-get data with timer
//                msg.setData(pdfRepository.findAllByOrderByIdDesc());
            }catch (Exception e) {
                logger.info(e.getMessage());
                msg.setStatus_code(-1);
                msg.setMessage("upload failed!");
            }
        }
        else {
            msg.setStatus_code(-1);
            msg.setMessage("Upload files are empty!");
        }

        return msg;
    }
}
