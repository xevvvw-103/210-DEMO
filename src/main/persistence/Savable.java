package persistence;

import java.io.PrintWriter;

// Represents data that can be saved to file
public interface Savable {
    // MODIFIES: printWriter
    // EFFECTS: writes the savable to dogWri
    void save(PrintWriter writer);
}
