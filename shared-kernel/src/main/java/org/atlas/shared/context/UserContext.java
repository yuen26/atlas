package org.atlas.shared.context;

public class UserContext {

    private static final ThreadLocal<UserInfo> userContext = new ThreadLocal<>();

    public static UserInfo getCurrentUser() {
        return userContext.get();
    }

    public static void setCurrentUser(UserInfo context) {
        userContext.set(context);
    }

    public static void clear() {
        userContext.remove();
    }
}
