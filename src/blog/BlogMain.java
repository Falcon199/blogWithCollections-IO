package blog;



import blog.model.Category;
import blog.model.Post;
import blog.model.User;
import blog.storage.DataStorage;

import java.util.Date;
import java.util.Scanner;

public class BlogMain implements Commands {

    static DataStorage dataStorage = new DataStorage();
    static Scanner scanner = new Scanner(System.in);
    static User currentUser = null;

    public static void main(String[] args) {
        dataStorage.init();
        boolean isRun = true;
        while (isRun) {
            dataStorage.printPosts();
            printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case PRINT_POSTS_BY_AUTHOR:
                    printPostsByAuthor();
                    break;
                case PRINT_POSTS_BY_CATEGORY:
                    printPostsByCategory();
                    break;
                case DELETE_POSTS_BY_AUTHOR:
                    deletePostsByAuthor();
                    break;
                case DELETE_USER_BY_EMAIL:
                    deleteUserByEmail();
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    registration();
                    break;
                default:
                    System.out.println("Invalid command! Please try again");
            }
        }

    }

    private static void deleteUserByEmail() {
        System.out.println("Please choose user's email");
        dataStorage.printUsers();
        String email = scanner.nextLine();
        dataStorage.deleteUserByEmail(email);
    }

    private static void deletePostsByAuthor() {
        System.out.println("please choose user by email");
        dataStorage.printUsers();
        String authorEmail = scanner.nextLine();
        dataStorage.deletePostsByAuthor(authorEmail);
    }

    private static void registration() {
        System.out.println("Please input name,surname,email,password");
        String userDataStr = scanner.nextLine();
        String[] userData = userDataStr.split(",");
        if (userData.length == 4) {
            String email = userData[2];
            if (dataStorage.isUserExist(email)) {
                System.out.println("User with " + email + " already exists");
            } else {
                User user = new User();
                user.setName(userData[0]);
                user.setSurname(userData[1]);
                user.setEmail(email);
                user.setPassword(userData[3]);
                dataStorage.add(user);
                System.out.println("User registered!");
            }
        } else {
            System.out.println("invalid user data");
            registration();
        }
    }

    private static void login() {
        System.out.println("Please input email,password");
        String loginDataStr = scanner.nextLine();
        String[] loginData = loginDataStr.split(",");
        if (loginData.length == 2) {
            if (dataStorage.isUserExist(loginData[0])) {
                User user =
                        dataStorage.getUserByEmailAndPassword(
                                loginData[0], loginData[1]);
                if (user == null) {
                    System.out.println("invalid password");
                } else {
                    System.out.println("Welcome!");
                    currentUser = user;
                    loggedIn();
                }
            } else {
                System.out.println("User with " +
                        loginData[0] + " does not exists");
            }
        } else {
            System.out.println("invalid data");
            login();
        }
    }

    private static void loggedIn() {
        boolean isLogin = true;
        while (isLogin) {
            dataStorage.printPosts();
            printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    System.exit(0);
                    break;
                case PRINT_POSTS_BY_AUTHOR:
                    printPostsByAuthor();
                    break;
                case PRINT_POSTS_BY_CATEGORY:
                    printPostsByCategory();
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case LOGOUT:
                    isLogin = false;
                    break;
                default:
                    System.out.println("Invalid command! Please try again");
            }
        }
    }

    private static void addPost() {
        for (Category category : Category.values()) {
            System.out.println(category);
        }
        System.out.println("Please choose category from list");
        String categoryName = scanner.nextLine();
        try {
            Category category =
                    Category.valueOf(categoryName.toUpperCase());
            System.out.println("Please input title,text");
            String postDataStr = scanner.nextLine();
            String[] postData = postDataStr.split(",");
            if (postData.length == 2) {
                Post post = new Post();
                post.setAuthor(currentUser);
                post.setCreatedDate(new Date());
                post.setTitle(postData[0]);
                post.setText(postData[1]);
                post.setCategory(category);
                dataStorage.add(post);
                System.out.println("Post was successfully added");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category name");
            addPost();
        }
    }

    private static void printPostsByCategory() {
        for (Category category : Category.values()) {
            System.out.println(category);
        }
        System.out.println("Please choose category from list");
        String categoryName = scanner.nextLine();
        try {
            Category category =
                    Category.valueOf(categoryName.toUpperCase());
            dataStorage.printPostsByCategory(category);

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category name");
        }
    }

    private static void printPostsByAuthor() {
        dataStorage.printUsers();
        System.out.println("Please choose author's email");
        String authorEmail = scanner.nextLine();
        if (dataStorage.isUserExist(authorEmail)) {
            dataStorage.printPostsByUserEmail(authorEmail);
        } else {
            System.out.println("Invalid email");
        }
    }

    private static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + PRINT_POSTS_BY_AUTHOR + " for PRINT_POSTS_BY_AUTHOR");
        System.out.println("Please input " + PRINT_POSTS_BY_CATEGORY + " for PRINT_POSTS_BY_CATEGORY");
        System.out.println("Please input " + DELETE_POSTS_BY_AUTHOR + " for DELETE_POSTS_BY_AUTHOR");
        System.out.println("Please input " + DELETE_USER_BY_EMAIL + " for DELETE_USER_BY_EMAIL");
        System.out.println("Please input " + REGISTER + " for REGISTER");
        System.out.println("Please input " + LOGIN + " for LOGIN");
    }

    private static void printUserCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + PRINT_POSTS_BY_AUTHOR + " for PRINT_POSTS_BY_AUTHOR");
        System.out.println("Please input " + PRINT_POSTS_BY_CATEGORY + " for PRINT_POSTS_BY_CATEGORY");
        System.out.println("Please input " + ADD_POST + " for ADD_POST ");
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
    }

}
