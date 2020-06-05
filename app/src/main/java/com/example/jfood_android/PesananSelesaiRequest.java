package com.example.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Request untuk Menyelesaikan pesanan</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class PesananSelesaiRequest extends StringRequest {
    private static final String URL = NetworkAdapter.getIpAddress() + "/invoice/invoiceStatus/";
    private Map<String, String> params;

    /**
     * Method request untuk membatalkan pesanan
     * @param id Invoice yang dibatalkan
     * @param status perubahan status menjadi Finished
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public PesananSelesaiRequest(String id, String status, Response.Listener<String> listener) {
        super(Method.PUT, URL + id, listener, null);
        params = new HashMap<>();
        params.put("id",id);
        params.put("status", status);
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