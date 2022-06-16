package com.example.projet_s4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;
import modele.Modele;


public class Jeu_Principal extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textField;
    ImageView ilustration;
    Modele modele;
    JSONObject currentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InputStream in = getResources().openRawResource(R.raw.data);
        try {
            modele = new Modele(new Scanner(in).useDelimiter("\\A").next());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.activity_jeu_principal);
        button1 = (Button) findViewById(R.id.choix1);
        button2 = (Button) findViewById(R.id.choix2);
        button3 = (Button) findViewById(R.id.choix3);
        button4 = (Button) findViewById(R.id.choix4);
        textField = (TextView) findViewById(R.id.textView2);
        ilustration = findViewById(R.id.imageJeu);
        currentCard = modele.getCardById("CARD_CORRIDOR");
        try {
            button1.setText(currentCard.getJSONObject("options").getJSONObject("B_1").getString("text"));
            button2.setText(currentCard.getJSONObject("options").getJSONObject("B_2").getString("text"));
            button3.setText(currentCard.getJSONObject("options").getJSONObject("B_3").getString("text"));
            button4.setText(currentCard.getJSONObject("options").getJSONObject("B_4").getString("text"));
            textField.setText("COUCOU");
            ilustration.setImageResource(Integer.parseInt(currentCard.getString("image")));

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /** Called when the user touches the button */
    public void button1Selected(View view) {
        buttonPressed(button1);
    }
    public void button2Selected(View view) {
        buttonPressed(button2);
    }
    public void button3Selected(View view) {
        buttonPressed(button3);
    }
    public void button4Selected(View view) {
        buttonPressed(button4);
    }

    private void buttonPressed (Button buttonSelected){
        Log.d("COUCOU", buttonSelected.getContentDescription().toString());



        //sumValue(String name, int secondValue)

    }


}