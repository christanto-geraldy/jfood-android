package com.example.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Request untuk membuat pesanan</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class BuatPesananRequest extends StringRequest {
    private Map<String, String> params;
    private static final String URL = NetworkAdapter.getIpAddress() + "/invoice";

    /**
     * BuatPesananRequest Cash Invoice
     * @param food Parameter food yang dipesan
     * @param url URL untuk merequest object invoice dari API
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     * @param id Parameter customerId
     */
    public BuatPesananRequest(String food, String id, String url, Response.Listener<String> listener){
        super(Method.POST, URL + url, listener, null);
        params = new HashMap<>();
        params.put("foodIdList", food);
        params.put("customerId", id);
        params.put("deliveryFee", "0");
    }

    /**
     * BuatPesananRequest Cashless Invoice
     * @param food Parameter food yang dipesan
     * @param id Parameter CustomerId
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     * @param promoCode Promo code yang digunakan untuk membuat invoice
     * @param url URL untuk merequest object invoice dari API
     */
    public BuatPesananRequest(String food, String id, String promoCode, Response.Listener<String> listener, String url) {
        super(Method.POST, URL + url, listener, null);
        params = new HashMap<>();
        params.put("foodIdList", food);
        params.put("customerId", id);
        params.put("promoCode", promoCode);
    }

    /**
     * Mengembalikan parameter Map dari POST yang digunakan untuk request invoice
     *
     * @return Parameter request dalam aplikasi
     * @throws AuthFailureError jika terjadi kesalahan autentikasi
     */
    @Override
    protected Map<String, String> getParams() throws AuthFailureError{
        return params;
    }
}