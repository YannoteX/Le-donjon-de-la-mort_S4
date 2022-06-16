package com.example.projet_s4;

import org.json.JSONObject;

import modele.Modele;

public class Controleur {

    public Controleur(Modele modele){
        JSONObject currentCard = modele.getCardById("CARD_PLAYER_ATTACK_SUCCESS");

        System.out.println(currentCard.toString());
    }
}
