package org.atlas.commons.context;

public class UserContext {

    private static final ThreadLocal<CurrentUser> userContext = new ThreadLocal<>();

    public static CurrentUser getCurrentUser() {
        return userContext.get();
    }

    public static void setCurrentUser(CurrentUser context) {
        userContext.set(context);
    }

    public static void clear() {
        userContext.remove();
    }
}
