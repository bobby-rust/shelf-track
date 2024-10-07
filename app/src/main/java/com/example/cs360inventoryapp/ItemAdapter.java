package com.example.cs360inventoryapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private List<Item> itemList;
    NavController navController;

    public ItemAdapter(List<Item> itemList, NavController navController) {
        this.navController = navController;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.itemName.setText(item.getName());
        holder.itemDescription.setText(item.getDescription());
        holder.itemImage.setImageResource(item.getImage());

        holder.detailsButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_homeFragment_to_itemDetails);
        });

        holder.imageButton.setOnClickListener(v -> {
            itemList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, itemList.size());
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button detailsButton;
        TextView itemName;
        TextView itemDescription;
        ImageView itemImage;
        ImageButton imageButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.textViewItemName);
            itemDescription = itemView.findViewById(R.id.textViewItemDescription);
            itemImage = itemView.findViewById(R.id.imageViewItem);
            detailsButton = itemView.findViewById(R.id.buttonItemDetails);
            imageButton = itemView.findViewById(R.id.imageButtonRemoveItem);
        }
    }
}
