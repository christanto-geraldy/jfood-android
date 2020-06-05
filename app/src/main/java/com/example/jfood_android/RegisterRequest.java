package com.example.jfood_android;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>Request untuk Register customer</h1>
 * <p>Proses request yang dipanggil dari activity</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class RegisterRequest extends StringRequest {
    private static final String URL = NetworkAdapter.getIpAddress() + "/customer/register";
    private Map<String, String> params;

    /**
     * Method request untuk meregister customer baru
     * @param name Nama customer
     * @param email EMail customer
     * @param password Password customer
     * @param listener Response yang dilakukan dari objek yang terdapat pada View
     */
    public RegisterRequest (String name, String email, String password,
        Response.Listener<String> listener){
        super(Request.Method.POST, URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("email", email);
        params.put("password", password);
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
