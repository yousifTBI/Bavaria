package com.example.bavaria.ui.editProduct;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bavaria.MainActivity;
import com.example.bavaria.R;
import com.example.bavaria.ui.billsOnline.AdabterItems;
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;
import com.example.bavaria.ui.slideshow.OnClic;

import java.util.ArrayList;
import java.util.List;

public class AdapterEdit extends RecyclerView.Adapter<AdapterEdit.viewholderItems> {
    Dialog dialog;
    List<ItemsModel> itemsModels = new ArrayList<>();
    Context context;

    public AdapterEdit(Context context) {
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
    public AdapterEdit.viewholderItems onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.edit_row, parent, false);
        AdapterEdit.viewholderItems viewholder = new AdapterEdit.viewholderItems(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEdit.viewholderItems holder, int position) {

        holder.price.setText(itemsModels.get(position).getPrice() + "");
        holder.customer_name.setText(itemsModels.get(position). getTitle());
        // holder.due_date.setText(itemsModels.get(position).getItemType());
        holder.due_date.setText(itemsModels.get(position).getBarcode());
        holder.date.setText(itemsModels.get(position).getItemType());

        holder.editProductIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);

                // set custom dialog
                dialog.setContentView(R.layout.edit_dialog);

                // set custom height and width
                dialog.getWindow().setLayout(700, 1200);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsModels.size();
    }

    public class viewholderItems extends RecyclerView.ViewHolder {
        TextView price, customer_name, due_date, date ,editProductIdBtn;

        public viewholderItems(@NonNull View itemView) {
            super(itemView);

            price = itemView.findViewById(R.id.price);
            customer_name = itemView.findViewById(R.id.customer_name);
            due_date = itemView.findViewById(R.id.due_date);
            date = itemView.findViewById(R.id.date);
            editProductIdBtn =itemView.findViewById(R.id.editProductIdBtn);
        }
    }
}
