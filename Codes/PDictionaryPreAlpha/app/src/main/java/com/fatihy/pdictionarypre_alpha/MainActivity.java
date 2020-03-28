package com.fatihy.pdictionarypre_alpha;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FieldValue;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText firstWord, secondWord;
    TextView infoText;
    FirebaseFirestore firebaseFirestore;
    // TODO: Show the data !PRIORITY
    //TODO: Add authentication
    //TODO: Make firebase offline.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstWord =  findViewById(R.id.firstWord);
        secondWord = findViewById(R.id.secondWord);
        infoText  = findViewById(R.id.infoText);
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    public void save(View view)
    {
        if (!firstWord.getText().toString().equals("") && !secondWord.getText().toString().equals(""))
        {
            infoText.setTextColor(Color.rgb(00,00,00));
            infoText.setText("Please wait...");
            uploadToFirebase();
        }
        else {
            infoText.setTextColor(Color.rgb(255,00,00));
            infoText.setText("Please fill the empty areas!");
        }
    }
// Method for uploading Firebase server
    public void uploadToFirebase()
    {
        String[] theWord = new String[2];

    theWord[0] = firstWord.getText().toString();
    theWord[1] = secondWord.getText().toString();

    HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("firstWord",theWord[0]);
        hashMap.put("secondWord",theWord[1]);
        hashMap.put("date", FieldValue.serverTimestamp());

        firebaseFirestore.collection("Words").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            infoText.setTextColor(Color.rgb(75,181,67));
            infoText.setText("Word successfully added to your library!");
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(MainActivity.this,e.getLocalizedMessage().toString(),Toast.LENGTH_LONG).show();
            infoText.setTextColor(Color.rgb(255,00,00));
            infoText.setText("FAILED");
        }
    });
}

public void showLibrary(View view)
{
    Intent intent = new Intent(MainActivity.this, libraryActivity.class);
    startActivity(intent);
}

}
