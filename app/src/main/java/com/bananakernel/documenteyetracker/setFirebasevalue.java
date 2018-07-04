package com.bananakernel.documenteyetracker;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class setFirebasevalue {
    Context context;
    public setFirebasevalue(Context c){
        context = c;
    }
    FirebaseDatabase database;
    DatabaseReference myRef;
    public void set(){
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("DEPT");
        myRef.child("OTHERS").child("Slides").child("POL101").child("Chapter 3").setValue("https://drive.google.com/open?id=1E46qDINjRT6l_-xomUq9fXFQdgL7l1u8");
        Toast.makeText(context,"hello", Toast.LENGTH_SHORT).show();
    }
}
