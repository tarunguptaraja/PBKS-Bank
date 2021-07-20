package com.example.basicbankingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class sendToUser_Adapter extends RecyclerView.Adapter<sendToUser_Adapter.ViewHolder> {

    private final onUserListener onUserListener;
    private final ArrayList<User> userArrayList;


    public sendToUser_Adapter(ArrayList<User> useraArayList, onUserListener onUserListener) {
      this.userArrayList = useraArayList;
      this.onUserListener = onUserListener;
    }

    @NonNull
    @Override
    public sendToUser_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item,viewGroup,false);
        return new ViewHolder(view, onUserListener);
    }

    @Override
    public void onBindViewHolder(@NonNull sendToUser_Adapter.ViewHolder viewHolder, int position) {

        viewHolder.itemView.setTag(userArrayList.get(position));
        viewHolder.usernName.setText(userArrayList.get(position).getName());

        String balance = String.valueOf(userArrayList.get(position).getBalance());
        viewHolder.userAccountBalance.setText(balance);

    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView usernName,userAccountBalance;
        onUserListener onUserListener;

        public ViewHolder(@NonNull View itemView,onUserListener onUserListener) {
            super(itemView);

          usernName = itemView.findViewById(R.id.username);
          userAccountBalance = itemView.findViewById(R.id.amount);
          this.onUserListener = onUserListener;
          itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onUserListener.onUserClick(getAdapterPosition());

        }
    }

    public interface onUserListener
    {  void onUserClick(int position);

    }
}
