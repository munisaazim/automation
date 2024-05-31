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
import java.util.Enumeration;
import java.util.List;

public class BeforeTestAndAfterTestForCreateProjectPositiveCases extends BaseTestComponents {
    protected BusinessLayer businessLayer;
    protected Project project;
    protected Logger logger;
    protected String logFilePath, logFilePathDeleteProject;
    protected List<Project> projectsList;
    protected ProjectDataFactory projectDataFactory;

    protected FileAppender setFileAppender(String logFilePath) {
        FileAppender fileAppender = new FileAppender();
        fileAppender.setFile(logFilePath);
        fileAppender.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5p %c{1} - %m%n"));
        fileAppender.activateOptions();
        return fileAppender;
    }

    protected void deletingDataFromLogger() {
        logger.removeAllAppenders();
        logger.getLoggerRepository().resetConfiguration();
    }

    protected void attachingLogFileToAllure(String logFilePath) throws FileNotFoundException {
        File logFile = new File(logFilePath);
        FileInputStream fileInputStream = new FileInputStream(logFile);
        Allure.addAttachment("Log File", "text/plain", fileInputStream, "txt");

    }

    //Initializing objects
    @BeforeClass
    @Severity(SeverityLevel.CRITICAL)
    @Description("Initializing project and businesslayer objects")
    @Step("Initializing project and businesslayer objects")
    public void initializeObjectsForProjectCreate() throws FileNotFoundException {

        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePath = "log/CreateProjectPositiveCases/setUp/" + logFileName;
        logger = Logger.getLogger("Tests.CreateProjectPositiveCases.initializeObjectsForProjectCreate");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        project = new Project();
        businessLayer = new BusinessLayer();
        projectDataFactory = new ProjectDataFactory();
        projectsList = new ArrayList<>();
        logger.info("Initializing project/businesslayer objects for create project positive tests");

        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();
    }

    //Deleting project
    @AfterClass
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deleting created project")
    @Step("Deleting created project")
    public void afterTestDeleteCreatedTest() throws FileNotFoundException, JsonProcessingException {

        for (Project project : projectsList) {
                String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();
                String allName = getName + "_" + project.getTitle();

                logFilePath = "log/CreateProjectPositiveCases/tearDown/" + allName + "_" + formattedDateTime + ".log";
                logger = Logger.getLogger("Tests.CreateProjectPositiveCases");
                FileAppender fileAppender = setFileAppender(logFilePath);
                logger.addAppender(fileAppender);

                BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);
                logger.info("Delete project");
                logger.info("Delete project response code: " + customResponse.getStatusCode());
                logger.info("Delete project response message: " + customResponse.getBody());

                attachingLogFileToAllure(logFilePath);
                deletingDataFromLogger();

        }
    }

}