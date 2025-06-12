package neehru.app.controller;

import jakarta.validation.Valid;
import neehru.app.model.User;
import neehru.app.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
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
        model.addAttribute("successMessage",
                "User created successfully, please login");
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null){
            return "login";
        }
        model.addAttribute("username", authentication.getName());
        return "dashboard";
    }
}
