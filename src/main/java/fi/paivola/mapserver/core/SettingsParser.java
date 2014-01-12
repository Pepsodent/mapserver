package fi.paivola.mapserver.core;

import fi.paivola.mapserver.utils.CCs;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Parses a JSON file for all settings.
 *
 * @author Juhani
 */
public class SettingsParser {

    public List<String> models;
    public static JSONObject settings;
    private final JSONObject obj;

    public SettingsParser() throws IOException, ParseException {
        this(null);
    }

    public SettingsParser(InputStream is) throws IOException, ParseException {

        if (is == null) {
            is = SettingsParser.class.getClassLoader().getResourceAsStream("settings.json");
        }

        models = new ArrayList<>();

        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        obj = settings = (JSONObject) (new JSONParser().parse( s.hasNext() ? s.next() : ""));

    }

    public Map<String, CCs> getModels() {
        Map<String, CCs> map = new HashMap<>();

        // array of models that we totally want to use
        JSONArray msg = (JSONArray) obj.get("models");
        Iterator<JSONObject> iterator = msg.iterator();
        while (iterator.hasNext()) {
            JSONObject ob = iterator.next();
            try {
                map.put(ob.get("name").toString(), new CCs(Class.forName(ob.get("class").toString())));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(SettingsParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return map;
    }

}