package com.libreria.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder passEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(passEncoder());
    }
    
    /*
    // agregar mas usuarios y personalizarlos
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // metodo para desactivar el usuario por defecto 
        // y agregar nuevos usuarios
        // AUTENTICACION - agregar nuevos usuarios
        auth.inMemoryAuthentication()
                .withUser("admin")
                    .password("{noop}123") // el pasword no es increptado
                    .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                    .password("{noop}123") // el pasword no es increptado
                    .roles("USER");
    }
    */
    
    
    // para restrengir los url
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // metodo para restrengir los url
        // AUTORIZACION - asegurar los urls   
        http.authorizeRequests()
                .antMatchers("/agregar/**", "/editar/**", "/eliminar/**")
                    .hasRole("ADMIN")
                    .antMatchers("/")
                    .hasAnyRole("USER", "ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errors/403");
    }
}
