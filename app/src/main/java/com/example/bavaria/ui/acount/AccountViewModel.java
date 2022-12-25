package com.example.bavaria.ui.acount;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bavaria.network.RetrofitRefranc;
import com.example.bavaria.network.StateData;
import com.example.bavaria.pojo.models.BranchModel;
import com.example.bavaria.pojo.models.CompanyModel;
import com.example.bavaria.pojo.models.RequestModel;
import com.example.bavaria.pojo.models.Task;
import com.example.bavaria.pojo.models.Task2;
import com.example.bavaria.pojo.models.Task3;
import com.example.bavaria.pojo.models.Task4;
import com.example.bavaria.ui.roomContacts.AccountInfo.LoginModel;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ir.mirrajabi.searchdialog.SimpleSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.BaseSearchDialogCompat;
import ir.mirrajabi.searchdialog.core.SearchResultListener;

public class AccountViewModel extends ViewModel {
    Context context;

MutableLiveData <StateData<List<CompanyModel>>> stateCompanyLiveData =new MutableLiveData<>();
MutableLiveData <StateData<String> >stateBranchLiveData =new MutableLiveData<>();
MutableLiveData <StateData<String> >stateItemsLiveData =new MutableLiveData<>();

    ArrayList<RequestModel> RequestList=new ArrayList<>();

    public ArrayList<RequestModel> getRequestList() {
        return RequestList;
    }

    public void setRequestList(ArrayList<RequestModel> requestList) {
        RequestList = requestList;
    }

    ArrayList<CompanyModel> arrayList=new ArrayList<>();
    ArrayList<BranchModel> branchArrayList =new ArrayList<>();
    ArrayList<ItemsModel> itemsArrayList =new ArrayList<>();

    public ArrayList<ItemsModel> getItemsArrayList() {
        return itemsArrayList;
    }

    public void setItemsArrayList(ArrayList<ItemsModel> itemsArrayList) {
        this.itemsArrayList = itemsArrayList;
    }

