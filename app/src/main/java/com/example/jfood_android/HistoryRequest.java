package com.example.jfood_android;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * <h1>Request untuk fetch invoice berdasarkan customer</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class HistoryRequest extends StringRequest {
    private static final String URL = NetworkAdapter.getIpAddress() + "/invoice/customer/";
    private Map<String, String> params;

    /**
     * Method untuk merequest invoice berdasarkan customer
     * @param customerId Parameter id customer untuk mencari list invoice
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public HistoryRequest(int customerId, Response.Listener<String> listener) {
        super(Method.GET, URL+customerId, listener, null);
        Log.d("", "History Request: "+customerId);
    }

    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     *
     * @return Parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params;
    }
}
