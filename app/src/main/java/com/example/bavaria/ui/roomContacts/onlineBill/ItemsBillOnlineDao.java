package com.example.bavaria.ui.roomContacts.onlineBill;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao

public interface ItemsBillOnlineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(ItemsBillOnlin user);

    @Query("select*from ItemsBillOnlin")
    Single<List<ItemsBillOnlin>> getContacts();

    @Query("Select * from ItemsBillOnlin  WHERE IDBill= :ID")
    Single<List<ItemsBillOnlin>> getlistItems(String ID);
    @Query("DELETE FROM ItemsBillOnlin")
    public void delete();

}
