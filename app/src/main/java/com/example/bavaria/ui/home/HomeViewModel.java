package com.example.bavaria.ui.home;

import static android.content.Context.MODE_PRIVATE;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.network.StateData;
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
import com.example.bavaria.pojo.classes.models.Items;
import com.example.bavaria.pojo.classes.models.Product;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.backup.HeaderBackup;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.roomContacts.productRoom.Products;
import com.example.bavaria.ui.slideshow.OnClic;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;


public class HomeViewModel extends ViewModel  {
    OnClic onClic;

    public OnClic getOnClic() {
        return onClic;
    }

    public void setOnClic(OnClic onClic) {
        this.onClic = onClic;
    }

    ArrayList<ItemDatum> itemData=new ArrayList<>();
    MutableLiveData <Items> itemsList=new MutableLiveData<>();
    MutableLiveData <List<Items>> itemsList1=new MutableLiveData<>();

   public MutableLiveData<List<Items>> getItemsList1() {
       return itemsList1;
   }

   public void setItemsList1(List<Items> itemsList1) {
       this.itemsList1.setValue(itemsList1);// = itemsList1;
   }

    public MutableLiveData<Items> getItemsList() {
        return itemsList;
    }

    public void setItemsList(MutableLiveData<Items> itemsList) {
        this.itemsList = itemsList;
    }

