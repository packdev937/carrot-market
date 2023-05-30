package com.market.carrot.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;


@Slf4j
public class AuthInterceptor extends WebRequestHandlerInterceptorAdapter {

    /**
     * Create a new WebRequestHandlerInterceptorAdapter for the given WebRequestInterceptor.
     *
     * @param requestInterceptor the WebRequestInterceptor to wrap
     */
    public AuthInterceptor(WebRequestInterceptor requestInterceptor) {
        super(requestInterceptor);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null) {
            log.info("current user is not logined");

            response.sendRedirect("/user/home");
            return false;
        }
        return true;
    }
}
