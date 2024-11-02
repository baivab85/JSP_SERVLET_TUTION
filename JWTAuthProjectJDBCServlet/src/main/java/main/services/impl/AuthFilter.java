package main.services.impl;

import java.io.IOException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.auth.config.AuthConfig;
import main.services.AuthFilterInf;

@WebFilter("/*") 
public class AuthFilter implements Filter,AuthFilterInf {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getServletPath();
        
        // Bypass authentication for the /login endpoint
        if ("/login".equals(path)||("/register".equals(path))) {
            chain.doFilter(req, res);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // Extract the token
            try {
                String role = AuthConfig.getRoleFromToken(token);
                 path = request.getServletPath();

                if ("admin".equals(role)) {
                    response.getWriter().write("Hello admin ---->>>>---- ");

                    // Allow access to /credit path for admins
                    if ("/credit".equals(path)) {
                        chain.doFilter(req, res);
                        return; // End method here to avoid further processing
                    }
                } else if (!"admin".equals(role)) {
                    response.getWriter().write("You are not an admin and do not have permission to access this resource.");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return; // Stop further processing
                }

                // Allow all other paths that don't need admin privileges
                if (!"/credit".equals(path)) {
                    chain.doFilter(req, res);
                } else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Access denied");
                }

            } catch (JwtException e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid token");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization header missing or invalid");
        }
    }

    @Override
    public void destroy() {}
}
