package API.Base;

import Models.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

public class BaseAPIClass {
    private String API_TOKEN;
    private String BASE_URL;
    private String CONTENT_TYPE;
    public RequestSpecification requestSpecification;

    //Defining each header section
    public BaseAPIClass(String baseUrl, String token, String contentType){
        this.BASE_URL = baseUrl;
        this.API_TOKEN = token;
        this.CONTENT_TYPE = contentType;
        requestSpecification = RestAssured.given()
                .baseUri(BASE_URL)
                .header("Token", API_TOKEN)
                .header("accept", CONTENT_TYPE);
    }
    public <T> CustomResponse customResponse(Response response, Logger logger){
        CustomResponse <T> customResponse = new CustomResponse<>();
        customResponse.setStatusCode(response.getStatusCode());
        customResponse.setBody(response.getBody().asString());
        return customResponse;
    }
    public <T> CustomResponse postRequest(String Relative_Url, String json, Class<T> responseType, Logger logger){
        Response response = requestSpecification
                .header("content-type", "application/json")
                .basePath(Relative_Url)
                .body(json)
                .post();
        logger.debug("Creating new project with postRequest in BaseAPI");
        return customResponse(response, logger);

    }
    public <T> CustomResponse getRequest(String Relative_Url, String Relative_Url_For_Get, Logger logger){
        Response response = requestSpecification
                .basePath(Relative_Url)
                .get(Relative_Url_For_Get);
        return customResponse(response,logger);
    }

    public <T> CustomResponse deleteRequest(String Relative_Url,String Relative_Url_Delete, Logger logger){
        Response response = requestSpecification
                .basePath(Relative_Url)
                .delete(Relative_Url_Delete);
        return customResponse(response, logger);
    }

    public class CustomResponse <T> {
        private int statusCode;
        private String body;

        public int getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
        public T getObjectModel(Logger logger) throws JsonProcessingException {
            logger.debug("Checking status code to obtain object for Created Project");
            if (getStatusCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                logger.debug("Initializing objectMapper object with corresponding class");
                JsonNode jsonNode = objectMapper.readTree(getBody());
                logger.debug("Converting string data to json");
                JsonNode resultNode = jsonNode.path("result");
                logger.debug("Obtaining details about created project from node 'result'");
                return (T) objectMapper.treeToValue(resultNode, Project.class);
            } else {
                logger.debug("getObjectModel has not worked since status code is not 200, it is: " + getStatusCode());
                return null;
            }
        }

        public String getErrorMessage() throws JsonProcessingException {
            if(getStatusCode()==200){
                System.out.println("Status Code: " + getStatusCode() + "\n");
                return null;
            }
            else {
                String getResponseBody = getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getResponseBody);
                return jsonNode.path("errorFields").get(0).path("error").asText();
            }
        }
        public String getErrorMessageShort() throws JsonProcessingException {
            if(getStatusCode()==200){
                System.out.println("Status Code: " + getStatusCode() + "\n");
                return null;
            }
            else {
                String getResponseBody = getBody();
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(getResponseBody);
                System.out.println(jsonNode);
                return jsonNode.path("error").asText();
            }
        }

    }
}
