package APITests.Project.CreateProject.Positive.BeforeTestAndAfterTestForCreateProjectPositiveCases;

import API.Base.BaseAPIClass;
import API.BusinessLayer.BusinessLayer;
import APITests.BaseTestComponents.BaseTestComponents;
import Models.Project;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BeforeTestAndAfterTestForCreateProjectPositiveCases extends BaseTestComponents {
    protected BusinessLayer businessLayer;
    protected Project project;
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
    @BeforeMethod
    @Severity(SeverityLevel.CRITICAL)
    @Description("Initializing project and businesslayer objects")
    @Step("Initializing project and businesslayer objects")

    public void initializationforCreateProjectPositiveCase(){
        project = new Project();
        businessLayer = new BusinessLayer();

    }
    @AfterMethod
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deleting created project")
    @Step("Deleting created project")
    public void deleteCreatedProject() throws FileNotFoundException {
        logFilePathDeleteProject = "log/DeleteProjectByCodePositiveCase/deleteProjectByCode_" + formattedDateTime + ".log";
        logger = Logger.getLogger("Tests.DeleteProjectByCodePositiveCase");
        FileAppender fileAppender = setFileAppender(logFilePathDeleteProject);
        logger.addAppender(fileAppender);

        BaseAPIClass.CustomResponse customResponse = businessLayer.deleteProjectInBusinessLayer(BASE_URL, CONTENT_TYPE, API_TOKEN, project, logger);
        logger.info("Response code: " + customResponse.getStatusCode());
        logger.info("Body: " + customResponse.getBody());

        System.out.println("Response code: " + customResponse.getStatusCode());
        System.out.println("Body: " + customResponse.getBody());
        attachingLogFileToAllure(logFilePathDeleteProject);
        deletingDataFromLogger();
    }
}
