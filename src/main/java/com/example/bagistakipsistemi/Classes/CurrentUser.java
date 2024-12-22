package com.example.bagistakipsistemi.Classes;

public class CurrentUser extends User{
    private String datavar1;
    private String datavar2;
    private String datavar3;
    private String userDataType;

    public CurrentUser(){
        this.datavar1 = "";
        this.datavar2 = "";
        this.datavar3 = "";
        this.nickname = "";
        this.email = "";
        this.password = "";
        this.dataType = "currentuser";
        this.userDataType = "";
    }

    public CurrentUser(String datavar1, String datavar2, String datavar3, String nickname, String email, String password, String userDataType) {
        this.datavar1 = datavar1;
        this.datavar2 = datavar2;
        this.datavar3 = datavar3;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.dataType = "currentuser";
        this.userDataType = userDataType;
    }

    public String getDatavar1() {
        return datavar1;
    }

    public String getDatavar2() {
        return datavar2;
    }

    public String getDatavar3() {
        return datavar3;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    public String getUserDataType() {
        return userDataType;
    }

    public void setDatavar1(String datavar1) {
        this.datavar1 = datavar1;
    }

    public void setDatavar2(String datavar2) {
        this.datavar2 = datavar2;
    }

    public void setDatavar3(String datavar3) {
        this.datavar3 = datavar3;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public void setUserDataType(String userDataType) {
        this.userDataType = userDataType;
    }
}
