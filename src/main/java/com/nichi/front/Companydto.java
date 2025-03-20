package com.nichi.front;

public class Companydto {
    private String CompanyID;
    private String BoothNO;
    private String Name;
    private String Countryoforigin;
    private String category;
    private String Email;
    private String PhoneNo;
    private String Website;
    private String Address;
    private String SheetNumber;

    public Companydto() {
    }

    public Companydto(String companyID, String boothNO, String name, String countryoforigin, String category, String email, String phoneNo, String website, String address, String sheetNumber) {
        CompanyID = companyID;
        BoothNO = boothNO;
        Name = name;
        Countryoforigin = countryoforigin;
        this.category = category;
        Email = email;
        PhoneNo = phoneNo;
        Website = website;
        Address = address;
        SheetNumber = sheetNumber;
    }

    public String getCompanyID() {
        return CompanyID;
    }

    public void setCompanyID(String companyID) {
        CompanyID = companyID;
    }

    public String getBoothNO() {
        return BoothNO;
    }

    public void setBoothNO(String boothNO) {
        BoothNO = boothNO;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCountryoforigin() {
        return Countryoforigin;
    }

    public void setCountryoforigin(String countryoforigin) {
        Countryoforigin = countryoforigin;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getSheetNumber() {
        return SheetNumber;
    }

    public void setSheetNumber(String sheetNumber) {
        SheetNumber = sheetNumber;
    }
}
