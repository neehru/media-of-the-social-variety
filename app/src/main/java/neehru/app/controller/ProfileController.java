package neehru.app.controller;

import neehru.app.model.Post;
import neehru.app.model.User;
import neehru.app.service.PostServiceImpl;
import neehru.app.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/me/")
public class ProfileController {
    private final UserServiceImpl userService;
    private final PostServiceImpl postService;

    public ProfileController(UserServiceImpl userService, PostServiceImpl postService) {
        this.userService = userService;
        this.postService = postService;
    }


    @GetMapping("/{username}")
    public String profile(@PathVariable String username, Model model){
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());

            List<Post> posts = postService.getAllUsersPosts(user.get());
            Collections.reverse(posts);
            model.addAttribute("posts", posts);

            System.out.println("*** SIZE OF POSTS IS " + postService.getAllUsersPosts(user.get()).size());

            return "profile";
        }
        return "redirect:/dashboard";
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long id,
            @RequestBody User updatedUser){
        try {
            User savedUser = userService.updateUser(
                    id, updatedUser);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
