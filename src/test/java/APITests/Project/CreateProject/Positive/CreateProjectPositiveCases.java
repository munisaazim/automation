package APITests.Project.CreateProject.Positive;

import API.Base.BaseAPIClass;
import APITests.Project.CreateProject.Positive.BeforeTestAndAfterTestForCreateProjectPositiveCases.BeforeTestAndAfterTestForCreateProjectPositiveCases;
import FactoryAndBuilder.DataFactory.ProjectDataFactory;
import Models.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

//Creating Test Pages with required and optional fields --> positive tests
public class CreateProjectPositiveCases extends BeforeTestAndAfterTestForCreateProjectPositiveCases {
    @DataProvider(name = "projectData")
    public Object[][] projectData() {
        Project projectWithRequiredFields = projectDataFactory.createProjectWithRequiredFields();
        Project projectWithOptionalFields = projectDataFactory.createProjectWithOptionalFields();
        return new Object[][]{
                {projectWithRequiredFields},
                {projectWithOptionalFields},
        };
    }
    @Test(dataProvider = "projectData",priority = 1)
    @Epic("Creating project with positive case")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating project with API requests and verify its creation")
    @Step("1. Creating project and businessLayer objects\n" +
            "2. Creating project with POST request\n" +
            "3. Verify project is created with GET request"
    )
    public void createProjectPositiveCases(Project project) throws JsonProcessingException, FileNotFoundException {
        projectsList.add(project);

        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String allName = getName + "_" + project.getTitle();

        logFilePath = "log/CreateProjectPositiveCases/createProjectPositiveCases/" + allName  + "_" + formattedDateTime + ".log";
        logger= Logger.getLogger("APITests.CreateProjectPositiveCases");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL,CONTENT_TYPE,API_TOKEN, project, logger);
        logger.info("Creating new project");
        logger.info("Create project response code: " + customResponse.getStatusCode());
        logger.info("Create project response message: " + customResponse.getBody());

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();


        assertThat("Create Pages status code is: " + customResponse.getStatusCode() + "\n" + customResponse.getBody(), customResponse.getStatusCode(), equalTo(200));

        BaseAPIClass.CustomResponse customResponseForCreateProject = businessLayer.getProjectByCode(BASE_URL,CONTENT_TYPE,API_TOKEN,project,logger);
        logger.info("Checking created project");
        logger.info("Get project response code: " + customResponse.getStatusCode());
        logger.info("Get project response message: " + customResponse.getBody());

        assertThat("Create Pages status code is: " + customResponseForCreateProject.getStatusCode() + "\n" + customResponseForCreateProject.getBody(), customResponseForCreateProject.getStatusCode(), equalTo(200));
    }
}
