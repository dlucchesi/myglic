package com.dlucchesi.myglic.service;

import com.dlucchesi.myglic.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    User create();

    Set<User> find();

    Optional<User> find(Long id);

    Optional<User> findByLogin(String login);

    Optional<User> save(User instance);

    Boolean delete(User entity, Boolean phys);
}
