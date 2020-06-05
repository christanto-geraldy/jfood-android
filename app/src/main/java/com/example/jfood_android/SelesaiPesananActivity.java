package com.example.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <h1>Activity untuk menampilkan pesanan yang telah selesai</h1>
 * <p>Proses activity ketika sedang dijalankan</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class SelesaiPesananActivity extends AppCompatActivity {
    TextView tvInvoiceId, tvCustomerName, tvInvoiceDate, tvPaymentType, tvInvoiceStatus, tvPromoCode, tvFoodName, tvFoodPrice, tvTotalPrice;
    TextView staticPromoCode, staticCustomer, staticInvoiceDate,staticFood,staticInvoiceStatus,staticPayType,staticTotalPrice;
    Button btnCancel, btnFinish;

    String customerName, foodName, invoiceDate, promoCode, paymentType;
    int customerId, foodPrice, totalPrice, currentInvoiceId, deliveryFee, discount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai_pesanan);

        tvInvoiceId = findViewById(R.id.invoice_id);
        tvCustomerName = findViewById(R.id.customer_name);
        tvInvoiceDate = findViewById(R.id.invoice_date);
        tvPaymentType = findViewById(R.id.payment_type);
        tvInvoiceStatus = findViewById(R.id.invoice_status);
        tvPromoCode = findViewById(R.id.promo_code);
        tvFoodName = findViewById(R.id.food_name);
        tvFoodPrice = findViewById(R.id.food_price);
        tvTotalPrice = findViewById(R.id.total_price);
        staticCustomer = findViewById(R.id.static_customer);
        staticInvoiceDate = findViewById(R.id.st_invoice_date);
        staticPayType = findViewById(R.id.staticPaymentType);
        staticFood = findViewById(R.id.static_food);
        staticInvoiceStatus = findViewById(R.id.static_invoice_status);
        staticPromoCode = findViewById(R.id.static_promo_code);
        staticTotalPrice = findViewById(R.id.static_total_price);

        btnCancel = findViewById(R.id.btnCancel);
        btnFinish = findViewById(R.id.btnFinish);

        tvInvoiceId.setText("There are no ongoing orders");
        tvPromoCode.setVisibility(View.GONE);
        staticPromoCode.setVisibility(View.GONE);
        staticCustomer.setVisibility(View.GONE);
        staticInvoiceDate.setVisibility(View.GONE);
        staticFood.setVisibility(View.GONE);
        staticInvoiceStatus.setVisibility(View.GONE);
        staticPayType.setVisibility(View.GONE);
        staticTotalPrice.setVisibility(View.GONE);
        tvCustomerName.setVisibility(View.GONE);
        tvInvoiceDate.setVisibility(View.GONE);
        tvPaymentType.setVisibility(View.GONE);
        tvInvoiceStatus.setVisibility(View.GONE);
        tvFoodName.setVisibility(View.GONE);
        tvFoodPrice.setVisibility(View.GONE);
        tvTotalPrice.setVisibility(View.GONE);
        btnCancel.setVisibility(View.GONE);
        btnFinish.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customerName = extras.getString("customerName");
            customerId = extras.getInt("customerId");
            currentInvoiceId = extras.getInt("currentInvoiceId");
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fetchPesanan();
            }
        });

        Log.d("currentInvoiceId", String.valueOf(currentInvoiceId));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(SelesaiPesananActivity.this, "This invoice is cancelled", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("customerId", customerId);
                                intent.putExtra("customerName", customerName);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Please try again").create().show();
                        }
                    }
                };

                PesananBatalRequest request = new PesananBatalRequest(String.valueOf(currentInvoiceId), "Cancelled", responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(request);
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject != null) {
                                Toast.makeText(SelesaiPesananActivity.this, "This invoice is finished", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SelesaiPesananActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("customerId", customerId);
                                intent.putExtra("customerName", customerName);
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            AlertDialog.Builder builder = new AlertDialog.Builder(SelesaiPesananActivity.this);
                            builder.setMessage("Operation Failed! Please try again").create().show();
                        }
                    }
                };
                PesananSelesaiRequest request = new PesananSelesaiRequest(String.valueOf(currentInvoiceId), "Finished", responseListener);
                RequestQueue queue = Volley.newRequestQueue(SelesaiPesananActivity.this);
                queue.add(request);
            }
        });
    }

    public void fetchPesanan() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonResponse = new JSONArray(response);
                    if (jsonResponse != null) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject invoice = jsonResponse.getJSONObject(i);
                            JSONArray foods = invoice.getJSONArray("foods");
                            String invoiceStatus = invoice.getString("invoiceStatus");
                            if (invoiceStatus.equals("Ongoing")) {
                                for (int j = 0; j < foods.length(); j++) {
                                    JSONObject food = foods.getJSONObject(j);
                                    foodName = food.getString("name");
                                    foodPrice = food.getInt("price");
                                    tvFoodName.setText(foodName);
                                    tvFoodPrice.setText("Rp. " + foodPrice);
                                }

                                staticCustomer.setVisibility(View.VISIBLE);
                                staticInvoiceDate.setVisibility(View.VISIBLE);
                                staticFood.setVisibility(View.VISIBLE);
                                staticInvoiceStatus.setVisibility(View.VISIBLE);
                                staticPayType.setVisibility(View.VISIBLE);
                                staticTotalPrice.setVisibility(View.VISIBLE);
                                tvCustomerName.setVisibility(View.VISIBLE);
                                tvInvoiceDate.setVisibility(View.VISIBLE);
                                tvPaymentType.setVisibility(View.VISIBLE);
                                tvInvoiceStatus.setVisibility(View.VISIBLE);
                                tvFoodName.setVisibility(View.VISIBLE);
                                tvFoodPrice.setVisibility(View.VISIBLE);
                                tvTotalPrice.setVisibility(View.VISIBLE);
                                btnCancel.setVisibility(View.VISIBLE);
                                btnFinish.setVisibility(View.VISIBLE);

                                tvInvoiceId.setText("Invoice ID: " + currentInvoiceId);
                                tvCustomerName.setText(customerName);
                                tvInvoiceStatus.setText(invoiceStatus);

                                invoiceDate = invoice.getString("date");
                                DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
                                Date date = inputFormat.parse(invoiceDate);
                                Locale indonesia = new Locale("in", "ID");
                                SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm", indonesia);
                                invoiceDate = outputFormat.format(date);

                                tvInvoiceDate.setText(invoiceDate);
                                paymentType = invoice.getString("paymentType");
                                tvPaymentType.setText(paymentType);
                                totalPrice = invoice.getInt("totalPrice");
                                tvTotalPrice.setText("Rp. " + totalPrice);

                                switch (paymentType) {
                                    case "Cash":
                                        deliveryFee = invoice.getInt("deliveryFee");
                                        tvPromoCode.setVisibility(View.GONE);
                                        staticPromoCode.setVisibility(View.GONE);
                                        break;
                                    case "Cashless":
                                        JSONObject promo = invoice.getJSONObject("promo");
                                        promoCode = promo.getString("code");
                                        if(promo.isNull("code")) {
                                            tvPromoCode.setVisibility(View.GONE);
                                            staticPromoCode.setVisibility(View.GONE);
                                        } else{
                                            discount = promo.getInt("discount");
                                            tvPromoCode.setVisibility(View.VISIBLE);
                                            staticPromoCode.setVisibility(View.VISIBLE);
                                            tvPromoCode.setText(promoCode);
                                        }
                                        break;
                                }
                            } else {
                                tvInvoiceId.setText("There are no ongoing orders");
                                staticPromoCode.setVisibility(View.GONE);
                                staticCustomer.setVisibility(View.GONE);
                                staticInvoiceDate.setVisibility(View.GONE);
                                staticFood.setVisibility(View.GONE);
                                staticInvoiceStatus.setVisibility(View.GONE);
                                staticPayType.setVisibility(View.GONE);
                                staticTotalPrice.setVisibility(View.GONE);
                                tvCustomerName.setVisibility(View.GONE);
                                tvInvoiceDate.setVisibility(View.GONE);
                                tvPaymentType.setVisibility(View.GONE);
                                tvInvoiceStatus.setVisibility(View.GONE);
                                tvPromoCode.setVisibility(View.GONE);
                                tvFoodName.setVisibility(View.GONE);
                                tvFoodPrice.setVisibility(View.GONE);
                                tvTotalPrice.setVisibility(View.GONE);
                                btnCancel.setVisibility(View.GONE);
                                btnFinish.setVisibility(View.GONE);
                            }
                        }

                    }
                } catch (JSONException | ParseException e) {
                    Toast.makeText(SelesaiPesananActivity.this, ""+currentInvoiceId, Toast.LENGTH_LONG).show();
                }
            }
        };

        PesananFetchRequest request = new PesananFetchRequest(Integer.toString(customerId), responseListener);
        RequestQueue queue = new Volley().newRequestQueue(SelesaiPesananActivity.this);
        queue.add(request);
    }
}