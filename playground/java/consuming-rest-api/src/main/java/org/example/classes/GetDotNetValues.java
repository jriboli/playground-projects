package org.example.classes;

import io.github.cdimascio.dotenv.Dotenv;

public class GetDotNetValues {
    Dotenv dotenv = Dotenv.load();
    public GetDotNetValues() {

    }

    public String getValue(String key) {
        return dotenv.get(key);
    }
}