package FactoryAndBuilder.Builder;

import Models.TestSuite;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestSuiteBuilder {
    String title;
    String description;
    String preconditions;
    private TestSuite.Result result;
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

    public TestSuiteBuilder title(String title){
        this.title = title;
        return this;
    }
    public TestSuiteBuilder description(String description){
        this.description = description;
        return this;
    }
    public TestSuiteBuilder preconditions(String preconditions){
        this.preconditions = preconditions;
        return this;
    }
    public TestSuite build(){return new TestSuite(title,description,preconditions);}
}
