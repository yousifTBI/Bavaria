package com.example.bavaria.ui.roomContacts.productRoom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Products",indices = @Index(value = {"id"},unique = true))

public class Products {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "internalCode")
    public String internalCode;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "itemType")
    public String itemType;

    @ColumnInfo(name = "itemCode")
    public String itemCode;

    @ColumnInfo(name = "unitPrice")
    public String  unitPrice;

    @ColumnInfo(name = "unitType")
    public String unitType;



    public String getInternalCode() {
        return internalCode;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
}
