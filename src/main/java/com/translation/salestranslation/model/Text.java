package com.translation.salestranslation.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Text {

    @Id
    @GenericGenerator(name = "native", strategy = "native")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    private Long id;

    private Long pdfId;

    private Integer folderId;

    @Lob
    private String english;
    @Lob
    private String chinese;

    private Integer locX;
    private Integer locY;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPdfId() {
        return pdfId;
    }

    public void setPdfId(Long pdfId) {
        this.pdfId = pdfId;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }

    public Integer getLocX() {
        return locX;
    }

    public void setLocX(Integer locX) {
        this.locX = locX;
    }

    public Integer getLocY() {
        return locY;
    }

    public void setLocY(Integer locY) {
        this.locY = locY;
    }

    public Integer getFolderId() {
        return folderId;
    }

    public void setFolderId(Integer folderId) {
        this.folderId = folderId;
    }
}
