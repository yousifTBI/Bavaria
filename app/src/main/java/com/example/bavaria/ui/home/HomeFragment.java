package com.example.bavaria.ui.home;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bavaria.print.Print_c.textAsBitmap;
import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.bavaria.Bluetoothprint.PrintBluetooth;
import com.example.bavaria.MainActivity;
import com.example.bavaria.databinding.FragmentHomeBinding;

import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.classes.models.BillReturn;
import com.example.bavaria.pojo.classes.models.Items;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.roomContacts.productRoom.Products;
import com.example.bavaria.ui.slideshow.OnClic;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

public class HomeFragment extends Fragment implements OnClic {

    private FragmentHomeBinding binding;
    AdabterInvoice adabter;
    HomeViewModel homeViewModel;
    SharedPreferences sharedPreferenclanguageg;
    PrintBluetooth printBT = new PrintBluetooth();
    ArrayList<Items> itemsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // itemsList.add(new Items(" 1- شامبيون  سعة 2 كجم ",200.0));
        // itemsList.add(new Items(" 2- تيجرا  1 كجم",367.0));
        // itemsList.add(new Items(" 3- خرطوشة ضغط داخلية 1 كجم",212.0));
        // itemsList.add(new Items(" 4- اقتحام 25 اسطوانة ضغط خارجية",1092.0));
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
         // MainActivity m=new MainActivity();
         // m.setOnClic(this);

        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);
        // homeViewModel.setOnClic();
        //  homeViewModel.getproductsDaoRoom(getContext());
//
        // homeViewModel.getProducts.observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<StateData<List<Products>>>() {
        //     @Override
        //     public void onChanged(StateData<List<Products>> listStateData) {
        //         switch ( listStateData.getStatus()) {
        //              case SUCCESS:
        //               //   List<Book> bookList =
        //                         // productsStateData.getData();
        //                  Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
        //                  //TODO: Do something with your book data
        //                  break;
        //              case ERROR:
        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();
//
        //                  //   Throwable e = books.getError();
        //                  //TODO: Do something with your error
        //                  break;
        //              case LOADING:
        Toast.makeText(getContext(), " LOADING", Toast.LENGTH_SHORT).show();
//
        //                  //TODO: Do Loading stuff
        //                  break;
        //              case COMPLETE:
        //                  Toast.makeText(getContext(), "COMPLETE", Toast.LENGTH_SHORT).show();
//
        //                  //TODO: Do complete stuff if necessary
        //                  break;
        //        }
        //     }
        // });


//        sharedPreferenclanguageg = getActivity().getSharedPreferences("NumberBill", MODE_PRIVATE);
//        SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();

//        //ToPost
//       // editsg.putString("NumberOFBill","25.10");
//       // editsg.apply();


//        //toGet
//        String Number = sharedPreferenclanguageg.getString("NumberOFBill", "1");
//        int newNumber=Integer.valueOf(Number)+1;

