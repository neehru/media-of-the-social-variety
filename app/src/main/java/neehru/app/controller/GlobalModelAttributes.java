package neehru.app.controller;

import neehru.app.model.User;
import neehru.app.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
public class GlobalModelAttributes {
    private final UserServiceImpl userService;

    public GlobalModelAttributes(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ModelAttribute("currentUser")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication != null) {
            Optional<User> user = userService.getUserByUsername(authentication.getName());
            if (user.isPresent()) {
                return user.get();
            }
        }

        return null;
    }
}
