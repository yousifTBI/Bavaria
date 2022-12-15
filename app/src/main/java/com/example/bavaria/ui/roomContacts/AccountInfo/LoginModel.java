package com.example.bavaria.ui.roomContacts.AccountInfo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "LoginModel", indices = @Index(value = {"comid"}, unique = true))

public class LoginModel {
    @PrimaryKey(autoGenerate = true)

    int comid;

    @ColumnInfo(name = "billType")

    int branchID;
    @ColumnInfo(name = "billType")

    int POS_id;
    @ColumnInfo(name = "billType")

    String Name;
    int tax_id;
    String type;
    String taxpayerActivityCode;
    String BranchName;
    String country;
    String governate;
    String regionCity;
    String street;
    String buildingNumber;
    String postalCode;
    String LicenseExpiryDate;
    String POSName;
    String posserial;
    String clintId;
    String scId;
    String pososversion;
    int ENVIRONMENT;
    String AndroidID;
    String EnvironmentStatues;
    String TypeVersion;
    String SendToEtaWay;
    String SendWayName;
    String ItemFlag;
    String ItemFlagName;
    String PriceFlag;
    String PriceFlagName;
}
