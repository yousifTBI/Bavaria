package com.example.bavaria.ui.roomContacts.AccountInfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "LoginModel", indices = @Index(value = {"comid"}, unique = true))

public class LoginModel {
    @PrimaryKey(autoGenerate = true)
    int comid;

    @ColumnInfo(name = "branchID")
    int branchID;

    @ColumnInfo(name = "POS_id")
    int POS_id;

    @ColumnInfo(name = "Name")
    String Name;

    @ColumnInfo(name = "tax_id")
    int tax_id;

    @ColumnInfo(name = "type")
    String type;

    @ColumnInfo(name = "taxpayerActivityCode")
    String taxpayerActivityCode;

    @ColumnInfo(name = "BranchName")
    String BranchName;

    @ColumnInfo(name = "country")
    String country;

    @ColumnInfo(name = "governate")
    String governate;

    @ColumnInfo(name = "regionCity")
    String regionCity;

    @ColumnInfo(name = "street")
    String street;

    @ColumnInfo(name = "buildingNumber")
    String buildingNumber;

    @ColumnInfo(name = "postalCode")
    String postalCode;

    @ColumnInfo(name = "LicenseExpiryDate")
    String LicenseExpiryDate;

    @ColumnInfo(name = "POSName")
    String POSName;

    @ColumnInfo(name = "posserial")
    String posserial;

    @ColumnInfo(name = "clintId")
    String clintId;

    @ColumnInfo(name = "scId")
    String scId;

    @ColumnInfo(name = "pososversion")
    String pososversion;

    @ColumnInfo(name = "ENVIRONMENT")
    int ENVIRONMENT;

    @ColumnInfo(name = "AndroidID")
    String AndroidID;

    @ColumnInfo(name = "EnvironmentStatues")
    String EnvironmentStatues;

    @ColumnInfo(name = "TypeVersion")
    String TypeVersion;

    @ColumnInfo(name = "SendToEtaWay")
    int SendToEtaWay;

    @ColumnInfo(name = "SendWayName")
    String SendWayName;

    @ColumnInfo(name = "ItemFlag")
    int  ItemFlag;

    @ColumnInfo(name = "ItemFlagName")
    String ItemFlagName;

    @ColumnInfo(name = "PriceFlag")
    int PriceFlag;

    @ColumnInfo(name = "PriceFlagName")
    String PriceFlagName;

    public int getComid() {
        return comid;
    }

    public void setComid(int comid) {
        this.comid = comid;
    }

    public int getBranchID() {
        return branchID;
    }

    public void setBranchID(int branchID) {
        this.branchID = branchID;
    }

    public int getPOS_id() {
        return POS_id;
    }

    public void setPOS_id(int POS_id) {
        this.POS_id = POS_id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTax_id() {
        return tax_id;
    }

    public void setTax_id(int tax_id) {
        this.tax_id = tax_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaxpayerActivityCode() {
        return taxpayerActivityCode;
    }

    public void setTaxpayerActivityCode(String taxpayerActivityCode) {
        this.taxpayerActivityCode = taxpayerActivityCode;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String getRegionCity() {
        return regionCity;
    }

    public void setRegionCity(String regionCity) {
        this.regionCity = regionCity;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLicenseExpiryDate() {
        return LicenseExpiryDate;
    }

    public void setLicenseExpiryDate(String licenseExpiryDate) {
        LicenseExpiryDate = licenseExpiryDate;
    }

    public String getPOSName() {
        return POSName;
    }

    public void setPOSName(String POSName) {
        this.POSName = POSName;
    }

    public String getPosserial() {
        return posserial;
    }

    public void setPosserial(String posserial) {
        this.posserial = posserial;
    }

    public String getClintId() {
        return clintId;
    }

    public void setClintId(String clintId) {
        this.clintId = clintId;
    }

    public String getScId() {
        return scId;
    }

    public void setScId(String scId) {
        this.scId = scId;
    }

    public String getPososversion() {
        return pososversion;
    }

    public void setPososversion(String pososversion) {
        this.pososversion = pososversion;
    }

    public int getENVIRONMENT() {
        return ENVIRONMENT;
    }

    public void setENVIRONMENT(int ENVIRONMENT) {
        this.ENVIRONMENT = ENVIRONMENT;
    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }

    public String getEnvironmentStatues() {
        return EnvironmentStatues;
    }

    public void setEnvironmentStatues(String environmentStatues) {
        EnvironmentStatues = environmentStatues;
    }

    public String getTypeVersion() {
        return TypeVersion;
    }

    public void setTypeVersion(String typeVersion) {
        TypeVersion = typeVersion;
    }

    public int getSendToEtaWay() {
        return SendToEtaWay;
    }

    public void setSendToEtaWay(int sendToEtaWay) {
        SendToEtaWay = sendToEtaWay;
    }

    public String getSendWayName() {
        return SendWayName;
    }

    public void setSendWayName(String sendWayName) {
        SendWayName = sendWayName;
    }

    public int getItemFlag() {
        return ItemFlag;
    }

    public void setItemFlag(int itemFlag) {
        ItemFlag = itemFlag;
    }

    public String getItemFlagName() {
        return ItemFlagName;
    }

    public void setItemFlagName(String itemFlagName) {
        ItemFlagName = itemFlagName;
    }

    public int getPriceFlag() {
        return PriceFlag;
    }

    public void setPriceFlag(int priceFlag) {
        PriceFlag = priceFlag;
    }

    public String getPriceFlagName() {
        return PriceFlagName;
    }

    public void setPriceFlagName(String priceFlagName) {
        PriceFlagName = priceFlagName;
    }
}
