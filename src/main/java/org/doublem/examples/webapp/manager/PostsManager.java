package org.doublem.examples.webapp.manager;

import org.doublem.examples.webapp.adapter.PostsAdapter;
import org.doublem.examples.webapp.model.Post;

import java.util.Collection;

/**
 * Created by mint on 16/8/14.
 */
public class PostsManager {
    PostsAdapter adapter = new PostsAdapter();

    public Collection<Post> getAllPosts(){
        return adapter.findAllPosts();
    }

    public Post savePost(String title, String text, String author, String avatar){
        return adapter.addPost(title, text, author, avatar);
    }

    public void deletePost(String id){
        adapter.deletePost(id);
    }

}
