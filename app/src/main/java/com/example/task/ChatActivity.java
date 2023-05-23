package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.task.adapter.ChatAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;


public class ChatActivity extends AppCompatActivity {


    private OkHttpClient okHttpClient;
    private WebSocket webSocket;

    private ArrayList<String> messagesList = new ArrayList<>();

    private ChatAdapter chatAdapter;

    private WebSocketListener webSocketListener = new WebSocketListener() {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {

        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {

            handleIncomingMessage(text);
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

        }
    };

    private void handleIncomingMessage(String message) {



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Update the UI or perform actions based on the message content

                try {
                    JSONObject jsonObject = new JSONObject(message);

                    // Access the key-value pairs in the JSON object
                    String msg = jsonObject.getString("message");
                    String from_who = jsonObject.getString("from");
                    String to_who = jsonObject.getString("to");

                    messagesList.add(msg);

                    chatAdapter.notifyDataSetChanged();
//                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("wss://app-im-service-6frz3g6yha-el.a.run.app/ws/1")
                .build();

        webSocket = okHttpClient.newWebSocket(request, webSocketListener);


        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        chatAdapter = new ChatAdapter(messagesList);
        recyclerView.setAdapter(chatAdapter);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webSocket.close(1000, "Activity destroyed");
    }
}