package com.example.demo.util;

import com.example.demo.dataaccess.model.TestCase;
import com.example.demo.rest.model.TestCaseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtil {

    private static final String TESTCASE_DESC = "testcase_desc";

    public TestCase fakeTestCase() {
        TestCase testCase = new TestCase();
        testCase.setDescription(TESTCASE_DESC);
        return testCase;
    }

    public TestCaseDto fakeTestCaseDto() {
        TestCaseDto testCaseDto = new TestCaseDto();
        testCaseDto.setId(1L);
        testCaseDto.setDescription(TESTCASE_DESC);
        return testCaseDto;
    }
}
