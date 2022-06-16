package com.example.projet_s4;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import org.json.JSONException;

import java.io.InputStream;
import java.util.Scanner;
import modele.Modele;

public class Jeu_Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_principal);

        InputStream in = getResources().openRawResource(R.raw.data);
        try {
            Modele modele1 = new Modele(new Scanner(in).useDelimiter("\\A").next());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}