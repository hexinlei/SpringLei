package com.ant.ranger.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: Reply
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2016/11/30-下午1:51
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity
public class Reply extends BaseEntity{

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="user",referencedColumnName = "pk")
    private User user;

    @Column(length = 500)
    private String common;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "subReplies",referencedColumnName = "pk")
    private Set<Reply> subReplies;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "parReply",referencedColumnName = "pk")
    @NotFound(action = NotFoundAction.IGNORE)
    private Reply parReply;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public Set<Reply> getSubReplies() {
        return subReplies;
    }

    public void setSubReplies(Set<Reply> subReplies) {
        this.subReplies = subReplies;
    }

    public Reply getParReply() {
        return parReply;
    }

    public void setParReply(Reply parReply) {
        this.parReply = parReply;
    }
}
