package com.example.bavaria.ui.roomContacts;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface HeaderBillDao {



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertHeaderBill(HeaderBill user);


    @Query("select*from HeaderBill")
    Single<List<HeaderBill>> getHeaderBill();

    @Query("Select * from HeaderBill  WHERE BillNumber= :invoiceType")
    Single<List<HeaderBill>> getBillNumber(String invoiceType);
    @Query("Select * from HeaderBill  WHERE BillNumber= :invoiceType")
    Single<HeaderBill> getOneBillNumber(String invoiceType);

    @Query("DELETE FROM HeaderBill")
    Completable  delete() ;

}
