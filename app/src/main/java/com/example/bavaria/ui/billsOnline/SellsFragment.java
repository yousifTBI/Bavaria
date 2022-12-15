package com.example.bavaria.ui.billsOnline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentHomeBinding;
import com.example.bavaria.databinding.FragmentSellsBinding;


public class SellsFragment extends Fragment {

    FragmentSellsBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSellsBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
       // View root = binding.getRoot();
        // Inflate the layout for this fragment
      //  return inflater.inflate(R.layout.fragment_sells, container, false);
        return binding.getRoot();
    }
}