package com.project.rpr_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfesoriList extends AppCompatActivity {

    ListView myListview;
    List<Profesor> profesoriList;
    Dialog myDialog;

    DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesori_list);

        myListview = findViewById(R.id.lista_profesori);
        myDialog = new Dialog(this);

        profesoriList = new ArrayList<>();

        dbHelper = new DataBaseHelper(FirebaseDatabase.getInstance().getReference("profesori"));

        dbHelper.getmReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                profesoriList.clear();

                for(DataSnapshot profesorDatasnap : snapshot.getChildren())
                {
                    Profesor profesor = profesorDatasnap.getValue(Profesor.class);
                    profesoriList.add(profesor);
                }

                ListAdapter_Profesori adapter = new ListAdapter_Profesori(ProfesoriList.this, profesoriList);
                myListview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveTo(ProfilProfesor.class);
            }
        });
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
        Intent intent = new Intent(ProfesoriList.this,state);
        startActivity(intent);
    }
}