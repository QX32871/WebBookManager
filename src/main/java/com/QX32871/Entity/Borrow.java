package com.QX32871.Entity;

import java.util.Date;

public class Borrow {
    private int id;
    private int book_id;
    private String book_name;
    private Date time;
    private String student_name;
    private int student_id;

    public Borrow() {
    }

    public Borrow(int id, int book_id, String book_name, Date time, String student_name, int student_id) {
        this.id = id;
        this.book_id = book_id;
        this.book_name = book_name;
        this.time = time;
        this.student_name = student_name;
        this.student_id = student_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    @Override
    public String toString() {
        return "Borrow{" +
                "id=" + id +
                ", book_id=" + book_id +
                ", book_name='" + book_name + '\'' +
                ", time=" + time +
                ", student_name='" + student_name + '\'' +
                ", student_id=" + student_id +
                '}';
    }
}
