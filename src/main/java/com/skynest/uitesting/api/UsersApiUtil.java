package com.skynest.uitesting.api;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class UsersApiUtil {

    public static User getFirstWorkerIfAny(List<User> getAllUsersResponse) {
        Optional<User> first = getAllUsersResponse
                .stream()
                .filter(user ->
                    user.getRoleName().equals(Roles.WORKER.getString()))
                .findAny();

        return first.orElse(null);
    }

}
