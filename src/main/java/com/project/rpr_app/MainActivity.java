package com.project.rpr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {

    //buttons
    private Button loginButton;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseAuth firebaseAuth;
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null) {
            User_LoggedIn.read_LoggedIn_User();
            moveTo(PaginaPrinci.class);
        }


        loginButton = findViewById(R.id.buttonlog);
        registerButton = findViewById(R.id.buttonreg);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTo(RegisterState.class);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseAuth firebaseAuth;
                firebaseAuth = FirebaseAuth.getInstance();
                if(firebaseAuth.getCurrentUser() == null)
                    moveTo(Login_State.class);
                else
                    moveTo(PaginaPrinci.class);
            }
        });

    }

    public void moveTo(Class state)
    {
        Intent intent = new Intent(MainActivity.this, state);
        startActivity(intent);
    }


}

   /*
   *
    private Button button;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.buttonlog);
        button2 = findViewById(R.id.buttonreg);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToRegisterrr();
            }
            });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLoginnn();
            }
            });

    }

*/
