package blog.model;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

    private String title;
    private String text;
    private Date createdDate;
    private Category category;
    private User author;

    public Post(String title, String text, Date createdDate, Category category, User author) {
        this.title = title;
        this.text = text;
        this.createdDate = createdDate;
        this.category = category;
        this.author = author;
    }

    public Post() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (text != null ? !text.equals(post.text) : post.text != null) return false;
        if (createdDate != null ? !createdDate.equals(post.createdDate) : post.createdDate != null) return false;
        if (category != post.category) return false;
        return author != null ? author.equals(post.author) : post.author == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", category=" + category +
                ", author=" + author +
                '}';
    }
}
