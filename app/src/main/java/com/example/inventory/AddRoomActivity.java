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

public class AddRoomActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    private TextView roomNameTextView;
    private TextView roomDescriptionTextView;

    private CollectionReference homeCol;
    private CollectionReference userRoomsCol;
    private DocumentReference userHomeDoc;
    private CollectionReference userItemsCol;
    private DocumentReference roomDoc;

    private String userID;
    private String roomDocId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        firestore = FirebaseFirestore.getInstance();
        homeCol = firestore.collection("homes");
        userHomeDoc = homeCol.document(userID);
        userRoomsCol = userHomeDoc.collection("rooms");
        userItemsCol = userHomeDoc.collection("items");
        roomDoc = userItemsCol.document();

        roomNameTextView = findViewById(R.id.addRoomNameEdit);
        roomDescriptionTextView = findViewById(R.id.addRoomDescriptionEdit);

        roomDocId = roomDoc.getId();
    }

    public void AddNewRoom(View view)
    {

        String setRoomName = roomNameTextView.getText().toString();
        String setRoomDescriptionString = roomDescriptionTextView.getText().toString();

        if (!setRoomName.equals("")  && !setRoomDescriptionString.equals("")) {
            Map<String, Object> addNewItem = new HashMap<>();
            addNewItem.put("name", setRoomName);
            addNewItem.put("description", setRoomDescriptionString);
            addNewItem.put("roomID", roomDocId);
            firestore.collection("homes").document(userID).collection("rooms").document(roomDocId).set(addNewItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("ROOMADD", "Works");
                    Toast.makeText(AddRoomActivity.this, R.string.room_added, Toast.LENGTH_LONG).show();
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
