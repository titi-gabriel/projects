package com.project.rpr_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.*;

public class Profil_Utilizator extends AppCompatActivity {

    TextView mNume, mPrenume, mEmail;
    Button mLoad, mLogout, mAfisareProfesori;
    DataBaseHelper dbHelper;
    FirebaseAuth fAuth;
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_utilizator);

        mNume = (TextView) findViewById(R.id.nume_utilizator);
        mPrenume = (TextView) findViewById(R.id.prenume_utilizator);
        mEmail = (TextView) findViewById(R.id.email_utlizator);

        mNume.setText(User_LoggedIn.getUser_loggedin().getNume());
        mPrenume.setText(User_LoggedIn.getUser_loggedin().getPrenume());
        mEmail.setText(User_LoggedIn.getUser_loggedin().getEmail());

    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button profesori,acasa,contact,info,delog;
        ImageButton profil_utiliz;
        myDialog.setContentView(R.layout.activity_custompopup);
        txtclose =(TextView) myDialog.findViewById(R.id.textView2);
        profesori = (Button) myDialog.findViewById(R.id.Profesori);
        profil_utiliz = (ImageButton) myDialog.findViewById(R.id.button_profil_utilz);
        acasa = (Button) myDialog.findViewById(R.id.acasa);
        contact = (Button) myDialog.findViewById(R.id.Contact);
        info = (Button) myDialog.findViewById(R.id.Info);
        delog = (Button) myDialog.findViewById(R.id.Delog);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        acasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTo(PaginaPrinci.class);
            }
        });
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTo(Contact.class);
            }
        });
        profesori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTo(ProfesoriList.class);
            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTo(Info.class);
            }
        });

        profil_utiliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveTo(Profil_Utilizator.class);
            }
        });
        myDialog.show();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplication(),MainActivity.class));
        finish();
    }

    public void  moveTo(Class state )
    {
        Intent intent = new Intent(Profil_Utilizator.this,state);
        startActivity(intent);
    }
}