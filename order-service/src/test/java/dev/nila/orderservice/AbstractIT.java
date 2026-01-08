package dev.nila.orderservice;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.wiremock.integrations.testcontainers.WireMockContainer;

import java.math.BigDecimal;

// @WebMvcTest // web layer component testing
// @DataJpaTest // repository component testing
// @DataJdbcTest // repository component testing
// for integration test, it loads all the components
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
public abstract class AbstractIT {
    // catch the dynamic port that application start random port
    @LocalServerPort
    int port;

    static WireMockContainer wiremockServer = new WireMockContainer("wiremock/wiremock:3.5.2-alpine");

    @BeforeAll
    static void beforeAll() {
        wiremockServer.start();
        //setting default wiremock sever and port name
        configureFor(wiremockServer.getHost(), wiremockServer.getPort());
    }
    //we override the properties microservices url with wiremock url
    //When we call order service to catalog service we use wiremock for mock instead of actual result
    //for testing it will talk to mock server url not actual service url
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry){
        registry.add("orders.catalog-service-url", wiremockServer::getBaseUrl);
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }
    //set up the stub to make call to this wiremock server like path url, expectation response
    protected static void mockGetProductByCode(String code, String name, BigDecimal price) {
        stubFor(WireMock.get(urlMatching("/api/products/" + code))
                .willReturn(aResponse()
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(200)
                        .withBody(
                                """
                    {
                        "code": "%s",
                        "name": "%s",
                        "price": %f
                    }
                """
                                        .formatted(code, name, price.doubleValue()))));
    }
}
