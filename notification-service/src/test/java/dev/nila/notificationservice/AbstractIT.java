package dev.nila.notificationservice;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

// @WebMvcTest // web layer component testing
// @DataJpaTest // repository component testing
// @DataJdbcTest // repository component testing
// for integration test, it loads all the components
@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(ContainersConfig.class)
public abstract class AbstractIT {}
