package blog.storage;



import blog.model.Category;
import blog.model.Post;
import blog.model.User;

import java.util.*;

public class DataStorage {

    private Map<String, User> users;
    private List<Post> posts;

    public DataStorage() {
        users = new HashMap<>();
        posts = new ArrayList<>();
    }

    public void add(User user) {
        users.put(user.getEmail(), user);
        SerializeUtil.serializeUserMap(users);
    }

    public void add(Post post) {
        posts.add(post);
        SerializeUtil.serializePosts(posts);
    }

    public void printUsers() {
        for (User value : users.values()) {
            System.out.println(value.getEmail() + "->" +
                    value.getName() + " " + value.getSurname());
        }
    }

    public void printPosts() {
        for (Post post : posts) {
            System.out.println(post);
        }
    }

    public void printPostsByCategory(Category category) {
        for (Post post : posts) {
            if (post.getCategory() == category) {
                System.out.println(post);
            }
        }
    }


    public User getUserByEmailAndPassword(String email, String password) {
        User user = users.get(email);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean isUserExist(String email) {
        return users.containsKey(email);
    }

    public void printPostsByUserEmail(String email) {
        for (Post post : posts) {
            if (post.getAuthor().getEmail().equals(email)) {
                System.out.println(post);
            }
        }
    }

    public void deletePostsByAuthor(String authorEmail) {
        Iterator<Post> iterator = posts.iterator();
        while (iterator.hasNext()) {
            Post post = iterator.next();
            if (post.getAuthor().getEmail().equals(authorEmail)) {
                iterator.remove();
            }
        }
        SerializeUtil.serializePosts(posts);

    }

    public void deleteUserByEmail(String email) {
        deletePostsByAuthor(email);
        users.remove(email);
        SerializeUtil.serializeUserMap(users);
        SerializeUtil.serializePosts(posts);
    }

    public void init() {
        Map<String, User> userMapFromFile = SerializeUtil.deserializeUserMap();
        if (userMapFromFile != null) {
            users = userMapFromFile;
        }
        List<Post> postsFromFile = SerializeUtil.deserializePosts();
        if (postsFromFile != null) {
            posts = postsFromFile;
        }
    }
}
