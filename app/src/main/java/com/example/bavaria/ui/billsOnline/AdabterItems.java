package com.example.bavaria.ui.billsOnline;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bavaria.R;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.ArrayList;
import java.util.List;

public class AdabterItems extends RecyclerView.Adapter<AdabterItems.viewholderItems> {
    Dialog dialog;
    List<ItemsModel> itemsModels = new ArrayList<>();
    Context context;
    OnClickEditProduct onClickItem;

    public Dialog getDialog() {
        return dialog;
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public OnClickEditProduct getOnClickItem() {
        return onClickItem;
    }

    public void setOnClickItem(OnClickEditProduct onClickItem) {
        this.onClickItem = onClickItem;
    }

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
        holder.EditImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onClickItem.OnClickItem(position,holder.getAdapterPosition(),
                        itemsModels.get(position)
                        );
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsModels.size();
    }

    public class viewholderItems extends RecyclerView.ViewHolder {
        TextView price, customer_name, due_date, date ;
        ImageView EditImg;

        public viewholderItems(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.price);
            customer_name = itemView.findViewById(R.id.customer_name);
            due_date = itemView.findViewById(R.id.due_date);
            date = itemView.findViewById(R.id.date);
            EditImg=itemView.findViewById(R.id.editProductId);
        }
    }
}
