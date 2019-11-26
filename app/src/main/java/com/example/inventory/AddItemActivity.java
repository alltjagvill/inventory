package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.inventory.Model.ListItem;
import com.example.inventory.Model.ListRoom;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    private TextView itemNameTextView;
    private TextView itemCriticalTextView;
    private TextView itemDeleteTextView;

    private CollectionReference homeCol;
    private CollectionReference userRoomsCol;
    private DocumentReference userHomeDoc;
    private CollectionReference userItemsCol;
    private DocumentReference itemDoc;

    private String userID;
    private String itemCriticalCountString;
    private String itemNameString;
    private String itemDocId;

    private ListItem item;
    private ListRoom room;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        firestore = FirebaseFirestore.getInstance();
        homeCol = firestore.collection("homes");
        userHomeDoc = homeCol.document(userID);
        userRoomsCol = userHomeDoc.collection("rooms");
        userItemsCol = userHomeDoc.collection("items");
        itemDoc = userItemsCol.document();

        room = (ListRoom) getIntent().getSerializableExtra("ROOMOBJECT");

        itemNameTextView = findViewById(R.id.addItemNameEdit);
        itemCriticalTextView = findViewById(R.id.addItemCriticalEdit);

        itemDocId = itemDoc.getId();

        Log.d("ITEMID", itemDocId);
    }


    public void AddNewItem(View view)
    {

        String setItemName = itemNameTextView.getText().toString();
        String setItemCriticalCountString = itemCriticalTextView.getText().toString();

        if (!setItemName.equals("")  && !setItemCriticalCountString.equals("") && setItemCriticalCountString.matches("[0-9]+") ) {
            Map<String, Object> addNewItem = new HashMap<>();
            addNewItem.put("itemName", setItemName);
            addNewItem.put("itemCritical", Integer.parseInt(setItemCriticalCountString));
            addNewItem.put("itemID", itemDocId);
            addNewItem.put("itemCount", 0);
            addNewItem.put("itemWarning", 0);
            addNewItem.put("roomID", room.getRoomID());
            addNewItem.put("roomName", room.getName());
            addNewItem.put("toBuy", true);
            firestore.collection("homes").document(userID).collection("items").document(itemDocId).set(addNewItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("ITEMADD", "Works");
                    Toast.makeText(AddItemActivity.this, R.string.item_added, Toast.LENGTH_LONG).show();
                }


            });

            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);



        }
        else
        {
            Toast.makeText(this, R.string.check_all_fields, Toast.LENGTH_SHORT).show();

        }

    }

    public void GoBack(View view)
    {
        onBackPressed();
    }
}
