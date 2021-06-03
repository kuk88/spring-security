package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class IndexController {
  @GetMapping(value = "/{path:[^\\.]*}")
  public String redirect(@PathVariable String path) throws IOException {
    System.out.println("forwarding to " + path);
    return "forward:/" ;
  }
}
