package com.zyl.entity;

public class BookInfo {
    private int bookid;
    private String author;
    private String bookname;
    private int state;
    private int borrowerid;
    private int time;
    public int getBookid() {
        return bookid;
    }
    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getBookname() {
        return bookname;
    }
    public void setBookname(String bookname) {
        this.bookname = bookname;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public int getBorrowerid() {
        return borrowerid;
    }
    public void setBorrowerid(int borrowerid) {
        this.borrowerid = borrowerid;
    }
    public int getTime() {
        return time;
    }
    public void setTime(int time) {
        this.time = time;
    }


}
