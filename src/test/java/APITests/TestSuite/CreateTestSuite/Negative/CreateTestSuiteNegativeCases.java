package APITests.TestSuite.CreateTestSuite.Negative;

import API.Base.BaseAPIClass;
import APITests.TestSuite.CreateTestSuite.Negative.BeforeTestAndAfterTestForCreateTestSuiteNegativeCases.BeforeTestAndAfterTestForCreateTestSuiteNegativeCases;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateTestSuiteNegativeCases extends BeforeTestAndAfterTestForCreateTestSuiteNegativeCases {

    @Test(priority = 1)
    @Epic("Creating test suite with negative case 1 --> maximum char.s for title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating a test suite with negative outcome 1 --> maximum char.s for title\"")
    @Step("1. Creating test suite with POST request \n" +
            "2. Verify create testsuite is failed with error message")
    public void createTestSuiteNegativeTestOne() throws IOException, JsonProcessingException, FileNotFoundException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        testSuite = testSuiteDataFactory.createTestSuiteOne();

        logFilePath = "log/CreateTestSuiteNegativeCases/createTestSuiteNegativeCases/"+ logFileName;
        logger= Logger.getLogger("Tests.TestSuite.CreateTestSuiteNegativeCases.createTestSuiteNegativeTestOne");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        logger.info("Creating Test Class with maximum char.s for title");

        BaseAPIClass.CustomResponse customResponseTestSuite = businessLayer.createTestSuiteInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, testSuite, project, logger);
        String errorMessage = customResponseTestSuite.getErrorMessage();

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();

        assertThat(TEST_SUITE_MAX_CHARACTERS_STATUS_CODE + "\n" + errorMessage, customResponseTestSuite.getStatusCode(), equalTo(400));
        assertThat(TEST_SUITE_MAX_CHARACTERS_FAILED, errorMessage, equalTo(TEST_SUITE_MAX_CHARACTERS));
    }
    @Test(priority = 1)
    @Epic("Creating test suite with negative case 2 --> empty title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating a test suite with negative outcome 2 --> empty title")
    @Step("1. Creating test suite with POST request \n" +
            "2. Verify create testsuite is failed with error message")
    public void createTestSuiteNegativeTestTwo() throws JsonProcessingException, FileNotFoundException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        testSuite = testSuiteDataFactory.createTestSuiteTwo();

        logFilePath = "log/CreateTestSuiteNegativeCases/createTestSuiteNegativeCases/"+ logFileName;
        logger= Logger.getLogger("Tests.TestSuite.CreateTestSuiteNegativeCases.createTestSuiteNegativeTestTwo");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        logger.info("Creating Test Class with empty title");

        BaseAPIClass.CustomResponse customResponseTestSuite = businessLayer.createTestSuiteInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, testSuite, project, logger);
        String errorMessage = customResponseTestSuite.getErrorMessage();

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();

        assertThat(TEST_SUITE_UNAUTHENTICATED_STATUS_CODE + "\n" + errorMessage, customResponseTestSuite.getStatusCode(), equalTo(400));
        assertThat(TEST_SUITE_UNAUTHENTICATED_FAILED, errorMessage, equalTo(TEST_SUITE_TITLE_MUST_BE_STRING));
    }
    @Test(priority = 1)
    @Epic("Creating test suite with negative case 3 --> incorrect api token")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating a test suite with negative outcome 3 --> incorrect api token")
    @Step("1. Creating test suite with POST request \n" +
            "2. Verify create testsuite is failed with error message")
    public void createTestSuiteNegativeTestThree() throws JsonProcessingException, FileNotFoundException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePath = "log/CreateTestSuiteNegativeCases/createTestSuiteNegativeCases/"+ logFileName;
        logger= Logger.getLogger("Tests.TestSuite.CreateTestSuiteNegativeCases.createTestSuiteNegativeTestThree");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        logger.info("Creating test Class with incorrect api token");

        String INCORRECT_TOKEN_API = "eda47371622604158ddf064b0cfffdb0ba466e0a4cf03365c71293f0d534a5f";

        BaseAPIClass.CustomResponse customResponseTestSuite = businessLayer.createTestSuiteInBusinessLayer(BASE_URL, CONTENT_TYPE, INCORRECT_TOKEN_API, testSuite, project, logger);
        String errorMessage = customResponseTestSuite.getErrorMessageShort();

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();

        assertThat(TEST_SUITE_UNAUTHENTICATED_STATUS_CODE + "\n" + errorMessage, customResponseTestSuite.getStatusCode(), equalTo(401));
        assertThat(TEST_SUITE_UNAUTHENTICATED_FAILED, errorMessage, equalTo(TEST_SUITE_UNAUTHENTICATED));
    }
    @Test(priority = 1)
    @Epic("Deleting test suite with negative case 4 --> empty code")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating a test suite with negative outcome 4 --> empty code")
    @Step("1. Creating test suite with POST request \n" +
            "2. Verify create testsuite is failed with error message")
    public void createTestSuiteNegativeTestFour() throws IOException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePath = "log/CreateTestSuiteNegativeCases/createTestSuiteNegativeCases/"+ logFileName;
        logger= Logger.getLogger("Tests.TestSuite.CreateTestSuiteNegativeCases.createTestSuiteNegativeTestFour");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        logger.info("Creating Test Class with not defining test suite parameters");

        notDefinedProjectParameters.setCode("2");

        BaseAPIClass.CustomResponse customResponseTestSuite = businessLayer.createTestSuiteInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, testSuite, notDefinedProjectParameters, logger);
        String errorMessage = customResponseTestSuite.getErrorMessageForDelete(logger);

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();

        assertThat(TEST_SUITE_PROJECT_NOT_FOUND_STATUS_CODE + "\n" + errorMessage, customResponseTestSuite.getStatusCode(), equalTo(404));
        assertThat(TEST_SUITE_PROJECT_NOT_FOUND_FAILED, errorMessage, equalTo(TEST_SUITE_PROJECT_NOT_FOUND));
    }
}
