package com.sapta.b2bweb.util;

import org.apache.commons.codec.binary.Base64;

public class CommonUtil {

	public static String encode(String requestParameter){
		// Encoding data
		String requestObj = null;
		if(requestParameter != null && requestParameter.length() > 0){
			byte[] valueEncoded= Base64.encodeBase64(requestParameter.getBytes() );
			requestObj = new String(valueEncoded);
		}
		
		return requestObj != null ? requestObj : "";
	}
	
	public static String decode(String requestParameter){
		// Decoding data
		String requestObj = null;
		if(requestParameter != null && requestParameter.length() > 0){
			byte[] valueDecoded= Base64.decodeBase64(requestParameter.getBytes());
			requestObj = new String(valueDecoded);
		}
		
		return requestObj != null ? requestObj : "";
	}
	
}
