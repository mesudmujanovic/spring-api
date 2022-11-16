package springapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import springapi.entity.Post;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRespo {
    private List<PostDto> content;
    private int pageNo;
    private  int pageSize;
    private  long totalElements;
    private  int totalPages;
    private boolean last;

}
