package com.example.demo.dto;

public class UserDTO {
    private int id;
    private String name;
    private String password;
    private byte type;
    private String email;
    private byte disabled;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte getDisabled() {
        return disabled;
    }

    public void setDisabled(byte disabled) {
        this.disabled = disabled;
    }
}
