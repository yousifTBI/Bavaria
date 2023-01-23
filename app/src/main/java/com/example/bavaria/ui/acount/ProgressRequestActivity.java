package com.example.bavaria.ui.acount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.databinding.ActivityAccountBinding;
import com.example.bavaria.databinding.ActivityProgressRequestBinding;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.models.Task3;
import com.example.bavaria.ui.home.HomeViewModel;
import com.example.bavaria.ui.roomContacts.AccountInfo.LoginModel;
import com.example.bavaria.ui.utils.SharedPreferencesCom;

public class ProgressRequestActivity extends AppCompatActivity {
    AccountViewModel accountViewModel;
    ActivityProgressRequestBinding binding;
    HomeViewModel homeViewModel;
    String androidId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_request);
        accountViewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        binding = ActivityProgressRequestBinding.inflate(getLayoutInflater());

       androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
        SharedPreferencesCom.init(ProgressRequestActivity.this);

  //      String IDandroid=
                SharedPreferencesCom.getInstance().setSharedandroidId(androidId);
//
         //  Intent i=new Intent(ProgressRequestActivity.this,AccountActivity.class);
         //  startActivity(i);

        homeViewModel = new ViewModelProvider(ProgressRequestActivity.this).get(HomeViewModel.class);
        accountViewModel.LoginFun(androidId);
      //  homeViewModel.LoginFun(androidId, ProgressRequestActivity.this);

        accountViewModel.loginLiveData.observe(this, new Observer<StateData<Task3<LoginModel>>>() {
            @Override
            public void onChanged(StateData<Task3<LoginModel>> taskStateData) {
                switch (taskStateData.getStatus()) {
                    case SUCCESS:
                        Toast.makeText(ProgressRequestActivity.this, taskStateData.getData().getMessage(), Toast.LENGTH_SHORT).show();

                        if (taskStateData.getData().State == 0) {
                            //your Request is pending

                            Toast.makeText(ProgressRequestActivity.this, taskStateData.getData().getMessage(), Toast.LENGTH_SHORT).show();

                            // Intent intent = new Intent(ProgressRequestActivity.this, ProgressRequestActivity.class);
                           // startActivity(intent);
                           // finish();
                            binding.textView5.setText("your Request is pending");

                        } else if (taskStateData.getData().State == 1) {
                            //Successfully Login
                            Toast.makeText(ProgressRequestActivity.this, taskStateData.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            homeViewModel.LoginFun(androidId, ProgressRequestActivity.this);
                            homeViewModel.getItems("1", ProgressRequestActivity.this, androidId);

                            Intent intent = new Intent(ProgressRequestActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else if (taskStateData.getData().State == 2) {

                            //your Request has been rejected
                            Toast.makeText(ProgressRequestActivity.this, taskStateData.getData().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ProgressRequestActivity.this, AccountActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(taskStateData.getData().State == 3){
                            //your Machine has been suspended

                            binding.textView5.setText("your Machine has been suspended");

                            Toast.makeText(ProgressRequestActivity.this, taskStateData.getData().getMessage(), Toast.LENGTH_SHORT).show();


                        }

                        else if (taskStateData.getData().State == 4) {
                            //go to registeration first
                            Intent intent = new Intent(ProgressRequestActivity.this, AccountActivity.class);
                            startActivity(intent);
                            finish();
                        }


                        break;
                    case ERROR:


                     //   Log.d("onSuccess",
//
                     //           "" + taskStateData.getError().getMessage().toString() + "" +
                     //                   taskStateData.getError().fillInStackTrace().toString() + "" +
                     //                   taskStateData.getError().getCause().toString() + "" +
                     //                   taskStateData.getError().getSuppressed().toString()
//
                     //   );

                        binding.spinKit.setVisibility(View.GONE);
                        Dialog dialog=new Dialog(ProgressRequestActivity.this);

                        // set custom dialog
                        dialog.setContentView(R.layout.diolog_error);

                        // set custom height and width
                        dialog.getWindow().setLayout(600,900);

                        // set transparent background
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // show dialog

                        dialog.show();
                        Button button=dialog.findViewById(R.id.button);
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                accountViewModel.LoginFun(androidId);
                                dialog.dismiss();
                            }
                        });

                        break;
                    case LOADING:
                        // binding.progressBar;
                        binding.spinKit.setVisibility(View.VISIBLE);
                        //  binding.progressBar.setVisibility(View.GONE);

                        break;
                    case COMPLETE:
                        binding.spinKit.setVisibility(View.GONE);

                        break;
                    case Problem:
                        binding.spinKit.setVisibility(View.GONE);
                        Toast.makeText(ProgressRequestActivity.this, "لا يوجد انترنت", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ProgressRequestActivity.this, "لا يوجد انترنت", Toast.LENGTH_SHORT).show();
                        Toast.makeText(ProgressRequestActivity.this, "لا يوجد انترنت", Toast.LENGTH_SHORT).show();
                        Dialog dialogz=new Dialog(ProgressRequestActivity.this);

                        // set custom dialog
                        dialogz.setContentView(R.layout.diolog_error);

                        // set custom height and width
                        dialogz.getWindow().setLayout(600,800);

                        // set transparent background
                        dialogz.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        // show dialog
                        dialogz.show();
                        Button button1=dialogz.findViewById(R.id.button);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                accountViewModel.LoginFun(androidId);
                                dialogz.dismiss();
                            }
                        });
                        //  Toast.makeText(AccountActivity.this, "لاااااا يوجد انترنت", Toast.LENGTH_SHORT).show();

                        break;

                }
            }
        });

    }
}