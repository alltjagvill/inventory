package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    TextView emailTextView;
    TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        emailTextView = findViewById(R.id.loginEmail);
        passwordTextView = findViewById(R.id.loginPassword);
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if ( user != null)
        {
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
        }

    }

    public void loginSubmit(View view)
    {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();

        if (email.matches("") || password.matches(""))
        {
            String emptyEmailOrPass = getText(R.string.empty_email_pass).toString();
            Toast.makeText(this, emptyEmailOrPass, Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        StartStartActivity();
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, getText(R.string.login_unsuccsessful), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void LoginSignUp(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    void StartStartActivity()
    {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }
}
