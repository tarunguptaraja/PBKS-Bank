package com.example.basicbankingapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userList_Adapter extends RecyclerView.Adapter<userList_Adapter.ViewHolder> {

    private ArrayList<User> userArrayList;

    public userList_Adapter(Context context,ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }


    public class ViewHolder extends  RecyclerView.ViewHolder{

        TextView userName,userAccBalance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.username);
            userAccBalance = itemView.findViewById(R.id.amount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    @NonNull
    @Override
    public userList_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_list_item,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

      viewHolder.itemView.setTag(userArrayList.get(position));
      viewHolder.userName.setText(userArrayList.get(position).getName());
      viewHolder.userAccBalance.setText(String.valueOf(userArrayList.get(position).getBalance()));

      viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent = new Intent(view.getContext(),User_data.class);
              intent.putExtra("NAME",userArrayList.get(position).getName());
              intent.putExtra("ACCOUNT",userArrayList.get(position).getAccountNo());
              intent.putExtra("EMAIL",userArrayList.get(position).getEmail());
              intent.putExtra("PHONE",userArrayList.get(position).getPhoneNo());
              intent.putExtra("IFSC",userArrayList.get(position).getIfscCode());
              intent.putExtra("BALANCE",userArrayList.get(position).getBalance());

              view.getContext().startActivity(intent);

          }
      });


    }


    @Override
    public int getItemCount() {
        return userArrayList.size();
    }


}
