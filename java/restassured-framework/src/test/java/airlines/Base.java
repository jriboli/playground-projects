package airlines;

import utils.JsonUtils;

import java.util.Map;

// Common setup items
public class Base {

    public static Map<String, Object> dataFromJsonFile;

    static {
        String env = System.getProperty("env") == null ? "qa" : System.getProperty("env");
        try {
            dataFromJsonFile = JsonUtils.getJsonDataAsMap("airlines/" + env + "/airlinesApiData.json");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
