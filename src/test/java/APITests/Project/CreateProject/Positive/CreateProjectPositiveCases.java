package APITests.Project.CreateProject.Positive;

import API.Base.BaseAPIClass;
import APITests.Project.CreateProject.Positive.BeforeTestAndAfterTestForCreateProjectPositiveCases.BeforeTestAndAfterTestForCreateProjectPositiveCases;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateProjectPositiveCases extends BeforeTestAndAfterTestForCreateProjectPositiveCases {

    @DataProvider(name = "projectData")
    public Object[][] projectData() {
        return new Object[][]{
                {"tg16", "tg16", null},
                {"tg17", "tg17", "tg17 description"},
        };
    }

    // Setting project data
    private void createProjectPositiveCaseDefineParameters(String title, String code, String description) {
        project.setTitle(title.toUpperCase());
        project.setCode(code.toUpperCase());
        if (description != null) {
            project.setDescription(description);
        }
        logger.info("Setting data for project object");
    }

    // Running create project with require/optional in positive case
    @Test(dataProvider = "projectData", priority = 1)
    @Epic("Creating project with positive case")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with API requests and verify its creation")
    @Step("1. Creating project and businessLayer objects" +
            "2. Creating project with POST request" +
            "3. Verify project is created with GET request")
    public void createProjectPositiveCase(String title, String code, String description) throws JsonProcessingException, FileNotFoundException {
        logFilePath = "log/CreateProjectPositiveCases/createProjectPositiveCase_" + formattedDateTime + ".log";
        logger = Logger.getLogger("Tests.TestSuite.CreateProjectPositiveCases.createProjectPositiveCase");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        createProjectPositiveCaseDefineParameters(title, code, description);
        logger.info("Initializing project parameters");

        // Perform the API call
        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);

        // Log the response
        logger.info("Response code: " + customResponse.getStatusCode());
        logger.info("Body: " + customResponse.getBody());

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();

        assertThat("Create Project status code is: " + customResponse.getStatusCode() + "\n" + customResponse.getBody(), customResponse.getStatusCode(), equalTo(200));
    }
}
