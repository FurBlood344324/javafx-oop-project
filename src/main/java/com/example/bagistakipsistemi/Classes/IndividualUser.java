package com.example.bagistakipsistemi.Classes;

public class IndividualUser extends User {
    private String name;
    private String surname;
    private int telephonenumber;

    public IndividualUser(){
    }

    public IndividualUser(String name, String surname, int telephonenumber, String nickname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.telephonenumber = telephonenumber;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.dataType = "individualuser";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getTelephonenumber() {
        return telephonenumber;
    }

    public void setTelephonenumber(int telephonenumber) {
        this.telephonenumber = telephonenumber;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
