package com.skynest.uitesting.api;

import java.util.List;
import java.util.Optional;;

public class UsersApiUtil {

    public static User getFirstWorkerIfAny(List<User> getAllUsersResponse) {
        Optional<User> first = getAllUsersResponse
                .stream()
                .filter(user ->
                    user.getRoleName().equals(Roles.WORKER.getString()))
                .findAny();

        return first.orElse(null);
    }


}
