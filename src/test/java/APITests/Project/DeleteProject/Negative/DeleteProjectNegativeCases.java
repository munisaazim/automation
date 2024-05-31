package APITests.Project.DeleteProject.Negative;

import API.Base.BaseAPIClass;
import APITests.Project.DeleteProject.Negative.BeforeTestAndAfterTestNegativeCases.BeforeTestAndAfterTestForDeleteProjectNegativeCases;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class DeleteProjectNegativeCases extends BeforeTestAndAfterTestForDeleteProjectNegativeCases {
    @Test(priority = 1)
    @Epic("Deleting project with negative case 1 --> incorrect API")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deleting a project with negative outcome 1 --> incorrect API")
    @Step("1. Deleting project with POST request \n")
    public void deleteProjectByCodeNegativeCaseOne() throws JsonProcessingException, FileNotFoundException {

        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePathDeleteProject = "log/DeleteProjectByCodeNegativeCases/deleteProjectByCodeNegativeCases/" + logFileName;
        logger = Logger.getLogger("Tests.DeleteProjectByCodeNegativeCases.deleteProjectByCodeNegativeCaseOne");
        FileAppender fileAppender = setFileAppender(logFilePathDeleteProject);
        logger.addAppender(fileAppender);

        String INCORRECT_TOKEN_API = "eda47371622604158ddf064b0cfffdb0ba466e0a4cf03365c71293f0d534a5f";

        logger.info("Deleting project with incorrect API");

        BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,INCORRECT_TOKEN_API, project, logger);
        String errorMessage = customResponse.getErrorMessageShort();

        attachingLogFileToAllure(logFilePathDeleteProject);
        deletingDataFromLogger();

        assertThat(DELETE_PROJECT_BY_CODE_STATUS_CODE_UNATHORIZED_MESSAGE + "\n" + errorMessage, customResponse.getStatusCode(), equalTo(401));
        assertThat(UNAUTHENTICATED_MESSAGE_FAILED, errorMessage, equalTo(UNAUTHENTICATED));
    }
    public void deleteProjectByCodeNegativeCaseTwo() throws JsonProcessingException, FileNotFoundException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePathDeleteProject = "log/DeleteProjectByCodeNegativeCases/deleteProjectByCodeNegativeCases/" + logFileName;
        logger = Logger.getLogger("Tests.DeleteProjectByCodeNegativeCases.deleteProjectByCodeNegativeCaseTwo");
        FileAppender fileAppender = setFileAppender(logFilePathDeleteProject);
        logger.addAppender(fileAppender);

        logger.info("Deleting project with incorrect url");
        BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,API_TOKEN, project, logger);

        String errorMessage= customResponse.getErrorMessageWithData(logger);

        attachingLogFileToAllure(logFilePathDeleteProject);
        deletingDataFromLogger();

        assertThat(DELETE_PROJECT_INCORRECT_URL_STATUS_CODE + "\n" + errorMessage, customResponse.getStatusCode(), equalTo(405));
        assertThat(DELETE_PROJECT_INCORRECT_URL_FAILED, errorMessage, equalTo(DELETE_PROJECT_INCORRECT_URL));
    }
    @Test(priority = 1)
    @Epic("Deleting project with negative case 3 --> non existed project")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deleting a project with negative outcome 3 --> non existed project")
    @Step("1. Deleting project with POST request \n" +
            "2. Verify delete project is failed with error message")
    public void deleteProjectByCodeNegativeCaseThree() throws JsonProcessingException, FileNotFoundException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePathDeleteProject = "log/DeleteProjectByCodeNegativeCases/deleteProjectByCodeNegativeCases/" + logFileName;
        logger = Logger.getLogger("Tests.DeleteProjectByCodeNegativeCases.deleteProjectByCodeNegativeCaseThree");
        FileAppender fileAppender = setFileAppender(logFilePathDeleteProject);
        logger.addAppender(fileAppender);

        logger.info("Deleting project with non existed project");

        project = projectDataFactory.deleteProjectByCodeNegativeCaseThree();

        BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,API_TOKEN, project, logger);
        String errorMessage = customResponse.getErrorMessageForDelete(logger);

        attachingLogFileToAllure(logFilePathDeleteProject);
        deletingDataFromLogger();

        assertThat(DELETE_PROJECT_NOT_FOUND_STATUS_CODE + "\n" + errorMessage, customResponse.getStatusCode(),  equalTo(404));
        assertThat(DELETE_PROJECT_NOT_FOUND_FAILED, errorMessage, equalTo(DELETE_PROJECT_NOT_FOUND));
    }
}
