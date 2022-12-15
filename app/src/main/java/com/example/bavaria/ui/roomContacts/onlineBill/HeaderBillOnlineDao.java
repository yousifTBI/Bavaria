package com.example.bavaria.ui.roomContacts.onlineBill;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.HeaderBill;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface HeaderBillOnlineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertHeaderBill(HeaderBillOnline user);


    @Query("select*from HeaderBillOnline")
    Single<List<HeaderBillOnline>> getHeaderBill();

    @Query("Select * from HeaderBillOnline  WHERE InvoiceType= :invoiceType")
    Single<List<HeaderBillOnline>> getInvoiceByType(String invoiceType);

    @Query("DELETE FROM HeaderBillOnline")
    Completable  delete() ;

}
