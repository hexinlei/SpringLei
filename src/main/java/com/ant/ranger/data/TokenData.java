package com.ant.ranger.data;

/**
 * @packgeName: com.ant.ranger.data
 * @ClassName: TokenData
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/17-下午3:36
 * @version: 1.0
 * @since: JDK 1.8
 */
public class TokenData {

    private String token;

    public TokenData(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
