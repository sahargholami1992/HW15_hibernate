package org.example.service.base;

import org.example.base.service.BaseService;
import org.example.entity.User;

import java.util.Optional;

public interface UserService<T extends User> extends BaseService<Integer,T> {
    boolean existsByUsername(String username);

    boolean existsByMobileNumber(String mobileNumber);

    Optional<T> findByUsername(String username);
    boolean existByUserNameAndPassword(String userName, String password);

    T findByUserName(String userName);
}
