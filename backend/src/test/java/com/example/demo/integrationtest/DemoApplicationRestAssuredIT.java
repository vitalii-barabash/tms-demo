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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

//import static com.example.demo.invoker.ResponseSpecBuilders.shouldBeCode;
//import static com.example.demo.invoker.ResponseSpecBuilders.validatedWith;

//import com.example.demo.invoker.ApiClient;
//import com.example.demo.invoker.JacksonObjectMapper;

//import static io.restassured.config.ObjectMapperConfig.objectMapperConfig;
//import static io.restassured.config.RestAssuredConfig.config;
//import io.restassured.builder.RequestSpecBuilder;
//import io.restassured.config.LogConfig;

@Disabled
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ContextConfiguration(
        classes = IntegrationTestConfiguration.class,
        initializers = WiremockInitializer.class
)
@ActiveProfiles("inttest")
class DemoApplicationRestAssuredIT {

    // To run this test, uncomment respected lines here and in pom.xml

    @Autowired
    private WireMockServer wireMockServer;

    @Autowired
    private ObjectMapper mapper;

//    private ApiClient api;

    @BeforeEach
    void setUp(@LocalServerPort int port) {
//        api = ApiClient.api(ApiClient.Config.apiConfig().reqSpecSupplier(
//                () -> new RequestSpecBuilder()
//                        .setConfig(config()
//                                .logConfig(LogConfig.logConfig()
//                                        .enableLoggingOfRequestAndResponseIfValidationFails())
//                                .objectMapperConfig(objectMapperConfig()
//                                        .defaultObjectMapper(JacksonObjectMapper.jackson())))
//                        .setPort(port)));
    }

    @Test
    void canReplyToQueryEndpoint_withGeneratedClient() throws JsonProcessingException {
        // given
        Query query = new Query();
        query.setQuestion("this is a test");

        QueryResult expectedResult = new QueryResult();
        expectedResult.setAnswer("this is a success");

        wireMockServer.addStubMapping(post(urlPathMatching("/query"))
                .withRequestBody(matchingJsonPath("$.question", equalTo(query.getQuestion())))
                .willReturn(okJson(mapper.writeValueAsString(expectedResult)))
                .build());

//        // when
//        QueryResult actualQueryResult = api.testCaseController()
//                .query()
//                .body(query)
//                .executeAs(validatedWith(shouldBeCode(200)));
//
//        // then
//        assertThat(actualQueryResult).isEqualTo(expectedResult);
    }

}
