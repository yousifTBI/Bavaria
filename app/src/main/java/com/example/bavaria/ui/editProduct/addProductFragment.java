package com.example.bavaria.ui.editProduct;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bavaria.databinding.FragmentAddProductBinding;

import com.example.bavaria.pojo.models.AddItemModel;
import com.example.bavaria.pojo.models.EditItemModel;
import com.example.bavaria.ui.billsOnline.AdabterItems;
import com.example.bavaria.ui.home.AdabterInvoice;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.slideshow.OnClic;

import java.util.ArrayList;

public class addProductFragment extends Fragment {
    private FragmentAddProductBinding binding;
    HomeViewModel homeViewModel;
    ArrayList<ItemsModel> itemsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
//        AdapterEdit adapterEdit=new AdapterEdit(getContext());
//        itemsList = new ArrayList<>();
//        adapterEdit.setItemsModels(itemsList);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//        binding.recyclerView.setLayoutManager(linearLayoutManager);
//        binding.recyclerView.setAdapter(adapterEdit);



        return root;
    }
}