package com.example.bavaria.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.bavaria.R;
import com.example.bavaria.pojo.models.Items;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.slideshow.OnClic;

import java.util.ArrayList;
import java.util.List;

public class AdabterInvoice extends RecyclerView.Adapter<AdabterInvoice.viewholderInvoice>{
      List<ItemsModel> list=new ArrayList<>();
     Context context;
     OnClic onClic;

    public OnClic getOnClic() {
        return onClic;
    }

    public void setOnClic(OnClic onClic) {
        this.onClic = onClic;
    }

    public List<ItemsModel> getList() {
        return list;
    }

    public void setList(List<ItemsModel> list) {
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
       String x= list.get(position).getPrice().toString();

         Double d=(Double.valueOf(x)*14.0)/100;
         Double total= Double.valueOf(x)+d;
        holder.name_v.setText(list.get(position).getTitle());
       holder.price_v.setText(list.get(position).getPrice().toString());
        holder.contaty_v.setText( "1");
      holder.total_v.setText(total+"");
      holder.contaty_v.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              onClic.updateQuantity(position);
          }
      });


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
