package org.doublem.examples.webapp.adapter;

import org.doublem.examples.webapp.model.Post;
import org.doublem.examples.webapp.provider.PostsDataProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;


/**
 * Created by mint on 16/8/14.
 */
public class PostsAdapter {

    @Autowired
    private PostsDataProvider dataProvider;

    public Collection<Post> findAllPosts(){

        if(dataProvider != null){
            return dataProvider.getAllPosts();
        }

        throw new RuntimeException("Posts Data Provider is not initialized!");
    }

    public Post addPost(String title, String text, String author, String avatar){

        Post post = new Post();
        post.setTitle(title);
        post.setText(text);
        post.setAuthor(author);
        post.setAvatar(avatar);
        post.setCreated(new Date());

        if(dataProvider != null){
            dataProvider.savePost(post);
            return post;
        }

        throw new RuntimeException("Posts Data Provider is not initialized!");
    }

    public void deletePost(String id){
        dataProvider.deletePost(Long.parseLong(id));
    }

}
