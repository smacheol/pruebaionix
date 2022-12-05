package cl.ionix.prueba.infrastructurecross.config;

import cl.ionix.prueba.infrastructurecross.application.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.web.servlet.HttpSecurityDsl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Autowired
    private Setting setting;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.builder()
                .username(setting.getNombreUsuarioAdmin())
                .password(encoder().encode(setting.getClaveAdmin()))
                .roles("ADMINISTRADOR")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .cors().and()
                .authorizeHttpRequests(authz -> authz
                        .antMatchers(HttpMethod.POST, "/busqueda/buscar/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/usuario/listar/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/usuario/obtener/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/usuario/guardar/**").hasRole("ADMINISTRADOR")
                        .antMatchers(HttpMethod.DELETE, "/usuario/eliminar/**").hasRole("ADMINISTRADOR")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
