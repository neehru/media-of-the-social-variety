package neehru.app.service;

import neehru.app.model.Comment;
import neehru.app.model.Post;
import neehru.app.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }

    @Override
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }


    //    TODO: add?????
    public void deleteComment(Long id) {

    }

    public List<Comment> getAllPostComments(Post post){
        List<Comment> allComments = getAllComments();
        List<Comment> postComments = new ArrayList<>();

        for (Comment c: allComments) {
            if (c.getPost().equals(post)) {
                postComments.add(c);
            }
        }
        return postComments;
    }
}


