package FactoryAndBuilder.DataFactory;

import FactoryAndBuilder.Builder.TestSuiteBuilder;
import Models.TestSuite;

public class TestSuiteDataFactory {
    public TestSuite createTestSuiteWithRequiredFields(){
        return new TestSuiteBuilder().title("newT").build();
    }
    public TestSuite createTestSuiteWithOptionalFields(){
        return new TestSuiteBuilder().title("newY").description("newY").preconditions("newY").build();
    }
    public TestSuite createTestSuiteOne(){
        return new TestSuiteBuilder().title("nvceuojcaqzcrmomtunbfgzjgrpfawoywvqekkrtgqsrujmnonerlgxfrpqbslhwatimowonlxgneqqlfwakmrbgxjihgksaenbfgyigecbwjtbcviisbqlgefoftmiuldvwbrhnbypwxtkxkemwfauwpajlvwogeygaufxfqkljhxijgvjemuyekgdutbzcngglsyhvfcuusaebwxordxpbhihwqmmxoodkqqgjpoftuijqsvvfpownzaeyrblg").description("desc").preconditions("pre").build();
    }
    public TestSuite createTestSuiteTwo(){
        return new TestSuiteBuilder().description("desc").preconditions("pre").build();
    }
    public TestSuite createTestSuiteThree(){
        return new TestSuiteBuilder().title(null).description("desc").preconditions("pre").build();
    }
}
