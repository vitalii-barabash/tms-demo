package com.example.demo.rest;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.external.QueryServiceClient;
import com.example.demo.rest.model.TestCaseDto;
import com.example.demo.service.TestCaseService;
import com.example.demo.util.TestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = TestCaseController.class)
@MockBean(QueryServiceClient.class)
class TestCaseControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private TestCaseService testCaseService;

    @Test
    void canGetAll() throws Exception {
        TestCaseDto testCaseDto = TestUtil.fakeTestCaseDto();
        List<TestCaseDto> expectedTestCases = List.of(testCaseDto);
        when(testCaseService.getAll()).thenReturn(expectedTestCases);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/testcases")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writeValueAsString(expectedTestCases)));
    }
}