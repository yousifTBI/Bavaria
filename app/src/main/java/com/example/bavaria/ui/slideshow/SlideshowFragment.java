package com.example.bavaria.ui.slideshow;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.bavaria.databinding.FragmentSlideshowBinding;
import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Receipts;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.models.BillReturn;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.utils.SharedPreferencesCom;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class SlideshowFragment extends Fragment  {

    public FragmentSlideshowBinding binding;
    HomeViewModel homeViewModel;
    SlideshowViewModel slideshowViewModel;
  // ArrayList<Root> receiptslist = new ArrayList<>();
    ArrayList<Root> receiptslist1 = new ArrayList<>();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        //     View root =
        //     bind  ing.getRoot();
        //  getRoom();
        // getRoom();
        binding.setLifecycleOwner(this);
        getRoom();

        getRoot();
         slideshowViewModel.getHeadersBill(getActivity());
        AdabterBill adabter = new AdabterBill(getActivity());
      //  adabter.setOnClic(this);

//        Runtime rt = Runtime.getRuntime();
//        long maxMemory = rt.maxMemory();
//        Log.v("onSuccess", "onSuccess:" + Long.toString(maxMemory));



        slideshowViewModel.liveDataHeaderBill.observe(getViewLifecycleOwner(), new Observer<List<HeaderBill>>() {
            @Override
            public void onChanged(List<HeaderBill> headerBills) {
                if (headerBills==null) {
                    List<HeaderBill> list=new ArrayList<>();
               //     adabter.setList(list);
                    adabter.notifyDataSetChanged();
                    binding.recyclerView.setAdapter(adabter);
                    //Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();
                } else {

                    adabter.setList(headerBills);
                    adabter.notifyDataSetChanged();
                    binding.recyclerView.setAdapter(adabter);
                }

            }
        });

        binding.paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

           //    for (Root r:
           //            slideshowViewModel.receiptslist
           //         ) {

           //        Log.d("onSuccess", r.totalSales+"");
           //    }
           //   Log.d("onSuccess", "4");
           //   Receipts receipts = new Receipts();
           //   receipts.setReceipts(slideshowViewModel.receiptslist);
           //   Gson gson = new Gson();
           //   gson.toJson(receipts);
           //   Log.d("onSuccess", "4" + gson.toJson(receipts));
           //   binding.
           //    textView12.setText( gson.toJson(receipts));

           //   slideshowViewModel.sendList(receipts);


                  Receipts receipts = new Receipts();
                  receipts.setReceipts(receiptslist1);
                  Gson gson = new Gson();
                  gson.toJson(receipts);
                  Log.d("onSuccess", "4" + gson.toJson(receipts));

                SharedPreferencesCom.init(getContext());
                String IDandroid=   SharedPreferencesCom.getInstance().gerSharedandroidId();
                Log.d("c2",IDandroid);
                Toast.makeText(getContext(), IDandroid, Toast.LENGTH_SHORT).show();


                slideshowViewModel.sendList(receipts,IDandroid);

           //    delete();
              slideshowViewModel.  stateBranchLiveData2.observe(getViewLifecycleOwner(), new Observer<StateData<String>>() {
                  @Override
                  public void onChanged(StateData<String> stringStateData) {
                      switch (stringStateData.getStatus()) {
                           case SUCCESS:
                               List<HeaderBill> list=new ArrayList<>();
                               adabter.setList(list);
                               adabter.notifyDataSetChanged();
                               binding.recyclerView.setAdapter(adabter);
                               delete();
                               Toast.makeText(getContext(), stringStateData.getData(), Toast.LENGTH_SHORT).show();
                               Toast.makeText(getContext(), stringStateData.getData(), Toast.LENGTH_SHORT).show();

                               break;
                           case ERROR:
                               List<HeaderBill> list2=new ArrayList<>();
                         //     adabter.setList(list2);
                               adabter.notifyDataSetChanged();
                               binding.recyclerView.setAdapter(adabter);
                              // delete();
                               Toast.makeText(getContext(), "الانترنيت ضعيف او غير متصل --او ربما يوجد مشاكل فى السيرفر", Toast.LENGTH_SHORT).show();
                               Toast.makeText(getContext(), "الانترنيت ضعيف او غير متصل --او ربما يوجد مشاكل فى السيرفر", Toast.LENGTH_SHORT).show();
                               break;
                          case LOADING:

                          case COMPLETE:


                      }
                   //   Toast.makeText(getContext(), stringStateData., Toast.LENGTH_SHORT).show();
                  }
              });


               slideshowViewModel.liveDataHeaderBill.setValue(null);
              // slideshowViewModel.liveDataHeaderBill.ZZZ
               //homeViewModel.liveDataHeaderBill
               adabter.notifyDataSetChanged();
             //  adabter.notify();
//
                binding.recyclerView.setAdapter(adabter);


            }
        });

        Toast.makeText(getContext(), "Slider", Toast.LENGTH_SHORT).show();
        //    AdabterItems items=new AdabterItems(getActivity());
        //    slideshowViewModel.getItems(getContext());
        // //   slideshowViewModel.getItemsbyID("3",getContext());
        //    slideshowViewModel.liveData.observe(getActivity(), new Observer<List<ItemsBill>>() {
        //        @Override
        //        public void onChanged(List<ItemsBill> itemsBills) {
        //            items.setList(itemsBills);
        //            binding.recyclerView.setAdapter(items);
        //        }
        //    });
        // ArrayList<Root> s=
       // testSend();
        //  Receipts receipts = new Receipts();
        //  receipts.setReceipts(s);
        //  Gson gson = new Gson();
        //  gson.toJson(receipts);
        //  Log.d("onSuccess", "4" + gson.toJson(receipts));
        //  slideshowViewModel.sendList(receipts);
        return binding.getRoot();
    }

  public void delete(){
      ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
      contactsDatabase.headerBillDao().delete().subscribeOn(computation())
                .subscribeOn(computation())
              .observeOn(computation())
              .subscribe();
  }
    public void getRoot() {
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
        contactsDatabase.headerBillDao().getHeaderBill()
                .subscribeOn(computation())
                .observeOn(computation())
                .subscribe(new SingleObserver<List<HeaderBill>>() {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
 }
                               @Override
                               public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<HeaderBill> headerBills) {

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
                                           ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
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

                                                           ArrayList<ItemDatum> itemData = new ArrayList<>();

                                                          // Collections.sort(itemData,
                                                          //         ItemDatum);
                                                           for (ItemsBill i :  x) {
                                                               itemData.add(homeViewModel.getItems(i.getQuantity(), i.getUnitPrice(), i.getUnitPrice(), i.getPName()
                                                                       ,i.getInternalCode(),i.getUnitType()
                                                                       ,i.getItemType()
                                                                       , String.valueOf( i.getItemCode())
                                                               ));
                                                           //    itemData.add(homeViewModel.getItems(1.0, i.getUnitPrice(), i.getUnitPrice(), i.getPName()));
                                                             // itemData.add(homeViewModel.getItems(Double.valueOf(i.getQuantity()),
                                                             //         Double.valueOf(i.getPrice()),
                                                             //         Double.valueOf(i.getPrice()), i.getDescription(),i.getBarcode(),i.getUnitType()
                                                             //         ,i.getItemType()
                                                             //         , String.valueOf( i.getItemCode())

                                                             // ));
                                                           }
                                                           Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, id);
                                                           receiptslist1.add(r);
                                                           Gson gson = new Gson();

                                                     //      itemData.sort(Comparator.comparing(itemDatum -> ));
                                                           gson.toJson(r);
                                                           Log.d("onSuccess", "---1" + gson.toJson(r));
                                                           //  room.add(r);
                                                          // Send(r);

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
                           }
                );
        //  return  receiptslist1;
    }

    public void Send(Root r) {
        //  Gson g=new Gson();
        //  g.toJson(r);
        //  Log.d("onSuccess", g.toJson(r));

//        Thread timer = new Thread() {
//            public void run(){
//                try {
//                    // buttons[inew][jnew].setBackgroundColor(Color.BLACK);
//                    sleep(5000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        };
        Single ss = RetrofitRefranc.getInstance().getApiCalls().getProductm(r)
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<BillReturn> singleObserver = new SingleObserver<BillReturn>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull BillReturn billReturn) {
                for (String p : billReturn.getQrCode()) {
                    Log.d("onSuccess", p);
                }
                for (String p : billReturn.getErrorMessage()) {
                    Log.d("onSuccess", p);
                }
                Log.d("onSuccess", billReturn.getSubmitionID());
                //   PrintBluetooth.printer_id ="Printer001";
                //   // PrintBluetooth.printer_id ="InnerPrinter";
                //   java.util.Date today = new java.util.Date();
                //   SimpleDateFormat format_date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
                //   String print_date = format_date.format(today);
                //   String status_barang = "";
                //   //    if (radioButtonYa.isChecked()){
                //   //        status_barang = " vegetarian";
                //   //    }
                //   //    else if (radioButtonTidak.isChecked()){
                //   //        status_barang = "meet";
                //   //    }
                //   String s=     loopList();
                //   //    Log.d(" loopList",s);

                //   String  ss= setpill();
                //   String end=endBill();
                //   String endof=endOfend();
                //   try {
                //       printBT.findBT();
                //       printBT.openBT();

                //       // printBT.printQrCode(textAsBitmap(s,370, 30));

                //       //   printBT.printQrCode(textAsBitmap(setpill(),520,28));

                //       //  printBT.printQrCode(textAsBitmap(s,520, 30));
                //       printBT.printQrCode(textAsBitmap(ss,520, 22));
                //       // printBT.printQrCode(textAsBitmap(s,370, 27));
                //       // printBT.printQrCode(textAsBitmap(ss,370, 22));
                //       printBT.printQrCode(print( billReturn.getQrCode().get(0)));

                //       // printBT.printQrCode(textAsBitmap(s,370, 27));
                //       // printBT.printQrCode(textAsBitmap(ss,370, 22));
                //       // printBT.printQrCode(print( c.getUrl()));


                //       //        printBT.printQrCode(textAsBitmap(s,370, 27));

                //       //     //   printBT.printQrCode(textAsBitmap(setpill(),520,28));
                //       //        printBT.printQrCode(textAsBitmap(ss,370, 22));
                //       //      //  printBT.printQrCode(textAsBitmap(end,370, 27));
                //       //        printBT.printQrCode(textAsBitmap(endof,370, 22));
                //       //  printBT.printQrCode(print( c.getUrl()));
                //       //  printBT.closeBT();
                //   }catch(IOException
                //           | WriterException
                //           ex){
                //       ex.printStackTrace();
                //   }

                //   MultiFormatWriter multiFormatWriter=new MultiFormatWriter();
                //   BitMatrix bitMatrix;

                // Log.d("onSuccess",billReturn.getErrorMessage().get(0).toString());
                // Log.d("onSuccess",billReturn.getStatus());
                // Log.d("onSuccess",billReturn.getSubmitionID());
                // Log.d("onSuccess",billReturn.getQrCode().get(0).toString());
                // Toast.makeText(getActivity(), billReturn.getErrorMessage().get(0).toString(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();

                Log.d("onSuccess", e.getMessage());

            }
        };
        ss.subscribe(singleObserver);
    }

    public void getRoom() {
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
        contactsDatabase.headerBillDao().getHeaderBill()
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<HeaderBill>>() {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                               }

                               @Override
                               public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<HeaderBill> headerBills) {

                                   SetBills(headerBills);
                                   // binding.textView20.setText(headerBills.get(headerBills.size() - 1).getBillNumber());
                               }

                               @Override
                               public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                               }
                           }
                );

    }

    //    public void SetBills(List<HeaderBill> header) {
