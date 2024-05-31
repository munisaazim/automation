package APITests.Project.DeleteProject.Positive;

import API.Base.BaseAPIClass;
import APITests.Project.DeleteProject.Positive.BeforeTestAndAfterTestForDeleteProjectPositiveCases.BeforeTestAndAfterTestForDeleteProjectPositiveCases;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteProjectPositiveCases extends BeforeTestAndAfterTestForDeleteProjectPositiveCases {

    @Test(priority = 1)
    @Epic("Deleting project with positive case")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with positive outcome")
    @Step("1. Creating project and businessLayer objects\n" +
            "2. Creating project with POST request\n" +
            "3. Verifying project is created with GET request\n" +
            "4. Deleting created project with POST request\n" +
            "5. Verifying deleted project not present in the list with GET request"
    )
    public void deleteProjectByCode() throws FileNotFoundException, JsonProcessingException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePathDeleteProject = "log/DeleteProjectByCodePositiveCase/deleteProjectByCodePositiveCase/" + logFileName;
        logger = Logger.getLogger("APITests.DeleteProjectByCodePositiveCase.deleteProjectByCode");
        FileAppender fileAppender = setFileAppender(logFilePathDeleteProject);
        logger.addAppender(fileAppender);

        BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);
        logger.info("Delete created project");
        logger.info("Delete project response code: " + customResponse.getStatusCode());
        logger.info("Delete project response message: " + customResponse.getBody());

        assertThat("Status code: " + customResponse.getStatusCode(), customResponse.getStatusCode(), equalTo(200));

        BaseAPIClass.CustomResponse customResponseForGetProjectCode = businessLayer.getProjectByCode(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);
        logger.info("Get deleted project");
        logger.info("Get project response code: " + customResponse.getStatusCode());
        logger.info("Get project response message: " + customResponse.getBody());
        String errorMessage = customResponseForGetProjectCode.getErrorMessageForDelete(logger);

        attachingLogFileToAllure(logFilePathDeleteProject);
        deletingDataFromLogger();

        assertThat(EMPTY_CODE_STATUS_CODE_MESSAGE + "\n" + errorMessage, customResponseForGetProjectCode.getStatusCode(),  equalTo(404));
        assertThat(DELETE_PROJECT_NOT_FOUND_FAILED, errorMessage, equalTo(DELETE_PROJECT_NOT_FOUND));
    }

}
