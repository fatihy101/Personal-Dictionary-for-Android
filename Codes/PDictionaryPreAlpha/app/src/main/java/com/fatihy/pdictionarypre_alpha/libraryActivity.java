package com.fatihy.pdictionarypre_alpha;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

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
 ArrayList<String> words;
  ArrayList<String> meanings;
 FeedRecyclerAdapter feedRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //To display the go back button.

        //Initialization of variables
        firebaseFirestore = FirebaseFirestore.getInstance();
        words = new ArrayList<>();
        meanings = new ArrayList<>();
        getDataFirebase();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

         feedRecyclerAdapter = new FeedRecyclerAdapter(words,meanings);
         recyclerView.setAdapter(feedRecyclerAdapter);
    }



    public void getDataFirebase()
    {
        firebaseFirestore.collection("Words").orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
        if(e!=null)
        {
            Toast.makeText(libraryActivity.this,e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
        }
    for(DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments())
    {
        Map<String, Object> data = snapshot.getData();
        words.add((String) data.get("firstWord"));
        meanings.add((String) data.get("secondWord"));
        feedRecyclerAdapter.notifyDataSetChanged();
    }
    for (int i = 0; i<words.size();i++)
    {
        //It's for displaying the words and meanings on console.
        System.out.println(words.get(i)+ " = " + meanings.get(i));
    }
       }
        });
    }

}
