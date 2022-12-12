package com.example.bavaria.ui.slideshow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bavaria.R;
import com.example.bavaria.pojo.classes.ItemDatum;
import com.example.bavaria.pojo.classes.models.Items;
import com.example.bavaria.ui.roomContacts.productRoom.ItemsBill;

import java.util.ArrayList;
import java.util.List;

public class AdabterItems  extends RecyclerView.Adapter<AdabterItems .viewholderItems>{
    List<ItemsBill> list = new ArrayList<>();
    Context context;

    public AdabterItems(Context context) {
        this.context = context;
    }

    public List<ItemsBill> getList() {
        return list;
    }

    public void setList(List<ItemsBill> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public AdabterItems .viewholderItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_row, parent, false);
        AdabterItems .viewholderItems viewholder = new AdabterItems .viewholderItems(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterItems .viewholderItems holder, int position) {

        holder.name_v.setText(list.get(position).getPName());
        holder.price_v.setText(list.get(position).getIDBill());
        holder.contaty_v.setText(list.get(position).getUnitPrice()+"");
       // holder.total_v.setText(list.get(position).getTotal().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholderItems extends RecyclerView.ViewHolder {
        TextView name_v,total_v,price_v;
        TextView contaty_v;
        public viewholderItems(@NonNull View itemView) {
            super(itemView);
            name_v   =itemView.findViewById(R.id.name_v);
            total_v  =itemView.findViewById(R.id.total_v);
            price_v   =itemView.findViewById(R.id.price_v);
            contaty_v =itemView.findViewById(R.id.contaty_v);

        }
    }
}