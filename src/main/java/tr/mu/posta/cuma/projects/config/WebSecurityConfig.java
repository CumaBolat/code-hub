package tr.mu.posta.cuma.projects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(csrf -> csrf.disable())
          .cors(cors -> cors.disable()) 
          .authorizeHttpRequests((authorize) -> authorize
            .requestMatchers("/**").permitAll()
            .anyRequest().authenticated())
          .formLogin(formLogin -> formLogin
            .loginPage("/login")
            .permitAll())
          .logout(logout -> logout
            .permitAll());

      return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
