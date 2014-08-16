package org.doublem.examples.webapp.provider;

import org.doublem.examples.webapp.model.Post;

import java.util.Collection;

/**
 * Created by mint on 16/8/14.
 */
public interface PostsDataProvider {
    void savePost(Post post);
    Collection<Post> getAllPosts();
}
