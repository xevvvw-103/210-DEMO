package persistence;

import model.GoldenRetriever;
import model.Recipe;
import model.Recipes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE1 = "./data/testRecipes.json";
    private static final String TEST_FILE2 = "./data/testGR.json";
    private Writer testWriter;
    private GoldenRetriever goldenRetriever;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipes recipes;
    private Reader fileReader;

    @BeforeEach
    void runBefore() {
        goldenRetriever = new GoldenRetriever("Mac");
        recipe1 = new Recipe(1, 1, 1);
        recipe2 = new Recipe(0,0,3);
        recipes = new Recipes();
    }

    @Test
    void testWriterGR() throws FileNotFoundException {
        // save a dog to file
        testWriter = new Writer(new File(TEST_FILE2));
        testWriter.write(goldenRetriever);
        testWriter.close();

        fileReader = new Reader();
        try {
            JSONObject dog = (JSONObject) fileReader.read(new File(TEST_FILE2));
            assertEquals("Mac", dog.get("name"));
            assertEquals(40.0, dog.get("weight"));
        } catch (ParseException | IOException e) {
           fail();
        }

    }

    @Test
    void testWriterRecipes() throws FileNotFoundException {
        // save a recipe tp file
        testWriter = new Writer(new File(TEST_FILE1));
        recipes.addRecipe(recipe1.reToObject());
        recipes.addRecipe(recipe2.reToObject());
        testWriter.write(recipes);
        testWriter.close();

        fileReader = new Reader();
        try {
            JSONArray recipes = (JSONArray) fileReader.read(new File(TEST_FILE1));
            JSONObject object1 = (JSONObject) recipes.get(0);
            JSONObject recipe1 = (JSONObject) object1.get("recipe");
            assertEquals(1, (Long) recipe1.get("AP"));
            assertEquals(1, (Long) recipe1.get("BT"));
            assertEquals(1, (Long) recipe1.get("LFM"));

            JSONObject object2 = (JSONObject) recipes.get(1);
            JSONObject recipe2 = (JSONObject) object2.get("recipe");
            assertEquals(0, (Long) recipe2.get("AP"));
            assertEquals(0, (Long) recipe2.get("BT"));
            assertEquals(3, (Long) recipe2.get("LFM"));

        } catch (ParseException | IOException e) {
            fail();
        }
    }
}

