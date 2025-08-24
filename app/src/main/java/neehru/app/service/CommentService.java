package neehru.app.service;

import neehru.app.model.Comment;
import neehru.app.model.Post;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    public List<Comment> getAllComments();
    public Optional<Comment> getCommentById(Long id);
    public Comment saveComment(String comment);
    public void deleteComment(Long id);
    public List<Comment> getAllPostComments(Post post);
}
