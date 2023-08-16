package com.example.demo.dataaccess.mapper;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.dataaccess.model.TestCase;
import com.example.demo.rest.model.TestCaseDto;
import com.example.demo.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.Test;


class TestCaseMapperTest {

    private final TestCaseMapper underTest = new TestCaseMapperImpl();

    @Test
    void canMapToEntity() {
        TestCaseDto testCaseDto = TestUtil.fakeTestCaseDto();
        testCaseDto.setSteps(List.of("one", "two"));

        TestCase entity = underTest.toEntity(testCaseDto);

        assertThat(entity)
                .usingRecursiveComparison()
                .isEqualTo(testCaseDto);
    }
}