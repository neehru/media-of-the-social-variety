package neehru.app.controller;

import neehru.app.model.Post;
import neehru.app.model.User;
import neehru.app.repository.PostRepository;
import neehru.app.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/post")
public class PostController {
    private final UserServiceImpl userService;
    private final PostRepository postRepository;

    public PostController(UserServiceImpl userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    @GetMapping("/new")
    public String newPostView(Model model) {
        model.addAttribute("post", new Post());
        return "new_post";
    }

    @PostMapping("/new")
    public String createNewPost(@RequestParam("image") MultipartFile image,
                                @RequestParam("description") String description,
                                Model model) throws IOException {

        if (image.isEmpty()) {
            model.addAttribute("errorMessage", "Please select an image to post.");
            return "new_post";

        }

        try {

            // generating uuid to save as filename
            String fileExtension = image.getOriginalFilename().substring((image.getOriginalFilename()).lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + fileExtension;

            Path path = Paths.get("src/main/resources/static/uploads/posts");
            Files.copy(image.getInputStream(), path.resolve(fileName));

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {

                Optional<User> user = userService.getUserByUsername(authentication.getName());
                if (user.isPresent()) {

                    Post newPost = new Post();
                    newPost.setUser(user.get());
                    newPost.setDescription(description);
                    newPost.setImage(uuid);
                    newPost.setDatePosted(LocalDate.now());

                    postRepository.save(newPost);

                    return "redirect:/dashboard";
                } else {
                    model.addAttribute("errorMessage", "User not found.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error posting photo.");
        }

        return "new_post";
    }

    @GetMapping("/{uuid}")
    public String viewPost(@PathVariable String uuid, Model model) {

        Optional<Post> post = postRepository.findByImage(uuid);

        if (post.isPresent()) {

            model.addAttribute("imagePath", uuid);
                return "view_post";
        }
        return "redirect:/dashboard";
    }

}
