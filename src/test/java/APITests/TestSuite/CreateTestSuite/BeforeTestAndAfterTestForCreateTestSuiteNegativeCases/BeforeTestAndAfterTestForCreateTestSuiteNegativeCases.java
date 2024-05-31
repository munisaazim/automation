package APITests.TestSuite.CreateTestSuite.BeforeTestAndAfterTestForCreateTestSuiteNegativeCases;

import API.Base.BaseAPIClass;
import API.BusinessLayer.BusinessLayer;
import APITests.BaseTestComponents.BaseTestComponents;
import FactoryAndBuilder.DataFactory.ProjectDataFactory;
import FactoryAndBuilder.DataFactory.TestSuiteDataFactory;
import Models.Project;
import Models.TestSuite;

import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BeforeTestAndAfterTestForCreateTestSuiteNegativeCases extends BaseTestComponents {
    protected Project project, notDefinedProjectParameters;;
    protected ProjectDataFactory projectDataFactory;
    protected TestSuite testSuite;
    protected TestSuiteDataFactory testSuiteDataFactory;
    protected Logger logger;
    protected String logFilePath;
    protected BusinessLayer businessLayer;

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
    @Description("Initializing project,businesslayer and testSuite objects")
    @Step("Initializing project,businesslayer and testSuite objects")

    public void initializationAndCreateProject() throws Exception {
        String methodName = Reporter.getCurrentTestResult().getMethod().getMethodName();
        String logFileName = methodName + "_" + formattedDateTime  + ".";

        logFilePath = "log/CreateTestSuiteNegativeCases/setUp/"+ logFileName;
        logger = Logger.getLogger("Tests.CreateTestSuiteNegativeCases.initializeObjectsForTestSuiteCreate");
        FileAppender fileAppender = setFileAppender(logFilePath);
        logger.addAppender(fileAppender);

        project = new Project();
        notDefinedProjectParameters = new Project();
        projectDataFactory = new ProjectDataFactory();
        testSuite = new TestSuite();
        testSuiteDataFactory = new TestSuiteDataFactory();
        businessLayer = new BusinessLayer();

        project = projectDataFactory.createProjectToCreateTestSuiteForNegativeCase();

        logger.info("Initializing testSuite/businesslayer objects for create testSuite negative tests");

        BaseAPIClass.CustomResponse customResponse = businessLayer.createProjectInBusinessLayer(BASE_URL, CONTENT_TYPE,API_TOKEN,project,logger);

        logger.info("Create project for create testSuite");
        logger.info("Create project response code: " + customResponse.getStatusCode());
        logger.info("Create project response message: " + customResponse.getBody());

        if(customResponse.getStatusCode()==200){
            assertThat("Status code: " + customResponse.getStatusCode(),customResponse.getStatusCode(), equalTo(200));
        }
        else{
            throw new Exception("Not created new project");
        }
    }

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
