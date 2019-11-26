package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class EditItemActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    private TextView itemNameTextView;
    private TextView itemCriticalTextView;
    private TextView itemDeleteTextView;

    private CollectionReference homeCol;
    //private CollectionReference userRoomsCol;
    private DocumentReference userHomeDoc;
    private CollectionReference userItemsCol;

    private String userID;
    private String itemCriticalCountString;
    private String itemNameString;

    private ListItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        firestore = FirebaseFirestore.getInstance();
        homeCol = firestore.collection("homes");
        userHomeDoc = homeCol.document(userID);
        //   userRoomsCol = userHomeDoc.collection("rooms");
        userItemsCol = userHomeDoc.collection("items");

        itemNameTextView = findViewById(R.id.editItemNameEdit);
        itemCriticalTextView = findViewById(R.id.editItemCriticalEdit);
        itemDeleteTextView = findViewById(R.id.editItemDeleteEdit);

        item = (ListItem) getIntent().getSerializableExtra("ITEMOBJECT");

        itemNameString = item.getItemName();
        itemCriticalCountString = Integer.toString(item.getItemCritical());


        itemNameTextView.setText(itemNameString);
        itemCriticalTextView.setText(itemCriticalCountString);

        Log.d("IAMCREATED!", Integer.toString(item.getItemCritical()));


    }

    public void GoBack(View view)
    {
        onBackPressed();
    }

    public void UpdateItem(View view)
    {

            String setItemName = itemNameTextView.getText().toString();
            String setItemCriticalCountString = itemCriticalTextView.getText().toString();
        if (!setItemName.equals("")  && !setItemCriticalCountString.equals("") && setItemCriticalCountString.matches("[0-9]+") ) {

            int setItemCriticalCountInt = Integer.parseInt(setItemCriticalCountString);

            item.setItemName(setItemName);
            item.setItemCritical(setItemCriticalCountInt);

            Map<String, Object> updateItem = new HashMap<>();

            updateItem.put("itemName", item.getItemName());
            updateItem.put("itemCritical", item.getItemCritical());

            if (item.getItemCount() <= item.getItemCritical() && !item.getTobuy() )
            {
                item.setTobuy(true);
                updateItem.put("toBuy", item.getTobuy());
            }

            if (item.getItemCount() > item.getItemCritical() && item.getTobuy())
            {
                item.setTobuy(false);
                updateItem.put("toBuy", false);
            }

            firestore.collection("homes").document(userID).collection("items").document(item.getItemID()).update(updateItem).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("Add item count", "Works");

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

    public void DeleteItem(View view)
    {
        String deleteString = itemDeleteTextView.getText().toString();
        //String deleteMatchString = getResources().getString(R.string.delete_text);
        if (deleteString.equals(getResources().getString(R.string.delete_text)))
        {
            firestore.collection("homes").document(userID).collection("items").document(item.getItemID()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("Deleted: ", item.getItemName());
                    Toast.makeText(EditItemActivity.this, R.string.item_deleted, Toast.LENGTH_SHORT).show();

                }
            });

            Intent intent = new Intent(this, StartActivity.class);

            startActivity(intent);

        }
        else
        {
            Toast.makeText(this, R.string.need_text_delete, Toast.LENGTH_LONG).show();
        }
    }

}
