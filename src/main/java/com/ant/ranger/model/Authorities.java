package com.ant.ranger.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: Authorities
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 2017/5/16-下午6:18
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity(name = "authorities")
public class Authorities extends BaseEntity {

    @Column(name = "authority")
    private String authority;

    @ManyToMany
    private Set<User> users;

    @ManyToMany
    @JoinTable(name = "groupMembers",joinColumns = {@JoinColumn(name = "aid")},inverseJoinColumns = {@JoinColumn(name = "gid")})
    private Set<Groups> authorityMembers;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Groups> getAuthorityMembers() {
        return authorityMembers;
    }

    public void setAuthorityMembers(Set<Groups> authorityMembers) {
        this.authorityMembers = authorityMembers;
    }
}
