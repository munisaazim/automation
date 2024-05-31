package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;

public class TestSuite {
    String title;
    String description;
    String preconditions;

    private Result result;

    public void setTitle(String title){
        this.title = title;
    }
    @JsonProperty("title")
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    @JsonProperty("description")
    public String getDescription(){
        return this.description;
    }
    public void setPreconditions(String preconditions){
        this.preconditions = preconditions;
    }
    @JsonProperty("preconditions")
    public String getPreconditions(){
        return this.preconditions;
    }

    public TestSuite(){}
    public TestSuite(String title, String description, String preconditions){
        this.title = title;
        this.description = description;
        this.preconditions = preconditions;
    }
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    public static class Result {
        private List<TestSuite> entities = Collections.emptyList();
        private String title;
        private String description;
        private String preconditions;
        @JsonProperty("id")
        private int id;

        public void setEntities(List<TestSuite>entities){
            this.entities = entities;
        }
        public List<TestSuite>getEntities(){return entities;}

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getDescription(){
            return this.description;
        }

        public void setPreconditions(String preconditions) {
            this.preconditions = preconditions;
        }

        public String getPreconditions() {
            return preconditions;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

    }
}
