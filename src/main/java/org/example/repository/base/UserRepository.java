package org.example.repository.base;


import org.example.base.repository.BaseRepository;
import org.example.entity.User;

import java.util.Optional;

public interface UserRepository<T extends User> extends BaseRepository<Integer, T> {

    boolean existsByUsername(String username);

    boolean existsByMobileNumber(String mobileNumber);

    Optional<T> findByUsername(String username);
    boolean existByUserNameAndPassword(String userName, String password);

    T findByUserName(String userName);
}
