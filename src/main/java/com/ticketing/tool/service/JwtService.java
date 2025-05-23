package com.ticketing.tool.service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ticketing.tool.entity.Role;
import com.ticketing.tool.entity.User;
import com.ticketing.tool.repository.RoleRepository;
import com.ticketing.tool.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	@Value("${application.security.jwt.secret-key}")
	private String secretKey;

	@Value("${application.security.jwt.expiration}")
	private int jwtExpirationMs;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);
	}

	public UUID extractUserId(String token) {

		String userId = extractClaim(token, claims -> claims.get("userId", String.class));
		return UUID.fromString(userId);
	}

	public Timestamp extractExpirationAsTimestamp(String token) {

		Date expirationDate = extractClaim(token, Claims::getExpiration);
		return new Timestamp(expirationDate.getTime());
	}

	public String hashWithSHA256(String data) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashedBytes = digest.digest(data.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(hashedBytes);
	}

	public String generateToken(UserDetails userDetails) {

		if (userDetails instanceof User user) {
			List<String> userRoles = userService.getUserRoles(user.getUserId());
//			Map<String, Object> userClaims = Map.of("userId", user.getUserId(), "firstName", user.getFirstName(),
//					"lastName", user.getLastName(), "active", user.getActive(), "roles", userRoles
//
//			)

			Map<String, Object> userClaims = Map.of("roles", userRoles, "userName", user.getUsername() , "email" , user.getEmail() , "company" , user.getCompanyName() ,"Department", user.getDepartment() );
			return generateToken(userClaims, userDetails);
		}

		return generateToken(new HashMap<>(), userDetails);
	}

	public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

		return Jwts.builder().claims(extraClaims).subject(userDetails.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(getSignInKey()).compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getVerifyKey()).build().parseSignedClaims(token).getPayload();
	}
	
	public User getUserFromToken(String token) {
		String jwt = token.replace("Bearer ", "");
	
		Claims claims = extractAllClaims(jwt);
		String email = claims.get("email", String.class); 
		User user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found"));
	
		Role role = roleRepository.findByRoleId(user.getRoleId())
				.orElseThrow(() -> new RuntimeException("Role not found"));
	
		user.setRoleName(role.getRoleName());
	
		return user;
	}
	
	

	public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

	public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("roles", String.class));
    }


	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	private SecretKey getVerifyKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String doGenerateRefreshToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs)).signWith(getSignInKey())
				.compact();
	}
}
