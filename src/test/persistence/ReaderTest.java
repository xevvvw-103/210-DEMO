package persistence;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {

    @Test
    void testNotFind() {
        Reader reader = new Reader();
        try {
            reader.read(new File("./path/does/not/exist/testAccount.json"));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFind() {
        Reader reader = new Reader();
        try {
            Object object = reader.read(new File("./data/testReader1.json"));
            JSONArray recipes = (JSONArray) object;
            JSONObject object1 = (JSONObject) recipes.get(2);
            JSONObject recipe1 = (JSONObject) object1.get("recipe");
            assertEquals(1, (Long) recipe1.get("AP"));
            assertEquals(1, (Long) recipe1.get("BT"));
            assertEquals(1, (Long) recipe1.get("LFM"));
        } catch (ParseException | IOException e) {
            fail();
        }
    }
}
