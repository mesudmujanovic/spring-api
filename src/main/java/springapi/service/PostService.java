package springapi.service;

import springapi.entity.Post;
import springapi.payload.PostDto;
import springapi.payload.PostRespo;

import java.util.List;

public interface PostService {
    PostDto create (PostDto postDto);

    PostRespo getAllPost(int pageNo, int pageSize, String sortBy);

    PostDto getPostById(long id);

    void deletePostById (long id);

    PostDto updatePost (PostDto postDto,Long id);

}
