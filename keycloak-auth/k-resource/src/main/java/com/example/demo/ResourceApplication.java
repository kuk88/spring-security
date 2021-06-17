package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.example.demo")
@RestController
public class ResourceApplication extends WebSecurityConfigurerAdapter {

  @RequestMapping("/")
  @CrossOrigin(origins = "http://localhost:8080", allowedHeaders = {"x-auth-token", "x-requested-with", "x-xsrf-token"})
  public Greeting home() {
    var greeting = new Greeting(UUID.randomUUID().toString(), "Hello World");
    return greeting;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().authorizeRequests().anyRequest().authenticated();
  }



//  @Bean
//  public HeaderHttpSessionStrategy sessionStrategy(){
//    return new HeaderHttpSessionStrategy();
//  }


  public static void main(String[] args) {
    SpringApplication.run(ResourceApplication.class, args);
  }

  static class Greeting {
    private String id;
    private String content;

    public Greeting() {
    }

    public Greeting(String id, String content) {
      this.id = id;
      this.content = content;
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }
  }
}
