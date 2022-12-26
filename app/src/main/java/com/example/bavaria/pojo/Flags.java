package com.example.bavaria.pojo;

public class Flags {
    int  ItemFlag;
    int PriceFlag;

    public Flags(int itemFlag, int priceFlag) {
        ItemFlag = itemFlag;
        PriceFlag = priceFlag;
    }

    public int getItemFlag() {
        return ItemFlag;
    }

    public void setItemFlag(int itemFlag) {
        ItemFlag = itemFlag;
    }

    public int getPriceFlag() {
        return PriceFlag;
    }

    public void setPriceFlag(int priceFlag) {
        PriceFlag = priceFlag;
    }
}
