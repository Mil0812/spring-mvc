package mil0812.services;

import java.util.List;
import java.util.Optional;
import mil0812.models.Post;
import mil0812.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
  private final PostRepository postRepository;

  public PostService(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  public List<Post> getAllPosts(){
    return postRepository.findAll();
  }

  public Optional<Post> getPostById(String id){
    return postRepository.findById(id);
  }

  public void createPost(Post post){
    postRepository.save(post);
  }

  public void updatePost(Post post){
    postRepository.update(post);
  }

  public void deletePost(String id){
    postRepository.delete(id);
  }
}
