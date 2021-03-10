package com.project.rpr_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class PaginaPrinci extends AppCompatActivity {
    Dialog myDialog, myDialog2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_princi);
        myDialog = new Dialog(this);
        myDialog2 = new Dialog(this);
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

    public void InfoPopup(View v) {
        TextView textt;
        myDialog2.setContentView(R.layout.activity_infopupup2);
        textt =(TextView) myDialog2.findViewById(R.id.textView12);
        myDialog2.show();
    }

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplication(),MainActivity.class));
        finish();
    }

    public void  moveTo(Class state )
    {
        Intent intent = new Intent(PaginaPrinci.this,state);
        startActivity(intent);
    }

}