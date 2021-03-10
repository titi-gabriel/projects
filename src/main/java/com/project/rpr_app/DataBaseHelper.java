package com.project.rpr_app;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class DataBaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    public DataBaseHelper(DatabaseReference dbref)
    {
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mReference = dbref;
    }


    public FirebaseDatabase getmDatabase() {
        return mDatabase;
    }

    public DatabaseReference getmReference() {
        return mReference;
    }
}
