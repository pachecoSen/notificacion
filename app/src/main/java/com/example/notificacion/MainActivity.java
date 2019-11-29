package com.example.notificacion;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import classes.HTTP;

public class MainActivity extends AppCompatActivity {
    private static final String TAG;

    static {
        TAG = "[MAIN]";
    }

    private HTTP http;

    public MainActivity(){
        http = new HTTP("http://192.168.1.43/");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "APP Iniciada...", Toast.LENGTH_SHORT).show();
        this.http.deffMethod("get").deffRuta("/ticket/latest").requerido(getApplicationContext());
    }
}