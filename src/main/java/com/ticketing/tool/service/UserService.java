package com.ticketing.tool.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketing.tool.dto.Email;
import com.ticketing.tool.dto.UserInfo;
import com.ticketing.tool.entity.Role;
import com.ticketing.tool.entity.User;
import com.ticketing.tool.exceptions.UserAlreadyExistsException;
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

	@Autowired
	private EmailService emailService;

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

	@Transactional
	public void save(UserInfo userInfo) {

		Optional<User> dbUser = userRepository.findByEmail(userInfo.getEmail());

		if (dbUser.isPresent()) {
			throw new UserAlreadyExistsException("User with email " + userInfo.getEmail() + " already exists");
		}

		Optional<User> dbUser2 = userRepository.findByUserName(userInfo.getUserName());

		if (dbUser2.isPresent()) {
			throw new UserAlreadyExistsException("User with username " + userInfo.getUserName() + " already exists");
		}

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

		Email email = new Email();

		email.setTo(userInfo.getEmail());
		email.setSubject("User Created");

		StringBuilder body = new StringBuilder();

		body.append("<div style=\"background-color: #f9f9f9; padding: 20px; border-radius: 10px;\">" + "<div>");
		body.append(
				"<img src='cid:companyLogo' alt='Company Logo' style='width: 50%; max-width: 400px; background: none; height: auto; max-height: 250px;'>");
		body.append("</div>" + "<h1>Congratulations and Welcome to Smac-X Inno Labs.</h1>" + "<p>Dear, ");
		body.append(userInfo.getFirstName());
		body.append("</p>");
		body.append(
				"<p>Your registration was successful, and you're now all set to dive into our software's features and capabilities<br>Thank you for joining us.</p>");
		body.append("<p>If you have any questions or need assistance, feel free to contact us.</p>");
		body.append("<p>Best Regards,<br>Smac-X Inno Labs.</p>" + "</div>");

		email.setBody(body.toString());

		try {

			emailService.sendEmail(email);
		} catch (IOException e) {
			e.printStackTrace();
		}

		userRepository.save(user);

	}

	private Integer generateUserId() {
		return (int) (System.currentTimeMillis() % Integer.MAX_VALUE);
	}

	public List<String> getUserName(String companyName) {
		Integer roleID = roleService.getRoleID("APPROVER");
		List<User> user = userRepository.findByCompanyNameAndRoleId(companyName, roleID);
		return user.stream().map(User::getUsername).collect(Collectors.toList());

	}

}
