package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private final static String TAG = JsonUtils.class.getSimpleName();
    private static final String NAME = "name";
    private static final String MAIN = "mainName";
    private static final String AKA = "alsoKnownAs";
    private static final String ORIGIN = "placeOfOrigin";
    private static final String DESCRIP = "description";
    private static final String IMAGE = "image";
    private static final String INGREDS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {

        try {

            JSONObject sourceJSON = new JSONObject(json);
            JSONObject name = sourceJSON.getJSONObject(NAME);

            String mainName = name.getString(MAIN);
            JSONArray akaJSONarray = name.optJSONArray(AKA);
            String placeOfOrigin = sourceJSON.optString(ORIGIN);
            String description = sourceJSON.getString(DESCRIP);
            String image = sourceJSON.getString(IMAGE);
            JSONArray ingredsJSONarray = sourceJSON.getJSONArray(INGREDS);

            List<String> akaList = new ArrayList<>(akaJSONarray.length());
            for (int i = 0; i < akaJSONarray.length(); i++) {
                akaList.add(akaJSONarray.getString(i));
            }

            List<String> ingredsList = new ArrayList<>(ingredsJSONarray.length());
            for (int i = 0; i < ingredsJSONarray.length(); i++) {
                ingredsList.add(ingredsJSONarray.getString(i));
            }

            return new Sandwich(mainName, akaList, placeOfOrigin, description, image, ingredsList);

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
