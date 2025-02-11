package clinicalstudyconnections.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    	http
        .csrf().disable()
        .authorizeHttpRequests(requests -> requests
                //.requestMatchers(HttpMethod.GET, "/", "/static/**", "/index.html", "/api/users/me").permitAll()
                //.requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                //.requestMatchers(HttpMethod.GET, "/api/users/login", "/api/users/{username}", "/api/users/logout", "/api/customers", "/api/storages").authenticated()
                //.requestMatchers(HttpMethod.POST, "/api/customers", "/api/storages").authenticated()
                //.requestMatchers(HttpMethod.PUT, "/api/customers/{id}", "/api/storages/{id}").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/owners").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/owners").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/studies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/studies").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/patients").hasRole("ADMIN")
                .anyRequest().permitAll())
        .httpBasic();
    
    	return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
