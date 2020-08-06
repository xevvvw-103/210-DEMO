package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    private PrintWriter printWriter;

    public Writer(File file) throws FileNotFoundException {
        printWriter = new PrintWriter(file);
    }

    public void write(Savable savable) {
        savable.save(printWriter);
    }

    public void close() {
        printWriter.close();
    }

}
