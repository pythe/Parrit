package com.parrit.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    WorkspaceAuthorizationInterceptor workspaceAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(workspaceAuthorizationInterceptor)
            .addPathPatterns("/{workspaceName:.+}", "/api/**")
            .excludePathPatterns("/", "/api/workspace/new");
    }

}
