package org.doublem.examples.webapp.adapter;

import org.doublem.examples.webapp.model.Post;
import org.doublem.examples.webapp.provider.PostsDataProvider;
import org.doublem.examples.webapp.provider.PostsDataProviderImpl;
import org.doublem.examples.webapp.settings.GlobalSettings;
import org.doublem.examples.webapp.utils.FileDataUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mint on 16/8/14.
 */
public class PostsAdapter {
    private static final String POSTS_DB_FILE = System.getProperty("user.home") + "/webapp/forum/posts/posts.db";
    private static final String OBJECT_PROPERTIES_DELIMITER = ", ";

    private PostsDataProvider dataProvider = new PostsDataProviderImpl();

    public Collection<Post> findAllPosts(){

        if(dataProvider != null){
            return dataProvider.getAllPosts();
        }

        LinkedList<Post> posts = new LinkedList<Post>();

        File postsFile = new File(POSTS_DB_FILE);
        try {
            FileReader reader = new FileReader(postsFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;
            while((line = bufferedReader.readLine()) != null){
                posts.add(fromString(line));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Posts database not found!", e);
        } catch (IOException e) {
            throw new RuntimeException("Error reading from posts DB!", e);
        }

        return posts;
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

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(POSTS_DB_FILE, true)));
            out.println(post.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return post;
    }

    private static Post fromString(String string){
        String val = string.trim();
        if(!val.startsWith("Post")){
            throw new RuntimeException("Invalid string format!");
        }

        Pattern objectFieldsPattern = Pattern.compile("Post\\{(.*)\\}");
        Matcher objectFieldsMatcher = objectFieldsPattern.matcher(val);
        if(!objectFieldsMatcher.matches()){
            throw new RuntimeException("Can't match object body: " + val);
        }

        String objectDescription = objectFieldsMatcher.group(1);
        String[] properties = objectDescription.split(OBJECT_PROPERTIES_DELIMITER);
        System.out.println(Arrays.toString(properties));

        Post newPost = new Post();
        for(String property : properties){
            String[] split = property.split("=");
            String key = split[0];
            String value = split[1];
            setFieldValue(newPost, key, value, null);
        }

        return newPost;
    }

    public static void main(String[] args) {
        PostsAdapter postsAdapter = new PostsAdapter();
        Post post = postsAdapter.addPost("Added from code", "This post is added from java code. Only for demonstartion purposes!", "Mato", null);
        System.out.println("Added post: " + post.toString());

    }

    private static void setFieldValue(Object object, String fieldName, String fieldValue, Class<?> fieldType){
        if(fieldValue.matches("'.*'")){
            fieldValue = fieldValue.replace("'", "");
        }
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            if(fieldType != null){
                Constructor<?> constructor = fieldType.getConstructor(String.class);
                Object newObject = constructor.newInstance(fieldValue);
                field.set(object, newObject);
                return;
            }

            Class<?> type = field.getType();
            if(type.equals(Date.class)){
                SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalSettings.DB_DATE_FORMAT);
                Date date = dateFormat.parse(fieldValue);
                field.set(object, date);
                return;
            }
            Constructor<?> constructor = type.getConstructor(String.class);
            Object newObject = constructor.newInstance(fieldValue);
            field.set(object,newObject);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException("No such field found!", e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
