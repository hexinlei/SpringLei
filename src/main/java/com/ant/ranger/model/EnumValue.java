package com.ant.ranger.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: EnumValue
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 16/8/30-下午5:18
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity
public class EnumValue extends BaseEntity{

    @Column(name = "code",unique = true)
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
