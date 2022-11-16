package springapi.service;


import springapi.payload.CommentDto;

public interface CommentService {
    CommentDto createComment (long postId, CommentDto commentDto);

    CommentDto getCommentById(long postId,Long commentId);

    
}
