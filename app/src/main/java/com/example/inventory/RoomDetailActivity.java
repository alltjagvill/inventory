package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory.Adapter.InventoryListAdapter;
import com.example.inventory.Model.ListItem;
import com.example.inventory.Model.ListRoom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class RoomDetailActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    private CollectionReference homeCol;
    private CollectionReference userRoomsCol;
    private DocumentReference userHomeDoc;
    private CollectionReference userItemsCol;

    private String userID;
    private String roomID;

    private TextView roomNameTextview;
    private TextView roomDescriptionTextview;
    private RecyclerView inventoryListRecyclerview;
    private RecyclerView.Adapter adapter;

    private String roomNameString;
    private String roomDescriptionString;

    private List<ListItem> inventoryItemList;

    private ArrayList<ListItem> itemListHolder = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        ListRoom room = (ListRoom) getIntent().getSerializableExtra("ROOMOBJECT");

        roomNameString = room.getName();
        roomDescriptionString = room.getDescription();
        roomID = room.getRoomID();

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        firestore = FirebaseFirestore.getInstance();
        homeCol = firestore.collection("homes");
        userHomeDoc = homeCol.document(userID);
        userRoomsCol = userHomeDoc.collection("rooms");
        userItemsCol = userHomeDoc.collection("items");

        roomNameTextview = findViewById(R.id.roomRoomName);
        roomDescriptionTextview = findViewById(R.id.roomRoomDescription);
        inventoryListRecyclerview = findViewById(R.id.roomRoomInventory);
        inventoryListRecyclerview.setHasFixedSize(true);
        inventoryListRecyclerview.setLayoutManager(new LinearLayoutManager(this));



        roomNameTextview.setText(roomNameString);
        roomDescriptionTextview.setText(roomDescriptionString);

        inventoryItemList = new ArrayList<>();


        Log.d("AddingItems", roomID);

        userItemsCol.whereEqualTo("roomID", roomID).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                    {
                        ListItem item = documentSnapshot.toObject(ListItem.class);

                        inventoryItemList.add(item);
                        AddItemToList(item);
                        Log.d("AddingItems", "Name: " + item.getItemName());
                        Log.d("AddingItems", "Name: " + item.getItemID());
                    }
                    Log.d("AddingItems", Integer.toString(inventoryItemList.size()));


                }
                adapter = new InventoryListAdapter(RoomDetailActivity.this, inventoryItemList, new InventoryListAdapter.InventoryListAdapterListener()
                {
                    @Override
                    public void addItemCountOnclick(View v, int position, ListItem item) {

                        AddItemCount(item);


                    }

                    @Override
                    public void removeItemCountOnClick(View v, int position) {

                    }
                });
                inventoryListRecyclerview.setAdapter(adapter);

            }

        });
        Log.d("Hej", Integer.toString(itemListHolder.size()));
      //  Log.d("AddingItems", Integer.toString(inventoryItemList.size()));
//        for (int i = 0; i < 10; i++)
//        {
//            //String name, String itemID, String roomID, String roomName, int itemCount, int warningCount, int criticalCount
//            ListItem item = new ListItem("Item " + (i + 1), "eafaef", "gfegegw", "Kitchen", 1, 1, 1);
//            inventoryItemList.add(item);
//
//
//        }


//        adapter = new InventoryListAdapter(this, inventoryItemList);
//        inventoryListRecyclerview.setAdapter(adapter);

    }


    public void AddItem(View view)
    {
        Toast.makeText(this, "Item Added!", Toast.LENGTH_SHORT).show();
    }

    public void AddItemCount(ListItem item)
    {
        Log.d("Hej", item.getItemName());

        //OnCompleteListerner on document where itemID is this ItemsID

        //Check if itemCount =< criticalCount
    }

    public void RemoveItemCount(View view)
    {
        Toast.makeText(this, "Item Removed!", Toast.LENGTH_SHORT).show();
    }

    private void AddItemToList(ListItem item)
    {
        Log.d("Item", item.getItemName());
        itemListHolder.add(item);
    }


}
