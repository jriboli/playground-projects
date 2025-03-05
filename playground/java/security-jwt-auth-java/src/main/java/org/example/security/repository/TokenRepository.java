package org.example.security.repository;

import org.example.security.entity.Token;
import org.example.security.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

//    @Query(value = """
//            SELECT t from Token t\s
//            INNER JOIN _User u\s
//            ON t.user_id = u.id\s
//            WHERE u.id = :id AND (t.expired = false OR t.revoked = false)\s
//            """)
    List<Token> findAllValidTokenByUser(User user);

    Optional<Token> findByToken(String token);
}
