package com.example.bavaria.ui.home;

import static android.content.Context.MODE_PRIVATE;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.models.AddItemModel;
import com.example.bavaria.pojo.models.EditItemModel;
import com.example.bavaria.pojo.models.Task;
import com.example.bavaria.pojo.models.Task3;
import com.example.bavaria.ui.roomContacts.AccountInfo.LoginModel;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.pojo.TaskAPI;
import com.example.bavaria.pojo.classes.Beneficiary;
import com.example.bavaria.pojo.classes.BranchAddress;
import com.example.bavaria.pojo.classes.Buyer;
import com.example.bavaria.pojo.classes.CommercialDiscountDatum;
import com.example.bavaria.pojo.classes.Contractor;
import com.example.bavaria.pojo.classes.DocumentType;
import com.example.bavaria.pojo.classes.ExtraReceiptDiscountDatum;
import com.example.bavaria.pojo.classes.Header;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.ItemDiscountDatum;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.classes.Seller;
import com.example.bavaria.pojo.classes.TaxTotal;
import com.example.bavaria.pojo.classes.TaxableItem;
import com.example.bavaria.pojo.models.BillReturn;
import com.example.bavaria.pojo.models.Items;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.backup.HeaderBackup;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.onlineBill.HeaderBillOnline;
import com.example.bavaria.ui.roomContacts.onlineBill.ItemsBillOnlin;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.roomContacts.productRoom.Products;
import com.example.bavaria.ui.slideshow.OnClic;
import com.example.bavaria.ui.utils.SharedPreferencesCom;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;


public class HomeViewModel extends ViewModel {

    MutableLiveData<String> qr = new MutableLiveData<String>();

