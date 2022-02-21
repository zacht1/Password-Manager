package persistence;

import org.json.JSONObject;

// represents a formatter to format objects in JSON format
// Credit: this interface is based on the code from the Writable interface in JsonSerializationDemo
public interface JsonFormat {

    // EFFECTS: returns this as a JSONObject
    JSONObject formatJson();
}
