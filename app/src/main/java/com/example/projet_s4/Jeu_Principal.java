package com.example.projet_s4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
        currentCard = modele.getCardById("CARD_ENTER_DUNJEON");
        updateCard();


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

    private void updateCard (){
        /*
        Change text, buttons and picture depending on the currentCard
         */
        try {
            ilustration.setImageResource(getResources().getIdentifier(currentCard.getString("image"),"drawable",getPackageName()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            textField.setText(currentCard.getString("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            button1.setText("");
            button2.setText("");
            button3.setText("");
            button4.setText("");
            button1.setText(currentCard.getJSONObject("options").getJSONObject("B_1").getString("text"));
            button2.setText(currentCard.getJSONObject("options").getJSONObject("B_2").getString("text"));
            button3.setText(currentCard.getJSONObject("options").getJSONObject("B_3").getString("text"));
            button4.setText(currentCard.getJSONObject("options").getJSONObject("B_4").getString("text"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void buttonPressed (Button buttonSelected){
        JSONObject consequences = new JSONObject();
        JSONObject cardAndPlayerStat = new JSONObject();
        JSONObject nextCard = new JSONObject();
        JSONArray tabNextCards = new JSONArray();
        String nextCardId = null;

        try {
            consequences = currentCard.getJSONObject("options").getJSONObject(buttonSelected.getContentDescription().toString());
        } catch (JSONException e) {
            Log.d("BOUDIN", "consequences");
            e.printStackTrace();
        }


        try {
            tabNextCards = consequences.getJSONArray("nextCard");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            nextCardId = tabNextCards.getString(ThreadLocalRandom.current().nextInt(tabNextCards.length()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(nextCardId);





        //________________________________
        try {
            cardAndPlayerStat = modele.sendInfo(nextCardId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            currentCard = cardAndPlayerStat.getJSONObject("card");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        updateCard();


        //sumValue(String name, int secondValue)

    }


}