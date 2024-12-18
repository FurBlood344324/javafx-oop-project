package com.example.bagistakipsistemi.Classes;

public class Donate extends Data {
    private String SenderName;
    private String SenderSurname;
    private String InstutionName;
    private String DonateType;
    private String SpecialDonateType;
    private Boolean isAnonym;
    private String dataType;

    public Donate() {
    }

    public Donate(String SenderName, String SenderSurname, String instutionName, String donateType, Boolean isAnonym) {
        this.SenderName = SenderName;
        this.SenderSurname = SenderSurname;
        this.InstutionName = instutionName;
        this.DonateType = donateType;
        this.isAnonym = isAnonym;
        this.dataType = "donate";
    }

    public Donate(String senderName, String senderSurname, String instutionName, String donateType, String specialDonateType, Boolean isAnonym) {
        this(senderName, senderSurname, instutionName, donateType, isAnonym);
        this.SpecialDonateType = specialDonateType;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        this.SenderName = senderName;
    }

    public String getSenderSurname() {
        return SenderSurname;
    }

    public void setSenderSurname(String senderSurname) {
        this.SenderSurname = senderSurname;
    }

    public String getInstutionName() {
        return InstutionName;
    }

    public void setInstutionName(String instutionName) {
        this.InstutionName = instutionName;
    }

    public String getDonateType() {
        return DonateType;
    }

    public void setDonateType(String donateType) {
        this.DonateType = donateType;
    }

    public Boolean getIsAnonym() {
        return isAnonym;
    }

    public void setIsAnonym(Boolean isAnonym) {
        this.isAnonym = isAnonym;
    }

    public String getSpecialDonateType() {
        return SpecialDonateType;
    }

    public void setSpecialDonateType(String specialDonateType) {
        this.SpecialDonateType = specialDonateType;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
