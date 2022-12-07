package com.QX32871.Entity;

public class User {
    private int id;
    private String username;
    private String nikename;
    private String password;

    public User() {
    }

    public User(int id, String username, String nikename, String password) {
        this.id = id;
        this.username = username;
        this.nikename = nikename;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nikename='" + nikename + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
