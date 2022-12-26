package com.example.bavaria;

import static io.reactivex.rxjava3.schedulers.Schedulers.computation;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.bavaria.databinding.ActivityMainBinding;
import com.example.bavaria.pojo.models.AddItemModel;
import com.example.bavaria.pojo.models.EditItemModel;
import com.example.bavaria.pojo.models.TodayItemsModel;
import com.example.bavaria.ui.acount.AccountActivity;
import com.example.bavaria.ui.home.HomeFragment;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.ContactsDatabase;
import com.example.bavaria.ui.roomContacts.ContactsRoom;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.roomContacts.productRoom.Products;
import com.example.bavaria.ui.roomContacts.typebill;
import com.example.bavaria.ui.slideshow.OnClic;
import com.example.bavaria.ui.utils.SharedPreferencesCom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    Dialog dialog;
    String barcode = "";
    OnClic onClic;
    HomeViewModel homeViewModel;


    //setItemsOnline
    typebill item = new typebill();

    public OnClic getOnClic() {
        return onClic;
    }

    public void setOnClic(OnClic onClic) {
        this.onClic = onClic;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        homeViewModel = new ViewModelProvider(MainActivity.this).get(HomeViewModel.class);
      //  Intent i=new Intent(MainActivity.this, AccountActivity.class);
      //  startActivity(i);
        SharedPreferencesCom.init(getApplicationContext());
        Toast.makeText(this, "here", Toast.LENGTH_SHORT).show();
        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.receiptstatus, R.id.sendsellsFragment, R.id.companyDataFragment, R.id.usersFragment, R.id.logoutFragment,
                R.id.sellsID, R.id.logoutFragment, R.id.clientsId, R.id.sellsFragment ,R.id.returnsFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        FloatingActionButton floatingActionButton = findViewById(R.id.addProductFAB);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String x="0";
                Double cc = Double.valueOf(SharedPreferencesCom.getInstance().getSharedItemFlag());

                Toast.makeText(MainActivity.this, SharedPreferencesCom.getInstance().getSharedItemFlag() + "--", Toast.LENGTH_SHORT).show();
                if (cc == 0) {
                    dialog = new Dialog(MainActivity.this);

                    // set custom dialog
                    dialog.setContentView(R.layout.chos_itemse);
                    //  dialog.setContentView(R.layout.chos_itemse);

                    // set custom height and width
                    dialog.getWindow().setLayout(700, 1200);

                    // set transparent background
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                    // show dialog
                    dialog.show();
                    // Button save =dialog.findViewById(R.id.saveBtn);
                    EditText nameProduct = dialog.findViewById(R.id.editText1);
                    EditText internalCode = dialog.findViewById(R.id.editText2);
                    EditText itemCode = dialog.findViewById(R.id.editText3);
                    EditText priceID = dialog.findViewById(R.id.priceID);


                    final AutoCompleteTextView customerAutoTV = dialog.findViewById(R.id.customerTextView);

                    // create list of customer
                    ArrayList<String> customerList = getCustomerList();

                    //Create adapter
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, customerList);

                    //Set adapter
                    customerAutoTV.setAdapter(adapter);

                    //submit button click event registration


                    // save.setOnClickListener(new View.OnClickListener() {
                    //     @Override
                    //     public void onClick(View view) {

                    //        // addItem()
                    //     }
                    // });
                    // initUI();

                    final AutoCompleteTextView customerAutoTV2 = dialog.findViewById(R.id.customerTextView2);

                    // create list of customer
                    ArrayList<String> customerList2 = getCustomerList2();

                    //Create adapter
                    ArrayAdapter<String> adapter2 = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, customerList2);

                    //Set adapter
                    customerAutoTV2.setAdapter(adapter2);

                    //submit button click event registration
                    dialog.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Products products = new Products();
                            products.setDescription(nameProduct.getText().toString());
                            products.setInternalCode(internalCode.getText().toString());
                            products.setUnitType(customerAutoTV2.getText().toString());
                            //customerTextView item typ
                            products.setItemCode(itemCode.getText().toString());

                            products.setUnitPrice(priceID.getText().toString());

                            //unit typ
                            products.setItemType(customerAutoTV.getText().toString());


                            Log.d("teste",
                                    nameProduct.getText().toString() +
                                            internalCode.getText().toString() +
                                            customerAutoTV2.getText().toString() +
                                            itemCode.getText().toString() +
                                            priceID.getText().toString() +
                                            customerAutoTV.getText().toString() + "");
                       // int state =1;
                            int state=   SharedPreferencesCom.getInstance().getFlagsItems().getItemFlag();
                            //addItemINLocalDB
                            if (state==1){
                            ItemsModel itemsModel = new ItemsModel();
                            itemsModel.setBarcode(itemCode.getText().toString());
                            itemsModel.setPrice(Double.parseDouble(priceID.getText().toString()));
                            itemsModel.setDescription(nameProduct.getText().toString());
                            itemsModel.setItemType( customerAutoTV.getText().toString());
                            itemsModel.setUnitType( customerAutoTV2.getText().toString());


                            homeViewModel.setOneItemOnline(itemsModel,MainActivity.this);
                            }
                          //  addItem(products);
                            //addItemToOnline
                             else if (state ==0){

                                AddItemModel addItemModel = new AddItemModel();
                                addItemModel.setBarcode(itemCode.getText().toString());
                                addItemModel.setPrice(Double.parseDouble(priceID.getText().toString()));
                                addItemModel.setDescription(nameProduct.getText().toString());
                                addItemModel.setItemName(nameProduct.getText().toString());
                                addItemModel.setAndroidID("1524");
                                addItemModel.setEditor("");
                                addItemModel.setItemType( customerAutoTV.getText().toString());
                              //  addItemModel.setUnitType( customerAutoTV2.getText().toString());
                                //customerTextView item typ
                                addItemModel.setItemCode(itemCode.getText().toString());
                                //unit typ
                                products.setItemType(customerAutoTV.getText().toString());
                                homeViewModel.AddItem(addItemModel);
                            }

                            Toast.makeText(MainActivity.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });


                } else {
                    Toast.makeText(MainActivity.this, "غير متاح", Toast.LENGTH_SHORT).show();


                }


            }


        });


    }
    // @Override
    // public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    //     switch(item.getItemId())
    //     {
    //         case R.id.action_settings:
    //             homeViewModel.getItems("3",getApplicationContext());
    //             break;
