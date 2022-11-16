package springapi.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import springapi.entity.Post;
import springapi.exception.ResourceNotFoundException;
import springapi.payload.PostDto;
import springapi.payload.PostRespo;
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
    public PostRespo getAllPost(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> posts =  postRepository.findAll(pageable);
        List<Post> listOfPosts =posts.getContent();
        List<PostDto> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostRespo postRespo = new PostRespo();
        postRespo.setContent(content);
        postRespo.setPageNo(posts.getNumber());
        postRespo.setPageSize(posts.getSize());
        postRespo.setTotalElements(posts.getTotalElements());
        postRespo.setTotalPages(posts.getTotalPages());
        postRespo.setLast(postRespo.isLast());

        return postRespo;
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

    @Override
    public PostDto updatePost(PostDto postDto, Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);
        return  mapToDTO(updatedPost);
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
