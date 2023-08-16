package com.example.demo.integrationtest.initializers;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

public class WiremockInitializer implements
        ApplicationContextInitializer<GenericApplicationContext> {

    @Override
    public void initialize(GenericApplicationContext applicationContext) {
        WireMockServer wireMockServer = new WireMockServer(
                wireMockConfig()
                        .dynamicPort()
                        .notifier(new ConsoleNotifier(true))
        );
        wireMockServer.start();

        TestPropertyValues.of(
                "mockport=" + wireMockServer.port()
        ).applyTo(applicationContext.getEnvironment());

        applicationContext.registerBean(WireMockServer.class, () -> wireMockServer,
                beanDefinition -> beanDefinition.setDestroyMethodName("stop"));
    }
}
