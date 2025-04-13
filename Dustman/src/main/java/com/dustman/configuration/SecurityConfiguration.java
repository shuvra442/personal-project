package com.dustman.configuration;


import com.dustman.utils.JWTFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfiguration {
     private final JWTFilter jwtFilter;

     public SecurityConfiguration(JWTFilter jwtFilter) {
          this.jwtFilter = jwtFilter;
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
          return NoOpPasswordEncoder.getInstance();
     }

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          http.cors(cors-> cors.configurationSource(corsConfigurationSource()))
                  .csrf(AbstractHttpConfigurer::disable)
                  .authorizeHttpRequests(auth->auth
                          .requestMatchers("/admin/**").hasRole("ADMIN")
                          .requestMatchers("/shop/**").hasRole("SHOPKEEPER")
                          .requestMatchers("/secure/**","/shop/**","/admin/**").authenticated()
                          .requestMatchers("/api/**").permitAll()
                          .anyRequest().permitAll())
                  .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
          return http.build();
     }


     private CorsConfigurationSource corsConfigurationSource(){
          CorsConfiguration corsConfiguration=new CorsConfiguration();
          corsConfiguration.setAllowedOriginPatterns(List.of("http://localhost:*"));
          corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
          corsConfiguration.addAllowedHeader("*");
          corsConfiguration.setAllowCredentials(true);

          UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
          source.registerCorsConfiguration("/**",corsConfiguration);
          return source;

     }

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {  //what is the purpose of this
          return authenticationConfiguration.getAuthenticationManager();
     }

}
