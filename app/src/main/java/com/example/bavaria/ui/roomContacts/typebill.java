package com.example.bavaria.ui.roomContacts;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "typebill", indices = @Index(value = {"billType"}, unique = true))

public class typebill {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "billType")
    String billType;

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
