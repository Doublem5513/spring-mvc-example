package org.doublem.examples.webapp.adapter;

import org.doublem.examples.webapp.model.Post;
import org.doublem.examples.webapp.provider.PostsDataProvider;
import org.doublem.examples.webapp.provider.PostsDataProviderImpl;
import org.doublem.examples.webapp.utils.FileDataUtils;

import java.io.*;
import java.util.Collection;
import java.util.Date;


/**
 * Created by mint on 16/8/14.
 */
public class PostsAdapter {
    private static final String POSTS_DB_FILE = System.getProperty("user.home") + "/webapp/forum/posts/posts.db";

    private PostsDataProvider dataProvider = new PostsDataProviderImpl();

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

        long lastId = FileDataUtils.findLastId(new File(POSTS_DB_FILE));
        post.setId(lastId+1);
        post.setCreated(new Date());

        if(dataProvider != null){
            dataProvider.savePost(post);
            return post;
        }

        throw new RuntimeException("Posts Data Provider is not initialized!");
    }

}
