package com.example.bavaria.ui.slideshow;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Receipts;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.classes.models.BillReturn;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    final  ArrayList<Root> receiptslist = new ArrayList<>();

    ArrayList<Root> receiptslist3 = new ArrayList<>();
    MutableLiveData<List<Root>>receiptslist6=new MutableLiveData<>();


    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }
    public MutableLiveData<List<ItemsBill>> liveData=new MutableLiveData<>();

    public ArrayList<ItemDatum> getItemsbyID(String id,Context context) {
        ArrayList<ItemDatum> itemData = new ArrayList<>();
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.ItemsBillDao().getlistItems(id)
                //getlistItems("123")
        //receiptslist6.
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ItemsBill>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsBill> itemsBills) {
                        liveData.setValue(itemsBills);

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });

        // }
        return itemData;
    }

    public ArrayList<ItemDatum> getItems( Context context) {
        ArrayList<ItemDatum> itemData = new ArrayList<>();
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.ItemsBillDao().getContacts()
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ItemsBill>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsBill> itemsBills) {

                        liveData.setValue(itemsBills);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });

        // }
        return itemData;
    }
    public MutableLiveData<List<HeaderBill>> liveDataHeaderBill=new MutableLiveData<>();
    public void getHeadersBill(Context context) {
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.headerBillDao().getHeaderBill()
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<HeaderBill>>() {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                   Log.d("onSuccess","zzzz1");
                               }

                               @Override
                               public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<HeaderBill> headerBills) {
                                   if (headerBills.isEmpty()){

                                   }else {
                                       liveDataHeaderBill.setValue(headerBills);
                                   }
                                  // liveDataHeaderBill.setValue(headerBills);
                                 //  Log.d("onSuccess","zzzz");
                                //   SetBills(headerBills);
                                   // binding.textView20.setText(headerBills.get(headerBills.size() - 1).getBillNumber());
                               }

                               @Override
                               public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                   Log.d("onSuccess",e.getMessage());
                               }
                           }
                );

    }
    public void delete(){
//        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
//        contactsDatabase.headerBillDao().delete().subscribeOn(computation())
////                .subscribeOn(computation())
//                .observeOn(computation())
//                .subscribe();

    }
    public void sendList(Receipts r){
        Observable GetTransactions= RetrofitRefranc.getInstance().getApiCalls().SetListBill(r)
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer <BillReturn>listObserver= new Observer<BillReturn>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull BillReturn billReturn) {
                for (String p:billReturn.getQrCode()) {
                    Log.d("onSuccess",p);
                }
                for (String p:billReturn.getErrorMessage()) {
                    Log.d("onSuccess",p);
                }

                Log.d("onSuccess", billReturn.getStatus()+"");
                Log.d("onSuccess",billReturn.getSubmitionID()+"");
                Log.d("onSuccess",billReturn.getSubmitted()+"");
                delete();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("onSuccess", e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d("onSuccess", "onComplete");
            }
        };

       // io.reactivex.rxjava3.core.Observer <LoginModel> listObserver=new io.reactivex.rxjava3.core.Observer<LoginModel>() {
       //     @Override
       //     public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
//
       //     }
//
       //     @Override
       //     public void onNext(@io.reactivex.rxjava3.annotations.NonNull LoginModel loginModel) {
       //
       //     }
//
       //     @Override
       //     public void onError(@NonNull Throwable e) {
       //
       //     }
//
       //     @Override
       //     public void onComplete() {
//
       //     }
       // };
        GetTransactions.subscribe(listObserver);

    }



}