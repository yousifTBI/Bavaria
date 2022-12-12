package com.example.bavaria.ui.roomContacts.backup;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao
public interface ItemBackupDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(ItemsBackup user);

    @Query("select*from ItemsBackup")
    Single<List<ItemsBackup>> getContacts();

    @Query("Select * from ItemsBackup  WHERE IDBill= :ID")
    Single<List<ItemsBackup>> getlistItems(String ID);
    @Query("DELETE FROM ItemsBackup")
    public void delete();

}
