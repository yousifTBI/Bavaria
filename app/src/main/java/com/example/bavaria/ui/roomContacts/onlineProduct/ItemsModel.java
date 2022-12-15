package com.example.bavaria.ui.roomContacts.onlineProduct;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import ir.mirrajabi.searchdialog.core.Searchable;

@Entity(tableName = "ItemsModel", indices = @Index(value = {"Record_ID"}, unique = true))
public class ItemsModel implements Searchable {
    @PrimaryKey(autoGenerate = true)
    int Record_ID;
    @ColumnInfo(name = "ItemName")
    String ItemName;
    @ColumnInfo(name = "Barcode")
    String Barcode;
    @ColumnInfo(name = "Description")
    String Description;
    @ColumnInfo(name = "Editor")
    String Editor;
    @ColumnInfo(name = "Date")
    String Date;
    @ColumnInfo(name = "ItemType")
    String ItemType;
    @ColumnInfo(name = "ItemCode")
    int ItemCode;
    @ColumnInfo(name = "UnitType")
    String UnitType;
    @ColumnInfo(name = "Price")
    double Price;

    @Override
    public String getTitle() {
        return Description;
    }

    public String getPrice() {
        return String.valueOf(Price);
    }

    public String getBarcode() {
        return Barcode;
    }

    public String getItemCode() {
        return String.valueOf(ItemCode);
    }

    public String getItemType() {
        return ItemType;
    }

    public String getUnitType() {
        return UnitType;
    }
}
