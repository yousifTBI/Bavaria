package com.example.bavaria.ui.roomContacts.returnsBill;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "ItemsReturn",indices = @Index(value = {"id"},unique = true))

public class ItemsReturn {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "IDBill")
    String IDBill;
    @ColumnInfo(name = "ItemID")
    String ItemID;
    @ColumnInfo(name = "Quantity")
    Double Quantity;
    @ColumnInfo(name = "unitPrice")
    double unitPrice;
    @ColumnInfo(name = "totalSale")
    double totalSale;
    @ColumnInfo(name = "PName")
    String PName;

    @ColumnInfo(name = "itemType")
    public String itemType;


    @ColumnInfo(name = "itemCode")
    public String itemCode;

    @ColumnInfo(name = "internalCode")
    String internalCode;


    @ColumnInfo(name = "unitType")
    public String unitType;


    public String getItemID() {
        return ItemID;
    }

    public void setItemID(String itemID) {
        ItemID = itemID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIDBill() {
        return IDBill;
    }

    public void setIDBill(String IDBill) {
        this.IDBill = IDBill;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalSale() {
        return totalSale;
    }

    public void setTotalSale(double totalSale) {
        this.totalSale = totalSale;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
