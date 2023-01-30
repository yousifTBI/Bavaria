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
    Completable insertheaderReturn(HeaderReturn type);

    @Query("select*from HeaderReturn")
    Single<List<HeaderReturn>> getheaderReturn();
}
