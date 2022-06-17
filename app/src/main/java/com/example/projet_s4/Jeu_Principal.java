package com.example.projet_s4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import modele.Modele;


public class Jeu_Principal extends AppCompatActivity {


    TextView health;
    TextView attack;
    TextView defense;
    TextView gold;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textField;
    ImageView illustration;
    Modele modele;
    JSONObject currentCard;
    JSONObject currentStats = new JSONObject();

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
        health = findViewById(R.id.hp);
        attack = findViewById(R.id.attack);
        defense = findViewById(R.id.defense);
        gold = findViewById(R.id.gold);
        button1 = (Button) findViewById(R.id.choix1);
        button2 = (Button) findViewById(R.id.choix2);
        button3 = (Button) findViewById(R.id.choix3);
        button4 = (Button) findViewById(R.id.choix4);
        textField = (TextView) findViewById(R.id.textView2);
        illustration = findViewById(R.id.imageJeu);
        currentCard = modele.getCardById("CARD_ENTER_DUNJEON");
        updateCard();

        try {
            health.setText(modele.getPlayerStat().getString("playerHealth"));
            attack.setText(modele.getPlayerStat().getString("attack"));
            defense.setText(modele.getPlayerStat().getString("defense"));
            gold.setText(modele.getPlayerStat().getString("gold"));
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

    private void updateCard (){
        /*
        Change text, buttons and picture depending on the currentCard
         */
        try {
            illustration.setImageResource(getResources().getIdentifier(currentCard.getString("image"),"drawable",getPackageName()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            textField.setText(currentCard.getString("text"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);

        try {
            button1.setText(currentCard.getJSONObject("options").getJSONObject("B_1").getString("text"));
        } catch (JSONException e) {
            button1.setVisibility(View.INVISIBLE);
        }
        try {
            button2.setText(currentCard.getJSONObject("options").getJSONObject("B_2").getString("text"));
        } catch (JSONException e) {
            button2.setVisibility(View.INVISIBLE);
        }
        try {
            button3.setText(currentCard.getJSONObject("options").getJSONObject("B_3").getString("text"));
        } catch (JSONException e) {
            button3.setVisibility(View.INVISIBLE);
        }
        try {
            button4.setText(currentCard.getJSONObject("options").getJSONObject("B_4").getString("text"));
        } catch (JSONException e) {
            button4.setVisibility(View.INVISIBLE);
        }



    }

    private void updateStats (){
        /*
        Change health, attack, defense and gold depending on the currentStats
         */
        try {
            health.setText(currentStats.getString("playerHealth"));
            attack.setText(currentStats.getString("attack"));
            defense.setText(currentStats.getString("defense"));
            gold.setText(currentStats.getString("gold"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void setHealth(int newValue){

    }

    private void buttonPressed (Button buttonSelected){
        JSONObject consequences = new JSONObject();
        JSONObject cardAndPlayerStats = new JSONObject();
        JSONArray tabNextCards = new JSONArray();
        String nextCardId = null;
        JSONObject valuesToModify = new JSONObject();

        try {//consequences of the button pressed (inside the "B_1" : {...}
            consequences = currentCard.getJSONObject("options").getJSONObject(buttonSelected.getContentDescription().toString());
        } catch (JSONException e) {

            e.printStackTrace();
        }

        //next card_____________________
        try {//list of possible nextcards (in "nextCard" : [...])
            tabNextCards = consequences.getJSONArray("nextCard");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {//name ("id") of the next card randomly chosen in tabNextCards
            nextCardId = tabNextCards.getString(ThreadLocalRandom.current().nextInt(tabNextCards.length()));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //change stats in modele__________
        try {//get values to modify
            valuesToModify = consequences.getJSONObject("valuesToModify");
        } catch (JSONException e) {
            e.printStackTrace();
        }


        try {//try to apply new playerHealth if defined in valuesToModify
            modele.sumValue("playerHealth", valuesToModify.getInt("playerHealth"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {//try to apply new attack if defined in valuesToModify
            modele.sumValue("attack",valuesToModify.getInt("attack"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {//try to apply new defense if defined in valuesToModify
            modele.sumValue("defense",valuesToModify.getInt("defense"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {//try to apply new gold if defined in valuesToModify
            modele.sumValue("gold",valuesToModify.getInt("gold"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        //Request_______________
        try {
            cardAndPlayerStats = modele.sendInfo(nextCardId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Card________________
        try {
            currentCard = cardAndPlayerStats.getJSONObject("card");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Stats________________
        try {
            currentStats = cardAndPlayerStats.getJSONObject("player");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        updateStats();


                                                                                                        //fonction à supprimer ou modifier
        //-------------------------------------------------------------------------------------------------------------------------------<=  /!\
        try {                                                                                                                               //!\
            if(currentStats.getInt("playerHealth") <= 0){                                                                             //!\
                System.out.println("T MORT!!!!!!!!!!!!!!!!!!!!!!!!!!!______________");                                                      //!\
                Intent otherActivity= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(otherActivity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        updateCard();

    }


}