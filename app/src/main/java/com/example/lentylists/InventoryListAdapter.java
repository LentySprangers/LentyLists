package com.example.lentylists;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.MyViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<String> listItems = new ArrayList<String>();


    public InventoryListAdapter(Context context, ArrayList listItems) {
        Log.d(TAG, "Constructor was called");

        this.context = context;
        if (listItems != null) {
            this.listItems = listItems;

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        public RecyclerView listItemName;
        public TextView itemName;
        CardView cardView;


        MyViewHolder(@NonNull View itemView) {

            super(itemView);
            listItemName = itemView.findViewById(R.id.list_items);
            itemName = itemView.findViewById(R.id.item_name);
            cardView = itemView.findViewById(R.id.cardView);

        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.simple_list_item, parent, false);
        return new MyViewHolder(view);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        String listItem = listItems.get(position);
        holder.itemName.setText(listItem);

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
        return listItems.size();
    }


}
