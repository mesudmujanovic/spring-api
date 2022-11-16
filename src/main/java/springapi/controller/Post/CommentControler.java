package springapi.controller.Post;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springapi.payload.CommentDto;
import springapi.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentControler {

    private CommentService commentService;

    public CommentControler(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity <CommentDto> createComment(
            @PathVariable(value = "postId") long postId,
            @RequestBody CommentDto commentDto

    ){
        return  new ResponseEntity<>(commentService.createComment(postId,commentDto),HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity <CommentDto> getCommentById(
            @PathVariable(value = "postId") Long postId,
            @PathVariable (value = "id") Long commentId
    ){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

}
