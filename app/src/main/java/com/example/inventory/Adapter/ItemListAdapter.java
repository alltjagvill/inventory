package com.example.inventory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Model.ListItem;
import com.example.inventory.R;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;

    public ItemListAdapter(Context context, List itemList)
    {
        this.context = context;
        this.listItems = itemList;
    }

    @NonNull
    @Override
    public ItemListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemListAdapter.ViewHolder holder, int position) {



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

        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            name = itemView.findViewById(R.id.itemName);
            itemCount = itemView.findViewById(R.id.itemCount);
        }

        @Override
        public void onClick(View v) {



        }
    }

}
