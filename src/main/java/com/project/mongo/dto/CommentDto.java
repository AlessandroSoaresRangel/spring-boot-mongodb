package com.project.mongo.dto;

import java.io.Serializable;
import java.util.Date;

public class CommentDto implements Serializable {

    private String text;
    private Date date;
    private AuthorDto author;

    public CommentDto() {
    }

    public CommentDto(String text, Date date, AuthorDto author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }

}
