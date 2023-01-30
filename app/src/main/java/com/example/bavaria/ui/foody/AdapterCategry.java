package com.example.bavaria.ui.foody;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.bavaria.ui.roomContacts.onlineProduct.ItemsModel;

import java.util.ArrayList;

public class AdapterCategry extends RecyclerView.Adapter<AdapterCategry.viewholderRetuen>{
    Context context;
    ArrayList<ItemsModel> list=new ArrayList<>();
    public AdapterCategry(Context context ) {
        this.context = context;
    }


    public ArrayList<ItemsModel> getList() {
        return list;
    }

    public void setList(ArrayList<ItemsModel> list) {
        this.list = list;
    }
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterCategry.viewholderRetuen onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_category, parent, false);
        AdapterCategry.viewholderRetuen viewholder=new AdapterCategry.viewholderRetuen(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCategry.viewholderRetuen holder, int position)
    {

        holder.name_v.setText(list.get(position).getItemName());
        holder.total_v.setText(list.get(position).getTitle());
        holder.contaty_v.setText(list.get(position).getQuantity()+"");
        holder.contaty_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<Integer>arrayList=new ArrayList<>();

                // set value in array list
                arrayList.add(1);
                arrayList.add(2);
                arrayList.add(3);
                arrayList.add(4);
                arrayList.add(5);
                arrayList.add(6);
                arrayList.add(7);
                arrayList.add(8);
                arrayList.add(9);
                arrayList.add(10);
                arrayList.add(11);
                arrayList.add(12);
                arrayList.add(13);
                arrayList.add(14);
                arrayList.add(15);

                // Initialize dialog
                Dialog dialog=new Dialog(context);

                // set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);

                // set custom height and width
                dialog.getWindow().setLayout(600,800);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                // EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<Integer> adapterlist=new ArrayAdapter<Integer>(context, android.R.layout.simple_list_item_1,arrayList);

                // set adapter
                listView.setAdapter(adapterlist);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        holder.contaty_v.setText(adapterlist.getItem(position)+"");
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholderRetuen extends RecyclerView.ViewHolder {
        TextView name_v,total_v;
        TextView contaty_v;
        public viewholderRetuen(@NonNull View itemView) {
            super(itemView);
            name_v   =itemView.findViewById(R.id.name);
            total_v  =itemView.findViewById(R.id.textView44);
            contaty_v =itemView.findViewById(R.id.textView40);

        }
    }
}
