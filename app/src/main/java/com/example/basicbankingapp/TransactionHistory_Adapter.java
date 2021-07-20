package com.example.basicbankingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class TransactionHistory_Adapter extends RecyclerView.Adapter<TransactionHistory_Adapter.ViewHolder> {

    private ArrayList<Transaction> transactionArrayList;


    public TransactionHistory_Adapter(Context context, ArrayList<Transaction> transactionArrayList) {
        this.transactionArrayList = transactionArrayList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView fromName, toName, Amount,date, time;
        CardView cardView;
        LinearLayout toUserInfo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            fromName = itemView.findViewById(R.id.t_from_name);
            toName = itemView.findViewById(R.id.t_to_name);
            Amount = itemView.findViewById(R.id.t_amount);
            date = itemView.findViewById(R.id.t_date);
            time = itemView.findViewById(R.id.t_time);
            cardView = itemView.findViewById(R.id.transaction_card_view);
            toUserInfo = itemView.findViewById(R.id.to_user_info);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @NonNull
    @Override
    public TransactionHistory_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_history_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionHistory_Adapter.ViewHolder holder, int position) {

        holder.itemView.setTag(transactionArrayList.get(position));
        holder.fromName.setText(transactionArrayList.get(position).getFromUser());
        holder.toName.setText(transactionArrayList.get(position).getToUser());
        holder.Amount.setText(String.valueOf(transactionArrayList.get(position).getAmountTransferred()));


    }

    @Override
    public int getItemCount() {
        return transactionArrayList.size();
    }

}
