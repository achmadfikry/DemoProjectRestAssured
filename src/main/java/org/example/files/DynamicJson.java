package org.example.files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void AddBook(String isbn, String aisle){
        RestAssured.baseURI="http://216.10.245.166/";
        String response = given().log().all().header("Content-Type", "application/json")
                .body(payload.AddLibrary(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String id = jsonPath.get("ID");
        System.out.println(id);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData(){
        return new Object[][] {{"book1","1111"},{"book2","2222"},{"book3","3333"}};
    }

    @Test
    public void AddLocationByJsonFile() throws IOException {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("qaclick", "qaclick123").header("Content-Type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/main/java/org/example/files/AddPlace.json"))))
                .when().post("/maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        System.out.println(response);
    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
