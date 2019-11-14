package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RoomsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        bottomNavigationView = findViewById(R.id.bottomNavRooms);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.action_rooms:
                        Intent rooms = new Intent(RoomsActivity.this, RoomsActivity.class);
                        startActivity(rooms);
                        break;

                    case R.id.action_home:
                        Intent home = new Intent(RoomsActivity.this, StartActivity.class);
                        startActivity(home);
                        break;

                    case R.id.action_settings:
                        Intent settings = new Intent(RoomsActivity.this, SettingsActivity.class);
                        startActivity(settings);
                        break;
                }

                return true;
            }
        });
    }


}
