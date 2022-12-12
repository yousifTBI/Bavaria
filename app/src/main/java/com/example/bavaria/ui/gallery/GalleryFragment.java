package com.example.bavaria.ui.gallery;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.databinding.FragmentGalleryBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DecimalFormat;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    Dialog dialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

       /* final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
       */
  //  FloatingActionButton floatingActionButton=root.findViewById(R.id.addProductFAB);
  //  floatingActionButton.setOnClickListener(new View.OnClickListener() {
  //      @Override
  //      public void onClick(View view) {
  //          dialog = new Dialog(getContext());

  //          // set custom dialog
  //          dialog.setContentView(R.layout.product_item);
  //          //  dialog.setContentView(R.layout.chos_itemse);

  //          // set custom height and width
  //          dialog.getWindow().setLayout(800, 1300);

  //          // set transparent background
  //          dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

  //          // show dialog
  //          dialog.show();
  //      }
  //  });
//        double number = 0.9999999999999;
//        DecimalFormat numberFormat = new DecimalFormat("#.00");
//        numberFormat.format(number);
       // System.out.println(numberFormat.format(number));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}