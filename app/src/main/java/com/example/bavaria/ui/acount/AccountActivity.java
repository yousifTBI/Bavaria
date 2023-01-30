package com.example.bavaria.ui.acount;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.databinding.ActivityAccountBinding;
import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.models.BranchModel;
import com.example.bavaria.pojo.models.CompanyModel;
import com.example.bavaria.pojo.models.RequestModel;
import com.example.bavaria.pojo.models.Task;
import com.example.bavaria.pojo.models.Task3;
import com.example.bavaria.ui.home.HomeFragment;
import com.example.bavaria.ui.roomContacts.AccountInfo.LoginModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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


        binding.progressBar.setVisibility(View.GONE);
        AccountViewModel accountViewModel= new ViewModelProvider(this).get(AccountViewModel.class);
      // accountViewModel.getCom();


        Button submit= findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("loggg","d");
                binding.textView21.setText("");
                accountViewModel.branchArrayList.clear();
                String ComPlusId =binding.com.getText().toString();
                // String[] CompanyId = ComPlusId.split("1");
                StringBuffer BafferComId = splitString(ComPlusId);
                String CompanyId =BafferComId.toString();
                Log.d("loggg","d11");
                accountViewModel.getBranches(CompanyId,AccountActivity.this);

            }
        });






        Intent i=new Intent(AccountActivity.this, MainActivity.class);
        startActivity(i);


                // String x ="1524";
     //   HashMap <String,String> x =new HashMap<>();
     //   x.put("x" ,"1524");
     // accountViewModel.LoginFun(x);
      //  accountViewModel.getItems("3");

        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .LoginAPI("25810")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        io.reactivex.rxjava3.core.Observer<Task3<LoginModel>> observer=new io.reactivex.rxjava3.core.Observer<Task3<LoginModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task3<LoginModel> loginModelTask3) {
                Toast.makeText(AccountActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
               //Toast.makeText(AccountActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
               //Toast.makeText(AccountActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
               //Toast.makeText(AccountActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
               //Toast.makeText(AccountActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
                if (loginModelTask3.State==1){
                  //  Intent intent=new Intent(AccountActivity.this, MainActivity.class);
                 //   startActivity(intent);
                }
                // Log.d("Login", loginModelTask3.Message+"cf");
              //  loginLiveData.setValue(new StateData().success(loginModelTask3));

            }

            @Override
            public void onError(@NonNull Throwable e) {
                //   Log.d("Login", e.getMessage()+"chhf");
              //  loginLiveData.setValue(new StateData().error(e));
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    //   accountViewModel.LoginFun("25810");

    //   accountViewModel.loginLiveData.observe(AccountActivity.this, new Observer<StateData<Task<RequestModel>>>() {
    //       @Override
    //       public void onChanged(StateData<Task<RequestModel>> taskStateData) {
    //           switch (taskStateData.getStatus()) {
    //               case SUCCESS:
    //                   //      listStateData
    //                   Log.d("qassad",taskStateData.getData().getMessage());
    //                 //  Toast.makeText(AccountActivity.this, taskStateData.getData().getMessage()+"", Toast.LENGTH_SHORT).show();
    //                 // Toast.makeText(AccountActivity.this, taskStateData.getData().Message, Toast.LENGTH_SHORT).show();
    //                 // Toast.makeText(AccountActivity.this, taskStateData.getData().Message, Toast.LENGTH_SHORT).show();
    //                   if (taskStateData.getData().State==1){
    //                       Intent intent=new Intent(AccountActivity.this, MainActivity.class);
    //                       startActivity(intent);
    //                   }


    //                 //  binding.progressBar.setVisibility(View.GONE);

    //                   break;
    //               case ERROR:
    //                   Toast.makeText(AccountActivity.this, taskStateData.getError().getMessage(), Toast.LENGTH_SHORT).show();
    //                   Toast.makeText(AccountActivity.this, taskStateData.getError().getMessage(), Toast.LENGTH_SHORT).show();
    //                   Toast.makeText(AccountActivity.this, taskStateData.getError().getMessage(), Toast.LENGTH_SHORT).show();

    //                   break;
    //               case LOADING:
    //                   // binding.progressBar;
    //                   binding.progressBar.setVisibility(View.VISIBLE);


    //                   break;
    //               case COMPLETE:
    //                   binding.progressBar.setVisibility(View.GONE);

    //                   break;
    //           }
    //       }
    //   });


     // accountViewModel.ComLiveData.observe(this, new Observer<CompanyModel>() {
     //     @Override
     //     public void onChanged(CompanyModel companyModel) {
     //         if (companyModel==null){

     //         }else {
     //             binding.textView21.setText("");
     //             binding.textView20.setText(companyModel.Name);}
     //         binding.comID.setText(companyModel.getID());
     //     }
     // });
     //         .observe(this, new Observer<String>() {
     //     @Override
     //     public void onChanged(String s) {
     //         Toast.makeText(AccountActivity.this, "", Toast.LENGTH_SHORT).show();
     //
     //
     //         if (s==null){}else { binding.textView20.setText(s);}


     //     }
     // });

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
//                      Toast.makeText(AccountActivity.this, stringStateData.getData(), Toast.LENGTH_SHORT).show();
                      binding.progressBar.setVisibility(View.GONE);

                      break;
                  case ERROR:
                      binding.progressBar.setVisibility(View.GONE);

                      break;
                  case LOADING:
                      // binding.progressBar;
                      binding.progressBar.setVisibility(View.VISIBLE);
                    //  binding.progressBar.setVisibility(View.GONE);

                      break;
                  case COMPLETE:
                      binding.progressBar.setVisibility(View.GONE);

                      break;
                    case Problem:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(AccountActivity.this, "لاااااا يوجد انترنت", Toast.LENGTH_SHORT).show();
                      //  Toast.makeText(AccountActivity.this, "لاااااا يوجد انترنت", Toast.LENGTH_SHORT).show();

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
     //  accountViewModel.stateCompanyLiveData.observe(AccountActivity.this, new Observer<StateData<List<CompanyModel>>>() {
     //      @Override
     //      public void onChanged(StateData<List<CompanyModel>> listStateData) {

     //          switch (listStateData.getStatus()) {
     //                 case SUCCESS:
     //               //      listStateData
     //                     binding.progressBar.setVisibility(View.GONE);

     //                     break;
     //                 case ERROR:

     //                     break;
     //              case LOADING:
     //                 // binding.progressBar;
     //                  binding.progressBar.setVisibility(View.VISIBLE);


     //                  break;
     //              case COMPLETE:
     //                  binding.progressBar.setVisibility(View.GONE);

     //                   break;
     //          }
     //      }
     //  });

    //  binding.cardView5.setOnClickListener(new View.OnClickListener() {
    //      @Override
    //      public void onClick(View view) {

    //      accountViewModel.provideSimpleDialog(AccountActivity.this);
    //     // binding.textView20.setText(accountViewModel.getArrayList().get().getName());

    //      }
    //  });

        binding.cardView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            accountViewModel.provideBranchSimpleDialog(AccountActivity.this);

            }
        });

        String androidId = Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID);
     //   Log.d("account",BranchName);
       // binding.textView4.setText(androidId);

        binding.SendRequestId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  String name = (String) binding.textView20.getText();
             //   Log.d("account",name);

                String BranchName = (String) binding.textView21.getText().toString();
                Log.d("account",BranchName);
                String ID  = binding. textView12.getText().toString();
               //  int branchid = Integer.parseInt(ID);
               // Log.d("account",ID+"d");
                binding.comID.getText().toString();

                requestModel=new RequestModel("",BranchName,Integer.parseInt(ID)
                        ,"25810", binding.POStxt.getText().toString(),  Integer.parseInt(binding.comID.getText().toString()));
               accountViewModel.getRequest(requestModel);

            }
        });

    }
    public StringBuffer splitString(String str)
    {
      //  ArrayList CompanyId =new ArrayList<>();
        StringBuffer CompanyId = new StringBuffer();
       // StringBuffer    num = new StringBuffer();
        for (int i=0; i<str.length(); i++)
        {
           if(i>1)
           {
               CompanyId .append(str.charAt(i));
           }
        }
        return CompanyId;
    }


}