package com.example.lr44razrmp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private List<Product> productList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ProductAdapter(productList);
        recyclerView.setAdapter(adapter);

        loadProductsFromRaw();
    }

    private void loadProductsFromRaw() {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    InputStream is = getResources().openRawResource(R.raw.product);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    StringBuilder sb = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                    return sb.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String jsonString) {
                if (jsonString != null) {
                    parseJson(jsonString);
                } else {
                    Toast.makeText(MainActivity.this,
                            "Ошибка при чтении JSON файла",
                            Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void parseJson(String jsonString) {
        try {
            JSONArray jsonArray = new JSONArray(jsonString);
            productList.clear();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject productJson = jsonArray.getJSONObject(i);

                int id = productJson.getInt("id");
                String title = productJson.getString("title");
                double price = productJson.getDouble("price");
                String description = productJson.getString("description");
                String category = productJson.getString("category");
                String image = productJson.getString("image");

                JSONObject ratingJson = productJson.getJSONObject("rating");
                double rate = ratingJson.getDouble("rate");
                int count = ratingJson.getInt("count");

                Rating rating = new Rating(rate, count);
                Product product = new Product(id, title, price, description,
                        category, image, rating);

                productList.add(product);
            }

            adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this,
                    "Ошибка при разборе JSON: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}