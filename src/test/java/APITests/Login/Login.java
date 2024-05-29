package APITests.Login;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {

    private final String BASE_URL = "https://api.qase.io/v1";
    private final String API_TOKEN = "cf593883231bdea88201c3277336540c4bb6615f5d84c3e466dce7f60a0dcbdb";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void checkLogin() {
        RequestSpecification request = RestAssured.given();
        request.header("accept","application/json");
        request.header("Content-Type", "application/json");
        request.header("Token", API_TOKEN);

        Response response = request.get("/project?limit=10&offset=0");

        // Log response details
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        //Assert.assertTrue(response.getBody().asString().contains("email"));
    }
}
