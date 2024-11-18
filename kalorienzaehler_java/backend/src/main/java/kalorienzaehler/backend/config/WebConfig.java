package kalorienzaehler.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Konfiguration der Webanwendung.
 * 
 * Diese Klasse ermöglicht die Anpassung von CORS (Cross-Origin Resource Sharing),
 * um den Zugriff auf die API von anderen Ursprüngen (Domains) aus zu steuern.
 */
@Configuration
public class WebConfig {

    /**
     * Definiert die CORS-Einstellungen für die API.
     * 
     * Diese Methode erlaubt es, HTTP-Anfragen von einem bestimmten Ursprung (z. B. der Benutzeroberfläche)
     * an die API zu senden. Das ist besonders wichtig, wenn Frontend und Backend auf unterschiedlichen Servern laufen.
     * 
     * Beispiel:
     * - Frontend läuft auf `http://localhost:5173`
     * - Backend läuft auf `http://localhost:8080`
     * 
     * Ohne CORS-Konfiguration würde der Browser die Kommunikation blockieren.
     * 
     * @return Ein `WebMvcConfigurer`, das die CORS-Einstellungen anpasst.
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Erlaubt CORS für alle Endpunkte unter "/api/**".
                        .allowedOrigins("http://localhost:5173") // Erlaubt Anfragen nur von diesem Ursprung.
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Erlaubt diese HTTP-Methoden.
                        .allowedHeaders("*") // Erlaubt alle HTTP-Header in den Anfragen.
                        .allowCredentials(true); // Erlaubt die Übermittlung von Anmeldeinformationen (z. B. Cookies).
            }
        };
    }
}
