package model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import persistence.Savable;

import java.io.PrintWriter;

public class Recipes implements Savable {
    private JSONArray recipes;

    public Recipes() {
        recipes = new JSONArray();
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(recipes.toJSONString());
        printWriter.flush();
    }

    public boolean addRecipe(JSONObject object) {
        return recipes.add(object);
    }

}

