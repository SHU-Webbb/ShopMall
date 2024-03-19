package com.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, String> {
 
}
