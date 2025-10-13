import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.files.payload;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basics {
    public static void main(String[] args) {
        //validate if Add Place API is working as expected
        //given - all input details
        //when - submit the API - resource, http method
        //then - validate the response

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("qaclick", "qaclick123").header("Content-Type", "application/json")
                .body(payload.AddPlace()).
                when().post("/maps/api/place/add/json").
                then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);

        JsonPath jsonPath = new JsonPath(response); //with response in the brackets, then jsonPath have knowledge about response
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);

    }

    //Add Place -> Update Place with New Address -> Get Place to validate if New Address is present in response

}
