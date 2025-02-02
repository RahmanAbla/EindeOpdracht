package com.example.eindeopdrachtvanrahman.repository;

import com.example.eindeopdrachtvanrahman.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client,Long> {
}
