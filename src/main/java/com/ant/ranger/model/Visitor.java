package com.ant.ranger.model;


import javax.persistence.*;
import java.util.Set;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: Visitor
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 16/7/29-下午5:34
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity
@Table(name = "visitor")
public class Visitor extends User{
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "messages",referencedColumnName = "pk")
    private Set<Message> messages;

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }
}
