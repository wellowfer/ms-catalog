package br.com.fiap.catalog.main;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Service
public class CustomBasicAuthFilter extends OncePerRequestFilter {

    private static final int BASIC_LENGTH = 6;
    public static final String EXCEPTION_INVALID_CREDENTIALS = "Invalid credentials";
    public static final String MESSAGE_INVALID_CREDENTIALS1 = "Unauthorized: Authentication credentials were not provided or are incorrect.";

    @Value("${api.authentication.user}")
    private String user;

    @Value("${api.authentication.password}")
    private String password;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var headerAuthorization = request.getHeader("Authorization");

        if (headerAuthorization == null || !headerAuthorization.startsWith("Basic ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            var basicToken = headerAuthorization.substring(BASIC_LENGTH);
            byte[] basicTokenDecoded = Base64.getDecoder().decode(basicToken);
            String basicTokenValue = new String(basicTokenDecoded);
            String[] credentials = basicTokenValue.split(":");

            if (credentials.length == 2 && credentials[0].equals(user) && credentials[1].equals(password)) {
                var authToken = new UsernamePasswordAuthenticationToken(credentials[0], null, null);
                SecurityContextHolder.getContext().setAuthentication(authToken);
                filterChain.doFilter(request, response);
            } else {
                throw new SecurityException(EXCEPTION_INVALID_CREDENTIALS);
            }
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(MESSAGE_INVALID_CREDENTIALS1);
        }
    }
}
