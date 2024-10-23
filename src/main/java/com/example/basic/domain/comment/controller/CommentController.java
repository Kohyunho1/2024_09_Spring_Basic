package com.example.basic.domain.comment.controller;

import com.example.basic.domain.auth.entity.Member;
import com.example.basic.domain.comment.service.CommentService;
import com.example.basic.global.reqres.ReqResHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final ReqResHandler reqResHandler;

  @PostMapping("/write")
  public String write(String body, long articleId) {

    Member loginMember = reqResHandler.getLoginMember();
    commentService.write(body, articleId, loginMember);

    return "redirect:/article/detail/%d".formatted(articleId);
  }

  @PostMapping("/modify/{commentId}")
  public String modify(@PathVariable("commentId") long commentId, long articleId, String body) {
    commentService.update(commentId, body);
    return "redirect:/article/detail/%d".formatted(articleId);
  }

  @PostMapping("/delete/{commentId}")
  public String delete(@PathVariable("commentId") long commentId, long articleId ) {
    commentService.deleteById(commentId);
    return "redirect:/article/detail/%d".formatted(articleId);
  }
}
