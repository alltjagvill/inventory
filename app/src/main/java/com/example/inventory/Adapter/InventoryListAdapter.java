package com.example.inventory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.EditItemActivity;
import com.example.inventory.Model.ListItem;
import com.example.inventory.Model.ListRoom;
import com.example.inventory.R;
import com.example.inventory.RoomDetailActivity;

import java.util.List;

public class InventoryListAdapter extends RecyclerView.Adapter<InventoryListAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;
    private InventoryListAdapterListener onClickListener;

    public InventoryListAdapter(Context context, List itemList, InventoryListAdapterListener listener)
    {
        this.context = context;
        this.listItems = itemList;
        this.onClickListener = listener;
    }

    @NonNull
    @Override
    public InventoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_inventory_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryListAdapter.ViewHolder holder, int position) {



        ListItem item = listItems.get(position);

        holder.name.setText(item.getItemName());
        holder.itemCount.setText(Integer.toString(item.getItemCount()));




    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView itemCount;
        ImageView addItemCountButton;
        ImageView removeItemCountButton;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.inventoryName);
            itemCount = itemView.findViewById(R.id.inventoryCount);
            addItemCountButton = itemView.findViewById(R.id.roomRoomAddButton);
            removeItemCountButton = itemView.findViewById(R.id.roomRoomRemoveButton);

            addItemCountButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ListItem item = listItems.get(getAdapterPosition());
                    onClickListener.addItemCountOnclick(v, getAdapterPosition(), item);


                }
            });

            removeItemCountButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ListItem item = listItems.get(getAdapterPosition());
                    onClickListener.removeItemCountOnClick(v, getAdapterPosition(), item);
                }
            });



        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            ListItem item = listItems.get(position);
            Log.d("ItemClicked", "Item: " + item.getItemName());
            Intent intent = new Intent(context, EditItemActivity.class);
            intent.putExtra("ITEMOBJECT", item);
            context.startActivity(intent);


        }
    }

    public interface InventoryListAdapterListener
    {
        void addItemCountOnclick(View v, int position, ListItem item);
        void removeItemCountOnClick(View v, int position, ListItem item);
    }

}
