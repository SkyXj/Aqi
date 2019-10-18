package com.hexin.sample.entity;

import lombok.Data;

@Data
public class HttpResult {
    private boolean success;
    private int errcode;
    private String errmsg;
    private Result result;
}

