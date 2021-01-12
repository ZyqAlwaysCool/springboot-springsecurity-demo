package com.zyq.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //链式编程，设置授权规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level/1").hasRole("guest")
                .antMatchers("/level/2").hasRole("user")
                .antMatchers("/level/3").hasRole("root");
        http.formLogin().failureForwardUrl("/");
    }

    //认证
    //密码编码: PasswordEncoder
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("guest").password(new BCryptPasswordEncoder().encode("guest")).roles("guest")
                .and()
                .withUser("user").password(new BCryptPasswordEncoder().encode("user")).roles("user")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("root")).roles("root", "user", "guest");
    }
}
