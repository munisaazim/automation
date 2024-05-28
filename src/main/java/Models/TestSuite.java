package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        @JsonProperty("id")
        private int id;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
    }
}
