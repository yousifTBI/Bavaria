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
    public String rin;

    public String companyTradeName;

    public String branchCode;

    public BranchAddress branchAddress;

    public String deviceSerialNumber;

    public String syndicateLicenseNumber;

    public String activityCode;

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
}
