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
}
