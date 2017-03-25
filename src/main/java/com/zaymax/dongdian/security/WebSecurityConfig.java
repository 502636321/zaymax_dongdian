package com.zaymax.dongdian.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by huiquan on 2017/3/22.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .frameOptions()
                .sameOrigin()
                .and()
                .authorizeRequests()
                .antMatchers("/bootstrap-3.3.7/**",
                        "/bootstrap-datetimepicker-master/**",
                        "/bootstrap-treeview-1.2.0/**",
                        "/css/**",
                        "/js/**",
                        "/jquery-validation-1.15.1/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/admin/engineer");
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
