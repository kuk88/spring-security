package com.example.demo;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class IndexController {
  private final Logger logger = LoggerFactory.getLogger(IndexController.class);

  @Autowired
  private HttpServletRequest request;

  @GetMapping(value = "/")
  public String index(Model model) throws IOException {
    configCommonAttributes(model);
    System.out.println("forwarding to index or home" );
    KeycloakSecurityContext keycloakSecurityContext = getKeycloakSecurityContext();



    if(keycloakSecurityContext != null && keycloakSecurityContext.getAuthorizationContext() != null){
      System.out.println(keycloakSecurityContext.getTokenString());
      System.out.println(keycloakSecurityContext.getIdTokenString());

      return "home.html";
    }else{
      return "index.html" ;
    }


  }

  @GetMapping(value = "/home")
  public String home(Model model) throws IOException {
    configCommonAttributes(model);
    System.out.println("forwarding to index" );
    return "home.html" ;
  }

  @RequestMapping(value = "/sso/logout", method = RequestMethod.POST)
  public String logout() throws ServletException {
    request.logout();
    return "redirect:/";
  }

  @RequestMapping(value = "/access-denied", method = RequestMethod.GET)
  public String accessDenied(Model model) throws ServletException {
    configCommonAttributes(model);
    return "access-denied.html";
  }


  private void configCommonAttributes(Model model) {
    KeycloakSecurityContext keycloakSecurityContext = getKeycloakSecurityContext();
    if (keycloakSecurityContext != null){
      logger.info(keycloakSecurityContext.getIdToken().getEmail());
      logger.info(keycloakSecurityContext.getIdToken().getGivenName());

      logger.info(keycloakSecurityContext.getToken().getScope());
      logger.info(keycloakSecurityContext.getToken().getMiddleName());
      logger.info(keycloakSecurityContext.getToken().getProfile());
    }
    model.addAttribute("identity", new Identity(keycloakSecurityContext));
  }

  private KeycloakSecurityContext getKeycloakSecurityContext() {
    return (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
  }


}
