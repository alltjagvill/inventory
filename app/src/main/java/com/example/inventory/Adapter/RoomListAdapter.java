package com.example.inventory.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Model.ListRoom;
import com.example.inventory.R;
import com.example.inventory.RoomDetailActivity;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

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


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView name;
        public TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.roomsNameID);
            description = itemView.findViewById(R.id.roomsDescriptionID);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            ListRoom room = listRooms.get(position);
            Log.d("RoomClicked", "Room: " + room.getName());
            Intent intent = new Intent(context, RoomDetailActivity.class);
            intent.putExtra("ROOMOBJECT", room);
            context.startActivity(intent);

//Fragment roomFragment = new RoomDetailFragment();
//            Bundle roomBundle = new Bundle();
//            roomBundle.putSerializable("room_id", room);
//            roomFragment.setArguments(roomBundle);
//            StartActivity startActivity = (StartActivity) context;
//            startActivity.switchFragment(R.id.nav_host_fragment, roomFragment);

        }
    }


}
