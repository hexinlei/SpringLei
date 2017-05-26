package com.ant.ranger.data;

import java.io.Serializable;

/**
 * @packgeName: com.ant.ranger.data
 * @ClassName: ResponseData
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/17-下午3:35
 * @version: 1.0
 * @since: JDK 1.8
 */
public class ResponseData implements Serializable{

    private static final long serialVersionUID = 1L;

    protected int code;

    private String text;

    private Object data;

    public ResponseData(int code, String text, Object data) {
        this.code = code;
        this.text = text;
        this.data = data;
    }

    public ResponseData(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
