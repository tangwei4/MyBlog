package com.suye.personalblog.model;

//类别
public class Category {
    private int id;
    private String name;
    private int blognum;
    public Category(){}
    public Category(int id,String name,int blognum){
        this.id=id;
        this.name=name;
        this.blognum=blognum;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBlognum() {
        return blognum;
    }

    public void setBlognum(int blognum) {
        this.blognum = blognum;
    }
}
