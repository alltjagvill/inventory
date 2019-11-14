package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        firebaseAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.bottomNavStart);
//        bottomNavigationView.setSelectedItemId(R.id.action_rooms);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {
                    case R.id.action_rooms:
                        Intent rooms = new Intent(StartActivity.this, RoomsActivity.class);
                        startActivity(rooms);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(StartActivity.this, StartActivity.class);
                        startActivity(home);
                        break;
                    case R.id.action_settings:
                        Intent settings = new Intent(StartActivity.this, SettingsActivity.class);
                        startActivity(settings);
                        break;
                }

                return true;
            }
        });

    }


    public void LogOut(View view)
    {
        firebaseAuth.signOut();

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
