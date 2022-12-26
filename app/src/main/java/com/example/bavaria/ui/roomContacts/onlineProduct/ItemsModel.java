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

    @ColumnInfo(name = "Quantity")
    int Quantity=1;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

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


    public int getRecord_ID() {
        return Record_ID;
    }

    public void setRecord_ID(int record_ID) {
        Record_ID = record_ID;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEditor() {
        return Editor;
    }

    public void setEditor(String editor) {
        Editor = editor;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setItemType(String itemType) {
        ItemType = itemType;
    }

    public void setItemCode(int itemCode) {
        ItemCode = itemCode;
    }

    public void setUnitType(String unitType) {
        UnitType = unitType;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
