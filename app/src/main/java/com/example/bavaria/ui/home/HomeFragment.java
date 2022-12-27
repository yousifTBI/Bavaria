package com.example.bavaria.ui.home;

import static com.example.bavaria.print.Print_c.textAsBitmap;
import static java.lang.System.in;
import static java.lang.System.lineSeparator;
import static io.reactivex.rxjava3.schedulers.Schedulers.computation;
import static io.reactivex.rxjava3.schedulers.Schedulers.io;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Printer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bavaria.Bluetoothprint.PrintBluetooth;
import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentHomeBinding;

import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.models.BillReturn;
import com.example.bavaria.pojo.models.Items;
import com.example.bavaria.pojo.models.Task3;
import com.example.bavaria.pojo.testModels.ItemsModels;
import com.example.bavaria.ui.acount.AccountActivity;
import com.example.bavaria.ui.roomContacts.AccountInfo.LoginModel;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.backup.HeaderBackup;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.onlineBill.HeaderBillOnline;
import com.example.bavaria.ui.roomContacts.onlineBill.ItemsBillOnlin;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.slideshow.OnClic;
import com.example.bavaria.ui.utils.SharedPreferencesBillStatu;
import com.example.bavaria.ui.utils.SharedPreferencesCom;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import CTOS.CtPrint;
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

public class HomeFragment extends Fragment implements OnClic {

