package com.example.bavaria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.bavaria.databinding.FragmentAddProductBinding;
import com.example.bavaria.databinding.FragmentHomeBinding;
import com.example.bavaria.pojo.models.AddItemModel;
import com.example.bavaria.pojo.models.EditItemModel;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

public class addProductFragment extends Fragment {
    private FragmentAddProductBinding binding;
    HomeViewModel homeViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        homeViewModel = new ViewModelProvider(getActivity()).get(HomeViewModel.class);
        EditText comId = binding.editText1;
        EditText Barcode = binding.editText2;
        EditText priceID = binding.editText3;

        binding.submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditItemModel editItemModel = new EditItemModel();
                editItemModel.setComId(Integer.parseInt(comId.getText().toString()));
                editItemModel.setNewPrice(Double.parseDouble(priceID.getText().toString()));
                editItemModel.setBarCode(Barcode.getText().toString());
                editItemModel.setAndroidID("1524");
                homeViewModel.EditItem(editItemModel);


            }
        });




        return root;
    }
}