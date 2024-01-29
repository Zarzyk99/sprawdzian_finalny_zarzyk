package pl.kurs.sprawdzianfinalnyzarzyk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails employee = User.withUsername("employee")
                .password(passwordEncoder.encode("employee"))
                .roles("EMPLOYEE")
                .build();

        UserDetails importer = User.withUsername("importer")
                .password(passwordEncoder.encode("importer"))
                .roles("IMPORTER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin, employee, importer);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(registry -> registry
                        .requestMatchers(GET, "/api/people").hasAnyRole("ADMIN", "IMPORTER", "EMPLOYEE")
                        .requestMatchers(POST, "/api/people").hasRole("ADMIN")
                        .requestMatchers(PUT, "/api/people/**").hasRole("ADMIN")
                        .requestMatchers(POST, "/api/people/upload").hasAnyRole("ADMIN", "IMPORTER")
                        .anyRequest().authenticated())
                .httpBasic();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }

}
