package com.translation.salestranslation.controller;

import com.translation.salestranslation.model.Message;
import com.translation.salestranslation.model.UploadFile;
import com.translation.salestranslation.model.UploadFileRepository;
import com.translation.salestranslation.service.FileProcessService;
import com.translation.salestranslation.service.TranslationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/trans")
public class TranslationController {
    private static final Logger logger = LogManager.getLogger(TranslationController.class);

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
    private TranslationService translationService;

    @GetMapping("/{fileId}")
    public Message translateFile(@PathVariable Long fileId) {
        logger.info("Get translation request for file : " + fileId);
        Message msg = new Message();
        Optional<UploadFile> file = uploadFileRepository.findById(fileId);

        if (!file.isPresent()) {
            logger.info("The file " + fileId + " does not exist!");
            msg.setStatus_code(-1);
            msg.setMessage("The file " + fileId + " does not exist!");
            return msg;
        }
        else {
            UploadFile uploadFile = file.get();
            translationService.translate(uploadFile, msg);
        }
        return msg;
    }


}
