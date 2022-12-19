package com.example.bavaria.ui.billsOnline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentHomeBinding;
import com.example.bavaria.databinding.FragmentSellsBinding;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.List;


public class SellsFragment extends Fragment {

    FragmentSellsBinding binding;
    BillsOnlineViewModel ViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSellsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
       ViewModel = new ViewModelProvider(this).get(BillsOnlineViewModel.class);

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

        //  return inflater.inflate(R.layout.fragment_sells, container, false);
        return binding.getRoot();
    }
}