//
    //     }
    //     return true;
    // }


    // @Override
    // public boolean onCreateOptionsMenu(Menu menu) {
    //     // Inflate the menu; this adds items to the action bar if it is present.
    //     getMenuInflater().inflate(R.menu.main, menu);
    //     return true;
    // }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void addItem(Products i) {


        ContactsDatabase postsDataBass = ContactsDatabase.getGetInstance(getApplicationContext());
        //  postsDataBass.contactsDao().insertContacts()
        postsDataBass.productsDao().insertContacts(i)
                //   postsDataBass.contactsDao().insertContacts(post)


                .subscribeOn(computation())

                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        //  Log.d("yousiiiif","complete1");
                      //  Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(MainActivity.this, "Complete", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        //   Log.d("yousiiiif",e.getMessage().toString());
                        Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();

                    }
                });

    }

    public void getRoom() {
        ContactsDatabase contactsDatabase = ContactsDatabase.getGetInstance(this);
        contactsDatabase.typeBillDao1().gettypebill()
                //getlistItems("123")
                .subscribeOn(computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<typebill>>() {
                    @Override
                    public void onSubscribe(@androidx.annotation.NonNull Disposable d) {

                    }


                    @Override
                    public void onSuccess(@androidx.annotation.NonNull List<typebill> contactsRooms) {
                        // Log.d("yousiiiif",contactsRooms.get(1).getNumber().toString());

                        StringBuilder s = new StringBuilder();

                        for (int i = 0; i == contactsRooms.size(); i++) {

                            //     contactsRooms.get(i).getDescription();
                            //     contactsRooms.get(i).getInternalCode()  ;
                            //     s.append(contactsRooms.get(i).getItemCode()+""+
                            //             contactsRooms.get(i).getLastUUID() ) ;
                            // textView2.append( contactsRooms.get(0).getINTERNALCODE()+"__" +contactsRooms.get(0).getDESCRIPTION());
                        }
                        //  Log.d("yousiiiif" ,contactsRooms.get(1).getLastUUID()
                        //          +""+ contactsRooms.get(1).getDescription());

                        //  textView2.setText(s.toString());
                        //   contactUsers=contactsRooms;

                        //   adapter.setContactUserslist(contactsRooms);
                        //   //  for (ContactsRoom r:contactsRooms){
                        //   //      Log.i("Contact","name:"+r.getName()+"number"+r.getNumber()+"email"+r.getEmail());
                        //   //  }
                        //   // adapter.notifyDataSetChanged();
//
                        //   binding.contactsrecy.setAdapter(adapter);
                    }

                    @Override
                    public void onError(@androidx.annotation.NonNull Throwable e) {
                        Log.d("yousiiiif", "e.getMessage().toString()");

                    }
                });

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {

        if (e.getAction() == KeyEvent.ACTION_DOWN) {
            // getItem(e.toString().trim());
            //  homeViewModel.setQr("kk");
            //Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            //  Log.i("TAG", "dispatchKeyEvent: " + e.toString());
            char pressedKey = (char) e.getUnicodeChar();
            barcode += pressedKey;
        }
        if (e.getAction() == KeyEvent.ACTION_DOWN && e.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            // Toast.makeText(getApplicationContext(),
            //                 "barcode--->>>" + barcode, Toast.LENGTH_LONG)
            //         .show();
            // Toast.makeText(this, barcode.trim(), Toast.LENGTH_SHORT).show();
            //   textview.setText(barcode.trim());
            //  Log.d("TAG", barcode.trim());
            //  onClic.getQR(barcode);
            // Toast.makeText(this," QR", Toast.LENGTH_SHORT).show();
            homeViewModel.setQr(barcode);
            barcode = "";

            //String curText =  textview.getText();


        }

        return super.dispatchKeyEvent(e);
    }

    private void initUI() {
        //UI reference of textView
        final AutoCompleteTextView customerAutoTV = findViewById(R.id.customerTextView);

        // create list of customer
        ArrayList<String> customerList = getCustomerList();

        //Create adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item, customerList);

        //Set adapter
        customerAutoTV.setAdapter(adapter);

        //submit button click event registration
        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, customerAutoTV.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<String> getCustomerList() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("GS1");
        customers.add("GSC");

        return customers;
    }

    private ArrayList<String> getCustomerList2() {
        ArrayList<String> customers = new ArrayList<>();
        customers.add("KGM");
        customers.add("EA");

        return customers;
    }
}
