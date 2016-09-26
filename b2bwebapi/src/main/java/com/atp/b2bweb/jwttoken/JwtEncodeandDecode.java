package com.atp.b2bweb.jwttoken;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;

public class JwtEncodeandDecode {
	
	final String issuer = "https://localhost:8080/";
	final String secret = "SaptaSecret";

	final long iat = System.currentTimeMillis() / 1000l; // issued at claim 
	final long exp = iat + 60L; // expires claim. In this case the token expires in 60 seconds

	
	HashMap<String, Object> hasObj = null; 
	String jwt = null;
	
	
	public String encodeMethod(){
		JWTSigner signer = new JWTSigner(secret);
		
		hasObj = new HashMap<String, Object>();
		hasObj.put("iss", issuer);
		hasObj.put("exp", exp);
		hasObj.put("iat", iat);
		
		jwt = signer.sign(hasObj);
		System.out.println("jwt  "+jwt);	
		
		return jwt;
	}	

	public  void decodeMethod(String jwt) throws InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, SignatureException, IOException, JWTVerifyException{
	  final JWTVerifier jwtVerifier = new JWTVerifier("chethan");
	  final Map<String,Object> claims = jwtVerifier.verify(jwt);				
	}	

}