//        editsg.putString("NumberOFBill",String.valueOf(newNumber));
//        editsg.apply();
        //  getRoom();
        //  binding.button3.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //      public void onClick(View view) {
        //          getRoom();
        //     //  sharedPreferenclanguageg = getActivity().getSharedPreferences("NumberBill", MODE_PRIVATE);
        //     //  SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();

        //     //  //ToPost
        //     //  // editsg.putString("NumberOFBill","25.10");
        //     //  // editsg.apply();


        //     //  //toGet
        //     //  String Number = sharedPreferenclanguageg.getString("NumberOFBill", "1");
        //     //  Toast.makeText(getContext(), Number, Toast.LENGTH_SHORT).show();
        //     //  int newNumber = Integer.valueOf(Number) + 1;

        //     //  editsg.putString("NumberOFBill", String.valueOf(newNumber));
        //     //  editsg.apply();
        //      }
        //  });

        itemsList = new ArrayList<>();
        adabter = new AdabterInvoice(getContext());
        adabter.setList(itemsList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.billRecycler.setLayoutManager(linearLayoutManager);
        binding.billRecycler.setAdapter(adabter);
        String url = "https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";

//        binding.textView21.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Uri uri=Uri.parse(url);
//                startActivity(new Intent(Intent.ACTION_VIEW,uri));
//            }
//        });
        // binding.textView21.setMovementMethod(LinkMovementMethod.getInstance());
        int state=0;
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                binding.progressBar2.setVisibility(View.VISIBLE);
                if (itemsList.isEmpty()) {

                    binding.progressBar2.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "الفاتوره فارغه", Toast.LENGTH_SHORT).show();
                } else if ( state==0){

                    //To Create number Ricet Bill
                    String numberRicet = homeViewModel.getNumberBill(getContext());
                    //To Create Date Time Bill
                    String TimeRicet = homeViewModel.getTimeBill();

                    ArrayList<ItemDatum> itemData = new ArrayList<>();
                    ArrayList<ItemsBill> ItemsBillRoom = new ArrayList<>();
                    ArrayList<ItemsBackup> ItemsBillRoomBackup = new ArrayList<>();


                    Double price = 0.0;
                    Double Tax = 0.0;
                    Double totalPrice = 0.0;
                    int itemId = 0;
                    for (Items i : itemsList) {
                        //To Create List to UUID
                        itemData.add(homeViewModel.getItems(1.0, i.getprice(), i.getprice(), i.getTitle()));

                        //To Create List to Room
                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getTitle(), i.getprice(), numberRicet, String.valueOf(itemId)));

                        //To Create List to Room Backup
                        ItemsBillRoomBackup.add(homeViewModel. setItemsRoomBackup(i.getTitle(), i.getprice(), numberRicet, String.valueOf(itemId)));

                        Tax += i.getTax();
                        price += i.getprice();
                        totalPrice += i.getTotal();

                        itemId++;

                        // Log.d("onSuccess", price+"");
                    }


                    Log.d("onSuccess", price + "");
                    Log.d("onSuccess", Tax + "");
                    Log.d("onSuccess", totalPrice + "");





                    //To Create UUID
                    String uu = homeViewModel.CreateUUID(numberRicet, "", TimeRicet, itemData);
                    Log.d("onSuccess", uu);
                    String sss = " https://preprod.invoicing.eta.gov.eg/receipts/search/" + uu;


                    //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
                    //To Create New Bill in Room db

                    HeaderBill headerBill = new HeaderBill();
                    headerBill.setUUID(uu);
                    headerBill.setBillNumber(numberRicet);
                    headerBill.setInvoiceDate(TimeRicet);
                    headerBill.setTax(Tax);
                    headerBill.setNetPrice(price);
                    headerBill.setTotalPrice(totalPrice);
                    headerBill.setLink(sss);

                    //To Insert New Bill in Room db
                    homeViewModel.headBill(headerBill, ItemsBillRoom, getActivity());

                    // Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);

                    // Log.d("onSuccess",g.toJson(r));
                    // Send(r);
                    //  printp(sss,"Printer001");
                    printp(sss, "Bluetooth Printer");

                    //To Create online Bill
                }else if(state==1){
                    //To Create number Ricet Bill
                    String numberRicet = homeViewModel.getNumberBill(getContext());
                    //To Create Date Time Bill
                    String TimeRicet = homeViewModel.getTimeBill();

                    ArrayList<ItemDatum> itemData = new ArrayList<>();
                    ArrayList<ItemsBill> ItemsBillRoom = new ArrayList<>();
                    ArrayList<ItemsBackup> ItemsBillRoomBackup = new ArrayList<>();


                    Double price = 0.0;
                    Double Tax = 0.0;
                    Double totalPrice = 0.0;
                    int itemId = 0;
                    for (Items i : itemsList) {
                        //To Create List to UUID
                        itemData.add(homeViewModel.getItems(1.0, i.getprice(), i.getprice(), i.getTitle()));

                        //To Create List to Room
                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getTitle(), i.getprice(), numberRicet, String.valueOf(itemId)));

                        //To Create List to Room Backup
                        ItemsBillRoomBackup.add(homeViewModel. setItemsRoomBackup(i.getTitle(), i.getprice(), numberRicet, String.valueOf(itemId)));

                        Tax += i.getTax();
                        price += i.getprice();
                        totalPrice += i.getTotal();

                        itemId++;

                        // Log.d("onSuccess", price+"");
                    }


                    Log.d("onSuccess", price + "");
                    Log.d("onSuccess", Tax + "");
                    Log.d("onSuccess", totalPrice + "");





                    //To Create UUID
                    String uu = homeViewModel.CreateUUID(numberRicet, "", TimeRicet, itemData);
                    Log.d("onSuccess", uu);
                    String sss = " https://preprod.invoicing.eta.gov.eg/receipts/search/" + uu;


                    //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
                    //To Create New Bill in Room db

                    HeaderBill headerBill = new HeaderBill();
                    headerBill.setUUID(uu);
                    headerBill.setBillNumber(numberRicet);
                    headerBill.setInvoiceDate(TimeRicet);
                    headerBill.setTax(Tax);
                    headerBill.setNetPrice(price);
                    headerBill.setTotalPrice(totalPrice);
                    headerBill.setLink(sss);

                    //To Insert New Bill in Room db
                    homeViewModel.headBill(headerBill, ItemsBillRoom, getActivity());

                  //  homeViewModel.headBillRoomBackup()
                     Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
                    Send(r);

                    // Log.d("onSuccess",g.toJson(r));

                    //  printp(sss,"Printer001");
                    printp(sss, "Bluetooth Printer");
                }
            }
        });
        binding.progressBar2.setVisibility(View.GONE);
        homeViewModel.setheadBill.observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<StateData<String>>() {
            @Override
            public void onChanged(StateData<String> stringStateData) {
                switch (stringStateData.getStatus()) {
                    //  case SUCCESS:
                    //      //   List<Book> bookList =
                    //      // productsStateData.getData();
                    //      Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    //      //TODO: Do something with your book data
                    //      break;
                    //  case ERROR:
                    //      Toast.makeText(getContext(), "ERROR", Toast.LENGTH_SHORT).show();

                    //      //   Throwable e = books.getError();
                    //      //TODO: Do something with your error
                    //      break;
                    case LOADING:
                        Toast.makeText(getContext(), " LOADING", Toast.LENGTH_SHORT).show();

                        binding.progressBar2.setVisibility(View.VISIBLE);
                        //TODO: Do Loading stuff
                        // break;
                    case COMPLETE:
                        itemsList.clear();
                        adabter.notifyDataSetChanged();
                        homeViewModel.itemsList.setValue(null);
                        Toast.makeText(getContext(), "COMPLETE-----", Toast.LENGTH_SHORT).show();
                        binding.progressBar2.setVisibility(View.GONE);
                        //TODO: Do complete stuff if necessary
                        // break;
                }
            }
        });


        binding.cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                homeViewModel.provideSimpleDialog(getContext());

            }
        });
        homeViewModel.getItemsList().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<Items>() {
            @Override
            public void onChanged(Items items) {
                if (items != null) {
                    itemsList.add(items);
                    adabter.setList(itemsList);

                    // Toast.makeText(getContext(),  items.getTotal()+"", Toast.LENGTH_SHORT).show();
                    // adabter.notify();
                    //     homeViewModel.setItemsList1(itemsList);
                    adabter.notifyDataSetChanged();
                }


            }
        });


        homeViewModel.getItems(1.0, 50, 50, "فلامنكو");
        homeViewModel.getItems(1.0, 50, 50, "فلامنكو");
        homeViewModel.getItems(1.0, 50, 50, "فلامنكو");
        homeViewModel.getItems(1.0, 50, 50, "فلامنكو");

        // String uuID=  homeViewModel. sentApi("");


