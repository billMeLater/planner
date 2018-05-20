package io.github.externschool.planner.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().fullyAuthenticated()
                .antMatchers("/").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/", true)
                .loginPage("/login").permitAll().and().logout().permitAll()
                .and().httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception{
        authBuilder.inMemoryAuthentication()
                .withUser("user@").password("{noop}user").roles("USER")
                .and()
                .withUser("admin@").password("{noop}admin").roles("USER", "ADMIN");
    }
}
