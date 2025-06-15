package com.projetoAuth.jwt.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepository extends JpaRepository<userModel, Long> {
    UserDetails findByLogin(userRole role);
}
