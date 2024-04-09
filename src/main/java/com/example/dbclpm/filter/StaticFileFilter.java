package com.example.dbclpm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/")
public class StaticFileFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        if (
    		req.getRequestURI().endsWith(".js") ||
    		req.getRequestURI().endsWith(".css") ||
    		req.getRequestURI().startsWith("/api")
		) {
        	chain.doFilter(req, resp);
        	return;
        }
        resp.sendRedirect("/login");
	}
}
