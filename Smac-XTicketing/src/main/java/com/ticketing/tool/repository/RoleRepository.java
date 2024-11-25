package com.ticketing.tool.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	public Optional<Role> findByRoleId(Integer roleId);

	@Query("SELECT r.roleId FROM Role r WHERE r.roleName = :roleName")
	Integer findRoleIDByRoleName(@Param("roleName") String roleName);

}
