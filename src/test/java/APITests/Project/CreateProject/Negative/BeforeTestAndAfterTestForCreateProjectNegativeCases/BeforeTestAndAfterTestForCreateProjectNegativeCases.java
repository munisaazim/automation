package APITests.Project.CreateProject.Negative.BeforeTestAndAfterTestForCreateProjectNegativeCases;

import API.BusinessLayer.BusinessLayer;
import APITests.BaseTestComponents.BaseTestComponents;
import Models.Project;
import io.qameta.allure.*;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class BeforeTestAndAfterTestForCreateProjectNegativeCases extends BaseTestComponents {
    protected BusinessLayer businessLayer;
    protected Project project;
    protected Logger logger;
    protected String logFilePathFORNEGATIVE, logFilePathDeleteProject;

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
    public void initializationForCreateProjectNegativeCase() {
        project = new Project();
        businessLayer = new BusinessLayer();
    }
}
