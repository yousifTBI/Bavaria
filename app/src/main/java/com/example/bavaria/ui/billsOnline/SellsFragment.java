package com.example.bavaria.ui.billsOnline;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentHomeBinding;
import com.example.bavaria.databinding.FragmentSellsBinding;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.models.EditItemModel;
import com.example.bavaria.ui.acount.AccountActivity;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.List;


public class SellsFragment extends Fragment implements OnClickEditProduct{

    FragmentSellsBinding binding;
    BillsOnlineViewModel ViewModel;
    HomeViewModel homeViewModel;
    Dialog dialog;
    EditItemModel editItemModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSellsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
       ViewModel = new ViewModelProvider(this).get(BillsOnlineViewModel.class);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);




        // View root = binding.getRoot();
        // Inflate the layout for this fragment
      //  Toast.makeText(getContext(), "khfd", Toast.LENGTH_SHORT).show();
        AdabterItems adabterItems=new AdabterItems(getContext());
        ViewModel.setOneItemOnline(getContext());
        ViewModel.itemsModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<List<ItemsModel>>() {
            @Override
            public void onChanged(List<ItemsModel> itemsModels) {
                adabterItems.setItemsModels(itemsModels);
                binding.recyclerView.setAdapter(adabterItems);
            }
        });
        adabterItems.setOnClickItem(this);
        //  return inflater.inflate(R.layout.fragment_sells, container, false);
        return binding.getRoot();
    }



    @Override
    public void OnClickItem(int Position, int AdaptrPodition, ItemsModel itemsModel) {
        dialog = new Dialog(getContext());
       // biprogressBar3.setVisibility(View.GONE);


        // set custom dialog
        dialog.setContentView(R.layout.edit_dialog);

        // set custom height and width
        dialog.getWindow().setLayout(900, 1400);

        // set transparent background
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // show dialog
        dialog.show();

        EditText nameProduct = dialog.findViewById(R.id.productName);
        EditText internalCode = dialog.findViewById(R.id.Barcode);
        internalCode.setEnabled(false);
        EditText priceID = dialog.findViewById(R.id.price);
        ProgressBar progressBar= dialog.findViewById(R.id.progressBar3);
        progressBar    .setVisibility(View.GONE);
       nameProduct.setText(itemsModel.getItemName());
       internalCode.setText(itemsModel.getBarcode());

        priceID.setText(itemsModel.getPrice());


        Button submitButton =dialog.findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                editItemModel =new EditItemModel();
                editItemModel.setAndroidID("1524");
                editItemModel.setBarCode(internalCode.getText().toString());
                editItemModel.setNewPrice(Double.parseDouble(priceID.getText().toString()));
                editItemModel.setComId(3);
                homeViewModel.EditItem(editItemModel);

                //Update Price in local data ...
                ItemsModel itemsModel1 =new ItemsModel();
                itemsModel.setPrice(Double.parseDouble(priceID.getText().toString()));


                homeViewModel.getItemstate.observe(getActivity(), new Observer<StateData<String>>() {
                    @Override
                    public void onChanged(StateData<String> stringStateData) {
                        switch (stringStateData.getStatus()) {
                            case SUCCESS:
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "SUCCESS" , Toast.LENGTH_SHORT).show();

                                break;
                            case ERROR:
                                Toast.makeText(getActivity(), "ERROR" , Toast.LENGTH_SHORT).show();

                                break;
                            case LOADING:
                                progressBar.setVisibility(View.VISIBLE);
                                break;
                            case COMPLETE:
                                progressBar.setVisibility(View.GONE);
                                break;
                        }
                    }
                });

            }
        });
    }
}