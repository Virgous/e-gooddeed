package com.enigma.egooddeed.repository;

import com.enigma.egooddeed.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface IAdminRepository extends JpaRepository<Admin,String> {
    @Modifying
    @Query(value = "INSERT INTO m_admin(username,password) VALUES (:username,:password)", nativeQuery = true)
    void create(String username, String password);

    @Query(value = "SELECT * FROM m_admin",nativeQuery = true)
    List<Admin> getAll();
}
