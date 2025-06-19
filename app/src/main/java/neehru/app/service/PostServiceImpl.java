package neehru.app.service;

import neehru.app.model.Post;
import neehru.app.model.User;
import neehru.app.repository.PostRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id){
        return postRepository.findById(id);
    }

    public Optional<Post> getPostByImage(String uuid){
        return postRepository.findByImage(uuid);
    }

    public Post savePost(Post post) {
        return postRepository.save(post);
    }
//    public Post updatePost(Long id, Post updatedPost){

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    public List<Post> getAllUsersPosts(User user){

        List<Post> allPosts = getAllPosts();
        List<Post> userPosts = new ArrayList<>();

        for (Post post : allPosts) {
            if (post.getUser().equals(user)) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

}
