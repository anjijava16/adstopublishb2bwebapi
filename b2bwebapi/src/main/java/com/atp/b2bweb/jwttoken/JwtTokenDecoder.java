package com.atp.b2bweb.jwttoken;

import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.*;

public class JwtTokenDecoder {
	//Sample method to validate and read the JWT
	public void parseJWT(String jwt) {
	//This line will throw an exception if it is not a signed JWS (as expected)
	Claims claims = Jwts.parser()         
	   .setSigningKey(DatatypeConverter.parseBase64Binary("chethan"))
	   .parseClaimsJws(jwt).getBody();
	System.out.println("claims  "+claims);
	System.out.println("ID: " + claims.getId());
	System.out.println("Subject: " + claims.getSubject());
	System.out.println("Issuer: " + claims.getIssuer());
	System.out.println("Expiration: " + claims.getExpiration());
	}

}
