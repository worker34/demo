package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Igor on 4/1/2017.
 */
@Configuration
@EnableWebSecurity
public class ConfigurationSequrity extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll();
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/singup").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureJsonBasedUsers(AuthenticationManagerBuilder auth,
                                        SprindDataUserDetailsService userDetailsService) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}
