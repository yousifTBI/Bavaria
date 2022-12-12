package com.example.bavaria.ui.roomContacts.productRoom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.ContactsRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface ItemsBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(ItemsBill user);

    @Query("select*from ItemsBill")
    Single<List<ItemsBill>> getContacts();

    @Query("Select * from ItemsBill  WHERE IDBill= :ID")
    Single<List<ItemsBill>> getlistItems(String ID);
    @Query("DELETE FROM ItemsBill")
    public void delete();

}
