package mil0812.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Post {
  private String id;
  private String title;
  private String content;
  private User author;

  public Post(String id, String title, String content, User author) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.author = author;
  }
}
