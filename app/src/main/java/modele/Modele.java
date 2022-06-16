package modele;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;


public class Modele {

    private JSONArray cards;
    private JSONObject database;

    public Modele(String JSONString) throws JSONException {
        this.database = new JSONObject(JSONString);
        this.cards = database.getJSONObject("datas").getJSONArray("cards");
    }

    public JSONObject getCardById(String id){
        int i = 0;
        boolean found = false ;
        JSONObject card = new JSONObject();

        while (i < cards.length() && found == false){
            try {
                if (cards.getJSONObject(i).get("id") == id){
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
        ArrayList<JSONObject> candidates = new ArrayList<JSONObject>();
        int i = 0;

        while (i < candidates.size()){
            try {
                if (cards.getJSONObject(i).get("type") == type){
                    candidates.add(cards.getJSONObject(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            i++;
        }

        return candidates.get(ThreadLocalRandom.current().nextInt(candidates.size()));
    }

    public  Object getValue(String name) throws JSONException {
        return this.database.get(name);
    }

    public void setValue(String name, Object value) throws JSONException {
        this.database.put(name, value);
    }

}

