package FactoryAndBuilder.DataFactory;

import FactoryAndBuilder.Builder.ProjectBuilder;
import Models.Project;

public class ProjectDataFactory {
    public Project createProjectWithRequiredFields(){
        return new ProjectBuilder().title("ProjectWithRequiredFields").code("code").build();
    }
    public Project createProjectWithOptionalFields(){
        return new ProjectBuilder().title("ProjectWithOptionalFields").code("codeTwo").description("description").build();
    }
    public Project createProjectWithMinimumLength(){
        return new ProjectBuilder().title("o").code("o").build();
    }
    public Project createProjectWithMaximumLength(){
        return new ProjectBuilder().title("HIMDQTEVXMIKJRG").code("HIMDQTEVXMIKJRG").build();
    }
    public Project createProjectWithEmptyTitle(){
        return new ProjectBuilder().title("").code("qqqq").build();
    }
    public Project createProjectWithEmptyCode(){
        return new ProjectBuilder().title("qqqq").code("").build();
    }
    public Project createProjectForDeletePositiveCaseInBeforeClass(){
        return new ProjectBuilder().title("ou27").code("ou27").build();
    }
    public Project createProjectForDeleteNegativeCaseInBeforeClass(){
        return new ProjectBuilder().title("bvc3").code("bvc3").build();
    }
    public Project deleteProjectByCodeNegativeCaseThree(){
        return new ProjectBuilder().title("").code("3").build();
    }
    public Project createProjectToCreateTestSuiteForPositiveCase(){
        return new ProjectBuilder().title("testSuiteCP").code("suiteCP").build();
    }
    public Project createProjectToCreateTestSuiteForNegativeCase(){
        return new ProjectBuilder().title("testSuiteCN").code("suiteCN").build();
    }
    public Project createProjectToUpdateTestSuiteForPositiveCase(){
        return new ProjectBuilder().title("testSuiteUP").code("suiteUP").build();
    }
    public Project createProjectToUpdateTestSuiteForNegativeCase(){
        return new ProjectBuilder().title("testSuiteUN").code("suiteUN").build();
    }
    public Project createProjectToDeleteTestSuiteForPositiveCase(){
        return new ProjectBuilder().title("testSuiteDP").code("suiteDP").build();
    }
    public Project createProjectToDeleteTestSuiteForNegativeCase(){
        return new ProjectBuilder().title("testSuiteDN").code("suiteDN").build();
    }
    public Project notDefinedProjectToDeleteTestSuiteForNegativeCase(){
        return new ProjectBuilder().code("code1").build();
    }
    public Project createProjectToCreateTestCaseForPositiveCase(){
        return new ProjectBuilder().title("testCaseCP").code("caseCP").build();
    }
    public Project createProjectToCreateTestCaseForNegativeCase(){
        return new ProjectBuilder().title("testCaseCN").code("caseCN").build();
    }
    public Project createTestCaseNegativeCaseOne(){
        return new ProjectBuilder().code("UnknownOne").build();
    }
    public Project createProjectToUpdateTestCaseForPositiveCase(){
        return new ProjectBuilder().title("testCaseUP").code("caseUP").build();
    }
    public Project createProjectToUpdateTestCaseForNegativeCase(){
        return new ProjectBuilder().title("testCaseUN").code("caseUN").build();
    }
    public Project updateTestCaseNegativeCaseOne() {
        return new ProjectBuilder().code("11").build();
    }
    public Project createProjectToDeleteTestCaseForPositiveCase(){
        return new ProjectBuilder().title("testCaseDP").code("caseDP").build();
    }
    public Project createProjectToDeleteTestCaseForNegativeCase(){
        return new ProjectBuilder().title("testCaseDN").code("caseDP").build();
    }
    public Project deleteTestCaseNegativeTestOne(){
        return new ProjectBuilder().code("code1").build();
    }
    public Project createProjectWithRequiredFieldForUI(){
        return new ProjectBuilder().title("ProjectForUIWithMandateFields").code("PoMandat").build();
    }
    public Project createProjectWithOptionalFieldForUI(){
        return new ProjectBuilder().title("ProjectForUIWithOptionalFields").code("PoOpt").description("desc").build();
    }
}
