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
    private ArrayList<Item> listItems = new ArrayList<Item>();


    public InventoryListAdapter(Context context, ArrayList<Item> listItems) {
        Log.d(TAG, "Constructor was called");

        this.context = context;
        if (listItems != null) {
            this.listItems = listItems;

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final String TAG = getClass().getSimpleName();

        public RecyclerView listItemName;
        public TextView itemName;
        public TextView inStockCount;
        public TextView inUseCount;
        CardView cardView;


        MyViewHolder(@NonNull View itemView) {

            super(itemView);
            Log.d(TAG, "Constructor was called");
            listItemName = itemView.findViewById(R.id.list_items);
            itemName = itemView.findViewById(R.id.item_name);
            inStockCount = itemView.findViewById(R.id.in_stock);
            inUseCount = itemView.findViewById(R.id.in_use);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "OnCreateViewHolder was called");

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.simple_list_item, parent, false);
        return new MyViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        Log.d(TAG, "OnBindViewHolder was called");

        position = holder.getAdapterPosition();
        Item listItem = listItems.get(position);
        holder.itemName.setText(listItem.getName());
        holder.inStockCount.setText("In Stock: " + listItem.getInStockCount());
        holder.inUseCount.setText("In Use: " + listItem.getInUseCount());


        holder.cardView.startAnimation(AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recyclerview_anim));


        // TODO implement onClickListener for recyclerview
        //Recyclerview onClickListener
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });


    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount was called");
        return listItems.size();
    }


}
