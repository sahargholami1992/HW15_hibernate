package org.example.utill;


import lombok.Getter;
import org.example.entity.User;

public class SecurityContext {

    private SecurityContext() {
    }

    @Getter
    private static User currentUser;

    public static void fillContext(User baseUser) {
        currentUser = baseUser;
    }

    public static void logout() {
        currentUser = null;
    }

    public static boolean isAnyoneAuthenticated() {
        return currentUser != null;
    }

    public static Integer getCurrentUserId() {
        return currentUser.getId();
    }

//    public static String getCurrentUserType() {
//        return currentUser.getUserType();
//    }

}
