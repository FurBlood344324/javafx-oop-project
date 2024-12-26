package com.example.bagistakipsistemi.Classes;

public class Donate extends Data {
    private String SenderName;
    private String SenderSurname;
    private String InstutionName;
    private String DonateType;
    private String SpecialDonateType;
    private int DonateAmount;
    private int GoalDonateAmount;
    private String Explanation;
    private Boolean isAnonym;
    private String dataType;

    public Donate() {
    }

    public Donate(String SenderName, String SenderSurname, String instutionName, String donateType, String SpecialDonateType,
                  int DonateAmount, int GoalDonateAmount, String Explanation, Boolean isAnonym) {
        this.SenderName = SenderName;
        this.SenderSurname = SenderSurname;
        this.InstutionName = instutionName;
        this.DonateType = donateType;
        this.SpecialDonateType = SpecialDonateType;
        this.DonateAmount = DonateAmount;
        this.GoalDonateAmount = GoalDonateAmount;
        this.Explanation = Explanation;
        this.isAnonym = isAnonym;
        this.dataType = "donate";
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

    public int getDonateAmount() {
        return DonateAmount;
    }

    public void setDonateAmount(int donateAmount) {
        this.DonateAmount = donateAmount;
    }

    public int getGoalDonateAmount() {
        return GoalDonateAmount;
    }

    public void setGoalDonateAmount(int goalDonateAmount) {
        this.GoalDonateAmount = goalDonateAmount;
    }

    public String getExplanation() {
        return Explanation;
    }

    public void setExplanation(String explanation) {
        this.Explanation = explanation;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
