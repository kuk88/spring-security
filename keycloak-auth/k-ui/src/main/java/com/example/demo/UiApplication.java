package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@SpringBootApplication
@RestController
public class UiApplication {

  @RequestMapping("/user")
  public Principal user(Principal user){
	  return user;
  }

  @RequestMapping("/token")
  public Map<String, String> token(HttpSession session){
      return Collections.singletonMap("token", session.getId());
  }

  public static void main(String[] args) {
		SpringApplication.run(UiApplication.class, args);
	}

  @Configuration
  protected static class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .httpBasic().and()
        .authorizeRequests()
        .antMatchers("/index.html", "/", "/home", "/login", "/*.js", "/*.css", "/favicon.ico", "/token").permitAll()
        .anyRequest().authenticated()
        .and().logout()
        .logoutUrl("/logout")
        .and()
        .csrf()
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
  }

}
