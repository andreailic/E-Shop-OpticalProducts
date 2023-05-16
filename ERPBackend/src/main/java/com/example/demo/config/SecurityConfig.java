package com.example.demo.config;

import com.example.demo.model.ERole;
import com.example.demo.security.AuthenticationTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    public SecurityConfig(AuthenticationProvider authenticationProvider, AuthenticationTokenFilter authenticationTokenFilter) {
        this.authenticationProvider = authenticationProvider;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/auth/**",
                        "webhook/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html"
                )
                .permitAll()

                .requestMatchers(HttpMethod.GET, "/address/address/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/address/address").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/address/address/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/address/address/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/user").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.POST, "/user").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/user").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/user").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/brand/brand/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/brand/brand").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/brand/brand/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/brand/brand/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/categories/category/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/categories/category").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/categories/category/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/categories/category/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/product/product/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/product/product").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/product/product/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/product/product/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/review/review/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/review/review").hasRole(ERole.ROLE_CUSTOMER.name())
                .requestMatchers(HttpMethod.PUT, "/review/review/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/review/review/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/customer/customer/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.POST, "/customer/customer").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/customer/customer/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/customer/customer/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/staff/staff/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.POST, "/staff/staff").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/staff/staff/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/staff/staff/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/orders/orders/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.POST, "/orders/orders").hasRole(ERole.ROLE_CUSTOMER.name())
                .requestMatchers(HttpMethod.PUT, "/orders/orders/**").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/orders/orders/**").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/payment/payment/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/payment/payment").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/payment/payment/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/payment/payment/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .logout().disable()
        ;

        return http.build();
    }


}
