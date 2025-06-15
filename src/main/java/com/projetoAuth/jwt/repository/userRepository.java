package com.projetoAuth.jwt.repository;

import com.projetoAuth.jwt.model.userModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    userModel findByLogin(String login);
    boolean existsByLogin(String login);
}
