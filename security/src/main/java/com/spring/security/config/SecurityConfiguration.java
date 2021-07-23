package com.spring.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    /**
     * override lại config của Spring security
     * configure(Authentication Manager Builder) Authentication
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set your configuration on the UserDetailsService

        auth.userDetailsService(userDetailsService);
    }

    /**
     * override lại config của Spring security
     * configure(HttpSecurity) Authorization
     * Spring security không thể phân biệt role admin > role user
     * permitALl để xuống cuối để tránh tất cả đều bị match vào dù chưa có quyền
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/user").hasAnyRole("USER","ADMIN")
               .antMatchers("/").permitAll()
               .and().formLogin();
    }

    /**
     * để security hoạt động thì bắt buộc phải có PasswordEncoder
     * ở dưới không có encode tuy nhiên không nên dùng trong môi trường product
     * @return
     */
    @Bean
    public PasswordEncoder getPassswordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
