package com.example.eindeopdrachtvanrahman.config;

import com.example.eindeopdrachtvanrahman.sequence_diagram.Services.CustomUserDetailsService;
import com.example.eindeopdrachtvanrahman.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    public final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

   // authenticatie met customUserDetailsService en PasswordEncoder
    @Bean
    public AuthenticationManager authenticationManager( PasswordEncoder passwordEncoder) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

//Authorizatie met jwt
    @Bean
    protected SecurityFilterChain filter (HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                                auth
                                        // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
//                .requestMatchers("/**").permitAll()
                                        .requestMatchers( "/cars").hasRole("MECHANIC")
                                        .requestMatchers( "/cars/**").hasAnyRole("ADMIN","MECHANIC")
                                        .requestMatchers("carmechanics").hasRole("MECHANIC")
                                        .requestMatchers("/image").hasAnyRole("ADMIN","MECHANIC")
                                        .requestMatchers("/image/**").hasAnyRole("ADMIN","MECHANIC")
                                        .requestMatchers("carmechanics/**").hasRole("MECHANIC")
                                        .requestMatchers("clients").hasRole("MECHANIC")
                                        .requestMatchers("clients/**").hasRole("MECHANIC")
                                        .requestMatchers("clients/users/**").hasRole("MECHANIC")
                                        .requestMatchers("garagereceptionists").hasRole("MECHANIC")
                                        .requestMatchers("garagereceptionists/**").hasAnyRole("ADMIN","MECHANIC")
                                        .requestMatchers("inspections").hasRole("MECHANIC")
                                        .requestMatchers("inspections/**").hasRole("MECHANIC")
                                        .requestMatchers("repairs").hasRole("MECHANIC")
                                        .requestMatchers("repairs/**").hasRole("MECHANIC")
                                        .requestMatchers("invoices").hasRole("MECHANIC")
                                        .requestMatchers("invoices/**").hasAnyRole("ADMIN","MECHANIC")
                                        .requestMatchers("users").hasRole("MECHANIC")
                                        .requestMatchers("users/**").hasRole("MECHANIC")
                                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
                                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.POST, "/cimodules").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.DELETE, "/cimodules/**").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.POST, "/remotecontrollers").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.DELETE, "/remotecontrollers/**").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.POST, "/televisions").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.DELETE, "/televisions/**").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.POST, "/wallbrackets").hasRole("ADMIN")
//                                        .requestMatchers(HttpMethod.DELETE, "/wallbrackets/**").hasRole("ADMIN")
                                        // Je mag meerdere paths tegelijk definieren
//                                        .requestMatchers("/cimodules", "/remotecontrollers", "/televisions", "/wallbrackets").hasAnyRole("ADMIN", "USER")
                                        .requestMatchers("/authenticated").authenticated()
                                        .requestMatchers("/authenticate").permitAll()
                                        .anyRequest().denyAll()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }












}


