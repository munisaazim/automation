package APITests.Project.CreateProject.Negative;

import API.Base.BaseAPIClass;
import APITests.Project.CreateProject.Negative.BeforeTestAndAfterTestForCreateProjectNegativeCases.BeforeTestAndAfterTestForCreateProjectNegativeCases;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CreateProjectNegativeCases extends BeforeTestAndAfterTestForCreateProjectNegativeCases {
    @Test(priority = 1)
    @Epic("Creating project with negative case 1 --> minimum length")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with negative outcome 1 --> minimum length")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectWithMinimumLength() throws JsonProcessingException, FileNotFoundException {
        logFilePathFORNEGATIVE = "log/CreateProjectNegativeCases/CreateProjectWithMinLength/CreateProjectWithMinLength_" + formattedDateTime + ".log";
        logger= Logger.getLogger("Tests.CreateProjectNegativeCases");
        FileAppender fileAppender = setFileAppender(logFilePathFORNEGATIVE);
        logger.addAppender(fileAppender);

        logger.info("Creating project with minimum length");

        project.setTitle("o");
        project.setCode("o");

        logger.info("Creating project with minimum length of title and code");
        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);

        String errorMessage = customResponse.getErrorMessage();
        logger.error("Status code" + customResponse.getStatusCode());
        logger.error("Error message" + errorMessage);

        attachingLogFileToAllure(logFilePathFORNEGATIVE);
        deletingDataFromLogger();

        assertThat(PROJECT_CODE_MIN_LENGTH_STATUS_CODE_MESSAGE_FAILED + "\n" + errorMessage, customResponse.getStatusCode(), equalTo(400));
        assertThat(PROJECT_CODE_MIN_LENGTH_MESSAGE, errorMessage, equalTo(PROJECT_CODE_MIN_LENGTH_MESSAGE));
    }
    @Test(priority = 2)
    @Epic("Creating project with negative case 2 --> maximum length")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with negative outcome 2 --> maximum length")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectWithMaximumLength() throws JsonProcessingException, FileNotFoundException {
        logFilePathFORNEGATIVE = "log/CreateProjectNegativeCases/CreateProjectWithMaxLength/CreateProjectWithMaxLength_" + formattedDateTime + ".log";
        logger= Logger.getLogger("Tests.CreateProjectNegativeCases");
        FileAppender fileAppender = setFileAppender(logFilePathFORNEGATIVE);
        logger.addAppender(fileAppender);

        logger.info("Creating project with maximum length");

        project.setTitle("HIMDQTEVXMIKJRG");
        project.setCode("HIMDQTEVXMIKJRG");

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);
        String errorMessage = customResponse.getErrorMessage();
        logger.error("Status code" + customResponse.getStatusCode());
        logger.error("Error message" + errorMessage);

        attachingLogFileToAllure(logFilePathFORNEGATIVE);
        deletingDataFromLogger();

        assertThat(PROJECT_CODE_MAX_LENGTH_STATUS_CODE_MESSAGE_FAILED + "\n" + errorMessage, customResponse.getStatusCode(),  CoreMatchers.equalTo(400));
        assertThat(PROJECT_CODE_MAX_LENGTH_MESSAGE_FAILED, errorMessage, CoreMatchers.equalTo(PROJECT_CODE_MAX_LENGTH_MESSAGE));
    }

    @Test(priority = 3)
    @Epic("Creating project with negative case 3 --> empty title")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with negative outcome 3 --> empty title")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectWithEmptyTitle() throws JsonProcessingException, FileNotFoundException {
        logFilePathFORNEGATIVE = "log/CreateProjectNegativeCases/CreateProjectWithEmptyTitle/CreateProjectWithEmptyTitle_" + formattedDateTime + ".log";
        logger= Logger.getLogger("Tests.CreateProjectNegativeCases");
        FileAppender fileAppender = setFileAppender(logFilePathFORNEGATIVE);
        logger.addAppender(fileAppender);

        logger.info("Creating project with empty title");

        project.setTitle("");
        project.setCode("qwww");

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);
        String errorMessage = customResponse.getErrorMessage();
        logger.error("Status code: " + customResponse.getBody());
        logger.error("Error message: " + errorMessage);

        attachingLogFileToAllure(logFilePathFORNEGATIVE);
        deletingDataFromLogger();

        assertThat(EMPTY_TITLE_STATUS_CODE_MESSAGE + "\n" + errorMessage, customResponse.getStatusCode(),  CoreMatchers.equalTo(400));
        assertThat(EMPTY_TITLE_MESSAGE_FAILED, errorMessage, equalTo(EMPTY_TITLE_MESSAGE));
    }
    @Test(priority = 4)
    @Epic("Creating project with negative case 4 --> empty code")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with negative outcome 4 --> empty code")
    @Step("1. Creating project and businessLayer objects\n")
    public void createProjectWithEmptyCode() throws JsonProcessingException, FileNotFoundException {
        logFilePathFORNEGATIVE = "log/CreateProjectNegativeCases/CreateProjectWithEmptyCode/CreateProjectWithEmptyCode_" + formattedDateTime + ".log";
        FileAppender fileAppender = setFileAppender(logFilePathFORNEGATIVE);
        logger.addAppender(fileAppender);

        logger.info("Creating project with empty code");

        project.setTitle("TTt");
        project.setCode("");

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,API_TOKEN,project,logger);

        String errorMessage = customResponse.getErrorMessage();
        logger.info("Status code: " + customResponse.getStatusCode());
        logger.info("Error message: " + errorMessage);

        attachingLogFileToAllure(logFilePathFORNEGATIVE);
        deletingDataFromLogger();

        assertThat(EMPTY_CODE_STATUS_CODE_MESSAGE + "\n" + errorMessage, customResponse.getStatusCode(),  CoreMatchers.equalTo(400));
        assertThat(EMPTY_CODE_MESSAGE_FAILED, errorMessage, CoreMatchers.equalTo(EMPTY_CODE_MESSAGE));
    }
    @Test(priority = 5)
    @Epic("")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with negative outcome 5 --> incorrect API")
    @Step("1. Creating project with POST request\n" +
            "2. Verify create project is failed with error message\n"
    )
    public void createProjectUnathenticated() throws JsonProcessingException, FileNotFoundException {
        logFilePathFORNEGATIVE = "log/CreateProjectNegativeCases/CreateProjectUnauthenticated/CreateProjectUnauthenticated_" + formattedDateTime + ".log";
        logger= Logger.getLogger("Tests.CreateProjectNegativeCases");
        FileAppender fileAppender = setFileAppender(logFilePathFORNEGATIVE);
        logger.addAppender(fileAppender);

        logger.info("Creating project with incorrect api token");

        project.setTitle("rttttt");
        project.setCode("rttttt");
        String INCORRECT_TOKEN_API = "eda47371622604158ddf064b0cfffdb0ba466e0a4cf03365c71f293f06d534a5f";

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, INCORRECT_TOKEN_API,project,logger);
        String errorMessage = customResponse.getErrorMessageShort();
        logger.info("Status code: " + customResponse.getStatusCode());
        logger.info("Error message" + errorMessage);

        attachingLogFileToAllure(logFilePathFORNEGATIVE);
        deletingDataFromLogger();


    }

}
