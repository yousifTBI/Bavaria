package com.example.bavaria.ui.billsOnline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bavaria.R;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.ArrayList;
import java.util.List;

public class AdabterItems extends RecyclerView.Adapter<AdabterItems.viewholderItems> {

    List<ItemsModel> itemsModels = new ArrayList<>();
    Context context;

    public AdabterItems(Context context) {
        this.context = context;
    }

    public List<ItemsModel> getItemsModels() {
        return itemsModels;
    }

    public void setItemsModels(List<ItemsModel> itemsModels) {
        this.itemsModels = itemsModels;
    }

    @NonNull
    @Override
    public AdabterItems.viewholderItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        AdabterItems.viewholderItems viewholder = new AdabterItems.viewholderItems(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterItems.viewholderItems holder, int position) {

        holder.price.setText(itemsModels.get(position).getPrice() + "");
        holder.customer_name.setText(itemsModels.get(position). getTitle());
       // holder.due_date.setText(itemsModels.get(position).getItemType());
        holder.due_date.setText(itemsModels.get(position).getBarcode());
        holder.date.setText(itemsModels.get(position).getItemType());

    }

    @Override
    public int getItemCount() {
        return itemsModels.size();
    }

    public class viewholderItems extends RecyclerView.ViewHolder {
        TextView price, customer_name, due_date, date;

        public viewholderItems(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.price);
            customer_name = itemView.findViewById(R.id.customer_name);
            due_date = itemView.findViewById(R.id.due_date);
            date = itemView.findViewById(R.id.date);
        }
    }
}
