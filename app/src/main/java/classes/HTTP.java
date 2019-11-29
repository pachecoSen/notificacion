package classes;

import android.content.Context;
import android.util.Log;

import com.android.volley.ClientError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HTTP {
    private HTTP_Interface interfas;
    private static final String TAG;

    static {
        TAG = "CLASS - HTTP";
    }

    private String host;
    private int method;
    private String ruta;

    public HTTP(String host){
        this.setHost(host);
        this.setMethod("get");
    }

    private String getHost() {
        return host;
    }

    private void setHost(String host) {
        host = host.toLowerCase();
        Log.d(TAG, "setHost: " + host);

        this.host = host;
    }

    private int getMethod() {
        return method;
    }

    private void setMethod(String method) {
        method = method.toLowerCase();
        Log.d(TAG, "setMethod: " + method);

        this.method = "get".equals(method) ? Request.Method.GET : Request.Method.POST;
    }

    private String getRuta() {
        return ruta;
    }

    private void setRuta(String ruta) {
        ruta = this.getHost() + ruta;
        ruta = ruta.replace("//", "/");
        ruta = ruta.toLowerCase();
        Log.d(TAG, "setRuta: " + ruta);

        this.ruta = ruta;
    }

    public void requerido(final Context contexto){
        RequestQueue queue = Volley.newRequestQueue(contexto);
        StringRequest stringRequest = new StringRequest(this.getMethod(), this.getRuta(),
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(error instanceof TimeoutError)
                        Log.e(TAG, "onErrorResponse: " + "Fuera de tiempo", error);
                    if(error instanceof ClientError)
                        Log.e(TAG, "onErrorResponse: " + "Error de cliente", error);
                    else
                        Log.e(TAG, "onErrorResponse: " + "General", error);
                }
            }
        );

        queue.add(stringRequest);
    }

    public HTTP deffMethod(String method){
        this.setMethod(method);

        return this;
    }

    public HTTP deffRuta(String ruta){
        this.setRuta(ruta);

        return this;
    }


}
