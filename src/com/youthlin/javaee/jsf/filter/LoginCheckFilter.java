package com.youthlin.javaee.jsf.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String uri = ((HttpServletRequest) request).getRequestURI();
        String loginPath = ((HttpServletRequest) request).getContextPath()
                + "/login.xhtml";
//		System.out.println("url=" + uri + "\nlogin path=" + loginPath);
        if (uri.equals(loginPath) || uri.endsWith(".js")
                || uri.endsWith(".ico") || uri.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }
        if (session.getAttribute("login") == null) {
            ((HttpServletResponse) response).sendRedirect(loginPath);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
