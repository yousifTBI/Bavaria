package com.example.bavaria;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bavaria.databinding.FragmentCompanyDataBinding;
import com.example.bavaria.databinding.FragmentSlideshowBinding;
import com.example.bavaria.pojo.classes.BranchAddress;
import com.example.bavaria.pojo.classes.Seller;
import com.example.bavaria.ui.utils.SharedPreferencesCom;


public class CompanyDataFragment extends Fragment {

    FragmentCompanyDataBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCompanyDataBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        Toast.makeText(getActivity(), "asa", Toast.LENGTH_SHORT).show();
        // return inflater.inflate(R.layout.fragment_company_data, container, false);
        Seller seller = SharedPreferencesCom.getInstance().getSharedValuesSeller();
        BranchAddress branchAddress = SharedPreferencesCom.getInstance().getSharedValuesBranchAddress();


        branchAddress.getCountry();
        branchAddress.getGovernate();
        branchAddress.getRegionCity();
        branchAddress.getStreet();
        branchAddress.getBuildingNumber();
        binding.com.setText("   "+seller.getCompanyTradeName());
        binding.numOfRecord.setText("   "+seller.getRin());
        binding.branchcode.setText("   "+seller.getBranchCode());
        binding.BuildingNumber.setText("   "+branchAddress.getBuildingNumber());
//        binding.editText2.setText("   "+seller.getActivityCode());


     //  seller.getRin(),
     //          seller.getCompanyTradeName(),
     //          seller.getBranchCode(),

     //          seller.getDeviceSerialNumber(),
     //          seller.getSyndicateLicenseNumber(),
     //          seller.getActivityCode()

        return binding.getRoot();
    }
}