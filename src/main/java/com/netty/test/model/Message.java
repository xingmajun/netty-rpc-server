package com.netty.test.model;

import lombok.Data;

/**
 * Created by zcfrank1st on 2/17/16.
 */
@Data
public class Message<T> {
    private int code;
    private String description;
    private T body;
}
