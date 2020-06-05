package com.example.jfood_android;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Request untuk mengambil daftar pesanan dari database</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class PesananFetchRequest extends StringRequest {
    private static final String URL =  NetworkAdapter.getIpAddress() + "/invoice/customer/";
    private Map<String, String> params;

    /**
     * Method request untuk mengambil pesanan berdasarkan customer id
     * @param customerId Parameter Id customer
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public PesananFetchRequest(String customerId, Response.Listener<String> listener){
        super(Method.GET, URL+customerId, listener, null);
        params = new HashMap<>();
        Log.d("", "PesananFetchRequest: "+ customerId);
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