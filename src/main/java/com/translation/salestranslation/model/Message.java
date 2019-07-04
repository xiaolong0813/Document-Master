package com.translation.salestranslation.model;

import javax.persistence.Entity;

// no @Entity is allowed here
public class Message {

    // no id is needed for message, no need to restore to database,
    // just response
    // so no repository is needed, either.

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    private Long id;

    private Integer status_code;

    private String message;

    private Object data;

    public Integer getStatus_code() {
        return status_code;
    }

    public void setStatus_code(Integer status_code) {
        this.status_code = status_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
