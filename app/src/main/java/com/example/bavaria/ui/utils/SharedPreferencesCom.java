package com.example.bavaria.ui.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bavaria.network.ApisCalls;
import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.pojo.Flags;
import com.example.bavaria.pojo.classes.BranchAddress;
import com.example.bavaria.pojo.classes.DocumentType;
import com.example.bavaria.pojo.classes.Seller;

public class SharedPreferencesCom {
    private static SharedPreferencesCom Instance;
    private SharedPreferences sharedPreferencesLogIn;
    private SharedPreferences.Editor edits;

    public SharedPreferencesCom() {
    }

    public static void init(Context context) {
        Instance = new SharedPreferencesCom(context.getApplicationContext());

    }

    public static SharedPreferencesCom getInstance() {
        if (null == Instance)
            Instance = new SharedPreferencesCom();
        return Instance;
    }

    public SharedPreferencesCom(Context context) {
        sharedPreferencesLogIn = context.getSharedPreferences("LogIn", MODE_PRIVATE);
        edits = sharedPreferencesLogIn.edit();
    }

    public void remove() {
        sharedPreferencesLogIn.edit().clear().commit();
    }

    public void setSharedDocumentType(String typeVersion
    ) {
        edits.putString("typeVersion", typeVersion);
        edits.apply();
    }

    public void setSharedItemFlag(String ItemFlag
    ) {
        edits.putString("ItemFlag", ItemFlag);
        edits.apply();
    }
    public String getSharedItemFlag(
    ) {
        String ItemFlag = sharedPreferencesLogIn.getString("ItemFlag", "0");

        return ItemFlag;
    }

    public void setSharedPriceFlag(String PriceFlag
    ) {
        edits.putString("PriceFlag", PriceFlag);
        edits.apply();
    }
    public String getSharedPriceFlag(
    ) {
        String PriceFlag = sharedPreferencesLogIn.getString("PriceFlag", "0");

        return PriceFlag;
    }

    public void setFlagsItems(String itemsflage,String priceflage){

        edits.putString("itemsflage", itemsflage);
        edits.putString("priceflage", priceflage);
        edits.apply();
    }
    public Flags getFlagsItems() {
      //  Flags flags=new Flags();

        String itemsflage = sharedPreferencesLogIn.getString("itemsflage", "");
        String priceflage = sharedPreferencesLogIn.getString("priceflage", "");

        Flags flags=new Flags(

          Integer.valueOf(      itemsflage),
                Integer.valueOf(       priceflage)
        );
        return flags;
    }


    public void setSharedValuesSeller(String rin, String companyTradeName, String branchCode, String deviceSerialNumber,
                                      String syndicateLicenseNumber, String activityCode,String BranchName
    ) {
        edits.putString("rin", rin);
        edits.putString("companyTradeName", companyTradeName);
        edits.putString("branchCode", branchCode);
        edits.putString("deviceSerialNumber", deviceSerialNumber);
        edits.putString("syndicateLicenseNumber", syndicateLicenseNumber);
        edits.putString("activityCode", activityCode);
        edits.putString("BranchName", BranchName);

        edits.apply();
    }

    public String getBranchName(){
        String BranchName = sharedPreferencesLogIn.getString("BranchName", "");
        return BranchName;

    }

    public Seller getSharedValuesSeller() {
        Seller seller = new Seller();

        String rin = sharedPreferencesLogIn.getString("rin", "");
        String companyTradeName = sharedPreferencesLogIn.getString("companyTradeName", "");
        String branchCode = sharedPreferencesLogIn.getString("branchCode", "");
        String deviceSerialNumber = sharedPreferencesLogIn.getString("deviceSerialNumber", "");
        String syndicateLicenseNumber = sharedPreferencesLogIn.getString("syndicateLicenseNumber", "");
        String activityCode = sharedPreferencesLogIn.getString("activityCode", "");
        String BranchName = sharedPreferencesLogIn.getString("BranchName", "");

      //  seller.set
        seller.setRin(rin);
        seller.setCompanyTradeName(companyTradeName);
        seller.setBranchCode(branchCode);
        seller.setDeviceSerialNumber(deviceSerialNumber);
        seller.setSyndicateLicenseNumber(syndicateLicenseNumber);
        seller.setActivityCode(activityCode);


        return seller;
    }


    public void setSharedValuesBranchAddress(

            String country,
            String governate,
            String regionCity,
            String street,
            String buildingNumber
    ) {
        edits.putString("country", country);
        edits.putString("governate", governate);
        edits.putString("regionCity", regionCity);
        edits.putString("street", street);
        edits.putString("buildingNumber", buildingNumber);


        edits.apply();
    }


    public BranchAddress getSharedValuesBranchAddress() {
        BranchAddress branchAddress = new BranchAddress();

        String country = sharedPreferencesLogIn.getString("country", "");
        String governate = sharedPreferencesLogIn.getString("governate", "");
        String regionCity = sharedPreferencesLogIn.getString("regionCity", "");
        String street = sharedPreferencesLogIn.getString("street", "");
        String buildingNumber = sharedPreferencesLogIn.getString("buildingNumber", "");
        branchAddress.setCountry(country);
        branchAddress.setGovernate(governate);
        branchAddress.setRegionCity(regionCity);
        branchAddress.setStreet(street);
        branchAddress.setBuildingNumber(buildingNumber);

        return branchAddress;
    }
}
