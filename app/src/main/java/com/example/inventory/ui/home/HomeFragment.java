package com.example.inventory.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Adapter.ItemListAdapter;
import com.example.inventory.Model.ListItem;
import com.example.inventory.R;
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

public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private CollectionReference homeColRef;
    private CollectionReference userItemsColRef;
    private DocumentReference userHomeDocRef;


    private String userID;
    private String items;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListItem> listItems;
    private List<ListItem> allItems;

   // private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = root.findViewById(R.id.shoppingList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userID = firebaseAuth.getUid();
        homeColRef =  firestore.collection("homes");
        userHomeDocRef = homeColRef.document(userID);
        userItemsColRef = userHomeDocRef.collection("items");


        listItems = new ArrayList<>();


        userItemsColRef.whereEqualTo("toBuy", true).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        final ListItem item = documentSnapshot.toObject(ListItem.class);

                        listItems.add(item);
                        // AddItemToList(item);
                        Log.d("AddingItems", "Name: " + item.getItemName());
                        Log.d("AddingItems", "Name: " + item.getItemID());
                    }
                    Log.d("AddingItems", Integer.toString(listItems.size()));
                }
                adapter = new ItemListAdapter(getContext(), listItems);
                recyclerView.setAdapter(adapter);
            }

            });

        return root;
    }
}