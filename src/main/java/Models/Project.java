package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Project {
    @JsonProperty("title")
    private String title;
    @JsonProperty("code")
    private String code;
    @JsonProperty("description")
    private String description;
    private Result result;
    public Project() {
        // Default constructor
    }
    public Project(String title, String code, String description) {
        this.title = title;
        this.code = code;
        this.description = description;
    }
    public String getTitle() {
        return title != null ? title : "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
    // equals and toString overrides for Project
    @Override
    public String toString() {
        return "Project{" +
                "title='" + title + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", result=" + (result != null ? result.toString() : "null") +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Project project = (Project) obj;
        return Objects.equals(title, project.title) &&
                (code != null ? code.equalsIgnoreCase(project.code) : project.code == null) &&
                (description == null || description.isEmpty() ? project.description == null || project.description.isEmpty() : description.equals(project.description));
    }


    // Static inner class Result
    public static class Result {
        private List<Project> entities = Collections.emptyList();
        private String code;
        private String description;

        // Getters and setters
        public List<Project> getEntities() {
            return entities;
        }

        public void setEntities(List<Project> entities) {
            this.entities = entities;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


        // equals and toString overrides for Result
        @Override
        public String toString() {
            return "Result{" +
                    "entities=" + entities +
                    ", code='" + code + '\'' +
                    '}';
        }
    }
}
