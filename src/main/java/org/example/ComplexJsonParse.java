package org.example;

import io.restassured.path.json.JsonPath;
import org.example.files.payload;
import org.testng.Assert;

public class ComplexJsonParse {

    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(payload.CoursePrice());

        //1. Print No of courses returned by API
        int count = jsonPath.getInt("courses.size()"); //count of array
        System.out.println("1. Number of courses: " + count);

        //2. Print Purchase Amount
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println("2. Purchase Amount: " + purchaseAmount);

        //3. Print Title of the first course
        String firstCourseTitle = jsonPath.getString("courses[0].title");
        System.out.println("3. Title of the first course: " + firstCourseTitle);

        //4. Print All course titles and their respective Prices
        System.out.println("4. Course Titles and Prices:");
        for (int i = 0; i < count; i++) {
            String courseTitle = jsonPath.get("courses["+i+"].title");
            System.out.println(courseTitle);
            System.out.println(jsonPath.getString("courses["+i+"].price"));
        }

        //5. Print no of copies sold by RPA Course
        for (int i = 0; i < count; i++) {
            String courseTitle = jsonPath.get("courses["+i+"].title");
            if(courseTitle.equalsIgnoreCase("RPA")){
                int copies = jsonPath.getInt("courses["+i+"].copies");
                System.out.println("5. Number of copies sold by RPA Course: " + copies);
                break;
            }
        }

        //6. Verify if Sum of all Course prices matches with Purchase Amount
        count = jsonPath.getInt("courses.size()");
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
