package springapi.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import springapi.entity.Comment;
import springapi.entity.Post;
import springapi.exception.BlogApiException;
import springapi.exception.ResourceNotFoundException;
import springapi.payload.CommentDto;
import springapi.repository.CommentRepository;
import springapi.repository.PostRepository;
import springapi.service.CommentService;

@Service
public class CommentServiceimpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceimpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment = mapToEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow( ()-> new ResourceNotFoundException("Post","id",postId));
comment.setPost(post);
Comment newComment = commentRepository.save(comment);
return mapToDto(newComment);
    }

    @Override
    public CommentDto getCommentById(long postId, Long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id" ,postId));
    Comment comment =  commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment","id",commentId) );

     if (!comment.getPost().getId().equals(post.getId())){
         throw new BlogApiException((HttpStatus.BAD_REQUEST),"Comment does not belong to post");
     }
     return mapToDto(comment);
    }




    private CommentDto mapToDto (Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getName());
        commentDto.setBody(comment.getBody());
        return  commentDto;
    }

    private Comment mapToEntity (CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }


}
