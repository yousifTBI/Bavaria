package com.example.bavaria.pojo.classes;

//@JsonPropertyOrder({
//        "RIN",
//        "COMPANYTRADENAME",
//        "BRANCHCODE",
//        "BRANCHADDRESS",
//        "DEVICESERIALNUMBER",
//        "SYNDICATELICENSENUMBER",
//        "ACTIVITYCODE"
//})
public class Seller {
    // "tax_id"
    public String rin;


    // "Name"
    public String companyTradeName;

    //"branchID"
    public String branchCode;

    public BranchAddress branchAddress;

    //"posserial
    public String deviceSerialNumber;

          //  "LicenseExpiryDate": "2025-12-07T00:00:00",

    public String syndicateLicenseNumber;

    //        "taxpayerActivityCode": "2029",
    public String activityCode;

    public Seller() {
    }

    public Seller(String rin, String companyTradeName, String branchCode, BranchAddress branchAddress, String deviceSerialNumber, String syndicateLicenseNumber, String activityCode) {
        this.rin = rin;
        this.companyTradeName = companyTradeName;
        this.branchCode = branchCode;
        this.branchAddress = branchAddress;
        this.deviceSerialNumber = deviceSerialNumber;
        this.syndicateLicenseNumber = syndicateLicenseNumber;
        this.activityCode = activityCode;
    }

    final String COTATION = "\"";

    public String getString(){
        String result=COTATION+"RIN"+COTATION+COTATION+rin+COTATION;
        result+=COTATION+"COMPANYTRADENAME"+COTATION+COTATION+companyTradeName+COTATION;
        result+=COTATION+"BRANCHCODE"+COTATION+COTATION+branchCode+COTATION;
        result+=COTATION+"BRANCHADDRESS"+COTATION+branchAddress.getString();
        result+=COTATION+"DEVICESERIALNUMBER"+COTATION+COTATION+deviceSerialNumber+COTATION;
        result+=COTATION+"SYNDICATELICENSENUMBER"+COTATION+COTATION+syndicateLicenseNumber+COTATION;
        result+=COTATION+"ACTIVITYCODE"+COTATION+COTATION+activityCode+COTATION;
        return  result;
    }

    public String getRin() {
        return rin;
    }

    public void setRin(String rin) {
        this.rin = rin;
    }

    public String getCompanyTradeName() {
        return companyTradeName;
    }

    public void setCompanyTradeName(String companyTradeName) {
        this.companyTradeName = companyTradeName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public BranchAddress getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(BranchAddress branchAddress) {
        this.branchAddress = branchAddress;
    }

    public String getDeviceSerialNumber() {
        return deviceSerialNumber;
    }

    public void setDeviceSerialNumber(String deviceSerialNumber) {
        this.deviceSerialNumber = deviceSerialNumber;
    }

    public String getSyndicateLicenseNumber() {
        return syndicateLicenseNumber;
    }

    public void setSyndicateLicenseNumber(String syndicateLicenseNumber) {
        this.syndicateLicenseNumber = syndicateLicenseNumber;
    }

    public String getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(String activityCode) {
        this.activityCode = activityCode;
    }

    public String getCOTATION() {
        return COTATION;
    }
}
