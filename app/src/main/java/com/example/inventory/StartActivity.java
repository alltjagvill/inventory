package com.example.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class StartActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void LogOut(View view)
    {
        firebaseAuth.signOut();

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
