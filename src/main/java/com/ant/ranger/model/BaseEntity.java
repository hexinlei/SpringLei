package com.ant.ranger.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: BaseEntity
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 16/7/22-下午12:21
 * @version: 1.0
 * @since: JDK 1.8
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable{

    @GenericGenerator(name = "generator", strategy = "guid")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "PK")
    private String pk;

    @Column(name = "createTime")
    private Date createTime;

    @Column(name = "modifityTime")
    private Date modifityTime;


    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }
}