//        Single ss = RetrofitRefranc.getInstance().getApiCalls().getProductm(homeViewModel. sentApi1(uuID))
//                .subscribeOn(io())
//                .observeOn(AndroidSchedulers.mainThread());
//        SingleObserver<BillReturn> singleObserver= new SingleObserver<BillReturn>() {
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//
//            }
//
//            @Override
//            public void onSuccess(@NonNull BillReturn billReturn) {
//                Log.d("onSuccess",billReturn.getErrorMessage());
//                Log.d("onSuccess",billReturn.getStatus());
//                Log.d("onSuccess",billReturn.getSubmitionID());
//                Log.d("onSuccess",billReturn.getQrCode());
//                Toast.makeText(getActivity(), billReturn.getErrorMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                Log.d("onSuccess",e.getMessage());
//
//            }
//        };
//        ss.subscribe(singleObserver);

//       ObjectMapper mapper=new ObjectMapper();

//    //   Root r=sentApi();
//       Gson gson=new Gson();
//      String cc= gson.toJson(r).intern();
//   //    Log.d("sentApi",cc);
//      // for(int i = 0; i<jobject.names().length(); i++){
//      //     result="\""+jobject.names().getString(i).toUpperCase()+"\""+"\""+jobject.get(jobject.names().getString(i))+"\"";
//      // }
//      // r.setTotalAmount(500.0000);
//       try {
//          // mapper.
//           String mapperSt=  mapper.writeValueAsString(r);
//           Log.d("mapperSt",mapperSt);
//           String    jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(r);
//           Log.d("jsonInString", jsonInString);
//         //  mapper.read
//           try {
//               JSONObject jsonObject = new JSONObject(mapperSt);
//               Iterator<String> keys = jsonObject.keys();
//               while(keys.hasNext()) {

//                   String key = keys.next();
//                   if (jsonObject .get(key) instanceof JSONObject) {
//                     //  Log.d("trs",jsonObject .get(key).toString());
//                       Log.d("trs",key);

//                   }
//                   }
//               Log.d("mapperSt","////"+jsonObject .toString());

//           }catch (JSONException err){
//               Log.d("Error", err.toString());
//           }

