package com.example.bavaria.ui.roomContacts.returnsBill;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface HeaderReturnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertHeaderReturn(HeaderReturn type);

    @Query("select*from HeaderReturn")
    Single<List<HeaderReturn>> getHeaderReturn();

    @Query("DELETE FROM HeaderReturn")
    Completable  delete() ;
}
