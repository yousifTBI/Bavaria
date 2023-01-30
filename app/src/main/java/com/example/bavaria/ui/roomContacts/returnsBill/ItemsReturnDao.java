package com.example.bavaria.ui.roomContacts.returnsBill;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ItemsReturnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertItemsReturn(ItemsReturn type);


    @Query("select*from ItemsReturn")
    Single<List<ItemsReturn>> getitemsReturn();
}
