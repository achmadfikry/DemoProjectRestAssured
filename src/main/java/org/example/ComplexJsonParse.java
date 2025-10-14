package org.example;

import io.restassured.path.json.JsonPath;
import org.example.files.payload;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(payload.CoursePrice());
    }
}
