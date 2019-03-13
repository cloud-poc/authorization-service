package org.akj.springboot.authorization.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("authorizationRequest")
public class OAuth2ApprovalController {

	@RequestMapping("/oauth/confirm_access")
	public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
		ModelAndView modelAndView = new ModelAndView("oauth_approval");
		AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");

		modelAndView.addObject("authorizationRequest", authorizationRequest);
		modelAndView.addObject("client_id", authorizationRequest.getClientId());
		modelAndView.addObject("scopes", authorizationRequest.getScope());

		return modelAndView;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

}