package Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TestCase {
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("preconditions")
    String preconditions;
    @JsonProperty("postconditions")
    String postconditions;
    @JsonProperty("severity")
    int severity;
    @JsonProperty("priority")
    int priority;
    @JsonProperty("type")
    int type;
    @JsonProperty("layer")
    int layer;
    @JsonProperty("is_flaky")
    int is_flaky;
    @JsonProperty("behavior")
    int behavior;
    @JsonProperty("automation")
    int automation;
    @JsonProperty("status")
    int status;
    @JsonProperty("suite_id")
    int suite_id;
    @JsonProperty("milestone_id")
    int milestone_id;

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        String uuidString = UUID.randomUUID().toString();
        int maxLength = 30 - description.length();
        if (uuidString.length() > maxLength) {
            uuidString = uuidString.substring(0, maxLength);
        }
        this.description = description + uuidString;
    }
    public String getDescription(){
        return this.description;
    }
    public void setStatus(int status){this.status = status;}
    public int getStatus(){return  this.status;}
    public void setMilestone_id(int milestone_id) {
        this.milestone_id = milestone_id;
    }
    public long getMilestone_id() {
        return milestone_id;
    }
    public void setPreconditions(String preconditions){
        this.preconditions = preconditions;
    }
    public String getPreconditions(){
        return this.preconditions;
    }
    public void setPostconditions(String postconditions){
        this.postconditions = postconditions;
    }
    public String getPostconditions(){
        return this.postconditions;
    }
    public void setSeverity(int severity){
        this.severity = severity;
    }
    public int getSeverity(){
        return this.severity;
    }

    //Dropdown
    public void setPriority(int priority){
        this.priority = priority;
    }
    public int getPriority(){
        return this.priority;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType() {
        return this.type;
    }
    public void setLayer(int layer){
        this.layer = layer;
    }
    public int getLayer(){return this.layer;}
    public void setIs_flaky(int is_flaky){this.is_flaky = is_flaky;}
    public int getIs_flaky(){return this.is_flaky;}
    public void setBehavior(int behavior){this.behavior = behavior;}
    public int getBehavior(){return this.behavior;}
    public void setAutomation(int automation){this.automation = automation;}
    public int getAutomation(){return  this.automation;}
    public void setSuite_id(Integer suite_id) {
        this.suite_id = suite_id;
    }
    public int getSuite_id() {
        return suite_id;
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
