package neehru.app.service;

import neehru.app.model.Post;
import neehru.app.model.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    public List<Post> getAllPosts();
    public Optional<Post> getPostById(Long id);
//    public Post savePost(Post post);
//    public Post updatePost(Long id, Post updatedPost);
    public void deletePost(Long id);
}

