package ubb.postuniv.riddingaddict.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.any())
                .build()
                .apiInfo(apiDetails());
    }

    private ApiInfo apiDetails() {

        return new ApiInfo(
                "RIDDING-ADDICT API",
                "An API for a bike and accessories shop",
                "1.0",
                "Free to use",
                new springfox.documentation.service.Contact("Ioana Tagurean", "http://localhost:8080", "ioana_1001@yahoo.com"),
                "APi License",
                "http://localhost:8080",
                Collections.emptyList());
    }
}
