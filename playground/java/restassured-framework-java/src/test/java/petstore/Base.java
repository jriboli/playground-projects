package petstore;

import utils.JsonUtils;

import java.util.Map;

public class Base {

    public static Map<String, Object> dataFromJsonFile;

    static {
        String env = System.getProperty("env") == null? "qa" : System.getProperty("env");
        try {
            dataFromJsonFile = JsonUtils.getJsonDataAsMap("petstore/"+env+"/petstoreApiData.json");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
