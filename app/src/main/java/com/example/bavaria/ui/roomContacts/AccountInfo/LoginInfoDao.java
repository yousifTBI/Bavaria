package com.example.bavaria.ui.roomContacts.AccountInfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.ContactsRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface LoginInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(LoginModel user);

    @Query("select*from LoginModel")
    Single<List<LoginModel>> getContacts();

    @Query("Select * from LoginModel  WHERE comid= :ITEMCODE")
    Single<List<LoginModel>> getlistItems(String ITEMCODE);
}
