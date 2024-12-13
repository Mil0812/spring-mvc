package mil0812.controllers;


import java.util.UUID;
import mil0812.models.Post;
import mil0812.services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/posts")
@Controller
public class PostController {
  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public String getAllPosts(Model model) {
    model.addAttribute("posts", postService.getAllPosts());
    return "list";
  }

  @GetMapping("/{id}")
  public String getPostById(@PathVariable String id, Model model) {
    postService.getPostById(id).ifPresent(post -> model.addAttribute("post", post));
    return "details";
  }

  @GetMapping("/new")
  public String createPostForm(Model model) {
    model.addAttribute("post", new Post(UUID.randomUUID().toString(), "", "", null));
    return "form";
  }

  @PostMapping
  public String createOrUpdatePost(@ModelAttribute("post") Post post, BindingResult result) {
    if (result.hasErrors()) {
      return "form";
    }
    if (postService.getPostById(post.getId()).isPresent()) {
      postService.updatePost(post);
    } else {

      if (post.getId() == null || post.getId().isEmpty()) {
        post.setId(UUID.randomUUID().toString());
      }
      postService.createPost(post);
    }
    return "redirect:/posts";
  }

  @GetMapping("/delete/{id}")
  public String deletePost(@PathVariable String id) {
    postService.deletePost(id);
    return "redirect:/posts";
  }
}
