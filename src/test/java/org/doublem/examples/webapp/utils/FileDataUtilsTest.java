package org.doublem.examples.webapp.utils;

import org.doublem.examples.webapp.model.Post;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by mint on 16/8/14.
 */
public class FileDataUtilsTest {

    @Before
    public void setUp() throws Exception {


    }

    @Test
    public void testExtractId() throws Exception {
        Post post = new Post();
        post.setTitle("Naslov");
        post.setText("Ovo je neki tekst");
        post.setAuthor("Nepoznati Autor");
        post.setCreated(new Date());

        String object = post.toString();

    }
}
