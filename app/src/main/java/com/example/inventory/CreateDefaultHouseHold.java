package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateDefaultHouseHold extends AppCompatActivity {

    TextView houseName;
    TextView houseAddress;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    private CollectionReference homeCollection;
    private DocumentReference homeDoc;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_default_house_hold);
        houseName = findViewById(R.id.householdCreateName);
        houseAddress = findViewById(R.id.householdCreateAddress);

        firestore = FirebaseFirestore.getInstance();
        homeCollection = firestore.collection("homes");
        firebaseAuth = FirebaseAuth.getInstance();

        //Log.d("CreateHousehold", "Now we got to household creation");
    }


    public void CreateHouseholdSubmit(View view)
    {
        String householdName = houseName.getText().toString();
        String householdAddress = houseAddress.getText().toString();

        if (householdName.equals(""))
        {
            Toast.makeText(this, R.string.household_name_cant_be_empty, Toast.LENGTH_SHORT).show();
        }
        else
        {
            CreateHousehold(householdName, householdAddress);
        }
    }

    private void CreateHousehold(final String housholdName, final String householdAddress)
    {
        userID = firebaseAuth.getUid();
        if (userID != null)
        {
            homeDoc = homeCollection.document(userID);

            homeDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                      if (task.isSuccessful())
                      {
                          try
                          {
                              DocumentSnapshot documentSnapshot = task.getResult();
                              if (documentSnapshot.exists())
                              {
                                  GoToStartActivity();
                              }
                              else
                              {

                                  Map<String, Object> userHome = new HashMap<>();
                                  userHome.put("housholdName", housholdName);
                                  userHome.put("householdAddress", householdAddress);
                                  userHome.put("userID", userID);

                                  homeDoc.set(userHome);
                                  GoToStartActivity();
                              }
                          }
                          catch (NullPointerException e)
                          {
                              Toast.makeText(CreateDefaultHouseHold.this, e.toString(), Toast.LENGTH_LONG).show();
                          }
                      }
                }
            });
        }
    }

    public void HouseholdCreateSkip(View view)
    {
        GoToStartActivity();
    }

    private void GoToStartActivity()
    {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
