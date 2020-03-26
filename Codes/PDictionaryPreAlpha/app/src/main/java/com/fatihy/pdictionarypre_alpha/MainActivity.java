package com.fatihy.pdictionarypre_alpha;

        import androidx.appcompat.app.AppCompatActivity;

        import android.graphics.Color;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText firstWord, secondWord;
  TextView infoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstWord =  findViewById(R.id.firstWord);
        secondWord = findViewById(R.id.secondWord);
       infoText  = findViewById(R.id.infoText);

    }

    public void save(View view)
    {
        if (!firstWord.getText().toString().equals("") && !secondWord.getText().toString().equals(""))
        {
            infoText.setTextColor(Color.rgb(75,181,67));
            infoText.setText("Word successfully added to your library!");
        }
        else {
            infoText.setTextColor(Color.rgb(255,00,00));
            infoText.setText("Please fill the empty areas!");
        }
    }

}
