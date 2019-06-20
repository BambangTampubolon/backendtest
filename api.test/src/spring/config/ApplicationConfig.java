package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@Import({ TestConfig.class, InfrastructureConfig.class, AspectsConfig.class, SwaggerConfig.class, WebConfiguration.class })
@EnableWebMvc
@ComponentScan(basePackages = "spring")
@EnableTransactionManagement
public class ApplicationConfig {

}
