package com.example.bavaria.ui.billsOnline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bavaria.R;
import com.example.bavaria.ui.roomContacts.HeaderBill;
import com.example.bavaria.ui.roomContacts.onlineBill.HeaderBillOnline;

import com.example.bavaria.ui.slideshow.OnClic;

import java.util.ArrayList;
import java.util.List;

public class AdabterBillsOnline extends RecyclerView.Adapter<AdabterBillsOnline  .viewholderBillOnlin>{
    List<HeaderBillOnline> list = new ArrayList<>();
    Context context;
    OnClic onClic;





    @NonNull
    @Override
    public AdabterBillsOnline .viewholderBillOnlin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_online, parent, false);
        AdabterBillsOnline  .viewholderBillOnlin viewholder = new AdabterBillsOnline  .viewholderBillOnlin(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdabterBillsOnline  .viewholderBillOnlin holder, int position) {
//
        holder.TimeBill .   setText(list.get(position).getInvoiceDate());
        holder.netAmount   .setText(list.get(position).getNetPrice()+"");
        holder.ReceiptNumber.setText(" رقم الايصال  :     "+list.get(position).getBillNumber()+"");
        holder.taxesID     .setText(list.get(position).getTax()+"");
        holder.TotalCostID .setText(list.get(position).getTotalPrice()+"");

        holder.Link .setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                onClic.getPos(position);
                //onClic.(position);
                //   Uri uri=Uri.parse(list.get(position).getLink());
                //
                //  context. startActivity(new Intent(Intent.ACTION_VIEW,uri));

            }

        });

        holder.Link2.setText(list.get(position).getLink());
        //holder.lin.setText(list.get(position).getLink());
        // holder.lin.setMovementMethod(LinkMovementMethod.getInstance());
        // holder.lin.setText(Html.fromHtml(list.get(position).getLink()));
        //.setText(list.get(position).getTotalPrice()+"");
        // holder.total_v.setText(list.get(position).getTotal().toString());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholderBillOnlin extends RecyclerView.ViewHolder {
        TextView TimeBill,netAmount,ReceiptNumber,taxesID,TotalCostID,lin,Link2;
        Button Link;
        // TextView contaty_v;
        public viewholderBillOnlin(@NonNull View itemView) {
            super(itemView);
            TimeBill       =itemView.findViewById(R.id.TimeBill);
            netAmount      =itemView.findViewById(R.id.netAmount);
            ReceiptNumber   =itemView.findViewById(R.id.ReceiptNumber);
            taxesID         =itemView.findViewById(R.id.taxesID);
            Link         =itemView.findViewById(R.id.Link);
            TotalCostID     =itemView.findViewById(R.id.TotalCostID);
            lin     =itemView.findViewById(R.id.lin);
            Link2     =itemView.findViewById(R.id.Link2);
            //contaty_v =itemView.findViewById(R.id.contaty_v);

        }
    }
}
