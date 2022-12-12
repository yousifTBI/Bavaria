package com.example.bavaria.ui.roomContacts.backup;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.HeaderBill;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface HeaderBackupDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertHeaderBill(HeaderBackup user);


    @Query("select*from HeaderBackup")
    Single<List<HeaderBackup>> getHeaderBill();

    @Query("Select * from HeaderBackup  WHERE InvoiceType= :invoiceType")
    Single<List<HeaderBackup>> getInvoiceByType(String invoiceType);

    @Query("DELETE FROM HeaderBackup")
    Completable  delete() ;
}