//       String c=    mapperSt.replace("{","").replace("}","")
//                   .replace("[","").replace("]","")
//                   .replace("null","").replace(",","")
//               .replace(":","");

//  //     Log.d("sentApi",c);
//       } catch (JsonProcessingException e) {
//           e.printStackTrace();
//       }


       /* final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        */
        // ArrayList<ItemsBill> postitem=new ArrayList<ItemsBill> ();
        // ItemsBill itemsBill=new ItemsBill();
        // itemsBill.setIDBill("1234");
        // postitem.add(itemsBill);
        // HeaderBill h= new HeaderBill();
        // h.setBillNumber("1234");
        // headBill(h,postitem);
        // getRoom();
        // getRoom();
        // getRoom();
        return root;
    }

    public void Send(Root r) {
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
                for (String p : billReturn.getQrCode()) {
                    Log.d("onSuccess", p);
                }
                for (String p : billReturn.getErrorMessage()) {
                    Log.d("onSuccess", p);
                }
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

    // void provideSimpleDialog(Context context) {
    //     SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat(context, "Search...",
    //             "What are you looking for...?", null, createSampleData(),
    //             new SearchResultListener<Items>() {
    //                 @Override
    //                 public void onSelected(BaseSearchDialogCompat dialog, Items item, int position) {
    //                     Toast.makeText(context, "tem.getValue()", Toast.LENGTH_SHORT).show();
    //                     // binding.textView220.setText(item.getCurrencyName());
    //                     // binding.textView23.setText(item.getValue());
    //                     // binding.cashEditText1.setText("0");
    //                     // binding.cashEditText2.setText("0.0");
    //                     //   binding.textView22.setText(item.getCurrencyName()+"    ");
    //                     dialog.dismiss();
    //                 }

    //             }
    //     );
    //     dialog.show();

    //    // dialog.getSearchBox().setTypeface(Typeface.SERIF);
    // }
//String d="HEADER""DATETIMEISSUED""2""RECEIPTNUMBER""2022-12-01T17:56:22Z""UUID""f09d81115a3e5045be45f044549a04ae1d91515b40ccf508defb803a8daa7a87""PREVIOUSUUID""""REFERENCEOLDUUID""""CURRENCY""EGP""EXCHANGERATE""0.0""SORDERNAMECODE""""ORDERDELIVERYMODE""FC""GROSSWEIGHT""0.0""NETWEIGHT""0.0""DOCUMENTTYPE""RECEIPTTYPE""S""TYPEVERSION""1.1""SELLER""RIN""382107586""COMPANYTRADENAME""Domino's Pizza Hyper One Branch""BRANCHCODE""2""BRANCHADDRESS""COUNTRY""EG""GOVERNATE""Giza""REGIONCITY""6 Oct""STREET""Hyaber""BUILDINGNUMBER""1""POSTALCODE""""FLOOR""""ROOM""""LANDMARK""""ADDITIONALINFORMATION""""DEVICESERIALNUMBER""447788""SYNDICATELICENSENUMBER""""ACTIVITYCODE""1071""BUYER""TYPE""p""ID""""NAME""""MOBILENUMBER""""PAYMENTNUMBER""0""ITEMDATA""ITEMDATA""INTERNALCODE""29130""DESCRIPTION""ضغط مخزون""ITEMTYPE""GS1""ITEMCODE""99999999""UNITTYPE""EA""QUANTITY""1.0""UNITPRICE""367.0""NETSALE""367.0""TOTALSALE""367.0""TOTAL""418.38""COMMERCIALDISCOUNTDATA""COMMERCIALDISCOUNTDATA""AMOUNT""0.0""DESCRIPTION""itmdiscount""ITEMDISCOUNTDATA""ITEMDISCOUNTDATA""AMOUNT""0.0""DESCRIPTION""itemDiscount""VALUEDIFFERENCE""0""TAXABLEITEMS""TAXABLEITEMS""TAXTYPE""T1""AMOUNT""51.38""SUBTYPE""V009""RATE""14.0""TOTALSALES""367.0""TOTALCOMMERCIALDISCOUNT""0.0""TOTALITEMSDISCOUNT""0.0""EXTRARECEIPTDISCOUNTDATA""EXTRARECEIPTDISCOUNTDATA""AMOUNT""0.0""DESCRIPTION""itmdiscount""NETAMOUNT""367.0""FEESAMOUNT""0""TOTALAMOUNT""418.38""TAXTOTALS""TAXTOTALS""TAXTYPE""T1""AMOUNT""51.379999999999995""PAYMENTMETHOD""C""ADJUSTMENT""0""CONTRACTOR""NAME""0""AMOUNT""0.0""RATE""0.0""BENEFICIARY""AMOUNT""0.0""RATE""0.0"
//String c="HEADER""DATETIMEISSUED""2022-12-01T17:56:22Z""RECEIPTNUMBER""2""UUID""f09d81115a3e5045be45f044549a04ae1d91515b40ccf508defb803a8daa7a87""PREVIOUSUUID""""REFERENCEOLDUUID""""CURRENCY""EGP""EXCHANGERATE""0.0""SORDERNAMECODE""""ORDERDELIVERYMODE""FC""GROSSWEIGHT""0.0""NETWEIGHT""0.0""DOCUMENTTYPE""RECEIPTTYPE""S""TYPEVERSION""1.1""SELLER""RIN""382107586""COMPANYTRADENAME""Domino's Pizza Hyper One Branch""BRANCHCODE""2""BRANCHADDRESS""COUNTRY""EG""GOVERNATE""Giza""REGIONCITY""6 Oct""STREET""Hyaber""BUILDINGNUMBER""1""POSTALCODE""""FLOOR""""ROOM""""LANDMARK""""ADDITIONALINFORMATION""""DEVICESERIALNUMBER""447788""SYNDICATELICENSENUMBER""""ACTIVITYCODE""1071""BUYER""TYPE""p""ID""""NAME""""MOBILENUMBER""""PAYMENTNUMBER""0""ITEMDATA""ITEMDATA""INTERNALCODE""29130""DESCRIPTION""ضغط مخزون""ITEMTYPE""GS1""ITEMCODE""99999999""UNITTYPE""EA""QUANTITY""1.0""UNITPRICE""367.0""NETSALE""367.0""TOTALSALE""367.0""TOTAL""418.38""COMMERCIALDISCOUNTDATA""COMMERCIALDISCOUNTDATA""AMOUNT""0.0""DESCRIPTION""itmdiscount""ITEMDISCOUNTDATA""ITEMDISCOUNTDATA""AMOUNT""0.0""DESCRIPTION""itemDiscount""VALUEDIFFERENCE""0""TAXABLEITEMS""TAXABLEITEMS""TAXTYPE""T1""AMOUNT""51.38""SUBTYPE""V009""RATE""14.0""TOTALSALES""367.0""TOTALCOMMERCIALDISCOUNT""0.0""TOTALITEMSDISCOUNT""0.0""EXTRARECEIPTDISCOUNTDATA""EXTRARECEIPTDISCOUNTDATA""AMOUNT""0.0""DESCRIPTION""itmdiscount""NETAMOUNT""367.0""FEESAMOUNT""0""TOTALAMOUNT""418.38""TAXTOTALS""TAXTOTALS""TAXTYPE""T1""AMOUNT""51.379999999999995""PAYMENTMETHOD""C""ADJUSTMENT""0""CONTRACTOR""NAME""0""AMOUNT""0.0""RATE""0.0""BENEFICIARY""AMOUNT""0.0""RATE""0.0"

    private ArrayList<Items> createSampleData() {
        ArrayList<Items> list = new ArrayList<>();
        list.add(new Items("شامبيون  سعة 2 كجم ", 200.0));
        list.add(new Items("تيجرا  1 كجم", 367.0));
        list.add(new Items("خرطوشة ضغط داخلية 1 كجم", 212.0));
        return list;

    }


    public void headBill(HeaderBill post, ArrayList<ItemsBill> postitem) {


        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(getContext());
        //  postsDataBass.contactsDao().insertContacts()
        postsDataBass.headerBillDao().insertHeaderBill(post)
                //   postsDataBass.contactsDao().insertContacts(post)


                .subscribeOn(computation())

                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

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
                                setBill(itemsBill);
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

    public void setBill(ItemsBill post) {


        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(getContext());
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

                        ///      Log.d("onSuccess","11111111111");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("onSuccess", e.getMessage());
                    }
                });

    }
