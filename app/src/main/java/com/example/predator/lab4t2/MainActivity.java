package com.example.predator.lab4t2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class MainActivity extends AppCompatActivity {

    private WebSocketClient client;
    private TextView textView;
    private EditText editText;
    private Button button;
    private String messageToSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_messages);
        editText = findViewById(R.id.et_message);
        button = findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageToSend = editText.getText().toString();
                client.send(messageToSend);
                editText.setText(null);
            }
        });
        connectWebSocketClient();
    }

    private void connectWebSocketClient() {
        URI uri = null;
        try {
            uri = new URI("ws://obscure-waters-98157.herokuapp.com");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        client = new CustomWebSocketClient(uri,this);
        client.connect();
    }

    public void addMessage(String text){
        textView.setText(textView.getText() + "\n" + text);
    }
}