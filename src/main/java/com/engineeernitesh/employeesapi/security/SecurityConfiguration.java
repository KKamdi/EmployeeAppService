package com.engineeernitesh.employeesapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private BasicAuthenticationPoint basicAuthenticationPoint;
    
    private static String REALM = "NITESH_KUMAR";
     
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("nitesh").password("nitesh").roles("ADMIN","ANONYMOUS");
        auth.inMemoryAuthentication().withUser("anonymous").password("anonymous").roles("ANONYMOUS");
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
        .authorizeRequests()
        .antMatchers("/employee/**").hasRole("ANONYMOUS")
        .and().httpBasic().realmName(REALM).authenticationEntryPoint(basicAuthenticationPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
    }

   /* To allow Pre-flight [OPTIONS] request from browser */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }


}
