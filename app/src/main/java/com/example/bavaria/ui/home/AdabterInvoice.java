package com.example.bavaria.ui.home;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bavaria.R;
import com.example.bavaria.pojo.classes.models.Items;

import java.util.ArrayList;
import java.util.List;

public class AdabterInvoice extends RecyclerView.Adapter<AdabterInvoice.viewholderInvoice>{
      List<Items> list=new ArrayList<>();
     Context context;

    public List<Items> getList() {
        return list;
    }

    public void setList(List<Items> list) {
        this.list = list;
    }

    public AdabterInvoice(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public viewholderInvoice onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.bill_row, parent, false);
        viewholderInvoice viewholder = new viewholderInvoice(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewholderInvoice holder, int position) {

        holder.name_v.setText(list.get(position).getTitle());
        holder.price_v.setText(list.get(position).getprice().toString());
         holder.contaty_v.setText(list.get(position).getTax().toString());
       holder.total_v.setText(list.get(position).getTotal().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholderInvoice extends RecyclerView.ViewHolder {
        TextView name_v,total_v,price_v;
       TextView contaty_v;
        public viewholderInvoice(@NonNull View itemView) {
            super(itemView);
             name_v   =itemView.findViewById(R.id.name_v);
             total_v  =itemView.findViewById(R.id.total_v);
             price_v   =itemView.findViewById(R.id.price_v);
             contaty_v =itemView.findViewById(R.id.contaty_v);

        }
    }
}
