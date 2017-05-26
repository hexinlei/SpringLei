package com.ant.ranger.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @packgeName: com.ant.ranger.entity
 * @ClassName: User
 * @copyright: Copyright 2016-2027 Markor Investment Group Co. LTD. All Rights Reserved.
 * @description:<描述>
 * @author: hexinlei
 * @date: 16/7/22-上午11:48
 * @version: 1.0
 * @since: JDK 1.8
 */
@Entity
public class User extends BaseEntity{

    @Column(name = "uid",unique = true)
    private String uid;

    @Column(name = "name")
    private String name;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "img")
    private String img;

    @Column(name = "selfIntroduction")
    private String selfIntroduction;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "catalogs",referencedColumnName = "pk")
    private Set<Catalog> catalogs;

    @ManyToMany
    @JoinTable(name = "authorityMembers",joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "aid")})
    private Set<Authorities> authorities;

    @ManyToMany
    @JoinTable(name = "groupMembers",joinColumns = {@JoinColumn(name = "uid")},inverseJoinColumns = {@JoinColumn(name = "gid")})
    private Set<Groups> groupMembers;

    @Column(name = "enabled",columnDefinition="boolean default true")
    private boolean enabled;

    @Column(name = "accountNonExpired",columnDefinition="boolean default true")
    private boolean accountNonExpired;

    @Column(name = "accountNonLocked",columnDefinition="boolean default true")
    private boolean accountNonLocked;

    @Column(name = "credentialsNonExpired",columnDefinition="boolean default true")
    private boolean credentialsNonExpired;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public Set<Groups> getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Set<Groups> groupMembers) {
        this.groupMembers = groupMembers;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
