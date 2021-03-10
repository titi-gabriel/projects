package com.project.rpr_app;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public final class User_LoggedIn {

    private static User user_loggedin;

    private User_LoggedIn()
    {

    }

    public static void read_LoggedIn_User()
    {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseUser fbUser = FirebaseAuth.getInstance().getCurrentUser();
            String id = fbUser.getUid();
            DataBaseHelper dbHelper = new DataBaseHelper(FirebaseDatabase.getInstance().getReference("users").child(id));
            dbHelper.getmReference().addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user_loggedin = new User();
                    user_loggedin = snapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public static User getUser_loggedin() {
        return user_loggedin;
    }

    public static void setUser_loggedin(User user_loggedin) {
        User_LoggedIn.user_loggedin = user_loggedin;
    }
}
