package APITests.Project.DeleteProject.Positive.BeforeTestAndAfterTestForDeleteProjectPositiveCases;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;

public class BeforeTestAndAfterTestForDeleteProjectPositiveCases extends BaseTestComponents {
    protected Project project;
    protected BusinessLayer businessLayer;
    protected ProjectDataFactory projectDataFactory;
    protected List<Project>projectList;
    protected Logger logger;
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
    @BeforeClass
    @Epic("Creating a new project to delete it")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Creating a new project to delete it")
    @Step("1. Creating project and businessLayer objects\n" +
            "2. Creating project with POST request"
    )
    public void initializationAndCreateProject() throws JsonProcessingException, FileNotFoundException {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePath = "log/DeleteProjectByCodePositiveCase/setUp/" + logFileName;
        logger = Logger.getLogger("APITests.DeleteProjectByCodePositiveCase.initializeObjectsAndCreateProject");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        project = new Project();
        businessLayer = new BusinessLayer();
        projectDataFactory = new ProjectDataFactory();
        projectList = new ArrayList<>();

        project = projectDataFactory.createProjectToDeleteTestCaseForPositiveCase();

        logger.info("Initializing of all objects");

        projectList.add(project);

        BaseAPIClass.CustomResponse customResponseForCreateProject = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,API_TOKEN, project, logger);

        if(customResponseForCreateProject.getStatusCode() == 200){
            if (customResponseForCreateProject.getStatusCode() == 200) {
                assertThat("Status code: " + customResponseForCreateProject.getStatusCode(), customResponseForCreateProject.getStatusCode(), equalTo(200));
            } else {
                assertThat("Status code: " + customResponseForCreateProject.getStatusCode(), customResponseForCreateProject.getStatusCode(),not(equalTo(200)));
            }
        }
        attachingLogFileToAllure(logFilePath);
        deletingDataFromLogger();
    }

}
