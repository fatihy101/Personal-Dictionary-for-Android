package com.fatihy.pdictionarypre_alpha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class libraryActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFirebase();
    }

    public void getDataFirebase()
    {
        firebaseFirestore.collection("Words").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e!=null)
        {
            Toast.makeText(libraryActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
        }
    for(DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments())
    {
        Map<String, Object> data = snapshot.getData();
        String firstWord = (String) data.get("firstWord");
        String secondWord = (String) data.get("secondWord");
        System.out.println(firstWord +" = "+ secondWord );
    }

       }
        });
    }

}
