package modele;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;


public class Modele {

    private JSONArray cards;
    private JSONObject database;

    public Modele(String JSONString) throws JSONException {
        JSONObject json = new JSONObject(JSONString);
        this.database = json.getJSONObject("datas");
        this.cards = database.getJSONArray("cards");
    }

    public JSONObject getCardById(String id){
        int i = 0;
        boolean found = false ;
        JSONObject card = new JSONObject();

        while (i < cards.length() && !found){
            try {
                if (cards.getJSONObject(i).getString("id").equals(id)){
                    card = cards.getJSONObject(i);
                    found = true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }
        return card;
    }

    public JSONObject getCardByType(String type){
        ArrayList<JSONObject> candidates = new ArrayList<>();
        int i = 0;

        for (i = 0 ; i < cards.length() ; i++){
            try {
                if (cards.getJSONObject(i).get("type").equals(type)){
                    candidates.add(cards.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
    }

    public  Object getValue(String name) throws JSONException {
        return this.database.get(name);
    }

    public void setValue(String name, Object value) throws JSONException {
        this.database.put(name, value);
    }

    public void sumValue(String name, int secondValue) throws JSONException {
        this.database.put(name, this.database.getInt(name) + secondValue);
    }

    public JSONObject getPlayerStat() throws JSONException{
        JSONObject playerStat = new JSONObject();
        playerStat.put("playerHealth", this.getValue("playerHealth"));
        playerStat.put("attack", this.getValue("attack"));
        playerStat.put("defense", this.getValue("defense"));
        playerStat.put("gold", this.getValue("gold"));

        return playerStat;
    }

    public JSONObject SendInfo(String cardToGet) throws JSONException{
        JSONObject viewInfo = new JSONObject();
        if (cardToGet.toUpperCase().equals(cardToGet)){
            viewInfo.put("card", this.getCardById(cardToGet));
        }
        else{
            viewInfo.put("card", this.getCardByType(cardToGet));
        }

        viewInfo.put("player", this.getPlayerStat());

        return viewInfo;
    }

}

