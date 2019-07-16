package blog.storage;



import blog.model.Post;
import blog.model.User;

import java.io.*;
import java.util.List;
import java.util.Map;

public class SerializeUtil {

    public static final String USER_MAP_FILE_PATH = "F:\\IdeaProjects\\src\\blog\\file\\userMap.bl";

    public static final String POSTS_FILE_PATH = "F:\\IdeaProjects\\src\\blog\\file\\posts.bl";

    public static void serializeUserMap(Map<String, User> userMap) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USER_MAP_FILE_PATH))) {
            objectOutputStream.writeObject(userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, User> deserializeUserMap() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER_MAP_FILE_PATH))) {
            return (Map<String, User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There is no file for userMap");
        }
        return null;
    }

    public static void serializePosts(List<Post> posts) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(POSTS_FILE_PATH))) {
            objectOutputStream.writeObject(posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Post> deserializePosts() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(POSTS_FILE_PATH))) {
            return (List<Post>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There is no file for posts");
        }
        return null;
    }
}
