package API.BusinessLayer;

import API.APIPages.ProjectAPI;
import API.Base.BaseAPIClass;
import Models.Project;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.log4j.Logger;

public class BusinessLayer {
    ProjectAPI projectAPI;
    public BaseAPIClass.CustomResponse createProjectInBusinessLayer(String baseUrl, String contentType, String token, Project project, Logger logger) throws JsonProcessingException {
        projectAPI = new ProjectAPI(baseUrl,token, contentType);
        logger.debug("Sending new project details in BusinessLayerAPI to create new project in ProjectAPI");
        return projectAPI.createProject(project, logger);
    }
    public BaseAPIClass.CustomResponse deleteProjectInBusinessLayer(String baseUrl, String contentType, String token, Project project, Logger logger){
        projectAPI = new ProjectAPI(baseUrl, token, contentType);
        return projectAPI.deleteProject(project,logger);
    }
    public BaseAPIClass.CustomResponse getProjectByCode(String baseUrl, String contentType,String token, Project project, Logger logger){
        projectAPI = new ProjectAPI(baseUrl, token,contentType);
        return projectAPI.getProjectByCode(project, logger);
    }
}
