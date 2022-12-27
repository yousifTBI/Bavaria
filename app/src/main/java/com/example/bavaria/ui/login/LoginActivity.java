package com.example.bavaria.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.pojo.models.Task3;
import com.example.bavaria.ui.acount.AccountActivity;
import com.example.bavaria.ui.roomContacts.AccountInfo.LoginModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


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
                Toast.makeText(LoginActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, loginModelTask3.getMessage(), Toast.LENGTH_SHORT).show();
                if (loginModelTask3.State==1){
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else if (loginModelTask3.State==4){
                    Intent intent=new Intent(LoginActivity.this,AccountActivity.class);
                    startActivity(intent);
                }else {}
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
    }
}