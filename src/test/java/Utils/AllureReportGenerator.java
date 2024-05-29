package Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class AllureReportGenerator {

    public void setUpEnv() {
        // Specify the path to the environment.properties file
        String environmentFilePath = "src/test/java/OuterResources/allure-environment.properties";

        // Specify the path to the allure-results directory
        String allureResultsPath = "allure-results";

        try {
            // Copy the environment.properties file to the allure-results directory
            Path source = new File(environmentFilePath).toPath();
            Path destination = new File(allureResultsPath + "/environment.properties").toPath();
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Generate the Allure report using your preferred method
    }

    public void generateAllureReport() {
        try {
            // Command to generate the Allure report using the saved history folder
            String generateCommand = "allure generate allure-results --clean -o allure-report";

            // Generate the Allure report
            Process generateProcess = Runtime.getRuntime().exec(generateCommand);
            generateProcess.waitFor();

            System.out.println("New Allure report generated successfully!");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void openReport() throws IOException, InterruptedException {
        String generateCommand = "allure open";

        // Generate the Allure report
        Process generateProcess = Runtime.getRuntime().exec(generateCommand);
        generateProcess.waitFor();

        System.out.println("New Allure report generated successfully!");
    }
    public static void copyReportHistory(String sourceFolderPath, String targetFolderPath) {
        try {
            Path sourcePath = Paths.get(sourceFolderPath);
            Path targetPath = Paths.get(targetFolderPath);

            // Copy the entire content of the source folder to the target folder
            Files.walk(sourcePath)
                    .forEach(source -> {
                        try {
                            Path destination = targetPath.resolve(sourcePath.relativize(source));
                            Files.copy(source, destination);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            System.out.println("Report history copied to: " + targetPath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
