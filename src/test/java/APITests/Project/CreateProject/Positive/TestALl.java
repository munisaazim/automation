package APITests.Project.CreateProject.Positive;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestALl {

    @Test
    public void test(){
        RequestSpecification requestSpecification =RestAssured.given()
                .baseUri("https://api.qase.io/v1")
                .header("Token", "cf593883231bdea88201c3277336540c4bb6615f5d84c3e466dce7f60a0dcbdb")
                .header("accept", "application/json");
        Response response = requestSpecification
                .basePath("/project/CODE")
                .get();
        System.out.println(response.getBody().prettyPrint());
    }
}
