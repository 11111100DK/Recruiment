package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Account.Account;
import com.example.springrestful.model.entity.SupportQuestion.SupportQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportAndContactRepository extends JpaRepository<SupportQuestion, Integer> {
    // Option 1: Method Query
    List<SupportQuestion> findByAccount(Account account);

    // Option 2: JPQL Query (Java Persistence Query Language)
    @Query(value = "SELECT s FROM SupportQuestion s WHERE s.account.username = :username")
    List<SupportQuestion> findByAccountUsername(@Param("username") String username);

    @Query(value = "SELECT s FROM SupportQuestion s WHERE s.account.email = :email")
    List<SupportQuestion> findByAccountEmail(@Param("email") String email);
}
