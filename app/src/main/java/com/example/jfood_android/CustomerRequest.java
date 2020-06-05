package com.example.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Request untuk fetch objek customer</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class CustomerRequest extends StringRequest {
    private static final String URL = NetworkAdapter.getIpAddress() + "/customer";
    private Map<String, String> params;

    /**
     * Request objek customer
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public CustomerRequest (Response.Listener<String> listener){
        super(Request.Method.GET, URL, listener, null);
        params = new HashMap<>();
    }

    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     *
     * @return Parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String,String> getParams() throws AuthFailureError {
        return params;
    }
}
