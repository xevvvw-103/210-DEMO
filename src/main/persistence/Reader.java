package persistence;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private JSONParser jsonParser;

    public Reader() {
        jsonParser = new JSONParser();
    }

    public Object read(File file) throws IOException, ParseException {
        FileReader fileReader = new FileReader(file);
        return jsonParser.parse(fileReader);
    }

}
