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
@RequestMapping("/forum/posts/delete")
public class PostsControllerDelete {
    PostsManager manager = new PostsManager();

    @RequestMapping(method = RequestMethod.POST)
    public String deletePost(ModelMap model, @RequestParam("id") String id){
        manager.deletePost(id);

        model.addAttribute("posts", manager.getAllPosts());
        return "posts";
    }
}
