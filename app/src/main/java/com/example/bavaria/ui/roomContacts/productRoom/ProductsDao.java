package com.example.bavaria.ui.roomContacts.productRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface ProductsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(Products user);

    @Query("select*from Products")
    Single<List<Products>> getContacts();

   // @Query("Select * from Products  WHERE IDBill= :ID")
   // Single<List<Products>> getlistItems(String ID);
}
