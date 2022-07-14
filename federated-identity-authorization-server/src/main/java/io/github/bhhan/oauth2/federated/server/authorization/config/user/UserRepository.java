package io.github.bhhan.oauth2.federated.server.authorization.config.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private final Map<String, User> userCache = new ConcurrentHashMap<>();

    public void addUser(User user){
        userCache.put(user.getUserId(), user);
    }

    public List<User> getUsers() {
        return new ArrayList<>(userCache.values());
    }

    public User findByUserId(String userId) {
        return userCache.get(userId);
    }
}
