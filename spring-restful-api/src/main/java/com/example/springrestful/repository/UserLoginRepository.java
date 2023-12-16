package com.example.springrestful.repository;

import com.example.springrestful.model.entity.UserLogin.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
    @Query("SELECT DATE(ul.loginTime), count(ul.username) FROM UserLogin ul " +
            "WHERE DATE(ul.loginTime)  BETWEEN :startDate AND :endDate GROUP BY DATE(ul.loginTime)")
    List<Object[]> findByLoginTimeBetween(LocalDate startDate, LocalDate endDate);
    @Query("SELECT COUNT(ul) FROM UserLogin ul " +
            "WHERE ul.username = :username AND DATE(ul.loginTime) = DATE(:currentDate)")
    Long countUserLoginsToday(@Param("username") String username, @Param("currentDate") LocalDate currentDate);
}
