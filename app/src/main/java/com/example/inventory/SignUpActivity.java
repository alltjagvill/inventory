package com.example.inventory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class SignUpActivity extends AppCompatActivity {
    TextView emailTextView;
    TextView passwordTextView;
    TextView passwordConfirmTextView;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;
    private CollectionReference userCollection;
    private DocumentReference userDoc;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        userCollection = firestore.collection("users");

        emailTextView = findViewById(R.id.signUpEmail);
        passwordTextView = findViewById(R.id.signupPassword);
        passwordConfirmTextView = findViewById(R.id.signupPasswordAgain);
    }

    public void SignUpSubmit(View view)
    {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String passwordConfirm = passwordConfirmTextView.getText().toString();

        if (!passwordConfirm.equals(password) || email.equals("") || password.equals(""))
        {
            if (!passwordConfirm.equals(password) && !email.equals("") && !password.equals(""))
            {
                Toast.makeText(this, R.string.signup_password_missmatch, Toast.LENGTH_SHORT).show();


            }
            else
            {
                Toast.makeText(this, R.string.empty_email_pass, Toast.LENGTH_SHORT).show();
            }


        }
        else
        {
            CreateAccount(email, password);
        }

    }

    public void SignUpCancel(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void CreateAccount(String email, String password)
    {
        Log.d("AccountCreate", "Here we go");
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    userID = firebaseAuth.getUid();
                    if (userID != null) {
                        userDoc = userCollection.document(userID);
                    }

                    userDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                             if (task.isSuccessful())
                             {
                                 try {
                                     DocumentSnapshot documentSnapshot = task.getResult();
                                     if (documentSnapshot.exists())
                                     {
                                         GoToCreateHousehold();
                                     }
                                     else
                                     {
                                         Log.d("CreateUserDoc", "Starting to create");
                                         Map<String, Object> user = new HashMap<>();
                                         user.put("userID", userID);
                                         user.put("hasHome", false);

                                         userDoc.set(user);
                                        // Toast.makeText(SignUpActivity.this, "DocumentSnapshot", Toast.LENGTH_SHORT).show();
                                         GoToCreateHousehold();
                                     }
                                 }
                                 catch (NullPointerException e)
                                 {
                                     Log.d("CreateUserDoc", e.toString());
                                     Toast.makeText(SignUpActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                                 }
                             }
                        }
                    });


                }
                else
                {
                    Toast.makeText(SignUpActivity.this, R.string.check_all_fields, Toast.LENGTH_SHORT).show();
                }
            }
        }
        );





    }

    private void GoToCreateHousehold()
    {
        Intent intent = new Intent(this, CreateDefaultHouseHold.class);
        startActivity(intent);
    }
}
