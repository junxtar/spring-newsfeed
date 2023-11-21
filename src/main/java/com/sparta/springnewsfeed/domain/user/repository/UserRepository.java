package com.sparta.springnewsfeed.domain.user.repository;

import com.sparta.springnewsfeed.domain.user.entity.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
