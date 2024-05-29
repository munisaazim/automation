package Utils;

import org.testng.annotations.Test;

import java.io.IOException;

public class RunAllureGeneration extends AllureReportGenerator{
    @Test
    public void setUpEnvironment() throws IOException, InterruptedException {
        String reportHistorySourceFolder = "allure-report/history";
        String reportHistoryTargetFolder = "historyPackage";
        String reportHistoryTargetFolderTwo = "historyPackage/history";


        //copying allure-report to history
        //copyReportHistory(reportHistorySourceFolder, reportHistoryTargetFolderTwo);

        // Provide the path to the target folder where the new report will be generated
        String targetFolder = "allure-results";

        setUpEnv();

        // Copy the saved history folder to the target folder
        copyReportHistory(reportHistoryTargetFolder, targetFolder);

        // Generate the new Allure report
        generateAllureReport();


        //Open Allure report
        openReport();
    }
}
