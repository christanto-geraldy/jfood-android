package com.example.jfood_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * <h1>Activity untuk melihat history pesanan</h1>
 * <p>Proses activity ketika sedang dijalankan</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class HistoryActivity extends AppCompatActivity {

    //Instance variable
    private static int customerId;
    private static String customerName;

    TextView tvHistory;
    ListView lvHistory;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            customerId = extras.getInt("customerId");
            customerName = extras.getString("customerName");
        }

        tvHistory = findViewById(R.id.tvHistory);
        lvHistory = findViewById(R.id.lvHistory);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        //Thread untuk menjalankan fungsi initListView saat UI sedang dibuat
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initListView();
            }
        });
    }

    private void initListView() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ArrayList<String> invoiceList = new ArrayList<>();
                    JSONArray invoices = new JSONArray(response);
                    if(invoices.isNull(0))
                    {
                        Toast.makeText(HistoryActivity.this, "You don't have any invoices", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("customerId", customerId);
                        intent.putExtra("customerName", customerName);
                        startActivity(intent);
                    }
                    else {
                        for (int i = 0; i < invoices.length(); i++) {
                            String addTemp = "";
                            ArrayList<Food> temp = new ArrayList<Food>();
                            JSONObject invoice = invoices.getJSONObject(i);
                            JSONArray foods = invoice.getJSONArray("foods");
                            for (int j = 0; j < foods.length(); j++) {
                                JSONObject food = foods.getJSONObject(j);
                                JSONObject seller = food.getJSONObject("seller");
                                JSONObject location = seller.getJSONObject("location");
                                temp.add(new Food(food.getInt("id"),
                                        food.getString("name"),
                                        new Seller(seller.getInt("id"),
                                                seller.getString("name"),
                                                seller.getString("email"),
                                                seller.getString("phoneNumber"),
                                                new Location(location.getString("province"), location.getString("description"),
                                                        location.getString("city"))),
                                        food.getInt("price"), food.getString("category")));
                            }
                            for (Food food : temp) {
                                addTemp += "\n\t\tFood name\t\t\t: " + food.getName() +
                                        "\n\t\tFood Price\t\t\t\t: Rp. " + food.getPrice() +
                                        "\n\t\tFood Category\t: " + food.getCategory() + "\n";
                            }

                            String invoiceDate = invoice.getString("date");
                            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
                            Date date = inputFormat.parse(invoiceDate);
                            Locale indonesia = new Locale("in", "ID");
                            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy, HH:mm", indonesia);
                            invoiceDate = outputFormat.format(date);

                            invoiceList.add("\nInvoice id\t\t\t: " + invoice.getString("id") + "\n" +
                                    addTemp +
                                    "\nOrder Date\t\t\t: " + invoiceDate +
                                    "\nTotal Price\t\t\t\t: Rp. " + invoice.getInt("totalPrice") +
                                    "\nPayment Type\t: " + invoice.getString("paymentType") +
                                    "\nInvoice Status\t: " + invoice.getString("invoiceStatus") + "\n");
                        }
                        tvHistory.setVisibility(View.VISIBLE);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(HistoryActivity.this, android.R.layout.simple_list_item_1, invoiceList);
                        lvHistory.setAdapter(arrayAdapter);
                    }
                } catch (JSONException | ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        HistoryRequest request = new HistoryRequest(customerId, responseListener);
        RequestQueue queue = new Volley().newRequestQueue(HistoryActivity.this);
        queue.add(request);
    }
}