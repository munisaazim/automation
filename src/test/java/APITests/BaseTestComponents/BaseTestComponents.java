package APITests.BaseTestComponents;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTestComponents {
    public String BASE_URL = "https://api.qase.io/v1";
    public String API_TOKEN = "cf593883231bdea88201c3277336540c4bb6615f5d84c3e466dce7f60a0dcbdb";
    public String CONTENT_TYPE = "application/json";


    //Defining current data for logger
    Date currentDate = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
    public String formattedDateTime = dateFormat.format(currentDate);


    //Error message general shared

    //Bad request messages
    public String BAD_REQUEST_MESSAGE_FAILED = "Bad Request test failed";
    public String BAD_REQUEST = "Bad Request.";
    public String BAD_REQUEST_CODE_MESSAGE = "Bad Request test failed: Status Code not equal 400";

    //Unauthorized messages
    public String UNAUTHENTICATED_MESSAGE_FAILED = "Unauthenticated test failed";
    public String UNAUTHENTICATED = "Unauthenticated.";
    public String UNAUTHENTICATED_CODE_MESSAGE ="Unauthenticated test failed with status code:";
    //Forbidden messages
    public String FORBIDDEN_MESSAGE_FAILED = "Forbidden test failed";
    public String FORBIDDEN = "Forbidden.";
    public String FORBIDDEN_CODE_MESSAGE ="Forbidden test failed: Status Code with status code:";

    //Unprocessable messages
    public String UNPROCCESABLE_MESSAGE_FAILED = "Unprocessable Entity test failed";
    public String UNPROCCESABLE ="Unprocessable Entity.";
    public String UNPROCCESABLE_CODE_MESSAGE ="Unprocessable Entity test failed with status code:";

    //Too many requests messages
    public String TOO_MANY_REQUESTS_MESSAGE_FAILED = "Too Many Requests test failed";
    public String TOO_MANY_REQUESTS = "Too Many Requests.";
    public String TOO_MANY_REQUESTS_CODE_MESSAGE = "Too Many Requests test failed with status code:";

    //Not found messages
    public String NOT_FOUND_MESSAGE_FAILED = "Not found test failed";
    public String NOT_FOUND = "Not Found.";
    public String NOT_FOUND_REQUESTS_CODE_MESSAGE = "Not Found test failed with status code:";

    //Create Project

    //Minimum project code length messages
    public String PROJECT_CODE_MIN_LENGTH_MESSAGE_FAILED = "Project Code min-length test failed";
    public String PROJECT_CODE_MIN_LENGTH_MESSAGE = "Project code must be at least 2 characters.";
    public String PROJECT_CODE_MIN_LENGTH_STATUS_CODE_MESSAGE_FAILED = "Project Code min-length test failed with status code:";

    //Maximum project code length messages
    public String PROJECT_CODE_MAX_LENGTH_MESSAGE_FAILED = "Project Code max-length test failed";
    public String PROJECT_CODE_MAX_LENGTH_MESSAGE = "Project code may not be greater than 10 characters.";
    public String PROJECT_CODE_MAX_LENGTH_STATUS_CODE_MESSAGE_FAILED = "Project Code max-length test failed with status code:";

    //Empty title length messages
    public String EMPTY_TITLE_MESSAGE_FAILED = "Empty title test is failed";
    public String EMPTY_TITLE_MESSAGE = "Title is required.";
    public String EMPTY_TITLE_STATUS_CODE_MESSAGE = "Empty title test is failed with status code:";

    //Empty title project code length messages
    public String EMPTY_CODE_MESSAGE_FAILED = "Empty code test is failed";
    public String EMPTY_CODE_MESSAGE = "Project code is required.";
    public String EMPTY_CODE_STATUS_CODE_MESSAGE = "Empty code test is failed with status code:";

    //Already created project
    public String CREATED_PROJECT_MESSAGE_FAILED = "Project with the same code already exists. Test is failed";
    public String CREATED_PROJECT = "Project with the same code already exists.";
    public String CREATED_PROJECT_STATUS_CODE_MESSAGE = "Project with the same code already exists.Test is failed with status code:";


    //Negative Project

    //Negative Project with status code: 404
    public String DELETE_PROJECT_NOT_FOUND_FAILED = "Project not found test is failed";
    public String DELETE_PROJECT_NOT_FOUND = "Project not found";
    public String DELETE_PROJECT_NOT_FOUND_STATUS_CODE = "Project not found test is failed with status code:";

    //Negative Project with Bad Request
    public String DELETE_PROJECT_BY_CODE_STATUS_CODE_BAD_REQUEST_MESSAGE = "Negative Project failed with status code:";

    //Negative Project with Unauthorized
    public String DELETE_PROJECT_BY_CODE_STATUS_CODE_UNATHORIZED_MESSAGE = "Negative Project test failed with status code:";

    //Negative Project with Unprocessable messages
    public String DELETE_PROJECT_BY_CODE_STATUS_CODE_UPROCESSABLE_MESSAGE = "Negative Project test failed with status code:";

    //Negative Project with Too many requests messages
    public String DELETE_PROJECT_INCORRECT_URL_FAILED = "The DELETE method is not supported for route api/v1/project. Supported methods: GET, HEAD, POST. Test failed";
    public String DELETE_PROJECT_INCORRECT_URL = "The DELETE method is not supported for route api/v1/project. Supported methods: GET, HEAD, POST.";
    public String DELETE_PROJECT_INCORRECT_URL_STATUS_CODE = "The DELETE method is not supported for route api/v1/project. Supported methods: GET, HEAD, POST. Test is failed with status code";

    //TEST SUITE ---------> ERROR MESSAGES
    public String TEST_SUITE_PROJECT_NOT_FOUND_FAILED = "Project not found. Test failed";
    public String TEST_SUITE_PROJECT_NOT_FOUND = "Project not found";
    public String TEST_SUITE_PROJECT_NOT_FOUND_STATUS_CODE = "Project not found. Test is failed with status code";

    public String TEST_SUITE_NOT_FOUND_FAILED = "Suite not found. Test failed";
    public String TEST_SUITE_NOT_FOUND = "Suite not found";
    public String TEST_SUITE_NOT_FOUND_STATUS_CODE = "Suite not found. Test is failed with status code";
    public String TEST_SUITE_DATA_INVALID_FAILED = "Data is invalid. Test failed";
    public String TEST_SUITE_DATA_INVALID= "Data is invalid.";
    public String TEST_SUITE_DATA_INVALID_STATUS_CODE = "Data is invalid. Test is failed with status code";
    public String TEST_SUITE_UNAUTHENTICATED_FAILED = "Unauthenticated. Test failed";
    public String TEST_SUITE_UNAUTHENTICATED= "Unauthenticated.";
    public String TEST_SUITE_UNAUTHENTICATED_STATUS_CODE = "Unauthenticated. Test is failed with status code";
    public String TEST_SUITE_MAX_CHARACTERS_FAILED = "The title may not be greater than 255 characters. Test failed";
    public String TEST_SUITE_MAX_CHARACTERS= "The title may not be greater than 255 characters.";
    public String TEST_SUITE_MAX_CHARACTERS_STATUS_CODE = "The title may not be greater than 255 characters. Test is failed with status code";
    public String TEST_SUITE_TITLE_MUST_BE_STRING_FAILED = "The title must be a string. Test failed";
    public String TEST_SUITE_TITLE_MUST_BE_STRING= "The title must be a string.";
    public String TEST_SUITE_TITLE_MUST_BE_STRING_STATUS_CODE = "The title must be a string. Test is failed with status code";

    //TEST CASE ---------> ERROR MESSAGES

    public String TEST_CASE_PROJECT_NOT_FOUND_FAILED = "Project not found. Test failed";
    public String TEST_CASE_PROJECT_NOT_FOUND = "Project not found";
    public String TEST_CASE_PROJECT_NOT_FOUND_STATUS_CODE = "Project not found. Test is failed with status code";

    public String TEST_CASE_NOT_FOUND_FAILED = "TestCase not found. Test failed";
    public String TEST_CASE_NOT_FOUND = "TestCase not found";
    public String TEST_CASE_NOT_FOUND_STATUS_CODE = "TestCase not found. Test is failed with status code";

    public String TEST_CASE_MILESTONE_NOT_EXIST_FAILED = "The selected milestone doesn't exist. Test failed";
    public String TEST_CASE_MILESTONE_NOT_EXIST = "The selected milestone doesn't exist.";
    public String TEST_CASE_MILESTONE_NOT_EXIST_STATUS_CODE = "The selected milestone doesn't exist. Test is failed with status code";
    public String TEST_CASE_TITLE_REQUIRED_FAILED = "The title field is required. Test failed";
    public String TEST_CASE_TITLE_REQUIRED = "The title field is required.";
    public String TEST_CASE_TITLE_REQUIRED_STATUS_CODE = "The title field is required. Test is failed with status code";
    public String TEST_CASE_TITLE_MAX_FAILED = "The title may not be greater than 255 characters. Test failed";
    public String TEST_CASE_TITLE_MAX = "The title may not be greater than 255 characters.";
    public String TEST_CASE_TITLE_MAX_STATUS_CODE = "The title may not be greater than 255 characters. Test is failed with status code";
    public String TEST_CASE_INVALID_FIELDS_VALUE_FAILED = "The selected field value is invalid.  Test failed";
    public String INVALID_FIELDS_VALUE = "The selected field value is invalid. (and 1 more error)";
    public String TEST_CASE_INVALID_FIELDS_VALUE_STATUS_CODE = "The selected field value is invalid.  Test is failed with status code";
    public String TEST_CASE_INVALID_FIELD_VALUE_FAILED = "The selected field value is invalid. (and 1 more error) Test failed";
    public String INVALID_FIELD_VALUE = "The selected field value is invalid. (and 1 more error)";
    public String TEST_CASE_INVALID_FIELD_VALUE_STATUS_CODE = "The selected field value is invalid.";
    public String TEST_CASE_UNAUTHENTICATED_FAILED = "Unauthenticated. Test failed";
    public String TEST_CASE_UNAUTHENTICATED= "Unauthenticated.";
    public String TEST_CASE_UNAUTHENTICATED_STATUS_CODE = "Unauthenticated. Test is failed with status code";
    public String TEST_CASE_TITLE_NOT_STRING_FAILED = "The title must be a string. Test failed";
    public String TEST_CASE_TITLE_NOT_STRING= "The title must be a string. (and 1 more error)";
    public String TEST_CASE_TITLE_NOT_STRING_STATUS_CODE = "The title must be a string. Test is failed with status code";
    public String TEST_CASE_TITLE_MUST_BE_STRING= "The title must be a string.";



}
