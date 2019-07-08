package com.translation.salestranslation.service;

import com.translation.salestranslation.model.Message;
import com.translation.salestranslation.model.UploadFileRepository;
import com.translation.salestranslation.model.UploadFile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FileProcessService {
    private static final Logger logger = LogManager.getLogger(FileProcessService.class);
    @Value("${upload.path}")
    private String filePath;

    @Autowired
    private UploadFileRepository uploadFileRepository;

//    @Async("Async")
    public void uploadPDFs(Integer fileType, MultipartFile file, Integer folderId, ArrayList<Message> msgList) {
        // check if empty
        Message msg = new Message();
        // get name
        String name = file.getOriginalFilename();
        logger.info("Get file : " + name);
        // time stamps to uploaded files to avoid conflict with same-named files
        String filepath = filePath + System.currentTimeMillis() + "_" + name;

        if (!file.isEmpty()) {
            // upload time
            Date date = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :HH:mm:ss");
            String upTime = dateFormat.format(date);
            UploadFile uploadFile = new UploadFile();

            logger.info("start to upload file");
            BufferedOutputStream stream = null;
            // file saved
            try {
                java.io.File targetFile = new java.io.File(filepath);
                stream = new BufferedOutputStream(new FileOutputStream(targetFile));
                stream.write(file.getBytes());
                stream.flush();
                stream.close();

                // save to db
                uploadFile.setFilename(name);
                uploadFile.setFilepath(filepath);
                uploadFile.setStatus(2);
                uploadFile.setSavetime(upTime);
                uploadFile.setFolderId(folderId);
                uploadFile.setFiletype(fileType);
                uploadFileRepository.save(uploadFile);

                msg.setStatus_code(2);

                logger.info(name + " uploading finished!");
            } catch (Exception e) {
                logger.info("Failed to upload " + name + e.getMessage());
                msg.setStatus_code(-1);
                msg.setMessage("Failed to upload " + name);
            }
        }
        else {
            msg.setStatus_code(-1);
            msg.setMessage("Failed to upload " + name + ", it is empty");
        }
        msgList.add(msg);
    }
}
