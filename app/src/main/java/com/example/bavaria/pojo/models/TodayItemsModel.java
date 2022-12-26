package com.example.bavaria.pojo.models;

public class TodayItemsModel {
    int comId;
    String date;
    String AndroidID;

//    public TodayItemsModel(int comId, String date, String androidID) {
//        this.comId = comId;
//        this.date = date;
//        AndroidID = androidID;
//    }

    public int getComId() {
        return comId;
    }

    public void setComId(int comId) {
        this.comId = comId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAndroidID() {
        return AndroidID;
    }

    public void setAndroidID(String androidID) {
        AndroidID = androidID;
    }
}
