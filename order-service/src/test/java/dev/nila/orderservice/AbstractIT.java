package dev.nila.orderservice;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static java.util.Collections.singletonList;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

// @WebMvcTest // web layer component testing
// @DataJpaTest // repository component testing
// @DataJdbcTest // repository component testing
// for integration test, it loads all the components
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
@AutoConfigureMockMvc
public abstract class AbstractIT {
    static final String CLIENT_ID = "bookstore-webapp";
    static final String CLIENT_SECRET = "rOxlBkhj2vXD2QXc1zPNf8j3T1gmUqTa";
    static final String USERNAME = "nila";
    static final String PASSWORD = "nila";

    @Autowired
    OAuth2ResourceServerProperties oAuth2ResourceServerProperties;

    @Autowired
    protected MockMvc mockMvc;

    // catch the dynamic port that application start random port
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    //    static WireMockContainer wiremockServer = new WireMockContainer("wiremock/wiremock:3.5.2-alpine");
    //
    //    @BeforeAll
    //    static void beforeAll() {
    //        wiremockServer.start();
    //        // setting default wiremock sever and port name
    //        configureFor(wiremockServer.getHost(), wiremockServer.getPort());
    //    }
    //    // we override the properties microservices url with wiremock url
    //    // When we call order service to catalog service we use wiremock for mock instead of actual result
    //    // for testing it will talk to mock server url not actual service url
    //    @DynamicPropertySource
    //    static void configureProperties(DynamicPropertyRegistry registry) {
    //        registry.add("orders.catalog-service-url", wiremockServer::getBaseUrl);
    //    }

    // set up the stub to make call to this wiremock server like path url, expectation response
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

    protected String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.put(OAuth2Constants.GRANT_TYPE, singletonList(OAuth2Constants.PASSWORD));
        map.put(OAuth2Constants.CLIENT_ID, singletonList(CLIENT_ID));
        map.put(OAuth2Constants.CLIENT_SECRET, singletonList(CLIENT_SECRET));
        map.put(OAuth2Constants.USERNAME, singletonList(USERNAME));
        map.put(OAuth2Constants.PASSWORD, singletonList(PASSWORD));

        String authServerUrl =
                oAuth2ResourceServerProperties.getJwt().getIssuerUri() + "/protocol/openid-connect/token";

        var request = new HttpEntity<>(map, httpHeaders);
        KeyCloakToken token = restTemplate.postForObject(authServerUrl, request, KeyCloakToken.class);

        assert token != null;
        return token.accessToken();
    }

    record KeyCloakToken(@JsonProperty("access_token") String accessToken) {}
}
