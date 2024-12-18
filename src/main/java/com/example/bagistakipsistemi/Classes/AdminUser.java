package com.example.bagistakipsistemi.Classes;

public class AdminUser extends User {
    //Kod ortamındaki bilgilerle giriş yapılabilecek sadece
    public AdminUser() {
        this.nickname = "furkan";
        this.password = "1234";
    }

    public AdminUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public String getNickname() {
        return this.nickname;
    }

    @Override
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getDataType() {
        return "admin";
    }

    @Override
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public AdminUser createNewAdminUser(String nickname, String password) {
        return new AdminUser(nickname, password);
    }
}
