package com.bananakernel.documenteyetracker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class readFromFirebase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("DEPT").child("ECE").child("Slides").child("CSE225");

    //context splash screen
    Context context;
    public readFromFirebase(Context c){
        context = c;
    }


    public void read(){// Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot messageSnapshot: dataSnapshot.getChildren()) {
//                    String name = (String) messageSnapshot.child("Slides").getValue();
////                    String message = (String) messageSnapshot.child("message").getValue();
//                    Log.d("Firebase read", name);
//                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot messageThread : dataSnapshot.getChildren()) {
                    String key = String.valueOf(messageThread.getKey());
                    String value = String.valueOf(messageThread.getValue());
                    Log.d("Firebase read", "Key is:  "+ key +"  Value: "+ value);
//                    break;
                }
//                HashMap value =dataSnapshot.getValue(HashMap.class);
//                Log.d("Firebase read", "Value is: " + value);
//                Toast.makeText(context,value,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebase read failed", "Failed to read value.", error.toException());
            }
        });
    }
}