//  public void setBill(ItemsBill post){


//      ContactsDatabase postsDataBass =  ContactsDatabase.getGetInstance(getContext());
//      //  postsDataBass.contactsDao().insertContacts()
//      postsDataBass.ItemsBillDao().insertContacts(post)

//              .subscribeOn(computation())

//              .subscribe(new CompletableObserver() {
//                  @Override
//                  public void onSubscribe(@NonNull Disposable d) {

//                  }

//                  @Override
//                  public void onComplete() {
//                      Log.d("yousiiiif","complete3");
//                  }

//                  @Override
//                  public void onError(@NonNull Throwable e) {

//                  }
//              });

    //  }
    // public void getRoom() {
    //     ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(getContext());
    //     contactsDatabase.headerBillDao().getHeaderBill()
    //             //getlistItems("123")
    //             .subscribeOn(computation())
    //             .observeOn(AndroidSchedulers.mainThread())
    //             .subscribe(new SingleObserver<List<HeaderBill>>() {
    //                            @Override
    //                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

    //                            }

    //                            @Override
    //                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<HeaderBill> headerBills) {

    //                                SetBills(headerBills);
    //                               // binding.textView20.setText(headerBills.get(headerBills.size() - 1).getBillNumber());
    //                            }

    //                            @Override
    //                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

    //                            }
    //                        }
    //             );

    // }

    public void SetBills(List<HeaderBill> header) {

        for (HeaderBill h : header) {
            //  h.getBillNumber();
            String uu = h.getUUID();
            String id = h.getBillNumber();
            String TimeRicet = h.getInvoiceDate();

            getItems(id, uu, TimeRicet);
            //  Root r = homeViewModel.sentApi(uu,getItems(id), TimeRicet,id);
            // Send(r);
        }

    }

    public void getItems(String id, String uuID, String timeBill) {
        //  ArrayList<ItemDatum> itemData = new ArrayList<>();
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
                        ArrayList<ItemDatum> itemData = new ArrayList<>();
                        for (ItemsBill i : itemsBills) {
                            // Log.d("onSuccess",i.getIDBill()+"tesor");
                            //  Log.d("onSuccess",i.getPName()+"tesor");
                            itemData.add(homeViewModel.getItems(1.0, i.getUnitPrice(), i.getUnitPrice(), i.getPName()));
                            //  Log.d("onSuccess","yousif");
                        }
                        Root r = homeViewModel.sentApi(uuID, itemData, timeBill, id);
                        Send(r);

                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }
                });

        // }
        // return itemData;
    }
    //           {
    //    @Override
    //    public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

    //    }

    //    @Override
    //    public void onSuccess(@androidx.annotation.NonNull List<ContactsRoom> contactsRooms) {
    //        // Log.d("yousiiiif",contactsRooms.get(1).getNumber().toString());

    //        StringBuilder s=new StringBuilder();

    //        for (int i=0;i==contactsRooms.size();i++){

    //            contactsRooms.get(i).getINTERNALCODE();
    //            contactsRooms.get(i).getDESCRIPTION()  ;
    //            s.append(contactsRooms.get(i).getINTERNALCODE()+""+
    //                    contactsRooms.get(i).getDESCRIPTION() ) ;
    //            // textView2.append( contactsRooms.get(0).getINTERNALCODE()+"__" +contactsRooms.get(0).getDESCRIPTION());
    //        }
    //        Log.d("yousiiiif" ,contactsRooms.get(1).getINTERNALCODE()
    //                +""+ contactsRooms.get(1).getDESCRIPTION());

    //        textView2.setText(s.toString());
    //        //   contactUsers=contactsRooms;

    //        //   adapter.setContactUserslist(contactsRooms);
    //        //   //  for (ContactsRoom r:contactsRooms){
    //        //   //      Log.i("Contact","name:"+r.getName()+"number"+r.getNumber()+"email"+r.getEmail());
    //        //   //  }
    //        //   // adapter.notifyDataSetChanged();
