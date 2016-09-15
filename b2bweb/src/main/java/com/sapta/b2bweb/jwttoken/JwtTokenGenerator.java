package com.sapta.b2bweb.jwttoken;
import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import com.sapta.b2bweb.domainobject.VendorUserDO;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtTokenGenerator {
	
	public String createJWT(VendorUserDO vendorUserDO, HttpServletResponse response) {
    	
		
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		 
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		 
		//We will sign our JWT with our ApiKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("chethan");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		 
		 //Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(String.valueOf(vendorUserDO.getId()))
		                                .setIssuedAt(now)
		                                .setSubject(vendorUserDO.getUsername())
		                                .setIssuer(vendorUserDO.getEmail())
		                                .signWith(signatureAlgorithm, signingKey);
		 
		 //if it has been specified, let's add the expiration
		int ttlMillis = 9000000;
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		System.out.println("builder.compact()  "+builder.compact());
		 //Builds the JWT and serializes it to a compact, URL-safe string
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600000");
        response.setHeader("Access-Control-Allow-Headers", "X-requested-with, Content-Type");
        response.setHeader("Access-Control-Expose-Headers", "token");
		String token = builder.compact();
		response.setHeader("token", token);
		
		return token;
	}
}
