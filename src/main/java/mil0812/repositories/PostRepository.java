package mil0812.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import mil0812.models.Post;
import org.springframework.stereotype.Repository;

@Repository
public class PostRepository {

  private final List<Post> posts = new ArrayList<>();

  public List<Post> findAll(){
    return posts;
  }

  public Optional<Post> findById(String id) {
    return posts.stream()
        .filter(post -> post.getId() != null && post.getId().equals(id))
        .findFirst();
  }


  public void save(Post post){
    post.setId(UUID.randomUUID().toString());
    posts.add(post);
  }

  public void update(Post updatedPost) {
    findById(updatedPost.getId()).ifPresent(post -> {
      post.setTitle(updatedPost.getTitle());
      post.setContent(updatedPost.getContent());
      post.setAuthor(updatedPost.getAuthor());
    });
  }

  public void delete(String id){
    posts.removeIf(post -> post.getId().equals(id));
  }
}
