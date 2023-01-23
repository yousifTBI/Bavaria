package com.example.bavaria.ui.returns;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class ReturnsViewModel  extends ViewModel {


    public void getRoot(String  billNumber, Context context) {
        Log.d("onSuccessReturns", "headerBills.get(0).getInvoiceDate()");

        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.headerBillDao().getBillNumber(billNumber)
                .subscribeOn(computation())
                .observeOn(computation())
                .subscribe(new SingleObserver<List<HeaderBill>>() {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                               }
                               @Override
                               public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<HeaderBill> headerBills) {
                                   Log.d("onSuccessReturns", headerBills.get(0).getInvoiceDate());

                                   Observable ob = Observable.create(new ObservableOnSubscribe<HeaderBill>() {
                                               @Override
                                               public void subscribe(@NonNull ObservableEmitter<HeaderBill> emitter) throws Exception {

                                                   List<HeaderBill> postArrayList = headerBills;
                                                   for (HeaderBill x : postArrayList) {
                                                       emitter.onNext(x);
                                                   }
                                               }
                                           }).subscribeOn(computation())
                                           .observeOn(computation());

                                   io.reactivex.rxjava3.core.Observer obs = new io.reactivex.rxjava3.core.Observer<HeaderBill>() {
                                       @Override
                                       public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                       }
                                       @Override
                                       public void onNext(@io.reactivex.rxjava3.annotations.NonNull HeaderBill headerBill) {

                                           String uu = headerBill.getUUID();
                                           String id = headerBill.getBillNumber();
                                           String TimeRicet = headerBill.getInvoiceDate();
                                           ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
                                           contactsDatabase.ItemsBillDao().getlistItems(id)
                                                   .subscribeOn(computation())
                                                   .observeOn(AndroidSchedulers.mainThread())
                                                   .subscribe(new SingleObserver<List<ItemsBill>>() {
                                                       @Override
                                                       public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                                       }
                                                       @Override
                                                       public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsBill> itemsBills) {
                                                           List<ItemsBill> x=itemsBills;
                                                           Collections.sort(x,
                                                                   new Comparator<ItemsBill>() {
                                                                       @Override
                                                                       public int compare(ItemsBill itemsBill, ItemsBill t1) {
                                                                           return  itemsBill.getItemID().compareTo(t1.getItemID());
                                                                       }
                                                                   });

                                                        Log.d("onSuccessReturns", x.get(0).getPName());

                                                           //      ArrayList<ItemDatum> itemData = new ArrayList<>();

                                                  //      // Collections.sort(itemData,
                                                  //      //         ItemDatum);
                                                  //      for (ItemsBill i :  x) {
                                                  //          itemData.add(homeViewModel.getItems(i.getQuantity(), i.getUnitPrice(), i.getUnitPrice(), i.getPName()
                                                  //                  ,i.getInternalCode(),i.getUnitType()
                                                  //                  ,i.getItemType()
                                                  //                  , String.valueOf( i.getItemCode())
                                                  //          ));
                                                  //          //    itemData.add(homeViewModel.getItems(1.0, i.getUnitPrice(), i.getUnitPrice(), i.getPName()));
                                                  //          // itemData.add(homeViewModel.getItems(Double.valueOf(i.getQuantity()),
                                                  //          //         Double.valueOf(i.getPrice()),
                                                  //          //         Double.valueOf(i.getPrice()), i.getDescription(),i.getBarcode(),i.getUnitType()
                                                  //          //         ,i.getItemType()
                                                  //          //         , String.valueOf( i.getItemCode())

                                                  //          // ));
                                                  //      }
                                                  //      Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, id);
                                                  //      receiptslist1.add(r);
                                                  //      Gson gson = new Gson();

                                                  //      //      itemData.sort(Comparator.comparing(itemDatum -> ));
                                                  //      gson.toJson(r);
                                                  //      Log.d("onSuccess", "---1" + gson.toJson(r));
                                                  //      //  room.add(r);
                                                  //      // Send(r);

                                                       }

                                                       @Override
                                                       public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                                       }

                                                   });
                                       }

                                       @Override
                                       public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                                       }

                                       @Override
                                       public void onComplete() {
                                           // Toast.makeText(getContext(), "_______", Toast.LENGTH_SHORT).show();
                                           // Receipts receipts = new Receipts();
                                           // receipts.setReceipts(receiptslist1);
                                           // Gson gson = new Gson();
                                           // gson.toJson(receipts);
                                           // Log.d("onSuccess", "4" + gson.toJson(receipts));
                                           // slideshowViewModel.sendList(receipts);

                                       }
                                   };

                                   ob.subscribe(obs);
                                   // binding.textView20.setText(headerBills.get(headerBills.size() - 1).getBillNumber());
                               }

                               @Override
                               public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                               }
                           });
        //  return  receiptslist1;
    }
    public void getBillRoot(String  billNumber, Context context) {
        Log.d("onSuccessReturns", "headerBills.get(0).getInvoiceDate()");

        Toast.makeText(context, "ll", Toast.LENGTH_SHORT).show();
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.headerBillDao().getOneBillNumber(billNumber)
                .subscribeOn(computation())
                .observeOn(computation())
                .subscribe(new SingleObserver<HeaderBill>() {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                               }
                               @Override
                               public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull HeaderBill headerBills) {
                                   Log.d("onSuccessReturns", headerBills.getInvoiceDate());

                                   Toast.makeText(context, headerBills.getInvoiceDate(), Toast.LENGTH_SHORT).show();

                                   // Observable ob = Observable.create(new ObservableOnSubscribe<HeaderBill>() {
                                 //             @Override
                                 //             public void subscribe(@NonNull ObservableEmitter<HeaderBill> emitter) throws Exception {

                                 //                 List<HeaderBill> postArrayList = headerBills;
                                 //                 for (HeaderBill x : postArrayList) {
                                 //                     emitter.onNext(x);
                                 //                 }
                                 //             }
                                 //         }).subscribeOn(computation())
                                 //         .observeOn(computation());

                                 // io.reactivex.rxjava3.core.Observer obs = new io.reactivex.rxjava3.core.Observer<HeaderBill>() {
                                 //     @Override
                                 //     public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                 //     }
                                 //     @Override
                                 //     public void onNext(@io.reactivex.rxjava3.annotations.NonNull HeaderBill headerBill) {

                                          // String uu = headerBill.getUUID();
                                          // String id = headerBill.getBillNumber();
                                          // String TimeRicet = headerBill.getInvoiceDate();
                                           ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
                                           contactsDatabase.ItemsBillDao().getOneItems(headerBills.getBillNumber())
                                                   .subscribeOn(computation())
                                                   .observeOn(AndroidSchedulers.mainThread())
                                                   .subscribe(new SingleObserver<ItemsBill>() {
                                                       @Override
                                                       public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                                       }
                                                       @Override
                                                       public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull ItemsBill itemsBills) {
                                                         // List<ItemsBill> x=itemsBills;
                                                         // Collections.sort(x,
                                                         //         new Comparator<ItemsBill>() {
                                                         //             @Override
                                                         //             public int compare(ItemsBill itemsBill, ItemsBill t1) {
                                                         //                 return  itemsBill.getItemID().compareTo(t1.getItemID());
                                                         //             }
                                                         //         });
                                                           Toast.makeText(context, itemsBills.getPName(), Toast.LENGTH_SHORT).show();


                                                           Log.d("onSuccessReturns", itemsBills.getPName());

                                                           //      ArrayList<ItemDatum> itemData = new ArrayList<>();

                                                           //      // Collections.sort(itemData,
                                                           //      //         ItemDatum);
                                                           //      for (ItemsBill i :  x) {
                                                           //          itemData.add(homeViewModel.getItems(i.getQuantity(), i.getUnitPrice(), i.getUnitPrice(), i.getPName()
                                                           //                  ,i.getInternalCode(),i.getUnitType()
                                                           //                  ,i.getItemType()
                                                           //                  , String.valueOf( i.getItemCode())
                                                           //          ));
                                                           //          //    itemData.add(homeViewModel.getItems(1.0, i.getUnitPrice(), i.getUnitPrice(), i.getPName()));
                                                           //          // itemData.add(homeViewModel.getItems(Double.valueOf(i.getQuantity()),
                                                           //          //         Double.valueOf(i.getPrice()),
                                                           //          //         Double.valueOf(i.getPrice()), i.getDescription(),i.getBarcode(),i.getUnitType()
                                                           //          //         ,i.getItemType()
                                                           //          //         , String.valueOf( i.getItemCode())

                                                           //          // ));
                                                           //      }
                                                           //      Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, id);
                                                           //      receiptslist1.add(r);
                                                           //      Gson gson = new Gson();

                                                           //      //      itemData.sort(Comparator.comparing(itemDatum -> ));
                                                           //      gson.toJson(r);
                                                           //      Log.d("onSuccess", "---1" + gson.toJson(r));
                                                           //      //  room.add(r);
                                                           //      // Send(r);

                                               }

                                                 @Override
                                                 public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                                     Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

                                                 }

                                             });
                                       }

                                       @Override
                                       public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                           Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

                                       }

                                  //    @Override
                                  //    public void onComplete() {
                                  //        // Toast.makeText(getContext(), "_______", Toast.LENGTH_SHORT).show();
                                  //        // Receipts receipts = new Receipts();
                                  //        // receipts.setReceipts(receiptslist1);
                                  //        // Gson gson = new Gson();
                                  //        // gson.toJson(receipts);
                                  //        // Log.d("onSuccess", "4" + gson.toJson(receipts));
                                  //        // slideshowViewModel.sendList(receipts);

                                  //    }
                                   }

                        //          ob.subscribe(obs);
                        //          // binding.textView20.setText(headerBills.get(headerBills.size() - 1).getBillNumber());
                        //      }

                        //      @Override
                        //      public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                        //      }
                        //  }
                );
        //  return  receiptslist1;
    }
}
