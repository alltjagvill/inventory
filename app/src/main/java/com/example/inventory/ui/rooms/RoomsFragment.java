package com.example.inventory.ui.rooms;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inventory.Adapter.RoomListAdapter;
import com.example.inventory.Model.ListRoom;
import com.example.inventory.R;
import com.example.inventory.StartActivity;
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

public class RoomsFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private CollectionReference homeCol;
    private DocumentReference userHomeDoc;
    //private CollectionReference userHome;
    private CollectionReference userRooms;
  //  private DocumentReference
    //private DocumentReference userRooms;


    private String userID;
    private RoomsViewModel roomsViewModel;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ListRoom> listRooms;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_rooms, container, false);



        recyclerView =  root.findViewById(R.id.roomRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        firebaseAuth = FirebaseAuth.getInstance();
        userID = firebaseAuth.getUid();
        firestore = FirebaseFirestore.getInstance();
        homeCol = firestore.collection("homes");
        userHomeDoc = homeCol.document(userID);
        userRooms = userHomeDoc.collection("rooms");

        String test = userRooms.getPath();
        Log.d("GetRooms", test);


        listRooms = new ArrayList<>();

        userRooms.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful())
                {
                    Log.d("GetRooms", Integer.toString(task.getResult().getDocuments().size()));
                    for (QueryDocumentSnapshot snapshot : task.getResult())
                    {
                        Log.d("GetRooms", "ID: " + snapshot.getId());
                        Log.d("GetRooms", "ID: " + snapshot.getData());
                        ListRoom room = snapshot.toObject(ListRoom.class);
                        Log.d("GetRooms", room.getName());

                        listRooms.add(room);
                    }
                }
                adapter = new RoomListAdapter(getContext(), listRooms);
                recyclerView.setAdapter(adapter);
                Log.d("GetRooms", "List size when inside snapshot: " + Integer.toString(listRooms.size()));
            }
        });
//        for (int i = 0; i < 10; i++)
//        {
//            ListRoom room = new ListRoom("Kitchen" + (i + 1), "Milk and everything!");
//            listRooms.add(room);
//
//        }

        Log.d("GetRooms", "List size just before applying adapter: " + Integer.toString(listRooms.size()));
//        adapter = new RoomListAdapter(getContext(), listRooms);
//        recyclerView.setAdapter(adapter);
//        roomsViewModel =
//                ViewModelProviders.of(this).get(RoomsViewModel.class);
//
//        final TextView textView = root.findViewById(R.id.text_dashboard);
//        roomsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    public interface Callbacks
    {
        public void onItemSelected(ListRoom room);
    }


}