package API.BusinessLayer;

import API.APIPages.ProjectAPI;
import API.APIPages.TestSuiteAPI;
import API.Base.BaseAPIClass;
import Models.Project;

import Models.TestSuite;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;

public class BusinessLayer {
    ProjectAPI projectAPI;
    TestSuiteAPI testSuiteAPI;
    public BaseAPIClass.CustomResponse createProjectInBusinessLayer(String baseUrl, String contentType, String token, Project project, Logger logger) throws JsonProcessingException {
        projectAPI = new ProjectAPI(baseUrl,token, contentType);
        logger.debug("Sending new project details in BusinessLayerAPI to create new project in ProjectAPI");
        return projectAPI.createProject(project, logger);
    }
    public BaseAPIClass.CustomResponse deleteProjectInBusinessLayer(String baseUrl, String contentType, String token, Project project, Logger logger){
        projectAPI = new ProjectAPI(baseUrl, token, contentType);
        logger.debug("Sending project details in BusinessLayerAPI to delete created projected in ProjectAPI");
        return projectAPI.deleteProject(project,logger);
    }
    public BaseAPIClass.CustomResponse getProjectByCode(String baseUrl, String contentType,String token, Project project, Logger logger){
        projectAPI = new ProjectAPI(baseUrl, token,contentType);
        logger.debug("Sending project details in BusinessLayerAPI to get created projected in ProjectAPI");
        return projectAPI.getProject(project, logger);
    }
    public BaseAPIClass.CustomResponse createTestSuiteInBusinessLayer(String baseUrl, String contentType, String token, TestSuite testSuite, Project project,Logger logger) throws JsonProcessingException {
        testSuiteAPI = new TestSuiteAPI(baseUrl,token,contentType);
        logger.debug("Sending new testSuite details in BusinessLayerAPI to create new testSuite in TestSuiteAPI");
        return testSuiteAPI.createTestSuite(testSuite,project,logger);
    }
}
