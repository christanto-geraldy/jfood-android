package com.example.jfood_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * <h1>Activity main menu aplikasi Jfood</h1>
 * <p>Proses activity ketika sedang dijalankan</p>
 *
 * @author Geraldy Christanto
 * @version 1.0
 * @since 6 Juni 2020
 */
public class MainActivity extends AppCompatActivity {
    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    TextView tvCustomer;

    private static int customerId;
    private static int currentInvoiceId;
    private static String customerName;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.top_app_bar);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            customerId = extras.getInt("customerId");
            customerName = extras.getString("customerName");
        }

        // Mendapatkan listview database seller dan makanan yang dijual
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        tvCustomer = findViewById(R.id.tvCustomer);

        tvCustomer.setText("Welcome, " + customerName);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshList();
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(MainActivity.this, BuatPesananActivity.class);
                int foodId = childMapping.get(listSeller.get(i)).get(i1).getId();
                String foodName = childMapping.get(listSeller.get(i)).get(i1).getName();
                String foodCategory = childMapping.get(listSeller.get(i)).get(i1).getCategory();
                int foodPrice = childMapping.get(listSeller.get(i)).get(i1).getPrice();

                intent.putExtra("food_id", foodId);
                intent.putExtra("food_name", foodName);
                intent.putExtra("food_category", foodCategory);
                intent.putExtra("food_price", foodPrice);

                intent.putExtra("customerId", customerId);
                intent.putExtra("customerName", customerName);

                startActivity(intent);
                return true;
            }
        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                Intent intent;
                switch(item.getItemId()){
                    case R.id.signOut:
                        Intent signOutIntent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(signOutIntent);
                        finish();
                        break;
                    case R.id.history:
                        intent = new Intent(MainActivity.this, HistoryActivity.class);
                        intent.putExtra("customerId", customerId);
                        intent.putExtra("customerName", customerName);
                        startActivity(intent);
                        break;
                    case R.id.pesanan:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fetchPesanan();
                            }
                        });
                        break;

                }
                return true;
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    /**
     * MEthod untuk mendapatkan jsonObject food
     */
    protected void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONArray jsonResponse = new JSONArray((response));
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject food = jsonResponse.getJSONObject(i);
                            JSONObject seller = food.getJSONObject("seller");
                            JSONObject location = seller.getJSONObject("location");

                            String city = location.getString("city");
                            String province = location.getString("province");
                            String description = location.getString("description");

                            Location location1 = new Location(city, province, description);

                            int sellerId = seller.getInt("id");
                            String sellerName = seller.getString("name");
                            String sellerEmail = seller.getString("email");
                            String sellerPhoneNumber = seller.getString("phoneNumber");

                            Seller seller1 = new Seller(sellerId, sellerName, sellerEmail, sellerPhoneNumber, location1);
                            if (listSeller.size() > 0) {
                                boolean success = true;
                                for (Seller sel : listSeller)
                                    if (sel.getId() == seller1.getId())
                                        success = false;
                                if (success) {
                                    listSeller.add(seller1);
                                }
                            } else {
                                listSeller.add(seller1);
                            }

                            int foodId = food.getInt("id");
                            int foodPrice = food.getInt("price");
                            String foodName = food.getString("name");
                            String foodCategory = food.getString("category");

                            Food food1 = new Food(foodId, foodName, seller1, foodPrice, foodCategory);
                            foodIdList.add(food1);

                            for (Seller sel : listSeller) {
                                ArrayList<Food> temp = new ArrayList<>();
                                for (Food foods : foodIdList) {
                                    if (foods.getSeller().getName().equals(sel.getName()) || foods.getSeller().getEmail().equals(sel.getEmail()) || foods.getSeller().getPhoneNumber().equals(sel.getPhoneNumber())) {
                                        temp.add(foods);
                                    }
                                }
                                childMapping.put(sel, temp);
                            }
                        }
                    listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
                    // Mengatur list adapter
                    expListView.setAdapter(listAdapter);
                } catch (JSONException ex) {
                        ex.printStackTrace();
                }
            }
        };
        MenuRequest menuRequest = new MenuRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(menuRequest);
    }

    /**
     * Method untuk mendapatkan pesanan yang sedang berjalan oleh customer
     */
    private void fetchPesanan(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray != null){

                        for(int i=0; i< jsonArray.length(); i++) {
                            JSONObject invoice = jsonArray.getJSONObject(i);
                            currentInvoiceId = invoice.getInt("id");
                        }
                        Intent intent = new Intent(MainActivity.this, SelesaiPesananActivity.class);
                        intent.putExtra("customerId", customerId);
                        intent.putExtra("customerName", customerName);
                        intent.putExtra("currentInvoiceId", currentInvoiceId);
                        startActivity(intent);
                    }
                } catch(JSONException e){
                    Toast.makeText(MainActivity.this,"Invoice is Empty", Toast.LENGTH_LONG).show();
                }
            }
        };

        PesananFetchRequest request = new PesananFetchRequest(Integer.toString(customerId),responseListener);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(request);
    }
}