    AdabterInvoice adabter;
    HomeViewModel homeViewModel;
    SharedPreferences sharedPreferenclanguageg;
    PrintBluetooth printBT = new PrintBluetooth();
    ArrayList<ItemsModel> itemsList;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);
        homeViewModel.getItemsOnline(getContext());
        //   adabter.setOnClic(this);
    //    LoginFun("1524");


        homeViewModel.qr.observe(getActivity(), new androidx.lifecycle.Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });


        itemsList = new ArrayList<>();
        adabter = new AdabterInvoice(getContext());
        List<ItemsModels> models = new ArrayList<>();
        //  adabter.setList(itemsList);
        adabter.setList(models);
        //  adabter.setOnClic(this);
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, @androidx.annotation.NonNull RecyclerView.ViewHolder target) {
                //  Toast.makeText(ItemsActivity.this, "Swipe to delete", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(binding.billRecycler, "Deleted item", Snackbar.LENGTH_SHORT).setAction("تم المسح", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                }).show();
                models.remove(viewHolder.getAdapterPosition());
                adabter.notifyDataSetChanged();

                adabter.notifyItemInserted( models.size() - 1);
                binding.billRecycler.scrollToPosition( models.size());
                binding.billRecycler.setAdapter(adabter);

            }
        }).attachToRecyclerView(binding.billRecycler);

        SharedPreferencesBillStatu.init(getContext());

        int state = 0;
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                binding.progressBar2.setVisibility(View.VISIBLE);
                if (itemsList.isEmpty()) {

                    binding.progressBar2.setVisibility(View.GONE);
                    Toast.makeText(getActivity(), "الفاتوره فارغه", Toast.LENGTH_SHORT).show();

                } else if (state == 0) {

                    String bill = setpill();

                    //To Create number Ricet Bill
                 //   String numberRicet = homeViewModel.getNumberBill(getContext());
                    String numberRicet =  SharedPreferencesBillStatu.getInstance().getNumberOFBill();


                    //To Create Date Time Bill
                    String TimeRicet = homeViewModel.getTimeBill();

                    ArrayList<ItemDatum> itemData = new ArrayList<>();
                    ArrayList<ItemsBill> ItemsBillRoom = new ArrayList<>();
                    ArrayList<ItemsBackup> ItemsBillRoomBackup = new ArrayList<>();


                    Double price = 0.0;
                    Double Tax = 0.0;
                    Double totalPrice = 0.0;
                    int itemId = 0;
                    for (ItemsModels i : models) {
                        //To Create List to UUID
                        itemData.add(homeViewModel.getItems(1.0, Double.valueOf(i.getPrice()), Double.valueOf(i.getPrice()), i.getDescription()));

                        //To Create List to Room
                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getDescription(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));

                        //To Create List to Room Backup
                        ItemsBillRoomBackup.add(homeViewModel.setItemsRoomBackup(i.getDescription(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));

                        // Tax += i.getTax();
                        // price += i.getprice();
                        // totalPrice += i.getTotal();
//
                        price += Double.valueOf(i.getPrice());
                        Tax = (price * 14.0) / 100;
                        totalPrice = price + Tax;
                        itemId++;


                        // Log.d("onSuccess", price+"");
                    }


                    Log.d("onSuccess", price + "");
                    Log.d("onSuccess", Tax + "");
                    Log.d("onSuccess", totalPrice + "");


                    //To Create UUID
                    String uu = homeViewModel.CreateUUID(numberRicet, "", TimeRicet, itemData);
                    Log.d("onSuccess", uu);
                    String QR = " https://preprod.invoicing.eta.gov.eg/receipts/search/" + uu;


                    //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
                    //To Create New Bill in Room db

                    HeaderBill headerBill = new HeaderBill();
                    headerBill.setUUID(uu);
                    headerBill.setBillNumber(numberRicet);
                    headerBill.setInvoiceDate(TimeRicet);
                    headerBill.setTax(Tax);
                    headerBill.setNetPrice(price);
                    headerBill.setTotalPrice(totalPrice);
                    headerBill.setLink(QR);

                    //To Insert New Bill in Room db
                    homeViewModel.headBill(headerBill, ItemsBillRoom, getActivity());

                    // Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);

                    // Log.d("onSuccess",g.toJson(r));
                    // Send(r);
                    //     printp(QR,"android.binder.printer");
                    // String bill = setpill();
                    //    ssss();
                    // setPrintBT(bill, QR);
                    // printp(QR, "android.binder.printer");

                    //To Create online Bill
                  //  Root createRoot = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
                  //  homeViewModel.Send(createRoot, HeaderOnline, ItemsBillRoomOnlin, getActivity());
                    models.clear();
                    CastlesPrinter(QR);
                } else if (state == 1) {
                    //To Create number Ricet Bill
                    String numberRicet = homeViewModel.getNumberBill(getContext());
                    //To Create Date Time Bill
                    String TimeRicet = homeViewModel.getTimeBill();

                    ArrayList<ItemDatum> itemData = new ArrayList<>();
                    ArrayList<ItemsBill> ItemsBillRoom = new ArrayList<>();
                    ArrayList<ItemsBackup> ItemsBillRoomBackup = new ArrayList<>();
                    ArrayList<ItemsBillOnlin> ItemsBillRoomOnlin = new ArrayList<>();


                    Double price = 0.0;
                    Double Tax = 0.0;
                    Double totalPrice = 0.0;
                    int itemId = 0;
                    for (ItemsModel i : itemsList) {

                        //To Create List to UUID
                        itemData.add(homeViewModel.getItems(1.0, Double.valueOf(i.getPrice()), Double.valueOf(i.getPrice()), i.getTitle()));

                        //To Create List to Room
                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getTitle(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));

                        //To Create List to Room Backup
                        ItemsBillRoomBackup.add(homeViewModel.setItemsRoomBackup(i.getTitle(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));

                        ItemsBillRoomOnlin.add(homeViewModel.setItemsRoomOnline(i.getTitle(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));

                        price += Double.valueOf(i.getPrice());
                        Tax = (price * 14.0) / 100;
                        totalPrice = price + Tax;
                        // Tax += i.getTax();
                        // price +=Double.valueOf( i. getPrice());
                        // totalPrice += i.getTotal();

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


                    HeaderBackup HeaderBackup = homeViewModel.getHeaderBackup(uu, numberRicet, TimeRicet, Tax, price, totalPrice, sss);
                    HeaderBillOnline HeaderOnline = homeViewModel.getHeaderOnline(uu, numberRicet, TimeRicet, Tax, price, totalPrice, sss);
                    //To Insert New Bill in Room db
                    //   homeViewModel.headBill(headerBill, ItemsBillRoom, getActivity());
                    homeViewModel.headBillRoomBackup(HeaderBackup, ItemsBillRoomBackup, getActivity());

                    //  homeViewModel.headBillRoomBackup()
                    Root createRoot = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
                    homeViewModel.Send(createRoot, HeaderOnline, ItemsBillRoomOnlin, getActivity());


                    // Log.d("onSuccess",g.toJson(r));

                    //  printp(sss,"Printer001");
                    //   printp(sss, "Saturn1000");

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

        // homeViewModel.getItems("3");

        binding.cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // homeViewModel.provideSimpleDialog(getContext());

                SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat(getActivity(), "Search...",
                        "What are you looking for...?", null,
                        homeViewModel.itemsModels1

                        ,
                        new SearchResultListener<ItemsModel>() {
                            @Override
                            public void onSelected(BaseSearchDialogCompat dialog, ItemsModel item, int position) {
                                // Toast.makeText(context, item.getTax()+"ء", Toast.LENGTH_SHORT).show();
                                itemsList.add(item);
                                ItemsModels products = new ItemsModels();
                                products.setDescription(item.getDescription());
                                products.setItemName(item.getDescription());
                                products.setItemType(item.getItemType());
                                products.setBarcode(item.getBarcode());
                                products.setPrice(Double.parseDouble(item.getPrice()));

                                models.add(products);

                                adabter.notifyDataSetChanged();
                                dialog.dismiss();
                            }

                        }
                );
                dialog.show();

            }
        });
        //   homeViewModel.getItemsList().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<ItemsModel>() {
        //       @Override
        //       public void onChanged(ItemsModel items) {
        //           if (items != null) {
        //               itemsList.add(items);
        //               adabter.setList(itemsList);

        //               // Toast.makeText(getContext(),  items.getTotal()+"", Toast.LENGTH_SHORT).show();
        //               // adabter.notify();
        //               //     homeViewModel.setItemsList1(itemsList);
        //               adabter.notifyDataSetChanged();
        //           }


        //       }
        //   });


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
                if (billReturn.getStatus() == "submitted") {

                } else {

                }
                binding.progressBar2.setVisibility(View.GONE);

                for (String p : billReturn.getQrCode()) {
                    Log.d("onSuccess1", p);
                }
                for (String p : billReturn.getErrorMessage()) {
                    Log.d("onSuccess1", p);
                }
                Log.d("onSuccess1", billReturn.getStatus() + "Status");
                Log.d("onSuccess1", billReturn.getSubmitionID() + "SubmitionID");
                Log.d("onSuccess1", billReturn.getSubmitted() + "Submitted");
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
                binding.progressBar2.setVisibility(View.GONE);

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
            printBT.printQrCode(textAsBitmap(ss, 520, 22));
            // printBT.printQrCode(textAsBitmap(s, 370, 27));
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
    // public void printe(){
    //     String TAG = "Saturn1000 Print demo";

    //     CtPrint print = new CtPrint();

    //     String print_font;
    //     int print_x = 0;
    //     int print_y = 36;
    //     int Currently_high = 20;
    //     int ret = 0;
    //     Bitmap bitmap = null;

    //     print.initPage(200);
    //     bitmap = print.encodeToBitmap("12ASDFSS34", print.QR_CODE, 150, 150);
    //     print.drawImage(bitmap, 0, 0);
    //     print.printPage();

    //     ret = print.roll(10);
    //     Log.d(TAG, String.format("Roll ret = %d", ret));

    //     ret = print.status();
    //     Log.d(TAG, String.format("status ret = %d", ret));

    //     print.setHeatLevel(2);

    //     print.initPage(100);

    //     print_font = "PRINT TESTING";
    //     print.drawText(0, print_y + Currently_high, print_font, print_y, 1, true, (float) 0, false, false);
    //     Currently_high += print_y;

    //     //Bitmap image = BitmapFactory.decodeFile("/data/" + "test.jpg");
    //     //print.drawImage(image, 0, Currently_high);

    //     print.printPage();
    // }
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
        int cunt=0;
        double totprice=0.0;
        int totquantity=0;
        for (ItemsModel s : itemsList) {
            //  if (s.ItemName.length()>15){
            //    int startRest= s.ItemName.indexOf(" ",15);
            //    String restOfItemName=s.ItemName.substring(startRest);
            //    s.ItemName=s.ItemName.substring(0,startRest);
//
            //      sb.append("  "+s.ItemName+"       "+s.contaty+"           "+s.balanc+"           "+"\n" +
            //            " "+restOfItemName+"\n" +"\n");
            //  }else {
            sb.append("   " + s.getPrice() + "");
            sb.append("         " + s.getQuantity() + "    ");
            sb.append("        n" + s.getTitle() +"");
            sb.append("\n");
            // sb.append(""+s.getQuantity()+"");

            cunt++;
            totprice+= Double.valueOf( s.getPrice());
            totquantity+= s.getQuantity();

            //  " " + s.getPrice() + "  " + "s.getTotal()" + "\n");

            //   }
            //  sb.append("  "+s.ItemName+"           "+s.contaty+"           "+s.balanc +"\n");


        }

        sb.append("\n");
        sb.append("_________________________________");
        sb.append("\n");
        sb.append("\n");
        sb.append(" Total Items  " + "                      " +  cunt);
        sb.append("\n");
        sb.append(" Discount     " + "                      " + "0.0");
        sb.append("\n");
        sb.append(" Cash Discount  " + "                " + "0.0");
        sb.append("\n");
        sb.append("_________________________________");

        sb.append("\n");
        sb.append(" Total Item Price  " + "     " + totprice);
        sb.append("\n");
        sb.append(" Total Quantity    " + "      " + totquantity);
        sb.append("\n");
        sb.append(" Credit Cards  " + "                 " + "0.0");
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
        String msg =
                msg2 + n4 + "\n" + "\n" +
                        sb;


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


    @Override
    public void getPos(int postion) {

    }


    // @Override
    // public void updateQuantity(int postionList, ItemsModel item) {
    //
    // }

    @Override
    public void updateQuantity(View v, int postionList, ItemsModel item, int AdabterPos) {
        ArrayList<Integer> arrayList = new ArrayList<>();

        // set value in array list
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        arrayList.add(10);
        arrayList.add(11);
        arrayList.add(12);
        arrayList.add(13);
        arrayList.add(14);
        arrayList.add(15);

        // Initialize dialog
        Dialog dialog = new Dialog(getContext());

        // set custom dialog
        dialog.setContentView(R.layout.dialog_searchable_spinner);

        // set custom height and width
        dialog.getWindow().setLayout(600, 800);

        // set transparent background
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // show dialog
        dialog.show();

        // Initialize and assign variable
        // EditText editText=dialog.findViewById(R.id.edit_text);
        ListView listView = dialog.findViewById(R.id.list_view);

        // Initialize array adapter
        ArrayAdapter<Integer> adapterlist = new ArrayAdapter<Integer>(getContext(), android.R.layout.simple_list_item_1, arrayList);

        // set adapter
        listView.setAdapter(adapterlist);

        // editText.addTextChangedListener(new TextWatcher() {
        //     @Override
        //     public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //         //  Toast.makeText(ItemsActivity.this, s+"s34", Toast.LENGTH_SHORT).show();

        //     }

        //     @Override
        //     public void onTextChanged(CharSequence s, int start, int before, int count) {
        //         adapterlist.getFilter().filter(s);
        //         //  adapterlist.filterList()

        //         Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
        //     }

        //     @Override
        //     public void afterTextChanged(Editable s) {
        //         //  Toast.makeText(ItemsActivity.this, s+"ss", Toast.LENGTH_SHORT).show();

        //     }
        // });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //    adabter.setList(itemsList);

                // Toast.makeText(getContext(),  items.getTotal()+"", Toast.LENGTH_SHORT).show();
                // adabter.notify();
                //     homeViewModel.setItemsList1(itemsList);

                // when item selected from list
                // set selected item on textView
                //   textview.setText(adapterlist.getItem(position).getItemName());
                //   // Toast.makeText(ItemsActivity.this,adapter.getItem(position).Record_ID, Toast.LENGTH_SHORT).show();
                //   list.add(new ItemsModel(adapterlist.getItem(position).getSelling_Price(),adapterlist.getItem(position).getItemName(),adapterlist.getItem(position).getBarcode()));
                //   // Dismiss dialog
                //   //  adabter.setList(list);
                //   adabter.notifyItemInserted(list.size()-1);
                //   billRecycler.scrollToPosition(list.size());
                //   billRecycler.setAdapter(adabter);
                //  holder.   contaty_v.setText(adapterlist.getItem(position)+"");
                //    item.setQuantity( Integer.valueOf(adapterlist.getItem(position)));
                itemsList.remove(postionList);
                ItemsModel i = new ItemsModel();
                i.setQuantity(Integer.valueOf(adapterlist.getItem(position)));
                i.setDescription(item.getDescription());
                i.setPrice(Double.valueOf(item.getPrice()));
                itemsList.add(i);
                //.setQuantity(Integer.valueOf(String.valueOf(adapterlist.getItem(position))));
                // Toast.makeText(getContext(), String.valueOf(adapterlist.getItem(position)), Toast.LENGTH_SHORT).show();

                // adabter.setList(itemsList);
                // holder.total_v.setText(list.get(position).getBalanc()+"");
                // list.get(position).setContaty(Double.valueOf(String.valueOf(charSequence)));
                // clic.cliceCuantaty(position,list.get(position).getBalanc());
                //   adabter.notifyDataSetChanged();
                adabter.notifyItemChanged(postionList);

                dialog.dismiss();
            }
        });
    }


    public void LoginFun(String s) {
        Log.d("Login", "cf");

        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .LoginAPI(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task3<LoginModel>> observer = new Observer<Task3<LoginModel>>() {
            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull Task3<LoginModel> loginModelTask3) {
                Log.d("Loginchhf", loginModelTask3.Message + "cf");


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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        //  int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_settings:


             //   homeViewModel.getItems("3", getContext(), "1524");
                homeViewModel.getItems("1", getContext(), "25810");
                homeViewModel.getItemsOnline(getContext());
                break;
            case R.id.action_settings2:
                homeViewModel.LoginFun("25810", getContext());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setPrintBT(String bill, String qr) {

        String TAG = "Saturn1000 Print demo";

        CtPrint print = new CtPrint();

        String print_font;
        int print_x = 0;
        int print_y = 36;
        int Currently_high = 20;
        int ret = 0;
        Bitmap bitmap = null;

        print.initPage(200);
        bitmap = print.encodeToBitmap(qr, print.QR_CODE, 150, 150);
        print.drawImage(bitmap, 0, 0);
        print.printPage();

        ret = print.roll(10);
        Log.d(TAG, String.format("Roll ret = %d", ret));

        ret = print.status();
        Log.d(TAG, String.format("status ret = %d", ret));

        print.drawText(20, 20, "shdkjshd" + "\n" + "kjds", 87);
        print.setHeatLevel(3);

        print.initPage(100);
        //  print.printPage();
        print.drawText(20, 20, "shdkjshd" + "\n" + "kjds", 23);
        //  print.printPage();


        print_font = "PRINT TESTING";
        StringBuffer s = new StringBuffer();
        s.append("dfkjd");
        s.append("%n");
        s.append("\n");

        s.append("dhjdhdj");
        String line1 = "sdsd";

        String line2 = "sd";
        String line3 = "\n";

        String rhyme = line1 + "" + line2 + line3 + "lskj";
        // rhyme = line1 + " " + line2;
        print.drawText(0, print_y + Currently_high, rhyme, print_y, 4, true, (float) 0, false, false);
        //    print.drawText(0, print_y + Currently_high, String.valueOf(s), print_y, 1, true, (float) 0, false, false);
        //    print.drawText(0, print_y + Currently_high, String.valueOf(s), print_y, 1, true, (float) 0, false, false);
        // Bitmap c=new BitmapDrawable().getBitmap().asShared();

        //  Typeface typeface = getResources().getColor()
        //   print. drawImage(textAsBitmap(String.valueOf(bill), 300, 50),43,53);
        //  Currently_high += print_y;
        //  print.drawText(0, 36,  "Uno BAVARIA",35, 45, 20,R.color.black,true, 20, true, true, false,Typeface.create("sans-serif-thin", Typeface.NORMAL));
        // { /* compiled code */ }
        //    print.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));


        //    print. drawText(2,30,"Uno Android Cashier",55);
        //    print. drawText(2,30,bill,55);
        //    print. drawText(2,30,bill,55);
        //    print. drawText(2,30,bill,55);
        //    print. drawText(2,30,bill,55);
        //    String n4 = "__________  BILL DETAILS _________";
//
        //    String n1 = "                 Uno Android Cashier";
        //    String n2 = "                             Cairo";
        //    String n3 = "                      CANDY SHOP";
        //    String msg2 =
        //    //Bitmap image = BitmapFactory.decodeFile("/data/" + "test.jpg");
        //    //print.drawImage(image, 0, Currently_high);

        print.printPage();
    }

    public void CastlesPrinter(String QR) {
        String print_font;
        int print_x = 45;
        int print_y = 36;
        int Currently_high = 20;
        int ret = 0;
        CtPrint print = new CtPrint();


        print.initPage(700);
        print.drawImage(textAsBitmap(setpill(), 600, 27), 4, 30);
        print.printPage();
        Bitmap bitmap = null;


        print.initPage(370);
        bitmap = print.encodeToBitmap(QR, print.QR_CODE, 350, 320);
        print.drawImage(bitmap, 0, 0);
        print.printPage();

        ret = print.roll(10);
        // Log.d(TAG, String.format("Roll ret = %d", ret));

        ret = print.status();
        //   Log.d(TAG, String.format("status ret = %d", ret));

        print.setHeatLevel(2);

        //  print.initPage(100);

        //   print.drawText(0, print_y + Currently_high, String.valueOf(sb), print_y, 4, 0xFF000000, true, (float) 0, true,
        //           false, Typeface.create("sans-serif-thin", Typeface.BOLD));
        //   Currently_high += print_y;
        //   print.drawText(0, print_y + Currently_high, print_font, print_y / 2, print_y, 5, 0xFF000000, true, (float) 0, false,
        //           false, Typeface.create("sans-serif-thin", Typeface.NORMAL));
        //   Currently_high += print_y;
        //   print.drawText(0, print_y + Currently_high, print_font, print_y * 2, print_y, 6, 0xFF000000, true, (float) 0, false,
        //           false, Typeface.create("sans-serif-thin", Typeface.NORMAL));
        //   String json_printname = "";
        //   String json_printip = "";
        //   String json_printport = "";
        // try {
        //     Root r=new Root();

        //     json_obj = json_arr.getJSONObject(0);
        //     json_printname = json_obj.getString("PrinterName");
        //     json_printip = json_obj.getString("PrinterIP");
        //     json_printport = json_obj.getString("PrinterPort");
        //   print.  printerSearch() throws Exception
        //     print.printerSelect(json_obj);
        // } catch (JSONException e) {
        //     e.printStackTrace();
        // }
        // try{
        //     print.printerSelect(json_obj);
        // }catch(Exception e){
        //     Log.d(TAG, e.getMessage());
        //     textView1.setText(e.getMessage());
        // }
        //  public int lineEx();


        //  print.printPage();

    }

    public Bitmap print(String url) throws WriterException {
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        BitMatrix bitMatrix;

        bitMatrix = multiFormatWriter.encode(url,
                BarcodeFormat.CODE_128,
                250,
                250);

        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
        return bitmap;
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }
}