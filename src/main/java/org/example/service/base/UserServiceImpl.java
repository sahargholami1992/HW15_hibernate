package org.example.service.base;


import org.example.base.service.impl.BaseServiceImpl;
import org.example.entity.User;
import org.example.repository.base.UserRepository;

import java.util.Optional;

public class UserServiceImpl<T extends User, R extends UserRepository<T>> extends BaseServiceImpl<Integer,T, R>
                             implements UserService<T> {
    public UserServiceImpl(R repository) {
        super(repository);
    }
    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByMobileNumber(String mobileNumber) {
        return repository.existsByMobileNumber(mobileNumber);
    }

    @Override
    public Optional<T> findByUsername(String username) {
        return repository.findByUsername(username);
    }
    @Override
    public boolean existByUserNameAndPassword(String userName, String password) {
        return repository.existByUserNameAndPassword(userName, password);
    }

    @Override
    public T findByUserName(String userName) {
        return repository.findByUserName(userName);
    }
}