    public ArrayList<CompanyModel> getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList<CompanyModel> arrayList) {
        this.arrayList = arrayList;
    }



    public ArrayList<BranchModel> getBranchArrayList() {
        return branchArrayList;
    }

    public void setBranchArrayList(ArrayList<BranchModel> branchArrayList) {
        this.branchArrayList = branchArrayList;
    }

    public MutableLiveData<StateData<List<CompanyModel>>> getStateCompanyLiveData() {
        return stateCompanyLiveData;
    }

    public void setStateCompanyLiveData(MutableLiveData<StateData<List<CompanyModel>>> stateCompanyLiveData) {
        this.stateCompanyLiveData = stateCompanyLiveData;
    }

    public void getCom(){
        branchArrayList.clear();
        stateCompanyLiveData.setValue(new StateData().loading());
        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .GetComAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Observer<Task<CompanyModel>> observer=new Observer<Task<CompanyModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

                stateCompanyLiveData.setValue(new StateData().loading());
            }

            @Override
            public void onNext(@NonNull Task<CompanyModel> companyModelTask) {
                stateCompanyLiveData.setValue(new StateData().success(companyModelTask));
              //  Log.d("SUCCESS",companyModelTask.getCompanies().get(0).Name);
              //  provideSimpleDialog(context);
             //   createSampleData(companyModelTask.getCompanies());
                setArrayList(companyModelTask.getData());

            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("SUCCESS", e.getMessage());
                stateCompanyLiveData.setValue(new StateData().error(e));
            }

            @Override
            public void onComplete() {
                stateCompanyLiveData.setValue(new StateData().complete());
            }
        };
        observable.subscribe(observer);
    }
    MutableLiveData<String> ComLiveData=new MutableLiveData<>();
   public void provideSimpleDialog(Context context) {
        SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat(context, "Search...",
                "What are you looking for...?", null, getArrayList(),
                new SearchResultListener<CompanyModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat dialog, CompanyModel item, int position) {
                          //Toast.makeText(context, "onSelected", Toast.LENGTH_SHORT).show();
                        Log.d("loggg","onSelected");
                        branchArrayList.clear();
                        getBranches(item.getID());
                        ComLiveData.setValue(item.getTitle());
                       // itemsList.setValue(item);
                        //  onClic.getQR("d");
                        // itemsList1.setValue(new L);
                        //.add(item);
                        // binding.textView220.setText(item.getCurrencyName());
                        // binding.textView23.setText(item.getValue());
                        // binding.cashEditText1.setText("0");
                        // binding.cashEditText2.setText("0.0");
                        //   binding.textView22.setText(item.getCurrencyName()+"    ");
                        dialog.dismiss();
                    }

                }
        );
        dialog.show();
        //dialog.getSearchBox().setTypeface(Typeface.SERIF);
    }

  //  private ArrayList<CompanyModel> createSampleData(List<CompanyModel> companyModelTask) {
  //
  //      ArrayList<CompanyModel> list= (ArrayList<CompanyModel>) companyModelTask;
  //
  //      return list;
  //
  //  }

    public void getBranches(String ComID){
        stateBranchLiveData.setValue(new StateData().loading());
        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .GetBranchAPI(ComID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task2<BranchModel>> observer=new Observer<Task2<BranchModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task2<BranchModel> branchModelTask) {
                if (branchModelTask.State==2){
                 //   stateBranchLiveData.setValue(new StateData().error(e));
                    stateBranchLiveData.setValue(new StateData().success("لا يوجد فروح  لهذه الشركه "));
                }else {
                    Log.d("loggg","onNext");
                   // stateBranchLiveData.setValue(new StateData().success(branchModelTask));
                    setBranchArrayList(branchModelTask.getBranches());
                }


            }

            @Override
            public void onError(@NonNull Throwable e) {
              //  Toast.makeText(context, "onError", Toast.LENGTH_SHORT).show();
                Log.d("loggg","onError");
                Log.d("SUCCESS", e.getMessage());
                stateBranchLiveData.setValue(new StateData().error(e));
            }

            @Override
            public void onComplete() {
                stateBranchLiveData.setValue(new StateData().complete());
            }
        };
        observable.subscribe(observer);
    }
    MutableLiveData<BranchModel> BranchLiveData=new MutableLiveData<>();
    public void provideBranchSimpleDialog(Context context) {
        SimpleSearchDialogCompat dialog = new SimpleSearchDialogCompat(context, "Search...",
                "What are you looking for...?", null, getBranchArrayList(),
                new SearchResultListener<BranchModel>() {
                    @Override
                    public void onSelected(BaseSearchDialogCompat dialog, BranchModel item, int position) {
                     //   Toast.makeText(context, item.getID()+"", Toast.LENGTH_SHORT).show();
                        BranchLiveData.setValue(item);
                     //   Log.d("account",item.BranchId+"d");
                     //   Log.d("account",item.BranchId+"d");
                      // String BranchId = BranchLiveData.setValue(item.getBranchId());

                        // itemsList.setValue(item);
                        //  onClic.getQR("d");
                        // itemsList1.setValue(new L);
                        //.add(item);
                        // binding.textView220.setText(item.getCurrencyName());
                        // binding.textView23.setText(item.getValue());
                        // binding.cashEditText1.setText("0");
                        // binding.cashEditText2.setText("0.0");
                        //   binding.textView22.setText(item.getCurrencyName()+"    ");
                        dialog.dismiss();
                    }

                }
        );
        dialog.show();
        //dialog.getSearchBox().setTypeface(Typeface.SERIF);
    }
    MutableLiveData <StateData<Task<RequestModel>>> RequestLiveData=new MutableLiveData<>();
    public void getRequest(RequestModel add){
        RequestLiveData.setValue(new StateData().loading());

        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .GetRequestAPI(add)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task<RequestModel>> observer=new Observer<Task<RequestModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task<RequestModel> requestModelTask) {
                RequestLiveData.setValue(new StateData().success(requestModelTask));

            }

            @Override
            public void onError(@NonNull Throwable e) {
                RequestLiveData.setValue(new StateData().error(e));

            }

            @Override
            public void onComplete() {
                RequestLiveData.setValue(new StateData().complete());

            }
        };
        observable.subscribe(observer);
    }
    public void LoginFun(String s ){
        Log.d("Login", "cf");

        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .LoginAPI(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task3<LoginModel>> observer=new Observer<Task3<LoginModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Task3<LoginModel> loginModelTask3) {
            Log.d("Login", loginModelTask3.Message+"cf");


            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("Login", e.getMessage()+"chhf");
            }

            @Override
            public void onComplete() {

            }
        };
        observable.subscribe(observer);
    }
    public void getItems(String ComID ,String AndroidID){
        Log.d("log","l");

        Observable observable = RetrofitRefranc.getInstance()
                .getApiCalls()
                .GetItemsAPI(ComID,AndroidID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observer<Task4<ItemsModel>> observer= new Observer<Task4<ItemsModel>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d("log","l");

            }

            @Override
            public void onNext(@NonNull Task4<ItemsModel> itemsModelTask4) {
            Log.d("log",itemsModelTask4.State+"l");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("log",e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        } ;
        observable.subscribe(observer);
    }

}
