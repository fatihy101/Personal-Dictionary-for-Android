package com.fatihy.pdictionarypre_alpha;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FieldValue;
        import com.google.firebase.firestore.FirebaseFirestore;

        import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText firstWord, secondWord;
    TextView infoText,currentUser;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    boolean isLoggedIn = false;
    int uniqueRandomGuestKey = -1;

    //TODO: Add authentication
    //TODO: Make firebase offline

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstWord =  findViewById(R.id.firstWord);
        secondWord = findViewById(R.id.secondWord);
        infoText  = findViewById(R.id.infoText);
        currentUser = findViewById(R.id.currentUser);
        firebaseFirestore = FirebaseFirestore.getInstance();
   checkCurrentUser(user);

    }

//This method for initialize to the top-side menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_options_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void visibilityCheck() // To activate or deactivate the menu items. FIXME
    {
        View login_item  = findViewById(R.id.login_menu);
        View signup_item = findViewById(R.id.sign_up_menu);
        View signout_item = findViewById(R.id.sign_out);

        if(user != null)
        {
            login_item.setVisibility(View.GONE);
            signup_item.setVisibility(View.GONE);
            signout_item.setVisibility(View.VISIBLE);
        }
        else
        {
            login_item.setVisibility(View.VISIBLE);
            signup_item.setVisibility(View.VISIBLE);
            signout_item.setVisibility(View.GONE);
        }
    }

    public void checkCurrentUser(FirebaseUser userM) //To check the current user signed in.
    {
        if(userM==null)
        {
            currentUser.setText("You are guest!");
        }
        else {
            currentUser.setText("Current User: "+ user.getEmail());
        isLoggedIn = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.login_menu)
        {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        }
        else if(item.getItemId()== R.id.sign_up_menu)
        {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
        }
        else if(item.getItemId() == R.id.sign_out)
        {


        }

        return super.onOptionsItemSelected(item);
    }



    public void save(View view) //Save the words to DB
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
        if (!isLoggedIn || uniqueRandomGuestKey == -1)  uniqueRandomGuestKey = (int) (Math.random() * 10000); //To get random value for guest user.
        // TODO: In the future two guest may have same numbers. Find a way to not use again the same number.

        theWord[0] = firstWord.getText().toString();
        theWord[1] = secondWord.getText().toString();

    HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("firstWord",theWord[0]);
        hashMap.put("secondWord",theWord[1]);
        hashMap.put("date", FieldValue.serverTimestamp());

        if(isLoggedIn) hashMap.put("UID", user.getUid());
        else hashMap.put("UID","guest"+ uniqueRandomGuestKey);

        firebaseFirestore.collection("Words").add(hashMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
        @Override
        public void onSuccess(DocumentReference documentReference) {
            infoText.setTextColor(Color.rgb(75,181,67));
            infoText.setText("Word successfully added to your library!");
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            Toast.makeText(MainActivity.this, e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
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
