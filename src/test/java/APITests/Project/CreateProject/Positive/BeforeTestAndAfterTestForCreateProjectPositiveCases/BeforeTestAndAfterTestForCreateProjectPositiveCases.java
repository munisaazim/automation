package APITests.Project.CreateProject.Positive.BeforeTestAndAfterTestForCreateProjectPositiveCases;

import API.Base.BaseAPIClass;
import API.BusinessLayer.BusinessLayer;
import APITests.BaseTestComponents.BaseTestComponents;
import FactoryAndBuilder.DataFactory.ProjectDataFactory;
import Models.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BeforeTestAndAfterTestForCreateProjectPositiveCases extends BaseTestComponents {
    protected BusinessLayer businessLayer;
    protected Project project;
    protected Logger logger;
    protected List<Project> projectsList;
    protected ProjectDataFactory projectDataFactory;
    protected String logFilePath, logFilePathDeleteProject;

    protected FileAppender setFileAppender(String logFilePath){
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile(logFilePath);
        fileAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5p %c{1} - %m%n"));
        fileAppender.activateOptions();
        return  fileAppender;
    }

    protected void deletingDataFromLogger(){
        logger.removeAllAppenders();
        logger.getLoggerRepository().resetConfiguration();
    }
    protected void attachingLogFileToAllure(String logFilePath) throws FileNotFoundException {
        File logFile = new File(logFilePath);
        FileInputStream fileInputStream = new FileInputStream(logFile);
        Allure.addAttachment("Log File", "text/plain", fileInputStream, "txt");

    }
    @BeforeTest
    @Severity(SeverityLevel.CRITICAL)
    @Description("Initializing project and businesslayer objects")
    @Step("Initializing project and businesslayer objects")
    public void initializationForCreateProjectPositiveCase() {
        projectDataFactory = new ProjectDataFactory();
        project = new Project();
        businessLayer = new BusinessLayer();
        projectsList = new ArrayList<>();
    }

    @AfterTest
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deleting created projects")
    @Step("Deleting created projects")
    public void afterTestDeleteCreatedProjects() throws FileNotFoundException {
        for (Project project : projectsList) {
            logFilePath = "log/CreateProjectPositiveCases/tearDown/" + project.getTitle() + "_" + formattedDateTime + ".log";
            logger = Logger.getLogger("APITests.CreateProjectPositiveCases");
            FileAppender fileAppender = setFileAppender(logFilePath);
            logger.addAppender(fileAppender);

            logger.info("Attempting to delete project: " + project.getCode() + " - " + project.getTitle());

            BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);

            logger.info("Deletion response code: " + customResponse.getStatusCode());
            logger.info("Deletion response body: " + customResponse.getBody());

            attachingLogFileToAllure(logFilePath);
            deletingDataFromLogger();

            assertThat("Delete Project status code is: " + customResponse.getStatusCode() + "\n" + customResponse.getBody(), customResponse.getStatusCode(), equalTo(200));
        }
    }

}
