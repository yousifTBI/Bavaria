package com.example.bavaria.network;

import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofItItems {

  // private  static final String Base_url="http://scopos-arch.cloud/";
  // private ApisCalls calls;
  // private static RetrofItItems Instance;
  // private Retrofit retrofit;


  //public RetrofItItems() {
  // //  HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
  // //  logging.level(HttpLoggingInterceptor.Level.BODY);
  //   OkHttpClient client = new OkHttpClient();

  //    client = new OkHttpClient.Builder()
  //            //  .networkInterceptors().add(Stet)
  //            // .networkInterceptors().add(new StethoInterceptor())

  //            .connectTimeout(40, TimeUnit.MINUTES)
  //            .readTimeout(40, TimeUnit.MINUTES)
  //            .writeTimeout(40, TimeUnit.MINUTES)
  //            .build();
  //         retrofit=new Retrofit.Builder()
  //                 .baseUrl(Base_url)
  //                 .addConverterFactory(GsonConverterFactory.create())
  //                 .client( client)
  //                 .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
  //                 .build();

  // }

  // public static RetrofItItems getInstance(){
  //     if( null ==Instance)
  //         Instance=new RetrofItItems();
  //     return Instance;
  // }


  // public ApisCalls getApiCalls(){
  //     return calls= retrofit.create(ApisCalls.class);

  // }
}
