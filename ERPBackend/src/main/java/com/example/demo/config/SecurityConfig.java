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
                .requestMatchers(HttpMethod.POST, "/address/address").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/address/address/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/address/address/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/user").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.POST, "/user").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/user").hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/user").hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/brand/brand/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/brand/brand").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/brand/brand/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/brand/brand/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/categories/category/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/categories/category").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/categories/category/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/categories/category/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/product/product/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/product/product").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/product/product/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/product/product/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/review/review/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/review/review").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/review/review/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/review/review/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/customer/customer/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/customer/customer").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/customer/customer/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/customer/customer/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/staff/staff/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/staff/staff").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/staff/staff/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/staff/staff/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/orders/orders/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/orders/orders").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/orders/orders/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/orders/orders/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())

                .requestMatchers(HttpMethod.GET, "/payment/payment/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/payment/payment").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.PUT, "/payment/payment/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())
                .requestMatchers(HttpMethod.DELETE, "/payment/payment/**").permitAll() //hasRole(ERole.ROLE_STAFF.name())


//                 .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//                 .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//                 .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())
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
