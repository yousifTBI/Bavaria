package com.example.bavaria.ui.billsOnline;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class BillsOnlineViewModel extends ViewModel {
    MutableLiveData<List<ItemsModel>> itemsModelMutableLiveData=new MutableLiveData<>();

    public void setOneItemOnline( Context context) {
        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.itemOnlineDao(). getContacts()
                .subscribeOn(computation()).subscribe(new SingleObserver<List<ItemsModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ItemsModel> itemsModels) {

                        itemsModelMutableLiveData.postValue(itemsModels);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
