package com.example.bavaria.ui.returns;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentReturnsBinding;
import com.example.bavaria.databinding.FragmentSellsBinding;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.testModels.ItemsModels;
import com.example.bavaria.ui.home.AdabterInvoice;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.roomContacts.returnsBill.HeaderReturn;
import com.example.bavaria.ui.utils.SharedPreferencesBillStatu;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReturnsFragment extends Fragment {
    public FragmentReturnsBinding binding;
    ReturnsViewModel returnsViewModel;
    AdabterInvoice adabter;
    AdabterInvoice adabterReturn;
    List<ItemsModels> itemsModelsk;
    List<ItemsModels> itemsReturn;
    private String startDate = "";
    private String stringUUID = "";
    HomeViewModel homeViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentReturnsBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);

        //  binding

        returnsViewModel = new ViewModelProvider(getActivity()).get(ReturnsViewModel.class);
        //  returnsViewModel.getRoot("46",getContext());
        binding.setLifecycleOwner(this);
        adabter = new AdabterInvoice(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        binding.billRecycler.setLayoutManager(linearLayoutManager);
        adabterReturn = new AdabterInvoice(getContext());
        itemsReturn = new ArrayList<>();
        itemsModelsk = new ArrayList<>();

        //    returnsViewModel.getBillRoot("46",getContext());
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                itemsReturn.clear();
                itemsModelsk.clear();
                adabterReturn.notifyDataSetChanged();
                adabter.notifyDataSetChanged();

                returnsViewModel.getRoot(binding.com.getText().toString(), getContext());

            }
        });
        returnsViewModel.stringUUID.observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                stringUUID=s;
            }
        });

        returnsViewModel.itemsList1.observe(getActivity(), new Observer<List<ItemsModels>>() {
            @Override
            public void onChanged(List<ItemsModels> itemsModels) {


                itemsModelsk = itemsModels;
                Log.d("onSuccessReturns", itemsModels.get(0).getBarcode() + "==");

                adabter.setList(itemsModelsk);
                binding.billRecycler.setAdapter(adabter);


                new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                        //  Toast.makeText(ItemsActivity.this, "Swipe to delete", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        Snackbar.make(binding.billRecycler, "Deleted item", Snackbar.LENGTH_SHORT).setAction("تم المسح", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(getContext(), itemsModelsk.get(viewHolder.getAdapterPosition()).getBarcode(), Toast.LENGTH_SHORT).show();
                                //  itemsModelsk.get(viewHolder.getAdapterPosition()).getBarcode()

                            }
                        }).show();
                        itemsReturn.add(itemsModelsk.get(viewHolder.getAdapterPosition()));
                        Toast.makeText(getContext(), itemsModelsk.get(viewHolder.getAdapterPosition()).getBarcode(), Toast.LENGTH_SHORT).show();
                        adabterReturn.setList(itemsReturn);
                        binding.returnRecycler.setAdapter(adabterReturn);

                        itemsModelsk.remove(viewHolder.getAdapterPosition());
                        adabter.notifyDataSetChanged();

                        adabter.notifyItemInserted(itemsModelsk.size() - 1);
                        binding.billRecycler.scrollToPosition(itemsModelsk.size());
                        binding.billRecycler.setAdapter(adabter);

                    }
                }).attachToRecyclerView(binding.billRecycler);
                //     if (itemsModels.isEmpty()&&itemsModels==null){}else {
                //         adabter.setList(itemsModels);

                //     }
                //  //   adabter.setList(itemsModels);
                //     //  adabter.setOnClic(this);

                //     binding.billRecycler.setAdapter(adabter);
            }
        });


        //Recycler return remove
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                //  Toast.makeText(ItemsActivity.this, "Swipe to delete", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Snackbar.make(binding.billRecycler, "Deleted item", Snackbar.LENGTH_SHORT).setAction("تم المسح", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(getContext(), itemsModelsk.get(viewHolder.getAdapterPosition()).getBarcode(), Toast.LENGTH_SHORT).show();
                        //  itemsModelsk.get(viewHolder.getAdapterPosition()).getBarcode()

                    }
                }).show();
                itemsModelsk.add(itemsReturn.get(viewHolder.getAdapterPosition()));
            //    itemsModelsk
               // Toast.makeText(getContext(), itemsModelsk.get(viewHolder.getAdapterPosition()).getBarcode(), Toast.LENGTH_SHORT).show();

                adabter.setList(itemsModelsk);
                binding.billRecycler.setAdapter(adabter);

                itemsReturn.remove(viewHolder.getAdapterPosition());
                adabterReturn.notifyDataSetChanged();
                adabter.notifyDataSetChanged();

          //     adabter.notifyItemInserted(itemsModelsk.size() - 1);
          //     binding.billRecycler.scrollToPosition(itemsModelsk.size());
          //     binding.billRecycler.setAdapter(adabter);

            }
        }).attachToRecyclerView(binding.returnRecycler);

        //  binding.startDateCard.setOnClickListener(view -> {
        //      TimePickerFragment startDateDialog = new TimePickerFragment(date -> {
        //          this.startDate = date;
        //          SimpleDateFormat simpleFormatter = new SimpleDateFormat("yyyy-MM-dd");
        //          try {
        //              simpleFormatter.parse(date);

        //          } catch (ParseException e) {
        //              e.printStackTrace();
        //          }
        //          binding.startDateTV.setText(date);
        //      });
        //      startDateDialog.show(getActivity().getSupportFragmentManager(), "startDateDialog");
        //  });

        //  binding.reportbtn.setOnClickListener(view -> {
        //      // Toast.makeText(this, startDate + " / " + endDate, Toast.LENGTH_SHORT).show();
        //      if (isValid()) {
        //         // getReturnReport(startDate, endDate);

        //      } else {
        //          // not valid
        //          Toast.makeText(getContext(), "Not Valid", Toast.LENGTH_SHORT).show();
        //      }
        //  });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                int state=0;
                if (state == 0) {

                 //   String bill = setpill();

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
                    for (ItemsModels i : itemsReturn) {


                        //To Create List to UUID
                        itemData.add(homeViewModel.getItems(
                                Double.valueOf(i.getQuantity()),
                                Double.valueOf(i.getPrice()),
                                Double.valueOf(i.getPrice()),
                                i.getDescription()
                                ,i.getBarcode()
                                ,i.getUnitType()
                                ,i.getItemType()
                                , String.valueOf( i.getItemCode())

                        ));

                        //To Create List to Room
                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getDescription(),
                                Double.valueOf(i.getPrice()),
                                numberRicet, String.valueOf(itemId), Double.valueOf(i.getQuantity())
                                ,i.getUnitType()
                                ,i.getItemType()
                                , String.valueOf( i.getItemCode()),i.getBarcode()

                        ));


                        price += Double.valueOf(i.getPrice());
                        Tax = (price * 14.0) / 100;
                        totalPrice = price + Tax;
                        itemId++;


                        Log.d("onSuccesss", price+"");
                    }


                    Log.d("onSuccess", price + "");
                    Log.d("onSuccess", Tax + "");
                    Log.d("onSuccess", totalPrice + "");


                    //To Create UUID
                    String uu = returnsViewModel.CreateUUIDReturns(numberRicet, "", TimeRicet, itemData,stringUUID);
                 //   Log.d("onSuccess", uu);
                 //   String QR = " https://preprod.invoicing.eta.gov.eg/receipts/search/" + uu;


                    //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
                    //To Create New Bill in Room db

                    HeaderReturn headerBill = new HeaderReturn();
                     headerBill.setUUID(uu);
                    headerBill.setBillNumber(numberRicet);
                     headerBill.setInvoiceDate(TimeRicet);
                    headerBill.setTax(Tax);
                    headerBill.setNetPrice(price);
                    headerBill.setTotalPrice(totalPrice);
                  //  headerBill.setLink(QR);

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
                 //   models.clear();
                    //  CastlesPrinter(QR);
                }
            }
        });
        return binding.getRoot();
    }

    private boolean isValid() {
        if (startDate.isEmpty() || startDate.equals(null) || startDate == "") {
            // enter start date message
            Toast.makeText(getContext(), "ادخل تاريخ البداية بطريقة صحيحة", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!startDate.isEmpty()) {
            // success
            return true;
        }
        return false;
    }
}