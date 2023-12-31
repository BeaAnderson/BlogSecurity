package com.fdmgroup.springauth.service;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.fdmgroup.springauth.model.ApplicationUser;
import com.fdmgroup.springauth.repository.UserRepository;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;

@Service
public class TokenService {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private JwtEncoder jwtEncoder;
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	public String generateJWT(Authentication auth){
		
		//create string of user id
		ApplicationUser user = userRepo.findByUsername(auth.getName()).get();
		
		int id = user.getUserId();
		
		String idToString = Integer.toString(id);
		
		Instant now = Instant.now();
		
		String scope = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));
		
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.subject(idToString)
				.claim("roles", scope)
				.build();
		
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	
}
