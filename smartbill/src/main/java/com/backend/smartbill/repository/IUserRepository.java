package com.backend.smartbill.repository;

import com.backend.smartbill.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, Long> {
    public UserModel findByName(String user);

    public static void saveUser(UserModel user) {

    }
}
