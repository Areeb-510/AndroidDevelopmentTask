package com.example.task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;

import java.util.List;

public class User1Adapter extends RecyclerView.Adapter<User1Adapter.User1ViewHolder> {


    private List<String> chatItems;

    public User1Adapter(List<String> chatItems) {
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public User1ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);


        return new User1Adapter.User1ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User1ViewHolder holder, int position) {
        holder.msgs.setText(chatItems.get(position));
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public class User1ViewHolder extends RecyclerView.ViewHolder{

        TextView msgs;

        public User1ViewHolder(@NonNull View itemView) {
            super(itemView);

            msgs = itemView.findViewById(R.id.messageText);
        }

    }



}
