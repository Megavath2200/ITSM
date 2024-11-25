package com.ticketing.tool.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ticketing.tool.dto.UserInfo;
import com.ticketing.tool.entity.Role;
import com.ticketing.tool.entity.User;
import com.ticketing.tool.repository.RoleRepository;
import com.ticketing.tool.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleService roleService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username).get();

		Role role = roleRepository.findByRoleId(user.getRoleId()).get();

		List<GrantedAuthority> authorities = new ArrayList<>(
				Arrays.asList(new SimpleGrantedAuthority("ROLE_" + role.getRoleName())));

		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				authorities);
	}

	public List<String> getUserRoles(Integer userId) {
		User user = userRepository.findByUserId(userId).get();
		Role role = roleRepository.findByRoleId(user.getRoleId()).get();

		return new ArrayList<>(Arrays.asList(role.getRoleName()));
	}

	public void save(UserInfo userInfo) {
		User user = new User();
		user.setUserId(generateUserId());
		user.setUserName(userInfo.getUserName());
		user.setPassword(passwordEncoder.encode(userInfo.getPassword()));

		Integer roleID = roleService.getRoleID(userInfo.getRoleName());
		if (roleID == null) {
			throw new IllegalArgumentException("Invalid role name: " + userInfo.getRoleName());
		}

		user.setRoleId(roleID);
		user.setFirstName(userInfo.getFirstName());
		user.setLastName(userInfo.getLastName());
		user.setEmail(userInfo.getEmail());
		user.setPhone(userInfo.getPhone());
		user.setCompanyName(userInfo.getCompanyName());
		user.setReferance(userInfo.getReferance());
		user.setActive(true);
		user.setCreated(new Timestamp(System.currentTimeMillis()));
		user.setUpdated(new Timestamp(System.currentTimeMillis()));

		user.setCreateBy("System");
		user.setUpdateBy("System");

		userRepository.save(user);

	}

	private Integer generateUserId() {
		return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
	}

}
