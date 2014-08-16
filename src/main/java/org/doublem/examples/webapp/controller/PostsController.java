package org.doublem.examples.webapp.controller;

import org.doublem.examples.webapp.manager.PostsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by mint on 16/8/14.
 */
@Controller
@RequestMapping("/forum/posts")
public class PostsController {
    PostsManager manager = new PostsManager();

    @RequestMapping(method = RequestMethod.GET)
    public String getPosts(ModelMap model){
        model.addAttribute("posts", manager.getAllPosts());
        return "posts";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addPost(ModelMap model, @RequestParam("title") String title, @RequestParam("text") String text,
                          @RequestParam("author") String author, @RequestParam("avatar") String avatar){

        manager.savePost(title, text, author, avatar);

        model.addAttribute("posts", manager.getAllPosts());
        return "posts";
    }
}
