package com.ticketing.tool.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ticketing.tool.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUserName(final String username);

	public Optional<User> findByUserId(Integer userId);

	public List<User> findByCompanyNameAndRoleId(String companyName, Integer roleId);

	public Optional<User> findByEmail(final String email);

}
