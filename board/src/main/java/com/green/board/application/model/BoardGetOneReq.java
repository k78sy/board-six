package com.green.board.application.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardGetOneReq {
    private int id;
    private String title;
    private String contents;
    private String createdAt;
}