//        List<Root> receiptslist=new ArrayList<>();
//        for (HeaderBill h : header) {
//            //  h.getBillNumber();
//            String uu= h.getUUID();
//            String id = h.getBillNumber();
//            String TimeRicet= h.getInvoiceDate();
//
//            getItems(id, uu, TimeRicet);
//            //  Root r = homeViewModel.sentApi(uu,getItems(id), TimeRicet,id);
//            // Send(r);
//        }
//
//    }


    public void SetBills(List<HeaderBill> header) {
        // ArrayList<Root> receiptslist=new ArrayList<>();
        Log.d("onSuccess", "0");
        for (HeaderBill h : header) {
            Log.d("onSuccess", "1");
            //  h.getBillNumber();
            String uu = h.getUUID();
            String id = h.getBillNumber();
            String TimeRicet = h.getInvoiceDate();

            getItems(id, uu, TimeRicet);

            // ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
            // contactsDatabase.ItemsBillDao().getlistItems(id)
            //         //getlistItems("123")
            //         .subscribeOn(computation())
            //         .observeOn(AndroidSchedulers.mainThread())
            //         .subscribe(new SingleObserver<List<ItemsBill>>() {
            //             @Override
            //             public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            //             }

            //             @Override
            //             public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsBill> itemsBills) {
            //                 //   itemData1=itemsBills;
            //                 ArrayList<ItemDatum> itemData = new ArrayList<>();
            //                 for (ItemsBill i : itemsBills) {
            //                     // Log.d("onSuccess",i.getIDBill()+"tesor");
            //                     //  Log.d("onSuccess",i.getPName()+"tesor");
            //                     itemData.add(homeViewModel.getItems(1.0, i.getUnitPrice(), i.getUnitPrice(), i.getPName()));
            //                     //  Log.d("onSuccess","yousif");
            //                 }
            //                 Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, id);
            //                 receiptslist.add(r);


            //                 Log.d("onSuccess", "2");
            //                 Gson gson=new Gson();
            //                 gson.toJson(r);
            //                 Log.d("onSuccess", "2"+gson.toJson(r));
            //                 // Send(r);

            //             }

            //             @Override
            //             public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

            //             }

            //         });
            //  Root r = homeViewModel.sentApi(uu,getItems(id), TimeRicet,id);
            // Send(r);
        }
        // Log.d("onSuccess", "4");
        // Receipts receipts = new Receipts();
        // receipts.setReceipts(receiptslist);
        // Gson gson = new Gson();
        // gson.toJson(receipts);
        // Log.d("onSuccess", "4" + gson.toJson(receipts));
        // slideshowViewModel.sendList(receipts);

    }
    // ArrayList<ItemDatum> itemData1 = new ArrayList<>();

    public void getItems(String id, String uuID, String timeBill) {


        // Root r=new Root();
        //  ArrayList<Root> room = new ArrayList<>();
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
        contactsDatabase.ItemsBillDao().getlistItems(id)
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ItemsBill>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsBill> itemsBills) {
                        //   itemData1=itemsBills;
                        ArrayList<ItemDatum> itemData = new ArrayList<>();
                        for (ItemsBill i : itemsBills) {
                            // Log.d("onSuccess",i.getIDBill()+"tesor");
                            //  Log.d("onSuccess",i.getPName()+"tesor");
                          itemData.add(homeViewModel.getItems(i.getQuantity(), i.getUnitPrice(), i.getUnitPrice(), i.getPName()
                                 ,i.getInternalCode(),i.getUnitType()
                                  ,i.getItemType()
                                  , String.valueOf( i.getItemCode())
                          ));
                            //  Log.d("onSuccess","yousif");
                        }
                        Root
                                r = homeViewModel.sentApi(uuID, itemData, timeBill, id);
                        slideshowViewModel.      receiptslist.add(r);
                        Gson gson = new Gson();
                        gson.toJson(r);
                        Log.d("onSuccess", gson.toJson(r));
                        //  room.add(r);
                        // Send(r);

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                });

        //  Receipts receipts=new Receipts();
//
        //  receipts.setReceipts(receiptslist);
        //  slideshowViewModel.sendList(receipts);
        //    Gson gson=new Gson();
        //   gson.toJson(receipts);
        //  Log.d("onSuccess", gson.toJson(receipts));

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}