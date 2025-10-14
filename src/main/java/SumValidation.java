import io.restassured.path.json.JsonPath;
import org.example.files.payload;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {
    @Test
    public void sumOfCourses(){
        JsonPath jsonPath = new JsonPath(payload.CoursePrice());
        int count = jsonPath.getInt("courses.size()");
        int sum = 0;
        for(int i = 0; i < count; i++){
            int price = jsonPath.getInt("courses["+i+"].price");
            int copies = jsonPath.getInt("courses["+i+"].copies");
            int amount = price * copies;
            sum = sum + amount;
            System.out.println("Amount of course " + jsonPath.getString("courses["+i+"].title") + " : " + amount);
        }
        System.out.println("Total amount: " + sum);
        int amountReference = jsonPath.get("dashboard.purchaseAmount");
        System.out.println("Amount reference: " + amountReference);
        Assert.assertEquals(sum, amountReference);
    }
}
