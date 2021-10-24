package com.libreria.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        // el lenguaje que manejamos por default
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        // interceptor para cambiar la idioma de manera dinamica
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // metodo para pads que utilizamos sin necesidad de pasar por el controlador
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/login");
        registry.addViewController("/errors/403").setViewName("/errors/403");
    }
}
