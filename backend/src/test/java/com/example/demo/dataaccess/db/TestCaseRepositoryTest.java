package com.example.demo.dataaccess.db;


import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.dataaccess.model.TestCase;
import com.example.demo.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.datasource.url=jdbc:tc:mysql:latest:///testcases"
})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class TestCaseRepositoryTest {

    @Autowired
    private TestCaseRepository underTest;

    @Autowired
    private TestEntityManager testEntityManager;

    private final TestCase testCase = TestUtil.fakeTestCase();

    @BeforeEach
    void setUp() {
        testCase.setDescription("blabla");
    }

    @Test
    void canFindAll() {
        testEntityManager.persistAndFlush(testCase);

        assertThat(underTest.findAll())
                .asList()
                .containsOnly(testCase);
    }

    @Test
    void canFindByDescription() {
        testEntityManager.persistAndFlush(testCase);

        assertThat(underTest.findByDescription("blabla"))
                .asList()
                .containsOnly(testCase);
    }

    @Test
    void canFindByDescription_negative() {
        testEntityManager.persistAndFlush(testCase);

        assertThat(underTest.findByDescription("not_correct"))
                .asList()
                .isEmpty();
    }
}