package springapi.controller.Post;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springapi.payload.PostDto;
import springapi.service.PostService;

import java.util.List;

@RestController
@RequestMapping("api/posts")
public class contoller {

    private PostService postService;

    public contoller(PostService postService) {

        this.postService = postService;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return  new ResponseEntity<>(postService.create(postDto), HttpStatus.CREATED);
    }

    // Get all posts rest api
@GetMapping
    public List<PostDto> getAllPosts(){
        return  postService.getAllPost();
    }

    @GetMapping("/{id}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
        return  ResponseEntity.ok(postService.getPostById(id));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity <String> deletePost(@PathVariable(name="id")long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted.",HttpStatus.OK);
    }
}
