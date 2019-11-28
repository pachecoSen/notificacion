package classes;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class HTTP {
    private static final String TAG;

    static {
        TAG = "CLASS - HTTP";
    }

    private String host;
    private String method;
    private RequestQueue queue;
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

    private String getMethod() {
        return method;
    }

    private void setMethod(String method) {
        method = method.toLowerCase();
        Log.d(TAG, "setMethod: " + method);
        this.method = method;
    }

    private String getRuta() {
        return ruta;
    }

    private void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void requerido(Context contexto){
        this.queue = Volley.newRequestQueue(contexto);
        final int metodo = "GET".equals(this.getMethod()) ? Request.Method.GET : Request.Method.POST;
        String ruta = this.getHost() + this.getRuta();
        ruta = ruta.replace("//", "/");
        Log.d(TAG, "requerido: " + "Route: " + ruta);
        StringRequest stringRequest = new StringRequest(metodo, ruta,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d(TAG, "onResponse: " + response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d(TAG, "onErrorResponse: " + error.getMessage());
                }
            }
        );
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
