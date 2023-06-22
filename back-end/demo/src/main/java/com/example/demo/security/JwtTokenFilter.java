package com.example.demo.security;

import com.example.demo.repositories.UserRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static org.aspectj.util.LangUtil.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JWTUtility jwtTokenUtil;
    private final UserRepository userRepo;
    private final DomainUserDetailsService userDetailsService;

    public JwtTokenFilter(JWTUtility jwtTokenUtil,
                          UserRepository userRepo,
                          DomainUserDetailsService userDetailsService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepo = userRepo;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

//        response.addHeader("Access-Control-Allow-Headers",
//                "Access-Control-Allow-Origin, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
//        if (response.getHeader("Access-Control-Allow-Origin") == null)
//            response.addHeader("Access-Control-Allow-Origin", "*");

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isEmpty(header) || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }


        // Get jwt token and validate
        final String token = header.split(" ")[1].trim();

        UserDetails userDetails = userDetailsService
                .loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));

        if (!jwtTokenUtil.validateToken(token, userDetails)) {
            chain.doFilter(request, response);
            return;
        }

        // Get user identity and set it on the spring security context

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                        List.of() : userDetails.getAuthorities()
        );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

}