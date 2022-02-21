package persistence;

import model.AccountRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// represents a writer, which writes AccountRepository to given destination file
// Credit: This class is based on the code from the JsonWriter class in JsonSerializationDemo
public class JsonWriter {
    private static final int INDENT = 4;
    PrintWriter writer;
    String destinationFile;

    // MODIFIES: this
    // EFFECTS: constructs a JsonWriter with the given destination file
    public JsonWriter(String destinationFile) {
        this.destinationFile = destinationFile;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer with destinationFile, throws FileNotFoundException if
    //          destination file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destinationFile));
    }

    // MODIFIES: this
    // EFFECTS: write AccountRepository in Json format to destination file
    public void write(AccountRepository accounts) {
        saveToFile(accounts.formatJson().toString(INDENT));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes the given data to destination file
    public void saveToFile(String data) {
        writer.print(data);
    }

}
