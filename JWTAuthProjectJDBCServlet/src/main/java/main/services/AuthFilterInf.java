package main.services;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

public interface AuthFilterInf extends Filter {

    void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException;
}