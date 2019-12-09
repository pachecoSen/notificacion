package com.example.notificacion;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import classes.GeoJson;
import classes.HTTP;
import interfaces.HTTPCallback;

import static com.example.notificacion.R.id.txtNewTicket;
import static com.example.notificacion.R.layout.main;

public class MainActivity extends AppCompatActivity {
    private static final String TAG;

    static {
        TAG = "[MAIN]";
    }

    private HTTP http;
    private GeoJson jsonTool;

    public MainActivity(){
        http = new HTTP("http://192.168.1.43/");
        jsonTool = new GeoJson();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "APP Iniciada...", Toast.LENGTH_SHORT).show();
        this.http.deffMethod("get");
        this.http.deffRuta("/ticket/latest");
        this.http.requerido(getApplicationContext(), new HTTPCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    final JSONObject json = jsonTool.deffInData(result).toJson();
                    if (json.getBoolean("estatus")) {
                        final TextView label = (TextView) findViewById(txtNewTicket);
                        final int ticket = json.getJSONObject("msg").getInt("latest");
                        label.setText(ticket);
                    }

                } catch (JSONException e) {
                    Log.e(TAG, "onSuccess: " + "Error Json", e);
                }
            }
        });
    }
}