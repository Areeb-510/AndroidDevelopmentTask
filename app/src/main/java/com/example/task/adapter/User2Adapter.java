package com.example.task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.R;

import java.util.List;

public class User2Adapter extends RecyclerView.Adapter<User2Adapter.User2ViewHolder> {

    private List<String> chatItems;

    public User2Adapter(List<String> chatItems) {
        this.chatItems = chatItems;
    }

    @NonNull
    @Override
    public User2ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);



        return new User2Adapter.User2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull User2ViewHolder holder, int position) {
        holder.msgs.setText(chatItems.get(position));
    }

    @Override
    public int getItemCount() {
        return chatItems.size();
    }

    public class User2ViewHolder extends RecyclerView.ViewHolder{

        TextView msgs;

        public User2ViewHolder(@NonNull View itemView) {
            super(itemView);

            msgs = itemView.findViewById(R.id.messageText);
        }

    }
}
