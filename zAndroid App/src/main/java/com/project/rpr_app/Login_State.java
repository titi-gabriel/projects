package com.project.rpr_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_State extends AppCompatActivity {

    private Button buttonLogin;
    EditText mEmail1,mParola1;
    FirebaseAuth fAuth;
    TextView ForgotTextLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.buttonlog);
        ForgotTextLink = findViewById(R.id.ForgotTextLink);

        mEmail1 = findViewById(R.id.EmailLogin);
        mParola1 = findViewById(R.id.ParolaLogin);

        fAuth = FirebaseAuth.getInstance();



        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = mEmail1.getText().toString().trim();
                String password = mParola1.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    mEmail1.setError("Email-ul este obligatoriu!");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mParola1.setError("Parola este obligatorie!");
                    return;
                }

                // autentificare
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Login_State.this, "Te-ai logat.", Toast.LENGTH_SHORT).show();
                            User_LoggedIn.read_LoggedIn_User();
                            startActivity(new Intent(getApplicationContext(), PaginaPrinci.class));
                        }else {
                            Toast.makeText(Login_State.this, "Eroare!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        ForgotTextLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Reseteaza Parola");
                passwordResetDialog.setMessage("Introdu mai jos email-ul tau");
                passwordResetDialog.setView(resetMail);

                passwordResetDialog.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //extract the email and send reset link
                        String mail = resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Login_State.this, "Link-ul pentru resetarea parolei a fost trimis pe Email.", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Login_State.this, "Eroare ! Link-ul nu a fost trimis." + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                passwordResetDialog.setNegativeButton("Nu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //close the dialog
                    }
                });

                passwordResetDialog.create().show();
            }
        });


    }


    public void moveTo(Class state){
        Intent intent= new Intent(Login_State.this , state);
        startActivity(intent);
    }
}