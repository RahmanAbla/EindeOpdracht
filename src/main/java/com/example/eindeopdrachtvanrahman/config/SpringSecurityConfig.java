package com.example.eindeopdrachtvanrahman.config;

import com.example.eindeopdrachtvanrahman.Services.CustomUserDetailsService;
import com.example.eindeopdrachtvanrahman.filter.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.AuthorizeHttpRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    // PasswordEncoderBean. Deze kun je overal in je applicatie injecteren waar nodig.
    // Je kunt dit ook in een aparte configuratie klasse zetten.
   @Bean
   public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
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
                                        .requestMatchers( "/cars").hasRole("USER")
                                        .requestMatchers( "/cars/**").hasRole("USER")
                                        .requestMatchers("carmechanics").hasRole("USER")
                                        .requestMatchers("carmechanics/**").hasRole("USER")
                                        .requestMatchers("clients").hasRole("USER")
                                        .requestMatchers("clients/**").hasRole("USER")
                                        .requestMatchers("garagereceptionists").hasRole("USER")
                                        .requestMatchers("garagereceptionists/**").hasRole("USER")
                                        .requestMatchers("inspections").hasRole("USER")
                                        .requestMatchers("inspections/**").hasRole("USER")
                                        .requestMatchers("repairs").hasRole("USER")
                                        .requestMatchers("repairs/**").hasRole("USER")
                                        .requestMatchers("invoices").hasRole("USER")
                                        .requestMatchers("invoices/**").hasRole("USER")
                                        .requestMatchers("users").hasRole("USER")
                                        .requestMatchers("users/**").hasRole("USER")
                                        .requestMatchers(HttpMethod.POST, "/users").hasRole("USER")
                                        .requestMatchers(HttpMethod.GET,"/users").hasRole("USER")
                                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("USER")
                                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("USER")
                                        .requestMatchers(HttpMethod.POST,"/users/**").hasRole("USER")
                                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("USER")
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


