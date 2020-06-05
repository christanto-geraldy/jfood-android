package com.example.jfood_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * <h1>Activity untuk membuat pesanan</h1>
 * <p>Proses activity ketika sedang dijalankan</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class BuatPesananActivity extends AppCompatActivity {

    // Instance variables
    private int customerId;
    private String customerName;
    private int foodId;
    private String foodName;
    private String foodCategory;
    private double foodPrice;
    private int diskon;
    private int minPrice;
    private boolean status;

    //Request untuk membuat pesanan
    BuatPesananRequest request;

    /**
     * Method untuk meninisialisasi activity_buat_pesanan
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Menampilkan layout xml activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_pesanan);

        //Mendapatkan informasi dari value yang disimpan pada variable extras
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customerId = extras.getInt("customerId");
            customerName = extras.getString("customerName");
            foodId = extras.getInt("food_id");
            foodName = extras.getString("food_name");
            foodPrice = extras.getInt("food_price");
            foodCategory = extras.getString("food_category");
        }

        //EditText
        final EditText etPromoCode = findViewById(R.id.promo_code);

        //TextView
        final TextView tvPromo = findViewById(R.id.textCode);
        final TextView tvFoodName = findViewById(R.id.food_name);
        final TextView tvFoodCategory = findViewById(R.id.food_category);
        final TextView tvFoodPrice = findViewById(R.id.food_price);
        final TextView tvTotalPrice = findViewById(R.id.total_price);

        //RadioGroup
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);

        //Button
        final Button btnPesan = findViewById(R.id.btnPesan);
        final Button btnHitung = findViewById(R.id.hitung);

        btnPesan.setVisibility(View.INVISIBLE);
        tvPromo.setVisibility(View.INVISIBLE);
        etPromoCode.setVisibility(View.INVISIBLE);

        btnHitung.setEnabled(false);
        btnPesan.setEnabled(false);

        tvFoodName.setText(foodName);
        tvFoodCategory.setText(foodCategory);
        tvFoodPrice.setText("Rp. " + String.valueOf((int) foodPrice));
        tvTotalPrice.setText("Rp. 0");

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i){
                btnPesan.setVisibility(View.INVISIBLE);

                RadioButton rbId = findViewById(i);
                String payMethod = rbId.getText().toString().trim();
                btnHitung.setEnabled(true);

                switch(payMethod){
                    case "Via CASH":
                        etPromoCode.setVisibility(View.GONE);
                        tvPromo.setVisibility(View.GONE);
                        btnHitung.setVisibility(View.VISIBLE);
                        break;
                    case "Via CASHLESS":
                        etPromoCode.setVisibility(View.VISIBLE);
                        etPromoCode.setEnabled(true);
                        tvPromo.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rdId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(rdId);
                String payMethod = radioButton.getText().toString().trim();

                btnPesan.setEnabled(true);

                switch (payMethod) {
                    case "Via CASH":
                        tvTotalPrice.setText("Rp. " + foodPrice);
                        btnHitung.setVisibility(View.GONE);
                        btnPesan.setVisibility(View.VISIBLE);
                        break;
                    case "Via CASHLESS":
                        if(!etPromoCode.getText().toString().isEmpty()) {
                            final String etPromo = etPromoCode.getText().toString();
                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try{
                                        JSONObject jsonObject = new JSONObject(response);
                                        if(jsonObject != null){
                                            diskon = jsonObject.getInt("discount");
                                            minPrice = jsonObject.getInt("minPrice");
                                            status = jsonObject.getBoolean("active");
                                            if(status && foodPrice>=minPrice && foodPrice > diskon) {
                                                Toast.makeText(BuatPesananActivity.this, "Promo Applied", Toast.LENGTH_LONG).show();
                                                tvTotalPrice.setText("Rp. " + (foodPrice - diskon));
                                                btnHitung.setVisibility(View.GONE);
                                                btnPesan.setVisibility(View.VISIBLE);
                                            }else{
                                                Toast.makeText(BuatPesananActivity.this, "Promo inactive or You haven't reached the minimum order amount to use this promo code",Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    } catch (JSONException j){
                                        Toast.makeText(BuatPesananActivity.this, "Promo Code not found", Toast.LENGTH_LONG).show();
                                    }
                                }
                            };

                            PromoRequest promoRequest = new PromoRequest(etPromo, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                            queue.add(promoRequest);
                        } else{
                            tvTotalPrice.setText("Rp. " + foodPrice);
                            btnHitung.setVisibility(View.GONE);
                            btnPesan.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        });

        btnPesan.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(radioButtonId);
                final String payMethod = radioButton.getText().toString().trim();
                final String etPromo = etPromoCode.getText().toString().trim();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(BuatPesananActivity.this, "Your food is being ordered", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(BuatPesananActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("customerId", customerId);
                                intent.putExtra("customerName", customerName);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(BuatPesananActivity.this, "Order failed", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(BuatPesananActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("customerId", customerId);
                            intent.putExtra("customerName", customerName);
                            startActivity(intent);

                        }
                    }
                };

                switch (payMethod) {
                    case "Via CASH":
                        request = new BuatPesananRequest(foodId + "" , customerId+ "", "/createCashInvoice", responseListener);
                        break;
                    case "Via CASHLESS":
                        request = new BuatPesananRequest(foodId + "" , customerId + "", ""+ etPromo, responseListener, "/createCashlessInvoice");
                        break;
                }

                RequestQueue queue = Volley.newRequestQueue(BuatPesananActivity.this);
                queue.add(request);
            }
        });
    }
}