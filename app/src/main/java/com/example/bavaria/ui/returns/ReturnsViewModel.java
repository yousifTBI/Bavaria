package com.example.bavaria.ui.returns;

import static android.content.Context.MODE_PRIVATE;
import static io.reactivex.rxjava3.schedulers.Schedulers.computation;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.pojo.classes.Beneficiary;
import com.example.bavaria.pojo.classes.BranchAddress;
import com.example.bavaria.pojo.classes.Buyer;
import com.example.bavaria.pojo.classes.Contractor;
import com.example.bavaria.pojo.classes.DocumentType;
import com.example.bavaria.pojo.classes.DocumentType2;
import com.example.bavaria.pojo.classes.ExtraReceiptDiscountDatum;
import com.example.bavaria.pojo.classes.Header;
import com.example.bavaria.pojo.classes.Header2;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.classes.RootReturns;
import com.example.bavaria.pojo.classes.Seller;
import com.example.bavaria.pojo.classes.TaxTotal;
import com.example.bavaria.pojo.testModels.ItemsModels;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.utils.SharedPreferencesCom;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
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

public class ReturnsViewModel extends ViewModel {

    MutableLiveData<List<ItemsModels>> itemsList1 = new MutableLiveData<>();

    public ItemsModels convertItemsToViewModel(Double Quantity,
                                               double unitPrice,
                                               double totalSale,
                                               String PName,
                                               String internalCode,
                                               String unitType,
                                               String itemType,
                                               String itemCode) {
        Log.d("onSuccessReturns", Quantity + "");

        ItemsModels i = new ItemsModels();
        Double d = Quantity;
        double tempD = d;
        int tempI = (int) tempD;
        //Integer i = tempI;

        Log.d("onSuccessReturns", tempI + "");
        // int d=  (int)Quantity ;
        i.setQuantity(tempI);
        i.setPrice(unitPrice);
        i.setPrice(unitPrice);
        i.setDescription(PName);
        i.setBarcode(internalCode);
        i.setUnitType(unitType);
        i.setItemType(itemType);
        i.setItemCode(itemCode);


        return i;
    }
    MutableLiveData<String> stringUUID = new MutableLiveData<>();
    public void getRoot(String billNumber, Context context) {
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
                                stringUUID.setValue(uu);
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
                                                List<ItemsBill> x = itemsBills;
                                                Collections.sort(x, new Comparator<ItemsBill>() {
                                                    @Override
                                                    public int compare(ItemsBill itemsBill, ItemsBill t1) {
                                                        return itemsBill.getItemID().compareTo(t1.getItemID());
                                                    }
                                                });


                                                ArrayList<ItemsModels> list = new ArrayList<ItemsModels>();
                                                for (ItemsBill i : x) {
                                                    if (i.getQuantity()==1){
                                                        Log.d("onSuccesss12", "xz"+"");

                                                        ItemsModels msn = convertItemsToViewModel(i.getQuantity(), i.getUnitPrice(), i.getUnitPrice(), i.getPName()
                                                                , i.getInternalCode(), i.getUnitType()
                                                                , i.getItemType()
                                                                , String.valueOf(i.getItemCode())
                                                        );
                                                        list.add(msn);
                                                    }else if (i.getQuantity()>1){
                                                        Double d =i.getQuantity();
                                                        double tempD = d;
                                                        int tempI = (int) tempD;
                                                        for (int xz=0;xz<tempI;xz++){
                                                            Log.d("onSuccesss12", xz+"");

                                                            ItemsModels msn = convertItemsToViewModel(1.1, i.getUnitPrice(), i.getUnitPrice(), i.getPName()
                                                                    , i.getInternalCode(), i.getUnitType()
                                                                    , i.getItemType()
                                                                    , String.valueOf(i.getItemCode())
                                                            );
                                                            list.add(msn);
                                                        }
                                                       // for ( int ic : tempI){
//
                                                       // }


                                                    }



                                                }
                                                itemsList1.setValue(list);


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

    public void getBillRoot(String billNumber, Context context) {
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

                                   //  Toast.makeText(context, headerBills.getInvoiceDate(), Toast.LENGTH_SHORT).show();

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
                                                   //  Toast.makeText(context, itemsBills.getPName(), Toast.LENGTH_SHORT).show();


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


    public String CreateUUIDReturns(String BillNumber, String LastUUID, String date, ArrayList<ItemDatum> list, String referenceUUID) {
        //   Date df = new Date();
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        Double TotalSale = 0.0;
        Double Total = 0.0;
        for (ItemDatum itemData : list) {
            TotalSale += itemData.getTotalSale();
            Total += itemData.getTotal();

        }
        //itemData
        Double TaxTotal = Total - TotalSale;

        String UUID = "";

        //  Date c = Calendar.getInstance().getTime();
        //  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        //  String formattedDate = df.format(c);
        //  //   Date df = new Date("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Header2 header = new Header2(
                date,
                //df,
                BillNumber,
                "",
                //"a5c56d8fc504e8be9e65d828e41de21d1bd7c21b32d1a8eca9bb2af6e7d0ac8d",
                "",

                "",
                referenceUUID
        );
        DocumentType2 d = new DocumentType2();

        Seller seller = SharedPreferencesCom.getInstance().getSharedValuesSeller();
        BranchAddress branchAddress = SharedPreferencesCom.getInstance().getSharedValuesBranchAddress();
        BranchAddress branch = new BranchAddress(branchAddress.getCountry(),
                branchAddress.getGovernate(),
                branchAddress.getRegionCity(),
                branchAddress.getStreet(),
                branchAddress.getBuildingNumber(),
                "",
                "",
                "",
                "",
                "");
        Seller sa = new Seller(seller.getRin(),
                //   seller.getCompanyTradeName(),
                SharedPreferencesCom.getInstance().getBranchName(),
                seller.getBranchCode(),
                branch,
                seller.getDeviceSerialNumber(),
                // seller.getSyndicateLicenseNumber(),
                "",
                seller.getActivityCode()
        );

        //  BranchAddress branch = new BranchAddress("EG",
        //          "Giza",
        //          "6 Oct",
        //          "Hyaber",
        //          "1",
        //          "",
        //          "",
        //          "",
        //          "",
        //          "");
        //  Seller sa = new Seller("382107586",
        //          "Domino's Pizza Hyper One Branch",
        //          "2",
        //          branch,
        //          "447788",
        //          "",
        //          "1071"
        //  );


        Buyer BU = new Buyer("p",
                "",
                "",
                "",
                "0");


        ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData = new ArrayList<>();

        extraReceiptDiscountData.add(new ExtraReceiptDiscountDatum());

        ArrayList<com.example.bavaria.pojo.classes.TaxTotal> taxTotals = new ArrayList<>();

        taxTotals.add(new TaxTotal(
                Double.valueOf(numberFormat.format(TaxTotal))
                //  TaxTotal
        ));


        Contractor contractor = new Contractor();
        Beneficiary beneficiary = new Beneficiary();

        RootReturns r = new RootReturns(header, d, sa, BU, list,
//                TotalSale
                Double.valueOf(numberFormat.format(TotalSale))
                ,
                0.0,
                0.0,
                extraReceiptDiscountData,
                //TotalSale
                Double.valueOf(numberFormat.format(TotalSale))
                ,
                0,
//                Total
                Double.valueOf(numberFormat.format(Total))

                ,
                taxTotals,
                "C",
                0,
                contractor,
                beneficiary);

        try {

            Gson g = new Gson();
            g.toJson(r);
            Log.d("onSuccess", "1" + r.getString());
            Log.d("onSuccess", "1" + g.toJson(r));

            UUID = computeHash(r.getString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return UUID;
    }

    public String computeHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();

        byte[] byteData = digest.digest(input.getBytes("UTF-8"));
        //   StringBuffer sb = new StringBuffer();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    public String getNumberBill(Context context) {
        SharedPreferences sharedPreferenclanguageg = context.getSharedPreferences("NumberBill", MODE_PRIVATE);
        SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();

        //toGet
        String Number = sharedPreferenclanguageg.getString("NumberOFBill", "900");
        //   Toast.makeText(context, Number, Toast.LENGTH_SHORT).show();
        int newNumber = Integer.valueOf(Number) + 1;
        //ToPost
        editsg.putString("NumberOFBill", String.valueOf(newNumber));
        editsg.apply();
        return String.valueOf(newNumber);

    }


}
