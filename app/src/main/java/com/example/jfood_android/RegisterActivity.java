package com.example.jfood_android;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>Activity untuk register customer</h1>
 * <p>Proses activity ketika sedang dijalankan</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class RegisterActivity extends AppCompatActivity{
    boolean valid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        final TextView tvLogin = findViewById(R.id.tvLogin);
        final EditText etName = findViewById(R.id.regName);
        final EditText etEmail = findViewById(R.id.regEmail);
        final EditText etPassword = findViewById(R.id.regPassword);

        final Button btnRegister = findViewById(R.id.btnRegister);

        runOnUiThread(new Runnable(){
            @Override
            public void run() {
                btnRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = etName.getText().toString();
                        String email = etEmail.getText().toString();
                        String password = etPassword.getText().toString();

                        if(name.isEmpty()){
                            etName.setError("Please enter your name");
                            etName.requestFocus();
                            return;
                        }

                        if(email.isEmpty()){
                            etEmail.setError("Please enter your email address");
                            etEmail.requestFocus();
                            return;
                        }

                        if(password.isEmpty()){
                            etPassword.setError("Please enter your password");
                            etPassword.requestFocus();
                            return;
                        }

                        if(validEmail(email) && validPassword(password)) {
                            if (validate(email)) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            if (jsonObject != null) {
                                                Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_LONG).show();
                                            }
                                        } catch (JSONException e) {
                                            Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                };

                                RegisterRequest registerRequest = new RegisterRequest(name, email, password, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                                queue.add(registerRequest);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Email Already Exists", Toast.LENGTH_LONG).show();
                            }
                        } else{
                            Toast.makeText(RegisterActivity.this, "Please enter a valid field", Toast.LENGTH_LONG).show();
                        }
                    }

                });
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvLogin.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
    }

    public boolean validate(final String mail){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                ArrayList<String> customers = null;

                try{
                    JSONArray jsonResponse = new JSONArray(response);
                    for (int i = 0; i < jsonResponse.length(); i++) {
                        JSONObject customer = jsonResponse.getJSONObject(i);
                        String emailCheck = customer.getString("email");
                        Toast.makeText(RegisterActivity.this,emailCheck,Toast.LENGTH_LONG).show();
                        customers = new ArrayList<String>();
                        customers.add(emailCheck);
                    }
                    for(String temp: customers){
                        valid = true;
                        if(temp.equals(mail)){
                            valid = false;
                        }
                    }
                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        };
        CustomerRequest request = new CustomerRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        queue.add(request);

        return valid;
    }

    public boolean validPassword(final String passwordCheck){
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordCheck);
        return matcher.matches();
    }

    public boolean validEmail(final String emailCheck){
        final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailCheck);
        return matcher.matches();
    }
}