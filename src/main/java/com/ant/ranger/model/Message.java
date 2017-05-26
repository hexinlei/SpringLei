package com.ant.ranger.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: Message
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 16/7/29-下午5:29
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity
@Table(name = "message")
public class Message extends BaseEntity{

    @Column(length = 500)
    private String common;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "visitor",referencedColumnName = "pk")
    @NotFound(action = NotFoundAction.IGNORE)
    private Visitor visitor;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "replies",referencedColumnName = "pk")
    private Set<Reply> replies;


    public Message() {
    }

    public Message(String common, Visitor visitor) {
        this.common = common;
        this.visitor = visitor;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }
}
