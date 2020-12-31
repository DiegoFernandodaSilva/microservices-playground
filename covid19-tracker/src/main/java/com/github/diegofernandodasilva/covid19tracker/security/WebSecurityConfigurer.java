package com.github.diegofernandodasilva.covid19tracker.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

/**
 * Enabled security resource server
 */
@ConditionalOnProperty(name = OAuthConfigProps.MICROSERVICES_OAUTH_SECURITY_ENABLED_PROP_KEY,
        havingValue = "true",
        matchIfMissing = true)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@EnableWebSecurity
@Slf4j
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Autowired
    private KeycloakRoleConverter keycloakRoleConverter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(keycloakRoleConverter);

        log.warn("OAuth security enabled.");
        http
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }
}
