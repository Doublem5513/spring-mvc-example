package org.doublem.examples.webapp.provider;

import org.doublem.examples.webapp.model.Post;
import org.doublem.examples.webapp.settings.GlobalSettings;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by mint on 16/8/14.
 */
public class PostsDataProviderImpl implements PostsDataProvider {

    private static Connection c = null;

    public PostsDataProviderImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + GlobalSettings.DB_FILE);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void savePost(Post post) {
        try {

            c.setAutoCommit(false);

            java.sql.Statement stmt = c.createStatement();

            String sql = "INSERT INTO posts (title, text, created, author, avatar) " +
                    "VALUES ('"+post.getTitle()+
                    "', '"+post.getText()+"', "+post.getCreated().getTime()+
                    ", '"+post.getAuthor()+"', '"+post.getAvatar()+"' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Collection<Post> getAllPosts() {
        LinkedList<Post> posts = new LinkedList<Post>();

        try {

            c.setAutoCommit(false);

            java.sql.Statement stmt = c.createStatement();

            String sql = "SELECT * FROM posts;";
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                Post post = new Post();
                post.setId((long)resultSet.getInt("id"));
                post.setTitle(resultSet.getString("title"));
                post.setText(resultSet.getString("text"));
                post.setCreated(new Date(resultSet.getLong("created")));
                post.setAuthor(resultSet.getString("author"));
                post.setAvatar(resultSet.getString("avatar"));
                posts.add(post);
            }

            stmt.close();
            c.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return posts;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        c.close();
    }
}
