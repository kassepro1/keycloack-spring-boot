package com.kp.web;


import org.keycloak.adapters.AdapterDeploymentContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.spi.HttpFacade;
import org.keycloak.adapters.springsecurity.facade.SimpleHttpFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SecurityController {

    @Autowired
    AdapterDeploymentContext adapterDeploymentContext;

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest) throws ServletException {
        httpServletRequest.logout();
        return "redirect:/";
    }


    @GetMapping("/changePassword")
    public String changePassword(RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse) throws ServletException {
        httpServletRequest.logout();
        HttpFacade httpFacade = new SimpleHttpFacade(httpServletRequest,httpServletResponse);
        KeycloakDeployment keycloakDeployment = adapterDeploymentContext.resolveDeployment(httpFacade);
        redirectAttributes.addAttribute("referrer",keycloakDeployment.getResourceName());
        return "redirect:"+keycloakDeployment.getAccountUrl()+"/password";
    }

}
