package springapi.service;

import springapi.entity.Post;
import springapi.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto create (PostDto postDto);

    List<PostDto>getAllPost();

    PostDto getPostById(long id);

    void deletePostById (long id);

}
