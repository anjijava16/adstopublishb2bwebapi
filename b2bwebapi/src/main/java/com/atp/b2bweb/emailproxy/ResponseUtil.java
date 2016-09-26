package com.atp.b2bweb.emailproxy;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public final class ResponseUtil {

	private ResponseUtil() {

	}

	public static String buildSuccessResponse() {
		
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		try {
			
			resultJSON.put(CommonConstants.STATUS, CommonConstants.TRUE);
			resultJSON.put(CommonConstants.RESULTS, CommonConstants.SUCCESS_MSG);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (Exception eException) {
			throw eException;
		}
		return responseJSON.toString();
	}

	
	public static String buildErrorResponse(String errorMsg) {
		
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		try {
			
			resultJSON.put(CommonConstants.STATUS, CommonConstants.FALSE);
			resultJSON.put(CommonConstants.RESULTS, errorMsg);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (Exception eException) {
			throw eException;
		}
		return responseJSON.toString();
	}
	
	public static String buildErrorResponse(String errorMsg, Exception exception) {
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		try {
			
			resultJSON.put(CommonConstants.STATUS, CommonConstants.FALSE);
			resultJSON.put(CommonConstants.RESULTS, errorMsg +"\\n"+ exception.getMessage());
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (Exception eException) {
			throw eException;
		}
		return responseJSON.toString();
	}
		
}
