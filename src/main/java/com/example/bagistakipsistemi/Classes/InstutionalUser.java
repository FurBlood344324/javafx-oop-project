package com.example.bagistakipsistemi.Classes;

public class InstutionalUser extends User {
    private String InstitutionName;
    private String IBANNumber;
    private String explanation;

    public InstutionalUser() {
    }

    public InstutionalUser(String institutionName, String IBANNumber, String explanation, String nickname, String email, String password) {
        this.InstitutionName = institutionName;
        this.IBANNumber = IBANNumber;
        this.explanation = explanation;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.dataType = "instutionaluser";
    }

    public String getInstitutionName() {
        return InstitutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.InstitutionName = institutionName;
    }

    public String getIBANNumber() {
        return IBANNumber;
    }

    public void setIBANNumber(String IBANNumber) {
        this.IBANNumber = IBANNumber;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
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
