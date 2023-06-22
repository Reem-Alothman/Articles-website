package com.example.demo.configs;

import com.example.demo.security.DomainUserDetailsService;
import com.example.demo.security.JWTFilter;
import com.example.demo.security.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.example.demo.services.UserService;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTFilter jwtFilter;
    @Autowired
    private  JwtTokenFilter jwtTokenFilter;
    @Autowired
    UserService userService;

    @Autowired
    DomainUserDetailsService domainUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(domainUserDetailsService);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().and()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/article").permitAll()
                .antMatchers(HttpMethod.GET, "/article/{id}").permitAll()
                .antMatchers(HttpMethod.GET, "/logout").permitAll()

                .antMatchers(HttpMethod.POST, "/article").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/article/{id}/comment").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/article/{id}/like").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/article/{id}/dislike").hasAnyAuthority("USER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/article/{id}/disable").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/article/{id}/enable").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/article/{id}").hasAnyAuthority("USER", "ADMIN")

                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);



    }




}
