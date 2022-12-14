package com.example.bavaria.ui.roomContacts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bavaria.ui.roomContacts.backup.HeaderBackup;
import com.example.bavaria.ui.roomContacts.backup.HeaderBackupDao;
import com.example.bavaria.ui.roomContacts.backup.ItemBackupDao;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBillDao;
import com.example.bavaria.ui.roomContacts.productRoom.Products;
import com.example.bavaria.ui.roomContacts.productRoom.ProductsDao;

@Database(entities = {ContactsRoom.class,HeaderBill.class,
        typebill.class, ItemsBill.class, Products.class,
        HeaderBackup.class,ItemsBackup.class}, version = 2)
public abstract class ContactsDatabase  extends RoomDatabase {
    private static ContactsDatabase Instance;
    public abstract ContactsDao contactsDao();
    public abstract HeaderBillDao headerBillDao();
    public abstract typeBillDao typeBillDao1();
    public abstract ItemsBillDao ItemsBillDao();
    public abstract ProductsDao productsDao();
    public abstract HeaderBackupDao HeaderBackupDao();
    public abstract ItemBackupDao ItemBackupDao();




    public static synchronized ContactsDatabase getGetInstance(Context context){
        if (Instance==null){
            Instance= Room.databaseBuilder(context,ContactsDatabase.class,"contactsNumer")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return Instance;
    }
}
