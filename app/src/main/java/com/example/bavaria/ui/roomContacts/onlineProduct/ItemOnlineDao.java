package com.example.bavaria.ui.roomContacts.onlineProduct;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.bavaria.ui.roomContacts.ContactsRoom;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
@Dao

public interface ItemOnlineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertContacts(ItemsModel  user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertAllOrders(List<ItemsModel> order);

    @Query("select*from ItemsModel ")
    Single<List<ItemsModel >> getContacts();

    @Query("Select * from ItemsModel   WHERE ITEMCODE= :ITEMCODE")
    Single<List<ItemsModel >> getlistItems(String ITEMCODE);

}
