package com.example.lentylists;

import android.content.Context;
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

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.MyViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<InventoryItem> inventoryItems = new ArrayList<>();


    public InventoryListAdapter(Context context, ArrayList<InventoryItem> inventoryItems) {
        Log.d(TAG, "Constructor was called");

        this.context = context;
        if (inventoryItems != null) {
            this.inventoryItems = inventoryItems;

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final String TAG = getClass().getSimpleName();

        public RecyclerView inventoryItemRecyclerView;
        public TextView inventoryItemNameTextView;
        public TextView inStockCountTextView;
        public TextView inUseCountTextView;
        CardView inventoryItemCardView;


        MyViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG, "Constructor was called");
            inventoryItemRecyclerView = itemView.findViewById(R.id.inventory_item_recyclerView);
            inventoryItemNameTextView = itemView.findViewById(R.id.inventory_item_name_TextView);
            inStockCountTextView = itemView.findViewById(R.id.in_stock_count_TextView);
            inUseCountTextView = itemView.findViewById(R.id.in_use_count_TextView);
            inventoryItemCardView = itemView.findViewById(R.id.cardView_inventory_item);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "OnCreateViewHolder was called");

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout_inventory_item, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder was called");

        position = holder.getAdapterPosition();
        InventoryItem inventoryItem = inventoryItems.get(position);
        holder.inventoryItemNameTextView.setText(inventoryItem.getName());
        holder.inStockCountTextView.setText("In Stock: " + inventoryItem.getInStockCount());
        holder.inUseCountTextView.setText("In Use: " + inventoryItem.getInUseCount());


        holder.inventoryItemCardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recyclerview_anim));


        // TODO implement onClickListener for recyclerview
        //Recyclerview onClickListener
//        holder.LinearLayout_inventory_item.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount was called");
        return inventoryItems.size();
    }


}