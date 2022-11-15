package springapi.service.impl;

import org.springframework.stereotype.Service;
import springapi.entity.Post;
import springapi.exception.ResourceNotFoundException;
import springapi.payload.PostDto;
import springapi.repository.PostRepository;
import springapi.service.PostService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService {

    private PostRepository postRepository;

    public PostServiceimpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto create(PostDto postDto) {

     Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

        PostDto postResponse = mapToDTO(newPost);
        return postResponse;

    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id",id));
        return mapToDTO(post);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id",id));
   postRepository.delete(post);
    }

    private  PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(postDto.getContent());
        return postDto;
    }
    private  Post mapToEntity(PostDto postDto){
Post post = new Post();
post.setTitle(postDto.getTitle());
post.setDescription(postDto.getDescription());
post.setContent(postDto.getContent());
return post;
    }

}
