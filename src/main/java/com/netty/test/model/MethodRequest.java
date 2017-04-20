package com.netty.test.model;

import lombok.Data;

/**
 * Created by lenovo on 2016/11/2.
 */
@Data
public class MethodRequest {
    private String requestId;
    private String serviceName;
    private String methodName;
    private Object[] argTypes;
    private Object[] parameters;
}
