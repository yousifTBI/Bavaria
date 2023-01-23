package com.example.bavaria.ui.returns;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentReturnsBinding;
import com.example.bavaria.databinding.FragmentSellsBinding;
import com.example.bavaria.ui.home.HomeViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ReturnsFragment extends Fragment {
    FragmentReturnsBinding binding;
    ReturnsViewModel returnsViewModel;
    private String startDate = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =FragmentReturnsBinding.inflate(inflater, container, false);
        returnsViewModel = new ViewModelProvider(getActivity()).get(ReturnsViewModel.class);
      //  returnsViewModel.getRoot("46",getContext());
        returnsViewModel.getBillRoot("46",getContext());

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

        return binding.getRoot();
    }

    private boolean isValid() {
        if (startDate.isEmpty() || startDate.equals(null) || startDate == "") {
            // enter start date message
            Toast.makeText(getContext(), "ادخل تاريخ البداية بطريقة صحيحة", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!startDate.isEmpty() ) {
            // success
            return true;
        }
        return false;
    }
}