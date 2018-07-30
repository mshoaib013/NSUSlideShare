package com.bananakernel.documenteyetracker;

import android.app.Notification;
import android.content.Context;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class readFromFirebase {
    String[] chapters = new String[25];
    int i = 0;
    String key;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("DEPT").child("ECE").child("Slides").child("CSE225");

    //context splash screen
    Context context;
    public readFromFirebase(Context c){
        context = c;
    }

    public void print(String a){
        chapters[i] = a;
        i++;
    }
    public void read(){// Read from the database
        i=0;
        myRef.keepSynced(false);
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                Log.d("read", message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
