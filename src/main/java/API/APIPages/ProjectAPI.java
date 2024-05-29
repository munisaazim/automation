package API.APIPages;

import API.Base.BaseAPIClass;
import Models.Project;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

public class ProjectAPI extends BaseAPIClass{
    final String RELATIVE_URL;

    public ProjectAPI(String baseUrl, String token, String contentType) {
        super(baseUrl, token, contentType);
        RELATIVE_URL = "/project";
    }
    public Project deserialization(JsonNode jsonNode){
        ObjectMapper objectMapper = new ObjectMapper();
        String projectCode = jsonNode.path("result").path("code").asText();
        Project project = new Project();
        project.setCode(projectCode);
        return project;
    }
    public String serialization(Project project) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(project);
    }
    public CustomResponse createProject(Project project, Logger logger) throws JsonProcessingException {
        String json = serialization(project);
        logger.debug("Sending new project details in ProjectAPI to create new project in BaseAPI");
        return postRequest(RELATIVE_URL, json, String.class,logger);
    }
    public CustomResponse deleteProject(Project project, Logger logger){
        String Relative_Url_Delete = "/" + project.getCode().toUpperCase();
        System.out.println("Relative_Url_Delete ProjectAPI" + Relative_Url_Delete);
        return deleteRequest(RELATIVE_URL, Relative_Url_Delete,logger);
    }

}
