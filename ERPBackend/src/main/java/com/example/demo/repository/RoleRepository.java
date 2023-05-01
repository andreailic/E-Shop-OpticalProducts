package com.example.demo.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ERole;
import com.example.demo.model.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>,PagingAndSortingRepository <Role, Long>{

	Optional<Role> findByName(ERole name);
}