    private final MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }


   void provideSimpleDialog(Context context) {
       SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat(context, "Search...",
               "What are you looking for...?", null, createSampleData(),
               new SearchResultListener<Items>() {
                   @Override
                   public void onSelected(BaseSearchDialogCompat dialog, Items item, int position) {
                   //  Toast.makeText(context, item.getTax()+"", Toast.LENGTH_SHORT).show();
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
        ArrayList<Items> list=new ArrayList<>();
        list.add(new Items("خرطوشة ضغط داخلية",200.0));
        list.add(new Items("ضغط مخزون",367.0));
        list.add(new Items("شامبيون",212.0));
        list.add(new Items("تيجـرا أوبتيمـا",1092.0));

        return list;

    }

    public LiveData<String> getText() {
        return mText;
    }

    public ItemsBill setItemsRoom(String nameItem,Double price,String ID ,String itemId){
        ItemsBill bill=new ItemsBill();
        bill.setPName(nameItem);
        bill.setItemID(itemId);
        bill.setUnitPrice(price);
        bill.setIDBill(ID);
        return bill;
    }
    public ItemsBackup setItemsRoomBackup(String nameItem,Double price,String ID ,String itemId){
        ItemsBackup bill=new ItemsBackup();
        bill.setPName(nameItem);
        bill.setItemID(itemId);
        bill.setUnitPrice(price);
        bill.setIDBill(ID);
        return bill;
    }

    public ItemDatum getItems(Double Quantity, double unitPrice,
                              double totalSale,String PName

                              ) {
        DecimalFormat numberFormat = new DecimalFormat("#.00");


        Double totalSales=(totalSale*14.0)/100.0;
        Double total= totalSale+totalSales;

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
                ,14.0));

        ItemDatum z = new ItemDatum("29130",
                PName,
                "GS1",
                "99999999",
                "EA",
                Double.valueOf(numberFormat.format(Quantity )),
                Double.valueOf(numberFormat.format(unitPrice)),
                Double.valueOf(numberFormat.format(totalSale)),
                Double.valueOf(numberFormat.format(totalSale)),
                Double.valueOf(numberFormat.format(total    ))
                , commercialDiscountData, itemDiscountData, 0, taxableItems);

        // ArrayList<ItemDatum> itemData=new ArrayList<>();
        // itemData.add(z);
        // itemData.add(z);
         itemData.add(z);
//
        return z;
    }
    public Root sentApi(String UUID,ArrayList<ItemDatum> list ,String date,String NumberBill){
        //   Date df = new Date();
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        Double TotalSale = 0.0;
        Double Total = 0.0;
        for ( ItemDatum itemData:list) {
            TotalSale+=     itemData.getTotalSale();
            Total+=itemData.getTotal();

        }
        //itemData

        Double TaxTotal=Total-TotalSale;

      //  numberFormat.format(number)
       // String UUID="";

      // Date c = Calendar.getInstance().getTime();
      // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
      // String formattedDate = df.format(c);
        //   Date df = new Date("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Header header=new Header(
                date,
                //df,
                NumberBill,
                UUID,
                //"a5c56d8fc504e8be9e65d828e41de21d1bd7c21b32d1a8eca9bb2af6e7d0ac8d",
                "",

                ""
        );
        DocumentType d=new DocumentType();


        BranchAddress b=new BranchAddress( "EG",
                "Giza",
                "6 Oct",
                "Hyaber",
                "1",
                "",
                "",
                "",
                "",
                "");
        Seller sa=new Seller(     "382107586",
                "Domino's Pizza Hyper One Branch",
                "2",
                b,
                "447788",
                "",
                "1071"
        );



        Buyer BU= new Buyer(  "p",
                "",
                "",
                "",
                "0");


        ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData=new ArrayList<>();

        extraReceiptDiscountData.add(new ExtraReceiptDiscountDatum());

        ArrayList<TaxTotal> taxTotals=new ArrayList<>();


        taxTotals.add(new  TaxTotal(  Double.valueOf(numberFormat.format(TaxTotal))

           //     TaxTotal
        ));


        Contractor contractor   =new Contractor( );
        Beneficiary beneficiary=new Beneficiary( );

        Root r=new Root(header,d,sa,BU, list,
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

          UUID=  computeHash(r.getString());
           Log.d("onSuccess",UUID);
           Log.d("onSuccess","2"+r.getString());

       } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
         return  r;
    }
    public String CreateUUID(String BillNumber,String LastUUID,String date,ArrayList<ItemDatum> list){
        //   Date df = new Date();
        DecimalFormat numberFormat = new DecimalFormat("#.00");

        Double TotalSale = 0.0;
        Double Total = 0.0;
        for ( ItemDatum itemData:list) {
            TotalSale+=     itemData.getTotalSale();
            Total+=itemData.getTotal();

        }
        //itemData
        Double TaxTotal=Total-TotalSale;

        String UUID="";

     //  Date c = Calendar.getInstance().getTime();
     //  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
     //  String formattedDate = df.format(c);
     //  //   Date df = new Date("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Header header=new Header(
                date,
                //df,
                BillNumber,
                "",
                //"a5c56d8fc504e8be9e65d828e41de21d1bd7c21b32d1a8eca9bb2af6e7d0ac8d",
                "",

                ""
        );
        DocumentType d=new DocumentType();


        BranchAddress b=new BranchAddress( "EG",
                "Giza",
                "6 Oct",
                "Hyaber",
                "1",
                "",
                "",
                "",
                "",
                "");
        Seller sa=new Seller(     "382107586",
                "Domino's Pizza Hyper One Branch",
                "2",
                b,
                "447788",
                "",
                "1071"
        );



        Buyer BU= new Buyer(  "p",
                "",
                "",
                "",
                "0");


        ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData=new ArrayList<>();

        extraReceiptDiscountData.add(new ExtraReceiptDiscountDatum());

        ArrayList<TaxTotal> taxTotals=new ArrayList<>();

        taxTotals.add(new  TaxTotal(
                Double.valueOf(numberFormat.format(TaxTotal))
              //  TaxTotal
        ));


        Contractor contractor   =new Contractor( );
        Beneficiary beneficiary=new Beneficiary( );

        Root r=new Root(header,d,sa,BU, list,
//                TotalSale
              Double.valueOf(  numberFormat.format( TotalSale))
                ,
                0.0,
                0.0,
                extraReceiptDiscountData,
                //TotalSale
                Double.valueOf(  numberFormat.format( TotalSale))
                ,
                0,
//                Total
                Double.valueOf(  numberFormat.format(  Total))

                ,
                taxTotals,
                "C",
                0,
                contractor,
                beneficiary);

        try {

            Gson g=new Gson();
           g.toJson(r);
            Log.d("onSuccess","1"+r.getString());
            Log.d("onSuccess","1"+g.toJson(r));

            UUID=  computeHash(r.getString());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return  UUID;
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
    public String getNumberBill(Context context){
        SharedPreferences   sharedPreferenclanguageg = context.getSharedPreferences("NumberBill", MODE_PRIVATE);
        SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();

        //toGet
        String Number = sharedPreferenclanguageg.getString("NumberOFBill", "900");
        Toast.makeText(context, Number, Toast.LENGTH_SHORT).show();
        int newNumber=Integer.valueOf(Number)+1;
        //ToPost
        editsg.putString("NumberOFBill",String.valueOf(newNumber));
        editsg.apply();
        return String.valueOf(newNumber);

    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeBill(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        LocalDateTime datetime = LocalDateTime.now();
        //  System.out.println("Before subtraction of hours from date: "+datetime.format(formatter));

        datetime=datetime.minusHours(2);
        String aftersubtraction=datetime.format(formatter);
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

    public MutableLiveData<StateData<String>>setheadBill=new MutableLiveData<>();

    public void headBill(HeaderBill post, ArrayList<ItemsBill> postitem,Context context) {


        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        //  postsDataBass.contactsDao().insertContacts()
        postsDataBass.headerBillDao().insertHeaderBill(post)
                //   postsDataBass.contactsDao().insertContacts(post)


                .subscribeOn(computation())

                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        setheadBill.postValue( new StateData().loading());

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
                                setBill(itemsBill,context);
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
    public MutableLiveData<StateData<String>>setheadBillRoomBackup=new MutableLiveData<>();

    public void headBillRoomBackup(HeaderBackup post, ArrayList<ItemsBackup> postitem, Context context) {

        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(context);
        postsDataBass.HeaderBackupDao().insertHeaderBill(post)
                .subscribeOn(computation())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        setheadBillRoomBackup.postValue( new StateData().loading());

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
                                setBillRoomBackup(itemsBackup,context);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        } ;
                        ob.subscribe(obs);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });

    }

    public void setBillRoomBackup(ItemsBackup post,Context context) {

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
                        setheadBillRoomBackup.postValue( new StateData().complete());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onSuccess",e.getMessage());
                    }
                });
    }
    public void setBill(ItemsBill post,Context context) {


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

                        setheadBill.postValue( new StateData().complete());
                        ///      Log.d("onSuccess","11111111111");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onSuccess",e.getMessage());
                    }
                });

    }

    public MutableLiveData<StateData<List<Products>>>getProducts=new MutableLiveData<>();

    public void getproductsDaoRoom(Context context) {
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(context);
        contactsDatabase.productsDao().getContacts()
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Products>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                        getProducts.setValue( new StateData().loading());
                    }

                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Products> products) {

                        getProducts.setValue(new StateData().success(products));
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        getProducts.setValue( new StateData().error(e));

                    }
                }
                );
    }
    // Date c = Calendar.getInstance().getTime();
    // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
    // String formattedDate = df.format(c);
    // Log.d("onSuccess",formattedDate);
}