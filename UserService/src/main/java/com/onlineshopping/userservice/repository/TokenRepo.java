package com.onlineshopping.userservice.repository;

import com.onlineshopping.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {

    @Query("SELECT t FROM Token t WHERE t.value = ?1 and t.deleted = ?2")
    Optional<Token> findByValue(String value, boolean deleted);
}
