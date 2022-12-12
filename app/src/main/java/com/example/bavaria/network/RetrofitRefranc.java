package com.example.bavaria.network;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRefranc implements Interceptor {
    private  static final String Base_url="http://scopos-uat-1.online/";
    private ApisCalls calls;
    private static RetrofitRefranc Instance;
    private Retrofit retrofit;


        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(chain.request());
            if (response.code() == 307) {
                request = request.newBuilder()
                        .url(response.header("Location"))
                        .build();
                response = chain.proceed(request);
            }
            return response;
        }
    public RetrofitRefranc() {
       // OkHttpClient okHttpClient = new OkHttpClient();
       // okHttpClient.interceptors().add(new RedirectInterceptor());
       // okHttpClient.setFollowRedirects(false);


       OkHttpClient client = new OkHttpClient();
   //    client.interceptors().add(new  )

        client = new OkHttpClient.Builder()

               .connectTimeout(40, TimeUnit.MINUTES)
                .readTimeout(40, TimeUnit.MINUTES)
                .writeTimeout(40, TimeUnit.MINUTES)
                .build();
        retrofit=new Retrofit.Builder()
                .baseUrl(Base_url)
                .client( client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();


    }

    public static RetrofitRefranc getInstance(){
        if( null ==Instance)
            Instance=new RetrofitRefranc();
        return Instance;
    }


    public ApisCalls getApiCalls(){
        return calls= retrofit.create(ApisCalls.class);

    }
}
