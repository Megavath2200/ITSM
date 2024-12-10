package com.ticketing.tool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.Role;
import com.ticketing.tool.repository.RoleRepository;

@Service
public class RoleService implements IRole {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Integer getRoleID(String roleName) {
		return roleRepository.findRoleIDByRoleName(roleName);
	}

	@Override
	public String getRoleName(Integer roleId) {
		Optional<Role> role = roleRepository.findByRoleId(roleId);
		return role.isPresent() ? role.get().getRoleName() : null;
	}
}
