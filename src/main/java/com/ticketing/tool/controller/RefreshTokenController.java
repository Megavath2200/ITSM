package com.ticketing.tool.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.tool.dto.AuthenticationResponse;
import com.ticketing.tool.service.JwtService;

import io.jsonwebtoken.impl.DefaultClaims;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class RefreshTokenController {
	private Logger logger = LoggerFactory.getLogger(RefreshTokenController.class);

	@Autowired
	private JwtService jwtService;

	@GetMapping(value = "/auth/refreshToken")
	public ResponseEntity<?> refreshToken(HttpServletRequest request) throws Exception {

		logger.info("Refreshing token");

		DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");

		logger.debug("Claims: {}", claims);

		Map<String, Object> expectedMap = getMapFromIoJsonWebTokenClaims(claims);

		logger.debug("Expected Map: {}", expectedMap);

		String token = jwtService.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());

		/* Updating refresh token to original token after expiration */
		/*
		 * UUID userId = jwtService.extractUserId(token); validateToken(request, token,
		 * userId);
		 */

		logger.info("Token refreshed successfully");

		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

//	private void validateToken(HttpServletRequest request, String token, UUID userId) {
//		Optional<UserToken> userToken = userTokenRepository.findByUserIdAndActive(userId, true);
//
//		try {
//			String refreshedHashedToken = jwtService.hashWithSHA256(token);
//			if (userToken.isPresent() && !(userToken.get().getJwtToken().equals(refreshedHashedToken))) {
//
//				UserToken userToken2 = userToken.get();
//
//				userToken2.setActive(false);
//				userToken2.setLastAccessedAt(Timestamp.valueOf(LocalDateTime.now()));
//
//				userTokenRepository.save(userToken2);
//
//				String userAgent = request.getHeader("User-Agent");
//				UserAgent userAgent2 = UserAgent.parseUserAgentString(userAgent);
//				UserToken userTokenNew = new UserToken();
//
//				userTokenNew.setUserId(userId);
//				userTokenNew.setJwtToken(refreshedHashedToken);
//				userTokenNew.setIssuedAt(Timestamp.valueOf(LocalDateTime.now()));
//				userTokenNew.setExpiredAt(null);
//				userTokenNew.setLastAccessedAt(Timestamp.valueOf(LocalDateTime.now()));
//				userTokenNew.setActive(true);
//				userTokenNew.setDeviceInfo("Browser :" + userAgent2.getBrowser().getName() + " OperatingSystem : "
//						+ userAgent2.getOperatingSystem().getName());
//				userTokenNew.setLogoutAt(Timestamp.valueOf(LocalDateTime.now()));
//
//				userTokenRepository.save(userTokenNew);
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}

	public Map<String, Object> getMapFromIoJsonWebTokenClaims(DefaultClaims claims) {

		logger.debug("Converting claims to map");

		Map<String, Object> expectedMap = new HashMap<String, Object>();

		for (Map.Entry<String, Object> entry : claims.entrySet()) {

			expectedMap.put(entry.getKey(), entry.getValue());
		}

		logger.debug("Claims converted to map: {}", expectedMap);

		return expectedMap;
	}
}
