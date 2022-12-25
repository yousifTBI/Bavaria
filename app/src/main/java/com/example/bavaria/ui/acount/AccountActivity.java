package com.example.bavaria.ui.acount;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.bavaria.databinding.ActivityAccountBinding;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.models.BranchModel;
import com.example.bavaria.pojo.models.CompanyModel;
import com.example.bavaria.pojo.models.RequestModel;
import com.example.bavaria.pojo.models.Task;

import java.util.List;

public class AccountActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityAccountBinding binding;
    RequestModel requestModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

       BranchModel branchModel=new BranchModel();



//        SharedPreferences   sharedPreferenclanguageg = getSharedPreferences("NumberBill", MODE_PRIVATE);
//        SharedPreferences.Editor editsg = sharedPreferenclanguageg.edit();
//        String Number = sharedPreferenclanguageg.getString("NumberOFBill", null);
//        binding.textView12.setText(Number);
//
//        binding.SendRequestId.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //toGet
//                String Number = sharedPreferenclanguageg.getString("NumberOFBill", "900");
//                Toast.makeText(AccountActivity.this, Number, Toast.LENGTH_SHORT).show();
//                int newNumber=Integer.valueOf(Number)+1;
//                //ToPost
//                editsg.putString("NumberOFBill",String.valueOf(newNumber));
//                editsg.apply();
//            }
//        });


        binding.progressBar.setVisibility(View.VISIBLE);
        AccountViewModel accountViewModel= new ViewModelProvider(this).get(AccountViewModel.class);
       accountViewModel.getCom();


      // String x ="1524";
     //   HashMap <String,String> x =new HashMap<>();
     //   x.put("x" ,"1524");
     // accountViewModel.LoginFun(x);
      //  accountViewModel.getItems("3");
       accountViewModel.ComLiveData.observe(this, new Observer<String>() {
           @Override
           public void onChanged(String s) {
               if (s==null){}else { binding.textView20.setText(s);}


           }
       });

       accountViewModel.BranchLiveData.observe(this, new Observer<BranchModel>() {
           @Override
           public void onChanged(BranchModel s) {
               if (s==null){}else {
                   binding.textView21.setText(s.Name);
                   binding. textView12.setText(s.getID());
                  // Log.d("account",s.getID());
               }

           }
       });


        accountViewModel.stateBranchLiveData.observe(this, new Observer<StateData<String>>() {
            @Override
            public void onChanged(StateData<String> stringStateData) {
                switch (stringStateData.getStatus()) {
                    case SUCCESS:
                        //      listStateData
                        Toast.makeText(AccountActivity.this, stringStateData.getData(), Toast.LENGTH_SHORT).show();
                        binding.progressBar.setVisibility(View.GONE);

                        break;
                    case ERROR:

                        break;
                    case LOADING:
                        // binding.progressBar;
                        binding.progressBar.setVisibility(View.VISIBLE);


                        break;
                    case COMPLETE:
                        binding.progressBar.setVisibility(View.GONE);

                        break;
                }
            }
        });
        accountViewModel.RequestLiveData.observe(AccountActivity.this, new Observer<StateData<Task<RequestModel>>>() {
            @Override
            public void onChanged(StateData<Task<RequestModel>> taskStateData) {
                switch (taskStateData.getStatus()) {
                    case SUCCESS:
                        //      listStateData
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(AccountActivity.this, taskStateData.getData().Message+"SUCCESS" , Toast.LENGTH_SHORT).show();
                        break;
                    case ERROR:
                        Toast.makeText(AccountActivity.this, taskStateData.getData().Message+"ERROR" , Toast.LENGTH_SHORT).show();

                        break;
                    case LOADING:
                        // binding.progressBar;
                        binding.progressBar.setVisibility(View.VISIBLE);


                        break;
                    case COMPLETE:
                        binding.progressBar.setVisibility(View.GONE);

                        break;
                }
            }
        });
        accountViewModel.stateCompanyLiveData.observe(AccountActivity.this, new Observer<StateData<List<CompanyModel>>>() {
            @Override
            public void onChanged(StateData<List<CompanyModel>> listStateData) {

                switch (listStateData.getStatus()) {
                       case SUCCESS:
                     //      listStateData
                           binding.progressBar.setVisibility(View.GONE);

                           break;
                       case ERROR:

                           break;
                    case LOADING:
                       // binding.progressBar;
                        binding.progressBar.setVisibility(View.VISIBLE);


                        break;
                    case COMPLETE:
                        binding.progressBar.setVisibility(View.GONE);

                         break;
                }
            }
        });

        binding.cardView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            accountViewModel.provideSimpleDialog(AccountActivity.this);
           // binding.textView20.setText(accountViewModel.getArrayList().get().getName());

            }
        });

        binding.cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            accountViewModel.provideBranchSimpleDialog(AccountActivity.this);

            }
        });


        binding.SendRequestId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = (String) binding.textView20.getText();
                Log.d("account",name);

                String BranchName = (String) binding.textView21.getText().toString();
                Log.d("account",BranchName);
                String ID  = binding. textView12.getText().toString();
             //  int branchid = Integer.parseInt(ID);
               // Log.d("account",ID+"d");
                requestModel=new RequestModel(name,BranchName,Integer.parseInt(ID),"AndroidID","PosSerial");
               accountViewModel.getRequest(requestModel);

            }
        });

    }

}