package com.ant.ranger.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: Groups
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/16-下午7:15
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity(name = "groups")
public class Groups extends BaseEntity{
    @Column
    private String code;
    @Column
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
