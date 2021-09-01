package com.example.demo;

import org.keycloak.KeycloakSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ApiController {
    private final Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/sso/logout", method = RequestMethod.POST)
    public String logout() throws ServletException {
        request.logout();
        return "ok";
    }

    @RequestMapping(value = "/api/customer", method = RequestMethod.GET)
    @ResponseBody
    public Greeting customerResource(Principal user){
//        logger.info(user.getName());

        KeycloakSecurityContext keycloakSecurityContext = getKeycloakSecurityContext();
        String givenName = keycloakSecurityContext.getIdToken().getGivenName();
        return new Greeting(UUID.randomUUID().toString(), "Hi Customer " + givenName);

    }

    @RequestMapping(value = "/api/merchant", method = RequestMethod.GET)
    @ResponseBody
    public Greeting merchantResource(Principal user){
        logger.info(user.getName());
        KeycloakSecurityContext keycloakSecurityContext = getKeycloakSecurityContext();
        String givenName = keycloakSecurityContext.getIdToken().getGivenName();
        return new Greeting(UUID.randomUUID().toString(), "Hi Merchant " + givenName);
    }

    @RequestMapping(value = "/api/greeting", method = RequestMethod.GET)
    @ResponseBody
    public Greeting greeting(){
        KeycloakSecurityContext keycloakSecurityContext = getKeycloakSecurityContext();
        String givenName = keycloakSecurityContext.getIdToken().getGivenName();
        return new Greeting(UUID.randomUUID().toString(), "Hi " + givenName);
    }

    private KeycloakSecurityContext getKeycloakSecurityContext() {
        KeycloakSecurityContext attribute = (KeycloakSecurityContext) request.getAttribute(KeycloakSecurityContext.class.getName());
        if (attribute != null){
            logger.info(attribute.getToken().getGivenName());
            Optional.ofNullable(attribute.getToken().getAuthorization().getPermissions())
                    .orElse(Collections.emptyList())
                    .forEach(permission -> {
                        logger.info(permission.toString());
                    });
        }
        return attribute;
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
