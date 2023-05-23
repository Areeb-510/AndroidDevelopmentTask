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

import com.example.task.adapter.User1Adapter;
import com.example.task.adapter.User2Adapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class UserTwoActivity extends AppCompatActivity {


    private OkHttpClient okHttpClient;
    private WebSocket webSocket;


    EditText user1_msg;
    Button sendbtn;

    private User2Adapter user2adapter;

    private ArrayList<String> messagesList = new ArrayList<>();

    private WebSocketListener webSocketListener = new WebSocketListener() {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {


            try {
                JSONObject jsonObject = new JSONObject(text);
                String from = jsonObject.getString("from");
                String message = jsonObject.getString("message");

//                if(from.equals("1")) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("message", message);
//                    editor.apply();
//                }


                // Handle the received message as needed
            } catch (JSONException e) {
                e.printStackTrace();
            }





        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {

        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {

        }
    };


    private void sendMessage(String message) {
        if (webSocket != null) {
            webSocket.send(message);



            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();


            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(user1_msg.getWindowToken(), 0);

            user1_msg.setText("");
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    private void showMessage(String message) {

        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_two);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        user2adapter = new User2Adapter(messagesList);
        recyclerView.setAdapter(user2adapter);




        okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("wss://app-im-service-6frz3g6yha-el.a.run.app/ws/2")
                .build();


        webSocket = okHttpClient.newWebSocket(request, webSocketListener);


        user1_msg = findViewById(R.id.editTextMessage2);
        sendbtn = findViewById(R.id.buttonSend2);


        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences2", Context.MODE_PRIVATE);
        String existingMessages = sharedPreferences.getString("messages", "");
        ArrayList<String> messageList = new ArrayList<>(Arrays.asList(existingMessages.split(",")));
        String[] messageArray = messageList.toArray(new String[0]);

        if(existingMessages.equals("")){

        }else{
            messagesList.addAll(Arrays.asList(messageArray));
        }



        user2adapter.notifyDataSetChanged();


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String msg1 = user1_msg.getText().toString();

                String messageFromUser1 = "{\"from\":\"2\",\"to\":\"1\",\"message\":\"" + msg1 + "\"}";
                webSocket.send(messageFromUser1);

                Toast.makeText(getApplicationContext(),messageFromUser1,Toast.LENGTH_SHORT).show();

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(user1_msg.getWindowToken(), 0);

                user1_msg.setText("");

                messagesList.add(msg1);
                user2adapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getSharedPreferences("my_preferences2", Context.MODE_PRIVATE);
                String existingMessages = sharedPreferences.getString("messages", "");
                ArrayList<String> messageList = new ArrayList<>(Arrays.asList(existingMessages.split(",")));
                messageList.add(msg1);
                String updatedMessages = TextUtils.join(",", messageList);

                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (existingMessages.equals("")){

                }else{
                    editor.putString("messages", updatedMessages);
                    editor.apply();
                }


            }
        });
    }
}