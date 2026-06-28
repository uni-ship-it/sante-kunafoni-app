package groupe3.example.santekunafoniapp.configSwaager;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration          // Dit à Spring : "cette classe contient des configurations"
public class SwaggerConfig {

    @Bean               // Dit à Spring : "crée cet objet et gère-le toi-même"
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SanteKunafoni API")          // Titre affiché en haut de Swagger UI
                        .version("1.0.0")                     // Version de ton API
                        .description("Documentation des APIs de l'application SanteKunafoni")
                        .contact(new Contact()
                                .name("Groupe 3")
                                .email("groupe3@example.com")
                        )
                );
    }
}