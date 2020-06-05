package com.example.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Request untuk mendapatkan promo</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class PromoRequest extends StringRequest {
    private Map<String,String> params;
    private static final String url = NetworkAdapter.getIpAddress() + "/promo/";

    /**
     * Method request untuk mendapatkan promo berdasarkan kode
     * @param promoCode Kodepromo yang ingin didapatkan
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public PromoRequest(String promoCode, Response.Listener<String> listener){
        super(Method.GET, url+promoCode, listener, null);
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