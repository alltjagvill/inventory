package com.example.inventory.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Model.ListRoom;
import com.example.inventory.R;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder>{

    private Context context;
    private List<ListRoom> listRooms;

    public RoomListAdapter(Context context, List listRooms)
    {
        this.context = context;
        this.listRooms = listRooms;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_rooms, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomListAdapter.ViewHolder holder, int position) {

        ListRoom room = listRooms.get(position);
        holder.name.setText(room.getName());
        holder.description.setText(room.getDescription());


    }

    @Override
    public int getItemCount() {
        return listRooms.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        public TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.roomsNameID);
            description = itemView.findViewById(R.id.roomsDescriptionID);
        }
    }
}
