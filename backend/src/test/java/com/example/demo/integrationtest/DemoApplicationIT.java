package com.example.demo.integrationtest;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.external.Query;
import com.example.demo.external.QueryResult;
import com.example.demo.integrationtest.initializers.WiremockInitializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        classes = IntegrationTestConfiguration.class,
        initializers = WiremockInitializer.class
)
@ActiveProfiles("inttest")
class DemoApplicationIT {

    @LocalServerPort
    private int port;

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void canReplyToQueryEndpoint(@Autowired TestRestTemplate restTemplate)
            throws JsonProcessingException {
        // given
        Query query = new Query();
        query.setQuestion("this is a test");

        QueryResult expectedResult = new QueryResult();
        expectedResult.setAnswer("this is a success");

        wireMockServer.addStubMapping(post(urlPathMatching("/query"))
                .withRequestBody(matchingJsonPath("$.question", equalTo(query.getQuestion())))
                .willReturn(okJson(mapper.writeValueAsString(expectedResult)))
                .build());

        // when
        ResponseEntity<QueryResult> queryResultResponseEntity = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/testcases/q", query, QueryResult.class);

        // then
        assertThat(queryResultResponseEntity)
                .extracting(ResponseEntity::getStatusCode, ResponseEntity::getBody)
                .containsExactly(HttpStatusCode.valueOf(200), expectedResult);
    }

}
