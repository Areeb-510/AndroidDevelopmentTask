package com.example.task.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.task.ChatActivity;
import com.example.task.MainActivity;
import com.example.task.R;
import com.example.task.UserOneActivity;
import com.example.task.UserTwoActivity;
import com.google.android.material.card.MaterialCardView;

public class ChatFragment extends Fragment {

//    private Button test1,user1,user2;

    private MaterialCardView user1card, user2card, test1;

    public ChatFragment() {

    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chat, container, false);

        test1 = view.findViewById(R.id.test1_card);
        user1card = view.findViewById(R.id.user1_card);
        user2card = view.findViewById(R.id.user2_card);



        test1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });

        user1card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserOneActivity.class);
                startActivity(intent);
            }
        });

        user2card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UserTwoActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}