package com.example.bavaria.ui.utils;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bavaria.network.ApisCalls;
import com.example.bavaria.network.RetrofitRefranc;

public class SharedPreferencesCom {
    private static SharedPreferencesCom Instance;
    private SharedPreferences sharedPreferencesLogIn;
    private SharedPreferences.Editor edits;

    public SharedPreferencesCom() {
    }

    public static void init(Context context) {
        Instance = new SharedPreferencesCom(context);

    }

    public static SharedPreferencesCom getInstance() {
        if (null == Instance)
            Instance = new SharedPreferencesCom();
        return Instance;
    }

    public SharedPreferencesCom(Context context) {
        sharedPreferencesLogIn = context.getSharedPreferences("LogIn", MODE_PRIVATE);
        edits = sharedPreferencesLogIn.edit();
    }

        public void setSharedValues(String s) {
        edits.putString("NumberOFBill", s);
        edits.putString("NumberOFBill", s);

        edits.apply();
    }

    public String getSharedValues() {
        String Number = sharedPreferencesLogIn.getString("NumberOFBill", "900");

        return Number;
    }


}
