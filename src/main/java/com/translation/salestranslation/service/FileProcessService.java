package com.translation.salestranslation.service;

import com.translation.salestranslation.model.PDFRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.Interval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class FileProcessService {
    private static final Logger logger = LogManager.getLogger(FileProcessService.class);

    @Autowired
    private PDFRepository pdfRepository;


    @Async("Async")
    public void uploadPDFs() throws Exception {


        // refer to scdn blog in java-spring
        logger.info("start to upload file");

        Thread.sleep(5000);

        logger.info("uploading finished!");
    }

}
