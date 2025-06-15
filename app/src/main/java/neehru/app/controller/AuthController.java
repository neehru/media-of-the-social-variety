package neehru.app.controller;

import jakarta.validation.Valid;
import neehru.app.model.User;
import neehru.app.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class AuthController {
    private final UserServiceImpl userService;

    public AuthController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {return "login";}

    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String performRegister(@Valid @ModelAttribute User user,
                                  BindingResult result, Model model) {
        if (result.hasErrors()){
            return "signup";
        }
        try {
            userService.signup(user);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "signup";
        }

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        model.addAttribute("successMessage",
                "Welcome to meso! Log in to access your account.");

        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null){
            return "login";
        }
        Optional<User> user = userService.getUserByUsername(authentication.getName());
        if (user.isPresent()){
            model.addAttribute("user", user.get());
        }
        return "dashboard";
    }

}
