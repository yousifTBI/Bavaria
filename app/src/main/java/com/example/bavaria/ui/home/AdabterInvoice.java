package com.example.bavaria.ui.home;

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
import com.example.bavaria.pojo.models.Items;
import com.example.bavaria.pojo.testModels.ItemsModels;
import com.example.bavaria.ui.slideshow.OnClic;

import java.util.ArrayList;
import java.util.List;

public class AdabterInvoice extends RecyclerView.Adapter<AdabterInvoice.viewholderInvoice> {
    List<ItemsModels> list = new ArrayList<>();
    Context context;
    OnClic onClic;

    public OnClic getOnClic() {
        return onClic;
    }

    public void setOnClic(OnClic onClic) {
        this.onClic = onClic;
    }

    public List<ItemsModels> getList() {
        return list;
    }

    public void setList(List<ItemsModels> list) {
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
        //  String x= list.get(position).getPrice().toString();

        //  Double d=(Double.valueOf(x)*14.0)/100;
        //  Double total= Double.valueOf(x)+d;
       // holder.name_v.setText(list.get(position).getTitle());
        holder.name_v.setText(list.get(position).getDescription());
       // holder.price_v.setText(list.get(position).getPrice().toString());
        holder.price_v.setText(list.get(position).getPrice()+"");

        holder.contaty_v.setText( list.get(position).getQuantity()+"");

         Double total= Double.valueOf(list.get(position).getPrice())*Double.valueOf(list.get(position).getQuantity());

     // holder.total_v.setText(list.get(position).getPrice() + "");
      holder.total_v.setText(total + "");
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

                // editText.addTextChangedListener(new TextWatcher() {
                //     @Override
                //     public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //         //  Toast.makeText(ItemsActivity.this, s+"s34", Toast.LENGTH_SHORT).show();

                //     }

                //     @Override
                //     public void onTextChanged(CharSequence s, int start, int before, int count) {
                //         adapterlist.getFilter().filter(s);
                //         //  adapterlist.filterList()

                //         Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                //     }

                //     @Override
                //     public void afterTextChanged(Editable s) {
                //         //  Toast.makeText(ItemsActivity.this, s+"ss", Toast.LENGTH_SHORT).show();

                //     }
                // });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //    adabter.setList(itemsList);

                        // Toast.makeText(getContext(),  items.getTotal()+"", Toast.LENGTH_SHORT).show();
                        // adabter.notify();
                        //     homeViewModel.setItemsList1(itemsList);

                        // when item selected from list
                        // set selected item on textView
                        //   textview.setText(adapterlist.getItem(position).getItemName());
                        //   // Toast.makeText(ItemsActivity.this,adapter.getItem(position).Record_ID, Toast.LENGTH_SHORT).show();
                        //   list.add(new ItemsModel(adapterlist.getItem(position).getSelling_Price(),adapterlist.getItem(position).getItemName(),adapterlist.getItem(position).getBarcode()));
                        //   // Dismiss dialog
                        //   //  adabter.setList(list);
                        //   adabter.notifyItemInserted(list.size()-1);
                        //   billRecycler.scrollToPosition(list.size());
                        //   billRecycler.setAdapter(adabter);
                        //  holder.   contaty_v.setText(adapterlist.getItem(position)+"");
                        //    item.setQuantity( Integer.valueOf(adapterlist.getItem(position)));
                     //  itemsList.remove(postionList);
                     //  ItemsModel i= new ItemsModel();
                     //  i.setQuantity(Integer.valueOf(adapterlist.getItem(position)));
                     //  i.setDescription( item.getDescription());
                     //  i.setPrice( Double.valueOf(item.getPrice()));
                     //  itemsList.add(i);
                     //  //.setQuantity(Integer.valueOf(String.valueOf(adapterlist.getItem(position))));
                     //  // Toast.makeText(getContext(), String.valueOf(adapterlist.getItem(position)), Toast.LENGTH_SHORT).show();

                     //  // adabter.setList(itemsList);
                     //  // holder.total_v.setText(list.get(position).getBalanc()+"");
                     //  // list.get(position).setContaty(Double.valueOf(String.valueOf(charSequence)));
                     //  // clic.cliceCuantaty(position,list.get(position).getBalanc());
                     //  //   adabter.notifyDataSetChanged();
                     //  adabter.notifyItemChanged(postionList);
                        list.get(holder.getAdapterPosition()).setQuantity(Integer.valueOf(String.valueOf(adapterlist.getItem(position))));
                        notifyDataSetChanged();

                        dialog.dismiss();
                    }
                });
         //       onClic.updateQuantity(view,position, list.get(position), holder.getAdapterPosition());
                   //     list.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholderInvoice extends RecyclerView.ViewHolder {
        TextView name_v, total_v, price_v;
        TextView contaty_v;

        public viewholderInvoice(@NonNull View itemView) {
            super(itemView);
            name_v = itemView.findViewById(R.id.name_v);
            total_v = itemView.findViewById(R.id.total_v);
            price_v = itemView.findViewById(R.id.price_v);
            contaty_v = itemView.findViewById(R.id.contaty_v);

        }
    }
}
