package hongik.eyearoundserver.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .version("v1.0.1")
                .title("Eye-Around")
                .description("Eye-Around API 명세서입니다")
                .summary("Hongik University Graduation Project"));
    }
}
