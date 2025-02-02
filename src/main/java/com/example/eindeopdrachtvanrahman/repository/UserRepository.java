
package com.example.eindeopdrachtvanrahman.repository;

import com.example.eindeopdrachtvanrahman.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String > {
}

