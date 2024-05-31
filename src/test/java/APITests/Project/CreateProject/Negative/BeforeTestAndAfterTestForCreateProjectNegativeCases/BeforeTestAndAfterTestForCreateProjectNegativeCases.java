package APITests.Project.CreateProject.Negative.BeforeTestAndAfterTestForCreateProjectNegativeCases;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

//Defining BeforeTestAndAfterTestCreateTestSuite for Crete Pages in Negative Cases
public class BeforeTestAndAfterTestForCreateProjectNegativeCases extends BaseTestComponents {
    protected BusinessLayer businessLayer;
    protected Project project;
    protected ProjectDataFactory projectDataFactory;
    protected Logger logger;
    protected String NegativeLogFile, logFilePathDeleteProject;
    protected BaseAPIClass.CustomResponse customResponse;

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
    @Severity(SeverityLevel.CRITICAL)
    @Description("Initializing project and businesslayer objects")
    @Step("Initializing project and businesslayer objects")
    public void initializeObjectsAndCreateProject() throws FileNotFoundException {
        NegativeLogFile = "log/CreateProjectNegativeCases/initializeObjectsForProjectDelete/initializeObjectsForProjectDelete_" + formattedDateTime + ".log";
        logger = Logger.getLogger("Tests.CreateProjectNegativeCases.initializeObjectsForProjectDelete");
        FileAppender fileAppender = setFileAppender(NegativeLogFile);
        logger.addAppender(fileAppender);

        project = new Project();
        businessLayer = new BusinessLayer();
        projectDataFactory = new ProjectDataFactory();
        logger.info("Initializing project/businesslayer objects for create project negative tests");

        attachingLogFileToAllure(NegativeLogFile);
        deletingDataFromLogger();
    }
}