package org.example.files;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
    public static JsonPath rawToJson(String getPlaceResponse){
        JsonPath jsonPath = new JsonPath(getPlaceResponse);
        return jsonPath;
    }
}
