package APITests.TestSuite.CreateTestSuite.Positive.BeforeTestAndAfterTestForCreateTestSuitePositiveCases;

import API.Base.BaseAPIClass;
import API.BusinessLayer.BusinessLayer;
import APITests.BaseTestComponents.BaseTestComponents;
import FactoryAndBuilder.DataFactory.ProjectDataFactory;
import FactoryAndBuilder.DataFactory.TestSuiteDataFactory;
import Models.Project;
import Models.TestSuite;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BeforeTestAndAfterTestForCreateTestSuitePositiveCases extends BaseTestComponents {
    protected Project project;
    protected TestSuite  testSuite;
    protected List<TestSuite> testSuiteList;
    protected BusinessLayer businessLayer;
    protected ProjectDataFactory projectDataFactory;
    protected TestSuiteDataFactory testSuiteDataFactory;
    protected Logger logger;
    protected String logFilePath;
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
    @Severity(SeverityLevel.NORMAL)
    @Description("Creating a new project --> to create test suite")
    @Step("1. Creating project and businessLayer objects\n" +
            "2. Creating project with POST request"
    )
    public void initializationAndCreateProject() throws Exception {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime + ".log";

        logFilePath = "log/CreateTestSuitePositiveCases/setUp/" + logFileName;
        logger = Logger.getLogger("Tests.CreateTestSuitePositiveCases.initializeObjectsAndCreateProject");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);
        logger.addAppender(fileAppender);

        project = new Project();
        testSuite = new TestSuite();
        testSuiteList = new ArrayList<>();
        businessLayer = new BusinessLayer();
        projectDataFactory = new ProjectDataFactory();
        testSuiteDataFactory = new TestSuiteDataFactory();

        project = projectDataFactory.createProjectToCreateTestSuiteForPositiveCase();

        logger.info("Initializing testSuite/businesslayer objects for create testSuite negative tests");

        BaseAPIClass.CustomResponse customResponseForCreateProject = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,API_TOKEN,project,logger);

        logger.info("Create project for create testSuite");
        logger.info("Create project response code: " + customResponseForCreateProject.getStatusCode());
        logger.info("Create project response message: " + customResponseForCreateProject.getBody());

        if(customResponseForCreateProject.getStatusCode()==200){
            assertThat("Status code: " + customResponseForCreateProject.getStatusCode(),customResponseForCreateProject.getStatusCode(), equalTo(200));
        }
        else {
            throw new Exception("Not created new project");
        }

    }
    //Deleting project
    @AfterClass
    @Severity(SeverityLevel.CRITICAL)
    @Description("Deleting created project")
    @Step("Deleting created project")
    public void afterTestDeleteCreatedTest() throws FileNotFoundException {
        String getName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String allName = getName + "_" + project.getTitle();

        logFilePath = "log/CreateTestSuites/tearDown" + allName + "_" + formattedDateTime + ".log";
        logger = Logger.getLogger("Tests.CreateTestSuitePositiveCases");
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
