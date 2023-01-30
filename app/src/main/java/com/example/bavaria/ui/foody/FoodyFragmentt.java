package com.example.bavaria.ui.foody;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.bavaria.databinding.FragmentFoodyFragmenttBinding;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.testModels.ItemsModels;
import com.example.bavaria.ui.home.AdabterInvoice;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.backup.HeaderBackup;
import com.example.bavaria.ui.roomContacts.backup.ItemsBackup;
import com.example.bavaria.ui.roomContacts.onlineBill.HeaderBillOnline;
import com.example.bavaria.ui.roomContacts.onlineBill.ItemsBillOnlin;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;
import com.example.bavaria.ui.utils.SharedPreferencesBillStatu;
import com.google.android.material.snackbar.Snackbar;
import java.util.ArrayList;
import java.util.List;

public class FoodyFragmentt extends Fragment{
    //implements OnClickAdapterItem {
    private FragmentFoodyFragmenttBinding binding;
    HomeViewModel homeViewModel;
    AdabterFoodyItems adabter;
    List<ItemsModels> models;
    AdapterCategry adapterCategry;
    AdabterInvoice adabterInvoice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentFoodyFragmenttBinding.inflate(inflater, container, false);

//        ArrayList<ItemsModel> list=new ArrayList<>();
//
//        adabter=new AdabterFoodyItems(getContext());
//        adabter.setOnClickAdapterItem(this);
//        adapterCategry =new AdapterCategry(getContext());
//        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
//      //  adabter.setList(homeViewModel.getItemsModels1());
//
//        LinearLayoutManager layoutManager =
//                new GridLayoutManager(getActivity(), 2, GridLayoutManager.HORIZONTAL, false);
//        binding.offers.setLayoutManager(layoutManager);
//        binding.offers.setAdapter(adabter);
//       // Log.d("log",homeViewModel.getItemsModels1().get(2)+"");
//
//        models = new ArrayList<>();
//        adabterInvoice = new AdabterInvoice(getContext());
//
//        SharedPreferencesBillStatu.init(getContext());
//
//        int state = 0;
//        List<ItemsModel> modelList=new ArrayList<>();
//        binding.payBtn.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) {
//                binding.progressBar2.setVisibility(View.VISIBLE);
//                if (models.isEmpty()) {
//
//                    binding.progressBar2.setVisibility(View.GONE);
//                    Toast.makeText(getActivity(), "الفاتوره فارغه", Toast.LENGTH_SHORT).show();
//
//                } else if (state == 0) {
//
//                  //  String bill = setpill();
//
//                    //To Create number Ricet Bill
//                    //   String numberRicet = homeViewModel.getNumberBill(getContext());
//                    String numberRicet =  SharedPreferencesBillStatu.getInstance().getNumberOFBill();
//
//
//                    //To Create Date Time Bill
//                    String TimeRicet = homeViewModel.getTimeBill();
//
//                    ArrayList<ItemDatum> itemData = new ArrayList<>();
//                    ArrayList<ItemsBill> ItemsBillRoom = new ArrayList<>();
//                    ArrayList<ItemsBackup> ItemsBillRoomBackup = new ArrayList<>();
//
//
//                    Double price = 0.0;
//                    Double Tax = 0.0;
//                    Double totalPrice = 0.0;
//                    int itemId = 0;
//                    for (ItemsModels i : models) {
//                        //To Create List to UUID
//                        itemData.add(homeViewModel.getItems(1.0, Double.valueOf(i.getPrice()), Double.valueOf(i.getPrice()), i.getDescription()));
//
//                        //To Create List to Room
//                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getDescription(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));
//
//                        //To Create List to Room Backup
//                        ItemsBillRoomBackup.add(homeViewModel.setItemsRoomBackup(i.getDescription(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));
//
//                        // Tax += i.getTax();
//                        // price += i.getprice();
//                        // totalPrice += i.getTotal();
//
//                        price += Double.valueOf(i.getPrice());
//                        Tax = (price * 14.0) / 100;
//                        totalPrice = price + Tax;
//                        itemId++;
//
//
//                        // Log.d("onSuccess", price+"");
//                    }
//
//
//                    Log.d("onSuccess", price + "");
//                    Log.d("onSuccess", Tax + "");
//                    Log.d("onSuccess", totalPrice + "");
//
//
//                    //To Create UUID
//                    String uu = homeViewModel.CreateUUID(numberRicet, "", TimeRicet, itemData);
//                    Log.d("onSuccess", uu);
//                    String QR = " https://preprod.invoicing.eta.gov.eg/receipts/search/" + uu;
//
//
//                    //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
//                    //To Create New Bill in Room db
//
//                    HeaderBill headerBill = new HeaderBill();
//                    headerBill.setUUID(uu);
//                    headerBill.setBillNumber(numberRicet);
//                    headerBill.setInvoiceDate(TimeRicet);
//                    headerBill.setTax(Tax);
//                    headerBill.setNetPrice(price);
//                    headerBill.setTotalPrice(totalPrice);
//                    headerBill.setLink(QR);
//
//                    //To Insert New Bill in Room db
//                    homeViewModel.headBill(headerBill, ItemsBillRoom, getActivity());
//
//                    // Root r = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
//
//                    // Log.d("onSuccess",g.toJson(r));
//                    // Send(r);
//                    //     printp(QR,"android.binder.printer");
//                    // String bill = setpill();
//                    //    ssss();
//                    // setPrintBT(bill, QR);
//                    // printp(QR, "android.binder.printer");
//
//                    //To Create online Bill
//                    //  Root createRoot = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
//                    //  homeViewModel.Send(createRoot, HeaderOnline, ItemsBillRoomOnlin, getActivity());
//                    models.clear();
//                    //  CastlesPrinter(QR);
//                } else if (state == 1) {
//                    //To Create number Ricet Bill
//                    String numberRicet = homeViewModel.getNumberBill(getContext());
//                    //To Create Date Time Bill
//                    String TimeRicet = homeViewModel.getTimeBill();
//
//                    ArrayList<ItemDatum> itemData = new ArrayList<>();
//                    ArrayList<ItemsBill> ItemsBillRoom = new ArrayList<>();
//                    ArrayList<ItemsBackup> ItemsBillRoomBackup = new ArrayList<>();
//                    ArrayList<ItemsBillOnlin> ItemsBillRoomOnlin = new ArrayList<>();
//
//
//                    Double price = 0.0;
//                    Double Tax = 0.0;
//                    Double totalPrice = 0.0;
//                    int itemId = 0;
//                    for (ItemsModel i : modelList) {
//
//                        //To Create List to UUID
//                        itemData.add(homeViewModel.getItems(1.0, Double.valueOf(i.getPrice()), Double.valueOf(i.getPrice()), i.getTitle()));
//
//                        //To Create List to Room
//                        ItemsBillRoom.add(homeViewModel.setItemsRoom(i.getTitle(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));
//
//                        //To Create List to Room Backup
//                        ItemsBillRoomBackup.add(homeViewModel.setItemsRoomBackup(i.getTitle(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));
//
//                        ItemsBillRoomOnlin.add(homeViewModel.setItemsRoomOnline(i.getTitle(), Double.valueOf(i.getPrice()), numberRicet, String.valueOf(itemId)));
//
//                        price += Double.valueOf(i.getPrice());
//                        Tax = (price * 14.0) / 100;
//                        totalPrice = price + Tax;
//                        // Tax += i.getTax();
//                        // price +=Double.valueOf( i. getPrice());
//                        // totalPrice += i.getTotal();
//
//                        itemId++;
//
//                        // Log.d("onSuccess", price+"");
//                    }
//
//
//                    Log.d("onSuccess", price + "");
//                    Log.d("onSuccess", Tax + "");
//                    Log.d("onSuccess", totalPrice + "");
//
//
//                    //To Create UUID
//                    String uu = homeViewModel.CreateUUID(numberRicet, "", TimeRicet, itemData);
//                    Log.d("onSuccess", uu);
//                    String sss = " https://preprod.invoicing.eta.gov.eg/receipts/search/" + uu;
//
//
//                    //    String url ="https://preprod.invoicing.eta.gov.eg/receipts/search/a700243730510ebd8499d9d895ad7eb4ee4ba9d22b3bf155d81552f7e31dd93d";
//
//
//                    HeaderBackup HeaderBackup = homeViewModel.getHeaderBackup(uu, numberRicet, TimeRicet, Tax, price, totalPrice, sss);
//                    HeaderBillOnline HeaderOnline = homeViewModel.getHeaderOnline(uu, numberRicet, TimeRicet, Tax, price, totalPrice, sss);
//                    //To Insert New Bill in Room db
//                    //   homeViewModel.headBill(headerBill, ItemsBillRoom, getActivity());
//                    homeViewModel.headBillRoomBackup(HeaderBackup, ItemsBillRoomBackup, getActivity());
//
//                    //  homeViewModel.headBillRoomBackup()
//                    Root createRoot = homeViewModel.sentApi(uu, itemData, TimeRicet, numberRicet);
//                    homeViewModel.Send(createRoot, HeaderOnline, ItemsBillRoomOnlin, getActivity());
//
//
//                    // Log.d("onSuccess",g.toJson(r));
//
//                    //  printp(sss,"Printer001");
//                    //   printp(sss, "Saturn1000");
//
//                }
//            }
//        });
//        //  adabter.setList(itemsList);
//      //  adabterInvoice.setList(models);
//        //  adabter.setOnClic(this);
//         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//         binding.categories.setLayoutManager(linearLayoutManager);
//         binding.categories.setAdapter(adabterInvoice);
//
//        adabterInvoice.setList(models);
//
//
//
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@androidx.annotation.NonNull RecyclerView recyclerView, @androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder,
//                                  @androidx.annotation.NonNull RecyclerView.ViewHolder target) {
//                //  Toast.makeText(ItemsActivity.this, "Swipe to delete", Toast.LENGTH_SHORT).show();
//                return false;
//            }
//
//            @Override
//            public void onSwiped(@androidx.annotation.NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                Snackbar.make(binding.categories, "Deleted item", Snackbar.LENGTH_SHORT).setAction("تم المسح", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }).show();
//                models.remove(viewHolder.getAdapterPosition());
//                adabter.notifyDataSetChanged();
//
//                adabter.notifyItemInserted( models.size() - 1);
//                binding.categories.scrollToPosition( models.size());
//                binding.categories.setAdapter(adabterInvoice);
//
//            }
//        }).attachToRecyclerView(binding.categories);
//
//
//
        return binding.getRoot();
//    }
//
//    @Override
//    public void Onclick(ItemsModel item) {
//        ItemsModels products = new ItemsModels();
//        products.setDescription(item.getDescription());
//        products.setItemName(item.getDescription());
//        products.setItemType(item.getItemType());
//        products.setBarcode(item.getBarcode());
//        products.setPrice(Double.parseDouble(item.getPrice()));
//        models.add(products);
//        Toast.makeText(getContext(), item.getDescription(), Toast.LENGTH_SHORT).show();
//
//        adabterInvoice.notifyDataSetChanged();
    }
}