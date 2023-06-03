package zw.co.afrocodemy.afrocodemyarticles;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(appInfo());
    }
    private Info appInfo(){
        return new Info()
                .title("Afrocodemy Articles Service")
                .description("Service for blogging functionality on the afrocodemy website. It relies " +
                        "on the media server for storage of media files")
                .version("0.0.1-SNAPSHOT")
                .contact(apiContact());
    }
    private Contact apiContact(){
        return new Contact()
                .name("Afrosoft Dev Team")
                .email("developers@afrosoft.co.zw")
                .url("https://www.afrosoft.co.zw/#/");
    }
}
