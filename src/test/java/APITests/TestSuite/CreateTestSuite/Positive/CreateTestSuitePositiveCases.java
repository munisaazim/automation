package APITests.TestSuite.CreateTestSuite.Positive;

import API.Base.BaseAPIClass;
import APITests.TestSuite.CreateTestSuite.Positive.BeforeTestAndAfterTestForCreateTestSuitePositiveCases.BeforeTestAndAfterTestForCreateTestSuitePositiveCases;
import Models.TestSuite;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateTestSuitePositiveCases extends BeforeTestAndAfterTestForCreateTestSuitePositiveCases {
    @DataProvider(name = "testSuiteData")
    public Object[][] testSuiteData() {
        TestSuite testSuiteWithRequiredFields = testSuiteDataFactory.createTestSuiteWithRequiredFields();
        TestSuite testSuiteWithOptionalFields = testSuiteDataFactory.createTestSuiteWithOptionalFields();
        return new Object[][]{
                {testSuiteWithRequiredFields},
                {testSuiteWithOptionalFields}
        };
    }
    @Test(dataProvider = "testSuiteData", priority = 1)
    @Epic("Creating test suite with positive case")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating test suite with API requests and verify its creation")
    @Step("1. Creating test suite with POST request \n" +
            "2. Verify test suite is created with GET request")
    public void createTestSuitePositiveCases(TestSuite testSuite) throws JsonProcessingException, FileNotFoundException {
        testSuiteList.add(testSuite);

        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String allName = getName + "_" + testSuite.getTitle();

        logFilePath = "log/CreateTestSuitePositiveCases/createTestSuitePositiveCase/" + allName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("Tests.TestSuite.CreateTestSuitePositiveCases");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        BaseAPIClass.CustomResponse customResponse = businessLayer.createTestSuiteInBusinessLayer(BASE_URL,CONTENT_TYPE,API_TOKEN,testSuite,project,logger);

        logger.info("Creating new testSuite");
        logger.info("Create testSuite response code: " + customResponse.getStatusCode());
        logger.info("Create testSuite response message: " + customResponse.getBody());

        System.out.println("body" + customResponse.getBody());

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();

        assertThat("Create TestSuite status code is: " + customResponse.getStatusCode() + "\n" + customResponse.getBody(), customResponse.getStatusCode(), equalTo(200));

        logFilePathForGetTestSuite = "log/CreateTestSuitePositiveCases/checkCreatedTestSuite/"  + allName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.TestSuite.CreateTestSuitePositiveCases.createTestSuitePositiveCase");
        FileAppender fileAppenderForGetTestSuite = setFileAppender(logFilePathForGetTestSuite);
        logger.addAppender(fileAppenderForGetTestSuite);

        TestSuite createdTestSuite =  businessLayer.getObjectModelForTestSuiteInBusiness(customResponse, TestSuite.class, logger);

        int testSuiteID = createdTestSuite.getResult().getId();
        logger.info("Defining created test suite id");

        BaseAPIClass.CustomResponse customResponseForGetTestSuite = businessLayer.getTestSuite(BASE_URL, CONTENT_TYPE, API_TOKEN,testSuiteID,project,logger);

        attachingLogFileToAllure(logFilePathForGetTestSuite);
        deletingDataFromLogger();

        assertThat("Create Project status code is: " + customResponseForGetTestSuite.getStatusCode() + "\n" + customResponseForGetTestSuite.getBody(), customResponseForGetTestSuite.getStatusCode(), equalTo(200));


    }
}
