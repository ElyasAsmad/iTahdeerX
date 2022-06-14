package com.group10.itahdeerx;

import java.io.Serializable;

public class UsersModel implements Serializable {

    private String username = "";
    private String password = "";
    private String matricNumber = "";
    private String fullName = "";
    private String mahallahName = "";

    public UsersModel() {
    }

    public UsersModel(String matricNumber, String password) {
        this.matricNumber = matricNumber;
        this.password = password;
    }

    public UsersModel(String username, String password, String matricNumber, String fullName) {
        this.username = username;
        this.password = password;
        this.matricNumber = matricNumber;
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMahallahName() {
        return mahallahName;
    }

    public void setMahallahName(String mahallahName) {
        this.mahallahName = mahallahName;
    }

    @Override
    public String toString() {
        return "Username: " + this.username + " | " +
                "Password: " + this.password + " | " +
                "Matric Number: " + this.matricNumber + " | " +
                "Full Name: " + this.fullName + " | " +
                "Mahallah's Name: " + this.mahallahName + "\n";
    }
}
