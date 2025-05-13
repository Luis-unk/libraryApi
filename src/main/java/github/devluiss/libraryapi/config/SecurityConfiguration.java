package github.devluiss.libraryapi.config;

import github.devluiss.libraryapi.security.CustomUserDetailsService;
import github.devluiss.libraryapi.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .httpBasic(Customizer.withDefaults()) //http basic caso uso postman
                .authorizeHttpRequests( authorize -> {
                    authorize.requestMatchers("/login").permitAll();
                    authorize.requestMatchers(HttpMethod.POST,"/usuarios/**").permitAll();
                    authorize.requestMatchers(HttpMethod.DELETE, "/autores/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST,"/autores/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT,"/autores/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.GET,"/autores/**").hasAnyRole("USER", "ADMIN");
                    authorize.requestMatchers("/livros/**").hasAnyRole("USER", "ADMIN");
                    authorize.anyRequest().authenticated();
                })
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDatailsService(UsuarioService usuarioService){

//        UserDetails user1 = User.builder()
//                .username("usuario")
//                .password(encoder.encode("123"))
//                .roles("USER") //geralmente caixa alta
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("admin")
//                .password(encoder.encode("123"))
//                .roles("ADMIN") //geralmente caixa alta
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
        return new CustomUserDetailsService(usuarioService);
    }

}