    public MutableLiveData<String> getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr.setValue(qr);
    }

    OnClic onClic;

    public OnClic getOnClic() {
        return onClic;
    }

    public void setOnClic(OnClic onClic) {
        this.onClic = onClic;
    }

    ArrayList<ItemDatum> itemData = new ArrayList<>();
    MutableLiveData<ItemsModel> itemsList = new MutableLiveData<>();
    MutableLiveData<List<ItemsModel>> itemsList1 = new MutableLiveData<>();

    public MutableLiveData<ItemsModel> getItemsList() {
        return itemsList;
    }

    public void setItemsList(MutableLiveData<ItemsModel> itemsList) {
        this.itemsList = itemsList;
    }

    public MutableLiveData<List<ItemsModel>> getItemsList1() {
        return itemsList1;
    }

    public void setItemsList1(MutableLiveData<List<ItemsModel>> itemsList1) {
        this.itemsList1 = itemsList1;
    }

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");

    }

    public void Send(Root r, HeaderBillOnline HeaderOnline, ArrayList<ItemsBillOnlin> postitem, Context context) {
        //  Gson g=new Gson();
        //  g.toJson(r);
        //  Log.d("onSuccess", g.toJson(r));
        Single ss = RetrofitRefranc.getInstance().getApiCalls().getProductm(r)
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread());
        SingleObserver<BillReturn> singleObserver = new SingleObserver<BillReturn>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onSuccess(@NonNull BillReturn billReturn) {
                if (billReturn.getStatus() == "submitted") {

                } else {
                    headBillRoomOnline(HeaderOnline, postitem, context);
                }
                //binding.progressBar2.setVisibility(View.GONE);

                for (String p : billReturn.getQrCode()) {
                    Log.d("onSuccess1", p);
                }
                for (String p : billReturn.getErrorMessage()) {
                    Log.d("onSuccess1", p);
                }
                Log.d("onSuccess1", billReturn.getStatus() + "Status");
                Log.d("onSuccess1", billReturn.getSubmitionID() + "SubmitionID");
                Log.d("onSuccess1", billReturn.getSubmitted() + "Submitted");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                headBillRoomOnline(HeaderOnline, postitem, context);

                Log.d("onSuccess", e.getMessage());

            }
        };
        ss.subscribe(singleObserver);
    }

    ArrayList<ItemsModel> itemsModels1 = new ArrayList<>();

    void provideSimpleDialog(Context context) {
        SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat(context, "Search...",
                "What are you looking for...?", null, itemsModels1,
                new SearchResultListener<ItemsModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat dialog, ItemsModel item, int position) {
                        // Toast.makeText(context, item.getTax()+"ء", Toast.LENGTH_SHORT).show();
                        itemsList.setValue(item);
                        //  onClic.getQR("d");
                        // itemsList1.setValue(new L);
                        //.add(item);
                        // binding.textView220.setText(item.getCurrencyName());
                        // binding.textView23.setText(item.getValue());
                        // binding.cashEditText1.setText("0");
                        // binding.cashEditText2.setText("0.0");
                        //   binding.textView22.setText(item.getCurrencyName()+"    ");
                        dialog.dismiss();
                    }

                }
        );
        dialog.show();
        //dialog.getSearchBox().setTypeface(Typeface.SERIF);
    }

    private ArrayList<Items> createSampleData() {
        ArrayList<Items> list = new ArrayList<>();
        list.add(new Items("خرطوشة ضغط داخلية", 200.0));
        list.add(new Items("ضغط مخزون", 367.0));
        list.add(new Items("شامبيون", 212.0));
        list.add(new Items("تيجـرا أوبتيمـا", 1092.0));

        return list;

    }

    public LiveData<String> getText() {
        return mText;
    }

    public ItemsBill setItemsRoom(String nameItem, Double price, String ID, String itemId) {
        ItemsBill bill = new ItemsBill();
        bill.setPName(nameItem);
        bill.setItemID(itemId);
        bill.setUnitPrice(price);
        bill.setIDBill(ID);
        return bill;
    }

    public ItemsBackup setItemsRoomBackup(String nameItem, Double price, String ID, String itemId) {
        ItemsBackup bill = new ItemsBackup();
        bill.setPName(nameItem);
        bill.setItemID(itemId);
        bill.setUnitPrice(price);
        bill.setIDBill(ID);
        return bill;
    }

    public ItemsBillOnlin setItemsRoomOnline(String nameItem, Double price, String ID, String itemId) {
        ItemsBillOnlin bill = new ItemsBillOnlin();
        bill.setPName(nameItem);
        bill.setItemID(itemId);
        bill.setUnitPrice(price);
        bill.setIDBill(ID);
        return bill;
    }

    public void getItems(String ComID, Context context) {
        Log.d("log", "l");

        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .GetItemsAPI(ComID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<TaskAPI<ItemsModel>> observer = new Observer<TaskAPI<ItemsModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("log", "l");

            }

            @Override
            public void onNext(@NonNull TaskAPI<ItemsModel> itemsModelTask4) {

                setItemsOnline(itemsModelTask4.getData(), context);
                Log.d("log", itemsModelTask4.State + "l");

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("log", e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }

    public ItemDatum getItems(Double Quantity, double unitPrice,
                              double totalSale, String PName

    ) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");


        Double totalSales = (totalSale * 14.0) / 100.0;
        Double total = totalSale + totalSales;

        ArrayList<CommercialDiscountDatum> commercialDiscountData = new ArrayList<>();
        commercialDiscountData.add(new CommercialDiscountDatum());

        ArrayList<ItemDiscountDatum> itemDiscountData = new ArrayList<>();
        itemDiscountData.add(new ItemDiscountDatum());

        ArrayList<TaxableItem> taxableItems = new ArrayList<>();
        // Double.valueOf(numberFormat.format(Quantity ))
        // Double.valueOf(numberFormat.format(unitPrice))
        // Double.valueOf(numberFormat.format(totalSale))
        // Double.valueOf(numberFormat.format(totalSale))
        // Double.valueOf(numberFormat.format(total    ))

        taxableItems.add(new TaxableItem(
                //totalSales
                Double.valueOf(numberFormat.format(totalSales))
                , 14.0));

        ItemDatum z = new ItemDatum("29130",
                PName,
                "GS1",
                "99999999",
                "EA",
                Double.valueOf(numberFormat.format(Quantity)),
                Double.valueOf(numberFormat.format(unitPrice)),
                Double.valueOf(numberFormat.format(totalSale)),
                Double.valueOf(numberFormat.format(totalSale)),
                Double.valueOf(numberFormat.format(total))
                , commercialDiscountData, itemDiscountData, 0, taxableItems);

        // ArrayList<ItemDatum> itemData=new ArrayList<>();
        // itemData.add(z);
        // itemData.add(z);
        itemData.add(z);
//
        return z;
    }

    public Root sentApi(String UUID, ArrayList<ItemDatum> list, String date, String NumberBill) {
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

        //  numberFormat.format(number)
        // String UUID="";

        // Date c = Calendar.getInstance().getTime();
        // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        // String formattedDate = df.format(c);
        //   Date df = new Date("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Header header = new Header(
                date,
                //df,
                NumberBill,
                UUID,
                //"a5c56d8fc504e8be9e65d828e41de21d1bd7c21b32d1a8eca9bb2af6e7d0ac8d",
                "",

                ""
        );
        DocumentType d = new DocumentType();


        BranchAddress b = new BranchAddress(
                "EG",
                "Giza",
                "6 Oct",
                "Hyaber",
                "1",
                "",
                "",
                "",
                "",
                "");


        Seller sa = new Seller("382107586",
                "Domino's Pizza Hyper One Branch",
                "2",
                b,
                "447788",
                "",
                "1071"
        );

        //    Seller seller=   SharedPreferencesCom.getInstance().getSharedValuesSeller();
        //    BranchAddress branchAddress=  SharedPreferencesCom.getInstance().getSharedValuesBranchAddress();
        //    BranchAddress branch = new BranchAddress(branchAddress.getCountry(),
        //            branchAddress.getGovernate(),
        //            branchAddress.getRegionCity(),
        //            branchAddress.getStreet(),
        //            branchAddress.getBuildingNumber(),
        //            "",
        //            "",
        //            "",
        //            "",
        //            "");
        //    Seller sa = new Seller(seller.getRin(),
        //            seller.getCompanyTradeName(),
        //            seller.getBranchCode(),
        //            branch,
        //            seller.getDeviceSerialNumber(),
        //            seller.getSyndicateLicenseNumber(),
        //            seller.getActivityCode()
        //    );

        Buyer BU = new Buyer("p",
                "",
                "",
                "",
                "0");


        ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData = new ArrayList<>();

        extraReceiptDiscountData.add(new ExtraReceiptDiscountDatum());

        ArrayList<TaxTotal> taxTotals = new ArrayList<>();


        taxTotals.add(new TaxTotal(Double.valueOf(numberFormat.format(TaxTotal))

                //     TaxTotal
        ));


        Contractor contractor = new Contractor();
        Beneficiary beneficiary = new Beneficiary();

        Root r = new Root(header, d, sa, BU, list,
                //  TotalSale
                Double.valueOf(numberFormat.format(TotalSale))
                ,
                0.0,
                0.0,
                extraReceiptDiscountData,
                // TotalSale
                Double.valueOf(numberFormat.format(TotalSale))
                ,
                0,
                // Total
                Double.valueOf(numberFormat.format(Total))
                ,
                taxTotals,
                "C",
                0,
                contractor,
                beneficiary);

        try {

            UUID = computeHash(r.getString());
            Log.d("onSuccess", UUID);
            Log.d("onSuccess", "2" + r.getString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return r;
    }

    public String CreateUUID(String BillNumber, String LastUUID, String date, ArrayList<ItemDatum> list) {
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
        Header header = new Header(
                date,
                //df,
                BillNumber,
                "",
                //"a5c56d8fc504e8be9e65d828e41de21d1bd7c21b32d1a8eca9bb2af6e7d0ac8d",
                "",

                ""
        );
        DocumentType d = new DocumentType();

        //   Seller seller=   SharedPreferencesCom.getInstance().getSharedValuesSeller();
        //   BranchAddress branchAddress=  SharedPreferencesCom.getInstance().getSharedValuesBranchAddress();
        //   BranchAddress branch = new BranchAddress(branchAddress.getCountry(),
        //           branchAddress.getGovernate(),
        //           branchAddress.getRegionCity(),
        //           branchAddress.getStreet(),
        //           branchAddress.getBuildingNumber(),
        //           "",
        //           "",
        //           "",
        //           "",
        //           "");
        //   Seller sa = new Seller(seller.getRin(),
        //           seller.getCompanyTradeName(),
        //           seller.getBranchCode(),
        //           branch,
        //           seller.getDeviceSerialNumber(),
        //           seller.getSyndicateLicenseNumber(),
        //           seller.getActivityCode()
        //   );

        BranchAddress branch = new BranchAddress("EG",
                "Giza",
                "6 Oct",
                "Hyaber",
                "1",
                "",
                "",
                "",
                "",
                "");
        Seller sa = new Seller("382107586",
                "Domino's Pizza Hyper One Branch",
                "2",
                branch,
                "447788",
                "",
                "1071"
        );


        Buyer BU = new Buyer("p",
                "",
                "",
                "",
                "0");


        ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData = new ArrayList<>();

        extraReceiptDiscountData.add(new ExtraReceiptDiscountDatum());

        ArrayList<TaxTotal> taxTotals = new ArrayList<>();

        taxTotals.add(new TaxTotal(
                Double.valueOf(numberFormat.format(TaxTotal))
                //  TaxTotal
        ));


        Contractor contractor = new Contractor();
        Beneficiary beneficiary = new Beneficiary();

        Root r = new Root(header, d, sa, BU, list,
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeBill() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        LocalDateTime datetime = LocalDateTime.now();
        //  System.out.println("Before subtraction of hours from date: "+datetime.format(formatter));

        datetime = datetime.minusHours(2);
        String aftersubtraction = datetime.format(formatter);
        return aftersubtraction;
    }

    //  public MutableLiveData<List<ItemsBill>> liveData=new MutableLiveData<>();

    //  public ArrayList<ItemsBill> getItems(String id,Context context) {
    //      ArrayList<ItemsBill> itemData = new ArrayList<>();
    //      ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
    //      contactsDatabase.ItemsBillDao().getlistItems(id)
    //              //getlistItems("123")
    //              .subscribeOn(computation())
    //              .observeOn(AndroidSchedulers.mainThread())
    //              .subscribe(new SingleObserver<List<ItemsBill>>() {
    //                  @Override
    //                  public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

    //                  }

    //                  @Override
    //                  public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsBill> itemsBills) {
    //                      liveData.setValue(itemsBills);
    //                    //for (ItemsBill i : itemsBills) {
    //                    //    Log.d("onSuccess",i.getIDBill()+"tesor");
    //                    //    Log.d("onSuccess",i.getPName()+"tesor");
    //                    //   // itemData.add(homeViewModel.getItems(1.0, i.getUnitPrice(), i.getUnitPrice(), i.getPName()));
    //                    //    Log.d("onSuccess","yousif");
    //                    //}
    //                   //   itemData=itemsBills;
    //                  }

    //                  @Override
    //                  public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

    //                  }
    //              });

    //      // }
    //      return itemData;
    //  }


    //  public MutableLiveData<StateData<String>>getLiveData1=new MutableLiveData<>();

    //  public void addItem(Context context, Products i){


    //      ContactsDatabase postsDataBass =  ContactsDatabase.getGetInstance(context);
    //      //  postsDataBass.contactsDao().insertContacts()
    //      postsDataBass.productsDao().insertContacts(i)
    //              //   postsDataBass.contactsDao().insertContacts(post)


    //              .subscribeOn(computation())

    //              .subscribe(new CompletableObserver() {
    //                  @Override
    //                  public void onSubscribe(@NonNull Disposable d) {
    //                      getLiveData1.setValue( new StateData().loading());

    //                  }

    //                  @Override
    //                  public void onComplete() {
    //                      Log.d("yousiiiif","complete1");
    //                      getLiveData1.setValue( new StateData().success("Complete"));

    //                  }
    //                  @Override
    //                  public void onError(@NonNull Throwable e) {
    //                 //     Log.d("yousiiiif",e.getMessage().toString());
    //                      getLiveData1.setValue( new StateData().error(e));
    //                  }
    //              });

    //  }


    public HeaderBackup getHeaderBackup(String uuID,
                                        String numberRicet,
                                        String TimeRicet,
                                        Double Tax,
                                        Double price,
                                        Double totalPrice,
                                        String link

    ) {
        HeaderBackup headerBill = new HeaderBackup();
        headerBill.setUUID(uuID);
        headerBill.setBillNumber(numberRicet);
        headerBill.setInvoiceDate(TimeRicet);
        headerBill.setTax(Tax);
        headerBill.setNetPrice(price);
        headerBill.setTotalPrice(totalPrice);
        headerBill.setLink(link);
        return headerBill;
    }

    public HeaderBillOnline getHeaderOnline(String uuID,
                                            String numberRicet,
                                            String TimeRicet,
                                            Double Tax,
                                            Double price,
                                            Double totalPrice,
                                            String link

    ) {
        HeaderBillOnline headerBill = new HeaderBillOnline();
        headerBill.setUUID(uuID);
        headerBill.setBillNumber(numberRicet);
        headerBill.setInvoiceDate(TimeRicet);
        headerBill.setTax(Tax);
        headerBill.setNetPrice(price);
        headerBill.setTotalPrice(totalPrice);
        headerBill.setLink(link);
        return headerBill;
    }

    public MutableLiveData<StateData<String>> setheadBill = new MutableLiveData<>();


    public void headBill(HeaderBill post, ArrayList<ItemsBill> postitem, Context context) {


        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        //  postsDataBass.contactsDao().insertContacts()
        postsDataBass.headerBillDao().insertHeaderBill(post)
                //   postsDataBass.contactsDao().insertContacts(post)


                .subscribeOn(computation())

                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        setheadBill.postValue(new StateData().loading());

                    }

                    @Override
                    public void onComplete() {
                        Log.d("yousiiiif", "complete1");

                        Observable ob = Observable.create(new ObservableOnSubscribe<ItemsBill>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<ItemsBill> emitter) throws Exception {

                                        ArrayList<ItemsBill> postArrayList = postitem;
                                        for (ItemsBill x : postArrayList) {
                                            //    Log.d("onSuccess",x.getPName()+"lkjkhjhghb");
                                            emitter.onNext(x);

                                        }
                                    }
                                }).subscribeOn(io())
                                .observeOn(computation());
                        Observer obs = new Observer<ItemsBill>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull ItemsBill itemsBill) {
                                setBill(itemsBill, context);
                                // Log.d("onSuccess",itemsBill.getPName()+"testOonly");
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                        ob.subscribe(obs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("yousiiiif", e.getMessage().toString());
                    }
                });

    }

    public MutableLiveData<StateData<String>> setheadBillRoomBackup = new MutableLiveData<>();

    public void headBillRoomBackup(HeaderBackup post, ArrayList<ItemsBackup> postitem, Context context) {

        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.HeaderBackupDao().insertHeaderBill(post)
                .subscribeOn(computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        setheadBillRoomBackup.postValue(new StateData().loading());

                    }

                    @Override
                    public void onComplete() {


                        Observable ob = Observable.create(new ObservableOnSubscribe<ItemsBackup>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<ItemsBackup> emitter) throws Exception {

                                        ArrayList<ItemsBackup> postArrayList = postitem;
                                        for (ItemsBackup x : postArrayList) {
                                            emitter.onNext(x);

                                        }
                                    }
                                }).subscribeOn(io())
                                .observeOn(computation());

                        Observer obs = new Observer<ItemsBackup>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull ItemsBackup itemsBackup) {
                                setBillRoomBackup(itemsBackup, context);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        };
                        ob.subscribe(obs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });

    }

    public void setOneItemOnline(ItemsModel itemsOnline, Context context) {
        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.itemOnlineDao().insertContacts(itemsOnline)
                .subscribeOn(computation()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.d("log", "sssssss");
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("log", e.getMessage());
                    }
                });
    }

    public void setItemsOnline(List<ItemsModel> itemsOnline, Context context) {
        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.itemOnlineDao().insertAllOrders(itemsOnline)
                .subscribeOn(computation()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.d("log", "sssssss");
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("log", e.getMessage());
                    }
                });
    }

    public void LoginFun(String androidID, Context context) {
        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .LoginAPI(androidID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task3<LoginModel>> observer = new Observer<Task3<LoginModel>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Task3<LoginModel> loginModelTask3) {


                SharedPreferencesCom.init(context);
                String rin = String.valueOf(loginModelTask3.getItem().getTax_id());
                String companyTradeName = loginModelTask3.getItem().getName();
                String branchCode = String.valueOf(loginModelTask3.getItem().getBranchID());
                String deviceSerialNumber = loginModelTask3.getItem().getPosserial();
                String syndicateLicenseNumber = loginModelTask3.getItem().getLicenseExpiryDate();
                String activityCode = loginModelTask3.getItem().getTaxpayerActivityCode();
                SharedPreferencesCom.getInstance().setSharedValuesSeller(rin, companyTradeName, branchCode
                        , deviceSerialNumber, syndicateLicenseNumber, activityCode);

                String country = loginModelTask3.getItem().getCountry();
                String governate = loginModelTask3.getItem().getGovernate();
                String regionCity = loginModelTask3.getItem().getRegionCity();
                String street = loginModelTask3.getItem().getStreet();
                String buildingNumber = loginModelTask3.getItem().getBuildingNumber();
                SharedPreferencesCom.getInstance().setSharedValuesBranchAddress(country, governate, regionCity, street,
                        buildingNumber);
                SharedPreferencesCom.getInstance().setSharedDocumentType(loginModelTask3.getItem().getTypeVersion());

                SharedPreferencesCom.getInstance().setSharedItemFlag(String.valueOf(loginModelTask3.getItem().getItemFlag()));
                SharedPreferencesCom.getInstance().setSharedPriceFlag(String.valueOf(loginModelTask3.getItem().getPriceFlag()));

                setInfoTdp(loginModelTask3.getData(), context);
                Log.d("Loginchhf", String.valueOf(loginModelTask3.getItem().getTax_id()));
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Log.d("Loginchhf", e.getMessage() + "chhf");
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);
    }


    public void setInfoTdp(List<LoginModel> order, Context context) {
        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.loginInfoDao().insertAllOrders(order)
                .subscribeOn(computation()).subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        Log.d("setInfoTdp", "onComplete");
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("onComplete", e.getMessage());
                    }
                });
    }

    public void getItemsOnline(Context context) {
        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.itemOnlineDao().getContacts()
                .subscribeOn(computation()).subscribe(new SingleObserver<List<ItemsModel>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<ItemsModel> itemsModels) {
                        itemsModels1 = (ArrayList<ItemsModel>) itemsModels;
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });
    }

    public void setBillRoomBackup(ItemsBackup post, Context context) {

        //  postsDataBass.contactsDao().insertContacts()
        //.ItemsBillDao().insertContacts(post)
        //contactsDao().insertContacts(post)
        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.ItemBackupDao().insertContacts(post)
                .subscribeOn(computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        setheadBillRoomBackup.postValue(new StateData().complete());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onSuccess", e.getMessage());
                    }
                });
    }

    public void setBill(ItemsBill post, Context context) {


        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        //  postsDataBass.contactsDao().insertContacts()
        postsDataBass.ItemsBillDao().insertContacts(post)
                //contactsDao().insertContacts(post)

                .subscribeOn(computation())

                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                        setheadBill.postValue(new StateData().complete());
                        ///      Log.d("onSuccess","11111111111");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onSuccess", e.getMessage());
                    }
                });

    }

    public MutableLiveData<StateData<List<Products>>> getProducts = new MutableLiveData<>();

    public void getproductsDaoRoom(Context context) {
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.productsDao().getContacts()
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Products>>() {
                               @Override
                               public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                                   getProducts.setValue(new StateData().loading());
                               }

                               @Override
                               public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Products> products) {

                                   getProducts.setValue(new StateData().success(products));
                               }

                               @Override
                               public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                   getProducts.setValue(new StateData().error(e));

                               }
                           }
                );
    }
    // Date c = Calendar.getInstance().getTime();
    // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    // String formattedDate = df.format(c);
    // Log.d("onSuccess",formattedDate);


    public void headBillRoomOnline(HeaderBillOnline post, ArrayList<ItemsBillOnlin> postitem, Context context) {

        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        // postsDataBass.HeaderBackupDao().insertHeaderBill(post)
        postsDataBass.headerBillOnlineDao().insertHeaderBill(post)
                .subscribeOn(computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        setheadBillRoomBackup.postValue(new StateData().loading());

                    }

                    @Override
                    public void onComplete() {


                        Observable ob = Observable.create(new ObservableOnSubscribe<ItemsBillOnlin>() {
                                    @Override
                                    public void subscribe(@NonNull ObservableEmitter<ItemsBillOnlin> emitter) throws Exception {

                                        ArrayList<ItemsBillOnlin> postArrayList = postitem;
                                        for (ItemsBillOnlin x : postArrayList) {
                                            emitter.onNext(x);

                                        }
                                    }
                                }).subscribeOn(io())
                                .observeOn(computation());

                        Observer obs = new Observer<ItemsBillOnlin>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@io.reactivex.rxjava3.annotations.NonNull ItemsBillOnlin itemsBillOnlin) {
                                setBillRoomOnline(itemsBillOnlin, context);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }

                        };
                        ob.subscribe(obs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });

    }

    public void setBillRoomOnline(ItemsBillOnlin post, Context context) {

        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        // postsDataBass.ItemBackupDao().insertContacts(post)
        postsDataBass.itemsBillOnlineDao().insertContacts(post)
                .subscribeOn(computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        // setheadBillRoomBackup.postValue( new StateData().complete());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onSuccess", e.getMessage());
                    }
                });
    }


    public void AddItem(AddItemModel add) {
        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .AddItemAPI(add)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task<AddItemModel>> observer = new Observer<Task<AddItemModel>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

               // Log.d("log",add.getDescription().toString());

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Task<AddItemModel> addItemModelTask) {
            Log.d("log",addItemModelTask.Message);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Log.d("log",e.getMessage());


            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);

    }

    public void EditItem(EditItemModel add) {
        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .EditItemAPI(add)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task<EditItemModel>> observer = new Observer<Task<EditItemModel>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Task<EditItemModel> editItemModelTask) {
                Log.d("log",editItemModelTask.Message);
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                Log.d("log",e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };

        observable.subscribe(observer);


    }
}