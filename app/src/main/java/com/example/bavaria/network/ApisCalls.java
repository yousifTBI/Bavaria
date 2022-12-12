package com.example.bavaria.network;


import com.example.bavaria.pojo.classes.Receipts;
import com.example.bavaria.pojo.classes.Root;
import com.example.bavaria.pojo.classes.models.BillReturn;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApisCalls {

//   @Headers("Content-Type: application/json")
//   @POST("api/Account/ClientLogin")
//   Single<LoginModel>login(
//           @Body String s);


//   @Headers("Content-Type: application/json")
//   @POST("api/Account/Client_Registration_Request")
//   Single<LoginModel>creatAcc(
//           @Body HashMap Map);


//   @Headers("Content-Type: application/json")
//   @GET("GetProductsTestPagination")
//   Call<ItemsModel> getItemsPagnation(
//           @Query("Count") int count ,
//           @Query("Cursor") int Cursor);


//   @Headers("Content-Type: application/json")
//   @GET("api/Product/GetProducts")
//   Observable<Task> getItems();


//   @Headers("Content-Type: application/json")
//   @POST("api/Product/GetProductByBarCade")
//   Single<ItemsModel> getItemsQr(@Body String s);

//   @Headers("Content-Type: application/json")
//   @POST("api/Product/SetpillTest")
//   Single<ModelStatMg> sentInvoice(@Body SetBillModel list);

//   @Headers("Content-Type: application/json")
//   @POST("api/Product/SetpillTest")
//   Single<ModelStatMg> sentInvoice2(@Body SetBillModel list);

//   @Headers("Content-Type: application/json")
//   @POST("api/Product/SetpillTest")
//   Single<ModelStatMg> senReturn(@Body SetBillRutern list);


//   @Headers("Content-Type: application/json")
//   @POST("api/Product/UpdateProductByBarCade")
//   Single<ItemsModel> udateProduct(@Body UpdateProductModel update);


//  // api/Product/SetClosingDay
//   @Headers("Content-Type: application/json")
//   @POST("api/Product/PostProduct")
//   Single<ItemsModel> addProduct(@Body AddProductModel add);

//   @Headers("Content-Type: application/json")
//   @GET("api/Product/SetClosingDay")
//   Single<ItemsModel> setClosingDay(@Query("ComID")String ComID);


//   @Headers("Content-Type: application/json")
//   @GET("api/Report/GetTotalItemForCom")
//   Observable<Task1Closed> sale(@Query("ComID") String ComID);

//   @Headers("Content-Type: application/json")
//   @GET("api/Report/GetTotalForEachReturnItem")
//   Observable<Task1Closed> ReturnItem(@Query("ComID") String ComID);

//   @Headers("Content-Type: application/json")
//   @GET("api/Report/GetSalesReportForCom")
//   Single<Task1Closed> getReport(@Query("DateFrom")String DateFrom
//           ,@Query("DateTo")String DateTo,@Query("ComID") String ComID);
///https://api.nasa.gov/planetary/apod?api_key=pEVGumcFSQ44T56oqswrfi0V9OEMqnRvZDkI8sCb

//   //   @Headers("Content-Type: application/json")
//   //   @GET("planetary/apod")
//   //   Single<اللى هيرجع>get nasa(
//   //             @Query("api_key") int key);
///


    //   //   @Headers("Content-Type: application/json")
////   @POST("api/Employee/EmployeeAttendanceV2")
////   Single<LoginModel>Location(
////           @Body LocationModelApi Map);
///
///
///
////  @Headers("Content-Type: application/json")
////   @POST("api/Employee/GetEmployeeInfo")
////   Single<InFoModel>getInfo(
////           @Body int x);
//
    @Headers("Content-Type: application/json")
    @POST("api2/AndroidReciets/SetBill")
    Single<BillReturn> getProductm(@Body Root root);

    @Headers("Content-Type: application/json")
    @POST("api2/AndroidReciets/SetListBill")
    Observable<BillReturn> SetListBill(@Body Receipts root);
}
