package com.example.lr44razrmp;


import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView priceTextView;
        private TextView categoryTextView;
        private TextView ratingTextView;
        private TextView descriptionTextView;
        private TextView idTextView;
        private TextView imageTextView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            ratingTextView = itemView.findViewById(R.id.ratingTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            idTextView = itemView.findViewById(R.id.idTextView);
            imageTextView = itemView.findViewById(R.id.imageTextView);
        }

        public void bind(Product product) {
            idTextView.setText("ID: " + product.getId());
            titleTextView.setText(product.getTitle());
            priceTextView.setText("Цена: $" + product.getPrice());
            categoryTextView.setText("Категория: " + product.getCategory());
            ratingTextView.setText("Рейтинг: " + product.getRating().getRate() +
                    " (" + product.getRating().getCount() + " отзывов)");
            imageTextView.setText("Изображение: " + product.getImage());
            descriptionTextView.setText(product.getDescription());
        }
    }
}