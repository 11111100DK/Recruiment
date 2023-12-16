package com.example.springrestful.repository;

import com.example.springrestful.model.entity.Account.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findById (int id);
    Account findByUsername (String username);
//    List<Account> findByRole(int role);
    @Query(value = "SELECT a FROM Account a WHERE a.email = :email")
    Account findByEmail (String email);

//    @Query(value = "SELECT a FROM Account a WHERE a.role.id = :id")
//    List<Account> findByRoleWithJPQL(@Param("id") int id);

    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.password = :password WHERE a.email = :email")
    void changePassword(@Param("email") String email, @Param("password") String password);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query(value = "SELECT a FROM Account a WHERE a.username LIKE %:userName% AND a.role <> 'Admin'")
    List<Account> getAllUser(@Param("userName") String userName);

    @Query(value = "SELECT a FROM Account a WHERE a.role <> 'Admin'")
    List<Account> getAllUser();

    @Query(value = "SELECT a FROM Account a WHERE a.username LIKE %:userName% AND a.role = 'Admin'")
    List<Account> getAllAdmin(@Param("userName") String userName);

    @Query(value = "SELECT a FROM Account a WHERE a.role = 'Admin'")
    List<Account> getAllAdmin();


    @Transactional
    @Modifying
    @Query("UPDATE Account a SET a.status = CASE WHEN a.status = 1 THEN 0 ELSE 1 END WHERE a.id = :id")
    void changeStatus(int id);

}
