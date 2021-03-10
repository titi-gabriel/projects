package com.project.rpr_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class RegisterState extends AppCompatActivity {
    EditText mNume,mPrenume,mJudet,mOras,mScoala,mCParola,mPhone,mEmail,mParola;
    TextView mtvDate;
    String date;
    Button mInregistreaza;
    CheckBox mcheckBox;
    Spinner mSex;

    private static final String TAG = "Register";
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    DatabaseReference dbUsers;
    FirebaseAuth fAuth;

    DataBaseHelper dbHelper = new DataBaseHelper(FirebaseDatabase.getInstance().getReference("users"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();

        mNume = findViewById(R.id.Nume);
        mPrenume = findViewById(R.id.Prenume);
        mJudet  = findViewById(R.id.Judet);
        mOras   = findViewById(R.id.Oras);
        mScoala = findViewById(R.id.Scoala);
        mParola = findViewById(R.id.Parola);
        mCParola = findViewById(R.id.CParola);
        mEmail = findViewById(R.id.Email);
        mPhone = findViewById(R.id.Phone);
        mtvDate = findViewById(R.id.tvDate);
        mcheckBox = findViewById(R.id.checkBox);
        mSex = (Spinner) findViewById(R.id.sex);

        mInregistreaza = findViewById(R.id.Inregistreaza);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        RegisterState.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                Log.d(TAG, "onDateSet: dd/mm/yyyy " + dayOfMonth + "/" + month + "/" + year);

                date = dayOfMonth + "/" + month + "/" +year;
                mDisplayDate.setText(date);
            }
        };

        ArrayAdapter<String> sexAdapter = new ArrayAdapter<String>(RegisterState.this, android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.sexes));

        sexAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSex.setAdapter(sexAdapter);

        mInregistreaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mParola.getText().toString().trim();
                String nume = mNume.getText().toString().trim();
                String prenume = mPrenume.getText().toString().trim();
                String judet = mJudet.getText().toString().trim();
                String oras = mOras.getText().toString().trim();
                String scoala = mScoala.getText().toString().trim();
                String cparola = mCParola.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String tvDate = mtvDate.getText().toString().trim();
                String checkBox = mcheckBox.getText().toString().trim();
                String sex = mSex.getSelectedItem().toString();

                if (TextUtils.isEmpty(tvDate)) {
                    mtvDate.setError("Data de nastere este obligatorie!");
                    return;
                }

                if (TextUtils.isEmpty(checkBox)) {
                    mcheckBox.setError("Trebuie sa fii de acord cu termenii si conditiile!");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email-ul este obligatoriu!");
                    return;
                }

                if (TextUtils.isEmpty(nume)) {
                    mEmail.setError("Numele este obligatoriu!");
                    return;
                }

                if (TextUtils.isEmpty(prenume)) {
                    mEmail.setError("Prenumele este obligatoriu!");
                    return;
                }

                if (TextUtils.isEmpty(judet)) {
                    mEmail.setError("Judetul este obligatoriu!");
                    return;
                }

                if (TextUtils.isEmpty(oras)) {
                    mEmail.setError("Orasul este obligatoriu!");
                    return;
                }

                if (TextUtils.isEmpty(scoala)) {
                    mEmail.setError("Scoala este obligatorie!");
                    return;
                }

                if (TextUtils.isEmpty(phone)) {
                    mEmail.setError("Numarul de telefon este obligatoriu!");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    mParola.setError("Parola este obligatorie!");
                    return;
                }

                if (password.length() < 6) {
                    mParola.setError("Parola este prea scurta!");
                    return;
                }

                if (TextUtils.isEmpty(cparola)) {
                    mEmail.setError("Parola este obligatorie!");
                    return;
                }

                if (!cparola.equals(password)) {
                    mCParola.setError("Parolele nu corespund!");
                    return;
                }



                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            String id = fAuth.getUid();
                            User user = new User(id,email,nume,prenume,date,sex,phone,judet,oras,scoala);
                            dbHelper.getmReference().child(id).setValue(user);
                            Toast.makeText(RegisterState.this, "User Created.", Toast.LENGTH_SHORT).show();;
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(RegisterState.this, "Eroare!" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });



    }



}