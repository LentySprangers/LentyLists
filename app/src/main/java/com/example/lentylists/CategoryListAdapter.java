package com.example.lentylists;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.MyViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<Category> categories = new ArrayList<>();


    public CategoryListAdapter(Context context, ArrayList<Category> categories) {
        Log.d(TAG, "Constructor was called");

        this.context = context;
        if (categories != null) {
            this.categories = categories;

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final String TAG = getClass().getSimpleName();

        public RecyclerView categoryRecyclerView;
        public TextView categoryNameTextView;
        public TextView itemCountTextView;
        CardView categoryCardView;


        MyViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG, "Constructor was called");
            categoryRecyclerView = itemView.findViewById(R.id.category_RecyclerView);

            categoryCardView = itemView.findViewById(R.id.cardView_category);

            categoryNameTextView = itemView.findViewById(R.id.category_name_textView);
            itemCountTextView = itemView.findViewById(R.id.number_of_items_TextView);


        }

    }

    @NonNull
    @Override
    public CategoryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "OnCreateViewHolder was called");

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_category, parent, false);
        return new CategoryListAdapter.MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final CategoryListAdapter.MyViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder was called");

        position = holder.getAdapterPosition();
        Category category = categories.get(position);
        holder.categoryNameTextView.setText(category.getName());
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        int itemCount = databaseHelper.readInventoryItemByCategoryID(category.getId()).size();
        holder.itemCountTextView.setText("Items: " + itemCount);

        holder.categoryCardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recyclerview_anim));


        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int categoryId = category.getId();
                Intent intent = new Intent(context, InventoryItemListActivity.class);
                intent.putExtra("CategoryId", categoryId);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount was called");
        return categories.size();
    }


}