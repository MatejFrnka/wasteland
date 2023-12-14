package com.greenfox.tribes.gameuser.controllers;

import com.greenfox.tribes.misc.exceptions.UserAlreadyExistsException;
import com.greenfox.tribes.gameuser.services.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@AllArgsConstructor
public class AuthController {

  CustomUserDetailService userDetailsService;

  @GetMapping("/login")
  public String login() {
    return "user-settings/login";
  }

  @GetMapping("/register")
  public String register() {
    return "user-settings/register";
  }

  @PostMapping("/register")
  public RedirectView registerPost(
      @RequestParam String username, @RequestParam String password, RedirectAttributes ra) {
    try {
      userDetailsService.createUser(username, password);
    } catch (UserAlreadyExistsException e) {
      ra.addFlashAttribute("alreadyExists", true);
      return new RedirectView("user-settings/register");
    }
    return new RedirectView("user-settings/login");
  }

  // todo remove the following, it is only an example
  @GetMapping("/secure")
  public String secure(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    model.addAttribute("username", auth.getName());

    return "user-settings/secure";
  }

  @GetMapping("/")
  public String main() {
    return "user-settings/index";
  }
}