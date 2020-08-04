package persistence;

import model.GoldenRetriever;
import model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

class WriterTest {
    private static final String TEST_FILE1 = "./data/testRecipes.txt";
    private static final String TEST_FILE2 = "./data/testGR.txt";
    private Writer testWriter1;
    private Writer testWriter2;
    private GoldenRetriever goldenRetriever;
    private Recipe recipe1;
    private Recipe recipe2;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter1 = new Writer(new File(TEST_FILE1));
        testWriter2 = new Writer(new File(TEST_FILE2));
        goldenRetriever = new GoldenRetriever("Mac");
        recipe1 = new Recipe(1, 1, 1);
        recipe2 = new Recipe(0,0,3);
    }

    @Test
    void testWriterGR() {
        // save a dog to file
        testWriter2.write(goldenRetriever);
        testWriter2.close();
    }

    @Test
    void testWriterRecipes() {
        // save a recipe tp file
        testWriter1.write(recipe1);
        testWriter1.write(recipe2);
        testWriter1.close();
    }

}

