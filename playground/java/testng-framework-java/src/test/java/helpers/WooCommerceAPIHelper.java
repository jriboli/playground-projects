package helpers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class WooCommerceAPIHelper {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String getValue(String json, Object... path) {
        try {
            JsonNode node = mapper.readTree(json);
            for (Object p : path) {
                if (node == null) return null;
                if(p instanceof Integer)
                    node = node.get(Integer.parseInt(p.toString()));
                else
                    node = node.get(p.toString());
            }
            return node != null ? node.asText() : null;
        } catch (Exception e) {
            System.err.println("Failed to parse JSON: " + e.getMessage());
            return null;
        }
    }

    public static boolean containsKey(String json, String key) {
        try {
            JsonNode node = mapper.readTree(json);
            return node.has(key);
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractCouponCodeFrom(String request) {
        //System.out.println(getValue(request, "requests"));
        return getValue(request, "requests", 0, "data", "code");
    }

    public static String extractNonceFrom(String request) {
        return getValue(request, "requests", 0, "headers", "Nonce");
    }

    public static String getMockResponse(String fileName, Map<String, String> placeholders) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("src/test/resources/mocks/" + fileName)));
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }
            return content;
        } catch (Exception e) {
            throw new RuntimeException("Failed to read mock file", e);
        }
    }
}
