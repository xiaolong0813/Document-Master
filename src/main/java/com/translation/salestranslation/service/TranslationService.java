package com.translation.salestranslation.service;

import com.translation.salestranslation.model.Message;
import com.translation.salestranslation.model.UploadFile;
import com.translation.salestranslation.model.UploadFileRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class TranslationService {
    private static final Logger logger = LogManager.getLogger(FileProcessService.class);

    @Autowired
    private UploadFileRepository uploadFileRepository;


    public void translate(UploadFile uploadFile, Message msg) {
        logger.info("Start to parse file");
        String filepath = uploadFile.getFilepath();

        try {
            File doc = new File(filepath);
            FileInputStream fis = new FileInputStream(doc);
            XWPFDocument document = new XWPFDocument(fis);
            // get all paragraphs
            List<XWPFParagraph> paragraphs = document.getParagraphs();





        } catch (Exception e) {
            msg.setStatus_code(-1);
            msg.setMessage(e.getMessage());
        }


    }
}
