package APITests.Project.CreateProject.Negative;
import APITests.Project.CreateProject.Negative.BeforeTestAndAfterTestForCreateProjectNegativeCases.BeforeTestAndAfterTestForCreateProjectNegativeCases;

import API.Base.BaseAPIClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//Creating Test Pages with required and optional fields -->negative tests

public class CreateProjectNegativeCases extends BeforeTestAndAfterTestForCreateProjectNegativeCases{
    @Test(priority = 1)
    @Epic("Creating project with negative case 1 --> minimum length")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creating project with negative outcome 1 --> minimum length")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectWithMinimumLength() throws JsonProcessingException, FileNotFoundException {
        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();

        NegativeLogFile = "log/CreateProjectNegativeCases/createProjectNegativeCases/" + getName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.CreateProjectPositiveCases");
        FileAppender fileAppender = setFileAppender(NegativeLogFile);
        logger.addAppender(fileAppender);

        logger.info("Creating project with minimum length");

        project = projectDataFactory.createProjectWithMinimumLength();

        logger.info("Creating project with minimum length of title and code");

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,  API_TOKEN, project, logger);
        String errorMessage = customResponse.getErrorMessage();


        attachingLogFileToAllure(NegativeLogFile);
        deletingDataFromLogger();

        assertThat(PROJECT_CODE_MIN_LENGTH_STATUS_CODE_MESSAGE_FAILED + "\n" + errorMessage, customResponse.getStatusCode(),  equalTo(400));
        assertThat(PROJECT_CODE_MIN_LENGTH_MESSAGE_FAILED, errorMessage, equalTo(PROJECT_CODE_MIN_LENGTH_MESSAGE));

    }
    @Test(priority = 2)
    @Epic("Creating project with negative case 2 --> maximum length")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creating project with negative outcome 2 --> maximum length")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectWithMaximumLength() throws JsonProcessingException, FileNotFoundException {
        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();

        NegativeLogFile = "log/CreateProjectNegativeCases/createProjectNegativeCases/" + getName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.CreateProjectPositiveCases");
        FileAppender fileAppender = setFileAppender(NegativeLogFile);
        logger.addAppender(fileAppender);

        logger.info("Creating project with maximum length");

        project = projectDataFactory.createProjectWithMaximumLength();

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN,project, logger);
        String errorMessage = customResponse.getErrorMessage();

        attachingLogFileToAllure(NegativeLogFile);
        deletingDataFromLogger();

        assertThat(PROJECT_CODE_MAX_LENGTH_STATUS_CODE_MESSAGE_FAILED + "\n" + errorMessage, customResponse.getStatusCode(),  equalTo(400));
        assertThat(PROJECT_CODE_MAX_LENGTH_MESSAGE_FAILED, errorMessage, equalTo(PROJECT_CODE_MAX_LENGTH_MESSAGE));

    }
    @Test(priority = 3)
    @Epic("Creating project with negative case 3 --> empty title")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creating project with negative outcome 3 --> empty title")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectWithEmptyTitle() throws JsonProcessingException, FileNotFoundException {
        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();

        NegativeLogFile = "log/CreateProjectNegativeCases/createProjectNegativeCases/" + getName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.CreateProjectPositiveCases");
        FileAppender fileAppender = setFileAppender(NegativeLogFile);
        logger.addAppender(fileAppender);

        logger.info("Creating project with empty title");

        project = projectDataFactory.createProjectWithEmptyTitle();


        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN,project, logger);
        System.out.println("Get: " + customResponse.getBody());
        String errorMessage = customResponse.getErrorMessage();

        attachingLogFileToAllure(NegativeLogFile);
        deletingDataFromLogger();

        assertThat(EMPTY_TITLE_STATUS_CODE_MESSAGE + "\n" + errorMessage, customResponse.getStatusCode(),  equalTo(400));
        assertThat(EMPTY_TITLE_MESSAGE_FAILED, errorMessage, equalTo(EMPTY_TITLE_MESSAGE));
    }
    @Test(priority = 4)
    @Epic("Creating project with negative case 4 --> empty code")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creating project with negative outcome 4 --> empty code")
    @Step("1. Creating project and businessLayer objects\n"
    )
    public void createProjectWithEmptyCode() throws JsonProcessingException, FileNotFoundException {
        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();

        NegativeLogFile = "log/CreateProjectNegativeCases/createProjectNegativeCases/" + getName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.CreateProjectPositiveCases");
        FileAppender fileAppender = setFileAppender(NegativeLogFile);
        logger.addAppender(fileAppender);

        logger.info("Creating project with empty code");

        project = projectDataFactory.createProjectWithEmptyCode();

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN,project, logger);
        String errorMessage = customResponse.getErrorMessage();

        attachingLogFileToAllure(NegativeLogFile);
        deletingDataFromLogger();

        assertThat(EMPTY_CODE_STATUS_CODE_MESSAGE + "\n" + errorMessage, customResponse.getStatusCode(),  equalTo(400));
        assertThat(EMPTY_CODE_MESSAGE_FAILED, errorMessage, equalTo(EMPTY_CODE_MESSAGE));
    }
    @Test(priority = 5)
    @Epic("Creating project with negative case 5 --> incorrect API")
    @Severity(SeverityLevel.NORMAL)
    @Description("Creating project with negative outcome 5 --> incorrect API")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectUnauthenticated() throws JsonProcessingException, FileNotFoundException {
        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();

        NegativeLogFile = "log/CreateProjectNegativeCases/createProjectNegativeCases/" + getName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.CreateProjectPositiveCases");
        FileAppender fileAppender = setFileAppender(NegativeLogFile);
        logger.addAppender(fileAppender);

        logger.info("Creating project with incorrect api token");

        project = projectDataFactory.createProjectWithRequiredFields();

        String INCORRECT_TOKEN_API = "eda47371622604158ddf064b0cfffdb0ba466e0a4cf03365c71f293f06d534a5f";
        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, INCORRECT_TOKEN_API, project, logger);
        String errorMessage = customResponse.getErrorMessageShort();

        attachingLogFileToAllure(NegativeLogFile);
        deletingDataFromLogger();

        assertThat(UNAUTHENTICATED_CODE_MESSAGE + "\n" + errorMessage, customResponse.getStatusCode(),  equalTo(401));
        assertThat(UNAUTHENTICATED_MESSAGE_FAILED, errorMessage, equalTo(UNAUTHENTICATED));
    }
}