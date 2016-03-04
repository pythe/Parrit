package com.parrit.configurations;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Component
public class WorkspaceAuthorizationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Principal userPrincipal = httpServletRequest.getUserPrincipal();

        if(userPrincipal != null) {
            String url = httpServletRequest.getRequestURL().toString();
            String currentLoggedInWorkspaceName = userPrincipal.getName();

            System.out.println("Interceptor going to url= " + url);
            System.out.println("Current logged in workspace= " + currentLoggedInWorkspaceName);

            if(!url.contains(currentLoggedInWorkspaceName)) {
                System.out.println("PLEASE RE-AUTHENTICATE!!!!");
                httpServletRequest.getSession(false).invalidate();
                SecurityContextHolder.getContext().setAuthentication(null);
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
}
