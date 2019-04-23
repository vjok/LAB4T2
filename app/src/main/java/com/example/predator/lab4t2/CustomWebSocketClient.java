package com.example.predator.lab4t2;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;

public class CustomWebSocketClient extends WebSocketClient {

    private MainActivity activity;

    public CustomWebSocketClient(URI serverURI, MainActivity activity) {
        super(serverURI);
        this.activity = activity;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
    }

    @Override
    public void onMessage(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.addMessage(message);
            }
        });
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
    }

    @Override
    public void onError(Exception ex) {
    }
}