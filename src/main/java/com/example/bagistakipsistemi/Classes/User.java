package com.example.bagistakipsistemi.Classes;

abstract public class User extends Data {
    protected String nickname;
    protected String email;
    protected String password;
    protected String dataType;

    abstract public String getNickname();
    abstract public String getEmail();
    abstract public String getPassword();
    abstract public String getDataType();
    abstract public void setNickname(String nickname);
    abstract public void setEmail(String email);
    abstract public void setPassword(String password);
    abstract public void setDataType(String dataType);
}