//
    //        //   binding.contactsrecy.setAdapter(adapter);
    //    }

    //    @Override
    //    public void onError(@androidx.annotation.NonNull Throwable e) {
    //        Log.d("yousiiiif",e.getMessage().toString());

    //    }
    //}


    public void printp(String qr, String namep) {
        PrintBluetooth.printer_id = namep;
        // PrintBluetooth.printer_id ="InnerPrinter";
        java.util.Date today = new java.util.Date();
        SimpleDateFormat format_date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String print_date = format_date.format(today);
        String status_barang = "";
        //    if (radioButtonYa.isChecked()){
        //        status_barang = " vegetarian";
        //    }
        //    else if (radioButtonTidak.isChecked()){
        //        status_barang = "meet";
        //    }
        String s = loopList();
        //    Log.d(" loopList",s);

        String ss = setpill();
        String end = endBill();
        String endof = endOfend();
        try {
            printBT.findBT();
            printBT.openBT();

            // printBT.printQrCode(textAsBitmap(s,370, 30));

            //   printBT.printQrCode(textAsBitmap(setpill(),520,28));

            //  printBT.printQrCode(textAsBitmap(s,520, 30));
            //  printBT.printQrCode(textAsBitmap(ss,520, 22));
            printBT.printQrCode(textAsBitmap(s, 370, 27));
            // printBT.printQrCode(textAsBitmap(ss,370, 22));
            printBT.printQrCode(print(qr));

            // printBT.printQrCode(textAsBitmap(s,370, 27));
            // printBT.printQrCode(textAsBitmap(ss,370, 22));
            // printBT.printQrCode(print( c.getUrl()));


            //        printBT.printQrCode(textAsBitmap(s,370, 27));

            //     //   printBT.printQrCode(textAsBitmap(setpill(),520,28));
            //        printBT.printQrCode(textAsBitmap(ss,370, 22));
            //      //  printBT.printQrCode(textAsBitmap(end,370, 27));
            //        printBT.printQrCode(textAsBitmap(endof,370, 22));
            //  printBT.printQrCode(print( c.getUrl()));
            //  printBT.closeBT();
        } catch (IOException
                | WriterException
                ex) {
            ex.printStackTrace();
        }

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }
//  public String computeHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//      MessageDigest digest = MessageDigest.getInstance("SHA-256");
//      digest.reset();

