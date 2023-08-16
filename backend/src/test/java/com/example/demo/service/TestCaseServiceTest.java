package com.example.demo.service;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import com.example.demo.dataaccess.db.TestCaseRepository;
import com.example.demo.dataaccess.mapper.TestCaseMapper;
import com.example.demo.dataaccess.mapper.TestCaseMapperImpl;
import com.example.demo.dataaccess.model.TestCase;
import com.example.demo.util.TestUtil;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = TestCaseMapperImpl.class)
@ExtendWith(SpringExtension.class)
class TestCaseServiceTest {

    private TestCaseService underTest;

    @MockBean
    private TestCaseRepository repository;

    @Autowired
    private TestCaseMapper mapper;

    @BeforeEach
    void setUp() {
        underTest = new TestCaseService(repository, mapper);
    }

    @Test
    void canGetAll() {
        TestCase testCase = TestUtil.fakeTestCase();
        when(repository.findAll()).thenReturn(List.of(testCase));

        assertThat(underTest.getAll())
                .asList()
                .containsOnly(mapper.toDto(testCase));
    }
}