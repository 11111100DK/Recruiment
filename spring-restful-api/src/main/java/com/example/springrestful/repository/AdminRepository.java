package com.example.springrestful.repository;


import com.example.springrestful.model.entity.Admin.Admin;
import com.example.springrestful.model.entity.Role.Role;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findById (int id);
    List<Admin> findAll();
    List<Admin> findByRole(String role);
    List<Admin> findByStatus(int status);
    List<Admin> findByManagedBy(Integer managedBy);
    @Query(value = "SELECT a FROM Admin a WHERE a.accountAdmin.id = :id")
    List<Admin> findByAccountWithJPQL(@Param("id") int id);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Account a SET a.role_id = :role WHERE a.id = :user")
    void changeRoleAdmin(@Param("user") int user,@Param("role") Role role);

    @Query("SELECT COUNT(a) FROM Account a WHERE a.role <> 'Admin'")
    int countNumberUserRegister();
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 'Recruiter'")
    int countNumberRecruiterRegister();
    @Query("SELECT COUNT(a) FROM Account a WHERE a.role = 'Candidate'")
    int countNumberCandidateRegister();

    @Query("SELECT COUNT(a) FROM Job a")
    int countNumberJobs();

}
