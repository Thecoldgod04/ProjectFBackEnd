package com.coldgod.projectfbackend.Authentication.Repositories;

import com.coldgod.projectfbackend.Authentication.Models.Token;
import com.coldgod.projectfbackend.Authentication.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
    select t from Token t
    join User u on u.id = t.user.id
    where u.id = ?1 and t.isExpired = false
""")
    List<Token> findAllValidTokensByUserId(Long userId);

    Optional<Token> findByToken(String token);
}
