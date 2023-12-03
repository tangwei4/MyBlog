package com.suye.personalblog.model;

import org.springframework.stereotype.Component;

//参观者
public class Visitor {
    private int id;
    private String names;
    private String avatar;
    private String email;
    private String address;
    private String describ;
    private int isFriend;
    private int isAdmin;

    public Visitor(){}
    public Visitor(int id,String name,String avatar,String email,String address,String describ,int isFriend,int isAdmin){
        this.id=id;
        this.names=name;
        this.avatar=avatar;
        this.email=email;
        this.address=address;
        this.describ=describ;
        this.isFriend=isFriend;
        this.isAdmin=isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return names;
    }

    public void setName(String name) {
        this.names = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescrib() {
        return describ;
    }

    public void setDescrib(String describ) {
        this.describ = describ;
    }

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
