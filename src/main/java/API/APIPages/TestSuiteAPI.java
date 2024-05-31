package API.APIPages;

import API.Base.BaseAPIClass;
import Models.Project;
import Models.TestSuite;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class TestSuiteAPI extends BaseAPIClass {
    final String RELATIVE_URL;
    public TestSuiteAPI(String baseUrl, String token, String contentType) {
        super(baseUrl, token, contentType);
        RELATIVE_URL = "/suite";
    }
    public String serialization(TestSuite testSuite) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(testSuite);
    }
    public CustomResponse createTestSuite(TestSuite testSuite, Project project, Logger logger) throws JsonProcessingException {
        String getCode = "/" + project.getCode().toUpperCase();
        String RELATIVE_URL_FOR_CREATE_TEST_SUITE = RELATIVE_URL + getCode;
        String getJsonForCreateTestSuite = serialization(testSuite);
        logger.debug("Sending new testSuite details in TestSuiteAPI to create testSuite in BaseAPI");
        return postRequest(RELATIVE_URL_FOR_CREATE_TEST_SUITE, getJsonForCreateTestSuite, String.class, logger);

    }
    public CustomResponse getTestSuite(int testSuiteID, Project project, Logger logger){
        String getCode = project.getCode().toUpperCase();
        String relativeUrlForGetTestSuite = RELATIVE_URL + "/" + getCode + "/" + testSuiteID;
        logger.debug("Sending new testSuite details in TestSuiteAPI to get created testSuite in BaseAPI");
        return getRequestByCode(relativeUrlForGetTestSuite,logger);


    }
}
