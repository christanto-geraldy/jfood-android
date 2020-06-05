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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <h1>Activity untuk login customer</h1>
 * <p>Proses activity ketika sedang dijalankan</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class LoginActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etEmail = findViewById(R.id.email);
        final EditText etPassword = findViewById(R.id.password);
        final Button btnLogin = findViewById(R.id.btnLogin);
        final TextView tvRegister = findViewById(R.id.tvRegister);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = etEmail.getText().toString();
                        String password = etPassword.getText().toString();

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

                        Response.Listener<String> responseListener = new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response) {
                                try{
                                    JSONObject jsonObject = new JSONObject(response);
                                    if(jsonObject != null){
                                        if(jsonObject.getString("name") == "null"){
                                            throw new JSONException("name");
                                        }
                                        else {
                                            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                            mainIntent.putExtra("customerId", jsonObject.getInt("id"));
                                            mainIntent.putExtra("customerName", jsonObject.getString("name"));
                                            mainIntent.addFlags(mainIntent.FLAG_ACTIVITY_CLEAR_TOP);
                                            Toast.makeText(LoginActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                                            startActivity(mainIntent);
                                            finish();
                                        }
                                    } else{
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                } catch(JSONException e){
                                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();
                                }
                            }
                        };

                        LoginRequest loginRequest = new LoginRequest(email,password,responseListener);
                        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                        queue.add(loginRequest);
                    }

                });

            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}