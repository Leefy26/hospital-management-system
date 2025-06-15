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

        // 获取请求的URL
        String uri = httpRequest.getRequestURI();

        // 如果是访问登录页面或静态资源，直接放行
        if (uri.equals("/login") || uri.startsWith("/css/") || uri.startsWith("/js/")) {
            chain.doFilter(request, response);
            return;
        }

        // 检查Session中是否有“通行证”
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