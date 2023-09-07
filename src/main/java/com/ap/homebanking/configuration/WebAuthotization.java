package com.ap.homebanking.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthotization {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                      //  .antMatchers("/**").permitAll();
               .antMatchers("/h2-console/*").permitAll()
                .antMatchers(HttpMethod.POST, "/api/login","/api/logout","/api/clients").permitAll()
                .antMatchers(HttpMethod.POST, "/api/loans").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/web/index.html","/web/index.js","/web/styles/**").permitAll()
                .antMatchers("/api/clients/current", "/api/accounts/**", "/api/loans").hasAnyAuthority("ADMIN","CLIENT")
                .antMatchers("/api/clients/current/accounts","/api/clients/current/cards","/api/clients/current/cards","/web/accounts.html", "/web/account.html","/web/cards.html").hasAuthority("CLIENT")
                .antMatchers("/clients/{id}").hasAnyAuthority("CLIENT", "ADMIN")
                .antMatchers("/rest/**","/api/clients").hasAuthority("ADMIN")
                        .anyRequest().hasAuthority("ADMIN");



        http.formLogin()
                .usernameParameter("email")
                .passwordParameter("password")
                .loginPage("/api/login");

        http.logout().logoutUrl("/api/logout").deleteCookies("");


        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        http.formLogin().successHandler((req, res, auth) -> this.clearAuthenticationAttributes(req));
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
