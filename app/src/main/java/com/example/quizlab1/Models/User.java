package com.example.quizlab1.Models;

public class User {
    private String firstname;
    private String lastname;
    private String avatar;
    private int id;

    public User(int id, String firstname, String lastname, String avatar) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}