package com.example.bavaria.ui.roomContacts.backup;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(tableName = "HeaderBackup",indices = @Index(value = {"LastUUID"},unique = true))

public class HeaderBackup {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "UUID")
    String UUID;

    @ColumnInfo(name = "BillNumber")
    String BillNumber;


    @ColumnInfo(name = "InvoiceDate")
    String InvoiceDate;

    @ColumnInfo(name = "netPrice")
    Double netPrice;

    @ColumnInfo(name = "tax")
    Double tax;

    @ColumnInfo(name = "totalPrice")
    Double totalPrice;

    @ColumnInfo(name = "Link")
    String Link;



    @ColumnInfo(name = "LastUUID")
    String LastUUID;

    @ColumnInfo(name = "IDBill")
    String IDBill;


    @ColumnInfo(name = "InvoiceType")
    String InvoiceType;
    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }
    public Double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(Double netPrice) {
        this.netPrice = netPrice;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastUUID() {
        return LastUUID;
    }

    public void setLastUUID(String lastUUID) {
        LastUUID = lastUUID;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }



    public String getBillNumber() {
        return BillNumber;
    }

    public void setBillNumber(String billNumber) {
        BillNumber = billNumber;
    }
}
