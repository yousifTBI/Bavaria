package com.example.bavaria.ui.roomContacts;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface typeBillDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable inserttypebill(typebill type);


    @Query("select*from typebill")
    Single<List<typebill>> gettypebill();
}
