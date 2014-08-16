package org.doublem.examples.webapp.model;

import org.doublem.examples.webapp.model.annotations.DataType;
import org.doublem.examples.webapp.settings.GlobalSettings;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;

import static java.text.DateFormat.MEDIUM;

/**
 * Created by mint on 16/8/14.
 */
public class Post {
    private Long id;
    private String title;
    private String author;
    @DataType(type = Date.class)
    private Date created;
    private String text;
    private String avatar;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(GlobalSettings.DB_DATE_FORMAT);
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", created=" + dateFormat.format(created) +
                ", text='" + text + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

}
