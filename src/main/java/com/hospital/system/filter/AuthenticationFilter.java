package com.hospital.system.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@WebFilter("/*") // 拦截所有请求
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        if (uri.equals("/login") || uri.startsWith("/css/") || uri.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = httpRequest.getSession(false); // false表示不创建新session
        if (session != null && session.getAttribute("loggedInUser") != null) {
            // 有通行证，放行
            chain.doFilter(request, response);
        } else {
            // 没有通行证，赶回登录页面
            httpResponse.sendRedirect("/login");
        }
    }
}