//      byte[] byteData = digest.digest(input.getBytes("UTF-8"));
//      //   StringBuffer sb = new StringBuffer();
//      StringBuilder sb = new StringBuilder();

//      for (int i = 0; i < byteData.length; i++) {
//          sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
//      }

//      return sb.toString();
//  }
////  public ArrayList<ItemDatum> getItems(){
////      ArrayList<CommercialDiscountDatum> commercialDiscountData=new ArrayList<>();
////      commercialDiscountData.add(new CommercialDiscountDatum());
//
////      ArrayList<ItemDiscountDatum> itemDiscountData=new ArrayList<>();
////      itemDiscountData.add(new ItemDiscountDatum());
//
////      ArrayList<TaxableItem> taxableItems=new ArrayList<>();
////      taxableItems.add(new TaxableItem());
//
////      ItemDatum z=new ItemDatum(  "29130",
////              "فلامنكو",
////              "GS1",
////              "99999999",
////              "EA",
////              1.0,
////              50.0,
////              50.00,
////              50.00,
////              57.000,commercialDiscountData,itemDiscountData,0,taxableItems);
//
////      ArrayList<ItemDatum> itemData=new ArrayList<>();
////      itemData.add(z);
////      itemData.add(z);
////      itemData.add(z);
//
////      return itemData;
////  }
////  public Root sentApi(){
//      //   Date df = new Date();
//      Date c = Calendar.getInstance().getTime();
//      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
//      String formattedDate = df.format(c);
//      //   Date df = new Date("yyyy-MM-dd'T'HH:mm:ss'Z'");
//      Header header=new Header(
//              "2022-11-23T07:06:06Z",
//              //df,
//              "815",
//              "",
//              //"a5c56d8fc504e8be9e65d828e41de21d1bd7c21b32d1a8eca9bb2af6e7d0ac8d",
//              "",

//              ""
//      );
//      DocumentType d=new DocumentType();


//      BranchAddress b=new BranchAddress( "EG",
//              "Giza",
//              "6 Oct",
//              "Hyaber",
//              "1",
//              "",
//              "",
//              "",
//              "",
//              "");
//      Seller s=new Seller(     "382107586",
//              "Domino's Pizza Hyper One Branch",
//              "2",
//              b,
//              "447788",
//              "",
//              "1071"
//      );


//      Buyer BU= new Buyer(  "p",
//              "",
//              "",
//              "",
//              "0");

//      //       ArrayList<CommercialDiscountDatum> commercialDiscountData=new ArrayList<>();
//      //       commercialDiscountData.add(new CommercialDiscountDatum());
//
//      //       ArrayList<ItemDiscountDatum> itemDiscountData=new ArrayList<>();
//      //       itemDiscountData.add(new ItemDiscountDatum());
//
//
//      //       ArrayList<TaxableItem> taxableItems=new ArrayList<>();
//      //       taxableItems.add(new TaxableItem());
//
//      //       ItemDatum z=new ItemDatum(  "29130",
//      //               "فلامنكو",
//      //               "GS1",
//      //               "99999999",
//      //               "EA",
//      //               1.0,
//      //               50.0,
//      //               50.00,
//      //               50.00,
//      //               57.000,commercialDiscountData,itemDiscountData,0,taxableItems);
//
//      //       ArrayList<ItemDatum> itemData=new ArrayList<>();
//      //       itemData.add(z);
//      //       itemData.add(z);
//      //       itemData.add(z);


//      ArrayList<ExtraReceiptDiscountDatum> extraReceiptDiscountData=new ArrayList<>();

//      extraReceiptDiscountData.add(new ExtraReceiptDiscountDatum());

//      ArrayList<TaxTotal> taxTotals=new ArrayList<>();

//      taxTotals.add(new  TaxTotal(21.000));


//      Contractor contractor   =new Contractor( );
//      Beneficiary beneficiary=new Beneficiary( );

