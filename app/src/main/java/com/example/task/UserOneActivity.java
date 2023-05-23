package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.task.adapter.ChatAdapter;
import com.example.task.adapter.User1Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class UserOneActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private WebSocket webSocket;


    EditText user1_msg;
    Button sendbtn;

    private User1Adapter user1adapter;

    private ArrayList<String> messagesList = new ArrayList<>();


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

                try {
                    JSONObject jsonObject = new JSONObject(message);
                    String from = jsonObject.getString("from");
                    String message = jsonObject.getString("message");
                    // Handle the received message as needed
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            }
        });
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_one);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        user1adapter = new User1Adapter(messagesList);
        recyclerView.setAdapter(user1adapter);




        okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("wss://app-im-service-6frz3g6yha-el.a.run.app/ws/1")
                .build();


        webSocket = okHttpClient.newWebSocket(request, webSocketListener);


        user1_msg = findViewById(R.id.editTextMessage);
        sendbtn = findViewById(R.id.buttonSend);

        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        String existingMessages = sharedPreferences.getString("messages", "");
        ArrayList<String> messageList = new ArrayList<>(Arrays.asList(existingMessages.split(",")));
        String[] messageArray = messageList.toArray(new String[0]);

        if(existingMessages.equals("")){

        }else{
            messagesList.addAll(Arrays.asList(messageArray));
        }



        user1adapter.notifyDataSetChanged();


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg1 = user1_msg.getText().toString();

                String messageFromUser1 = "{\"from\":\"1\",\"to\":\"2\",\"message\":\"" + msg1 + "\"}";
                webSocket.send(messageFromUser1);

                Toast.makeText(getApplicationContext(),messageFromUser1,Toast.LENGTH_SHORT).show();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(user1_msg.getWindowToken(), 0);

                user1_msg.setText("");

                messagesList.add(msg1);
                user1adapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
                String existingMessages = sharedPreferences.getString("messages", "");
                ArrayList<String> messageList = new ArrayList<>(Arrays.asList(existingMessages.split(",")));
                messageList.add(msg1);
                String updatedMessages = TextUtils.join(",", messageList);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("messages", updatedMessages);
                editor.apply();




            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

//        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
//        String existingMessages = sharedPreferences.getString("messages", "");
//        ArrayList<String> messageList = new ArrayList<>(Arrays.asList(existingMessages.split(",")));
//        String[] messageArray = messageList.toArray(new String[0]);
//
//
//        messagesList.addAll(Arrays.asList(messageArray));
//        user1adapter.notifyDataSetChanged();
    }

    private void showMessage(String message) {

        messagesList.add(message);

    }

}