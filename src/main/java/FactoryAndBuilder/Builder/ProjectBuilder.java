package FactoryAndBuilder.Builder;

import Models.Project;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ProjectBuilder {
    @JsonProperty("title")
    private String title;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    Project projectUI;

    public ProjectBuilder title(String title) {
        this.title = title;
        return this;
    }
    public ProjectBuilder code(String code) {
        this.code = code;
        return this;
    }
    public ProjectBuilder description(String description) {
        this.description = description;
        return this;
    }
    public Project build() {
        return new Project(title, code, description);
    }
    public Project getProjectUI(Project project){
        projectUI = new Project();
        projectUI.setTitle(project.getTitle());
        projectUI.setDescription(project.getDescription());
        projectUI.setCode(project.getCode());
        return projectUI;
    }
}
