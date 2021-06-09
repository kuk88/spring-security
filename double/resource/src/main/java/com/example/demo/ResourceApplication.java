package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@SpringBootApplication
@RestController
public class ResourceApplication {

  @RequestMapping("/")
  @CrossOrigin(origins = "http://localhost:8080")
  public Greeting home() {
    var greeting = new Greeting(UUID.randomUUID().toString(), "Hello World");
    return greeting;
  }

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
