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
    String SendToEtaWay;

    @ColumnInfo(name = "SendWayName")
    String SendWayName;

    @ColumnInfo(name = "ItemFlag")
    String ItemFlag;

    @ColumnInfo(name = "ItemFlagName")
    String ItemFlagName;

    @ColumnInfo(name = "PriceFlag")
    String PriceFlag;

    @ColumnInfo(name = "PriceFlagName")
    String PriceFlagName;
}