//      Root r=new Root(header,
//              d
//              ,
//              s
//              ,BU
//              ,getItems(),
//              150.00,
//              0.0,
//              0.0,
//              extraReceiptDiscountData,
//              150.00,
//              0,
//              171.000,
//              taxTotals,
//              "C",
//              0,
//              contractor,
//              beneficiary);


//      return r;
//  }
    //              taxTotals,
//              "C",
//              0,
//              contractor,
//              beneficiary);


    //      return r;
//  }
    public String loopList() {
        java.util.Date today = new java.util.Date();
        SimpleDateFormat format_date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String print_date = format_date.format(today);

        String n1 = "                 Uno Android Cashier";
        String n2 = "                             Cairo";
        String n3 = "                      CANDY SHOP";
        String msg =
                //n0+"\n"+"\n"+ "\n" +
                n1 + "\n" + n2 + "\n" + n3;
        return msg;

    }

    public String setpill() {
        java.util.Date today = new java.util.Date();
        SimpleDateFormat format_date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String print_date = format_date.format(today);

        StringBuffer sb = new StringBuffer();
        // ArrayList<Items> itemsList
        for (Items s : itemsList) {
            //  if (s.ItemName.length()>15){
            //    int startRest= s.ItemName.indexOf(" ",15);
            //    String restOfItemName=s.ItemName.substring(startRest);
            //    s.ItemName=s.ItemName.substring(0,startRest);
//
            //    sb.append("  "+s.ItemName+"       "+s.contaty+"           "+s.balanc+"           "+"\n" +
            //            " "+restOfItemName+"\n" +"\n");
            //  }else {
            sb.append("  " + s.getTitle() + "           " + s.getprice() + "           " + s.getTotal() + "\n");

            //   }
            //  sb.append("  "+s.ItemName+"           "+s.contaty+"           "+s.balanc +"\n");


        }

        //sb.append("\n");
        sb.append("_________________________________");
        sb.append("\n");
        sb.append("\n");
        sb.append(" Total Items  " + "                                   " + "Totals.getText()");
        sb.append("\n");
        sb.append(" Discount     " + "                                    " + "0.0");
        sb.append("\n");
        sb.append(" Cash Discount  " + "                             " + "0.0");
        sb.append("\n");
        sb.append("_________________________________");
        sb.append("\n");
        sb.append(" Total Due  " + "                                       " + "Totals.getText()");
        sb.append("\n");
        sb.append(" Cash Paid    " + "                                    " + "0.0");
        sb.append("\n");
        sb.append(" Credit Cards  " + "                                  " + "0.0");
        sb.append("\n");
        sb.append("_________________________________");
        sb.append("\n");
        sb.append("                      Administrator");
        sb.append("\n");
        sb.append("        " + print_date);
        sb.append("\n");
        sb.append("        " + "                        pos 1");
        sb.append("\n");
        sb.append("_________________________________");

        String n4 = "__________  BILL DETAILS _________";

        String n1 = "                 Uno Android Cashier";
        String n2 = "                             Cairo";
        String n3 = "                      CANDY SHOP";
        String msg2 =
                //n0+"\n"+"\n"+ "\n" +
                n1 + "\n" + n2 + "\n" + n3 + "\n" + "\n";
        String msg = msg2 + n4 + "\n" + "\n" + sb;


        return msg;
    }

    public String endBill() {
        String n3 = (" Total Due  " + "                             " + "0.0");
        String n5 = (" Cash Paid  " + "                          " + "0.0");
        String n9 = (" Credit Cards  " + "                        " + "0.0");


        String msg =
                //n0+"\n"+"\n"+ "\n" +
                n3 + "\n" + n5 + "\n" + n9;
        return msg;
    }

    public String endOfend() {
        java.util.Date today = new java.util.Date();
        SimpleDateFormat format_date = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
        String print_date = format_date.format(today);
        //  sb.append("_________________________________");


        String n1 = ("_________________________________");

        String n2 = ("\n");
        String n3 = ("                                      Administrator");
        String n4 = ("\n");
        String n5 = ("                    " + print_date);
        String n8 = ("\n");
        String n9 = ("                                         POS-01");
        String n10 = ("\n");
        String n11 = ("_________________________________");
        String msg =
                //n0+"\n"+"\n"+ "\n" +
                n1 + n2 + n3 + n4 + n5 + n8 + n9 + n10 + n11;
        return msg;
    }

    public Bitmap print(String url) throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix;

        bitMatrix = multiFormatWriter.encode(url,
                BarcodeFormat.QR_CODE,
                250,
                250);

        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        return bitmap;
    }

    @Override
    public void getPos(int postion) {

    }

    @Override
    public void getQR(String QR) {

        Toast.makeText(getActivity(), QR, Toast.LENGTH_SHORT).show();
    }
}