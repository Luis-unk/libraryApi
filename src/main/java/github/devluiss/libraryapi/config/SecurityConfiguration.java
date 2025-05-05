package github.devluiss.libraryapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable) //Disabilitar | Utilizado quando for usar aplicação Web(proteção para requisições token csrf)
                //.formLogin(configurer -> configurer.loginPage("/login.html").successForwardUrl("/home.html"))
                //.formLogin(Customizer.withDefaults()) //formulario padrão
                .formLogin(configurer -> {
                    configurer.loginPage("/login").permitAll();
                })
                //.httpBasic(Customizer.withDefaults()) //http basic caso uso postman
                .authorizeHttpRequests( authorize -> {
                        authorize.anyRequest().authenticated();
                })
                .build();
    }
}