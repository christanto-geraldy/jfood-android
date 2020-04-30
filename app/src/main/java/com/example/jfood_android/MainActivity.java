package com.example.jfood_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Seller> listSeller = new ArrayList<>();
    private ArrayList<Food> foodIdList = new ArrayList<>();
    private HashMap<Seller, ArrayList<Food>> childMapping = new HashMap<>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;

    protected void refreshList(){
        Response.Listener<String> responseListener = new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONArray jsonResponse = new JSONArray((response));
                    if(jsonResponse!= null) {
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
                    }
                } catch (JSONException ex) {
                        ex.printStackTrace();
                }
            }
        };
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listAdapter = new MainListAdapter(MainActivity.this, listSeller, childMapping);
        // setting list adapter
        expListView.setAdapter(listAdapter);
        refreshList();
    }
}
