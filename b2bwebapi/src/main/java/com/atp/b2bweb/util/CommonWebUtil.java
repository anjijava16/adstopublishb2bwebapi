package com.atp.b2bweb.util;

import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.DBObject;

public final class CommonWebUtil {

	private CommonWebUtil() {

	}

	public static JSONObject buildSuccessResponse() {
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		JSONObject dataJSON = new JSONObject();
		try {
			resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
			resultJSON.put(CommonConstants.ERRORS, "");
			resultJSON.put(CommonConstants.RESULTS, dataJSON);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJSON;
	}

	public static JSONObject buildErrorResponse(String errorMsg) {
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		JSONObject dataJSON = new JSONObject();
		try {
			resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.FALSE);
			resultJSON.put(CommonConstants.ERRORS, errorMsg);
			resultJSON.put(CommonConstants.RESULTS, dataJSON);
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJSON;
	}
	
	public static JSONObject buildSuccessResponseMsg(String msg) {
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		JSONObject dataJSON = new JSONObject();
		try {
			resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
			resultJSON.put(CommonConstants.ERRORS, "");
			//dataJSON.put(CommonConstants.SUCCESS_MSG, msg);
			resultJSON.put(CommonConstants.RESULTS, msg);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJSON;
	}
	
	public static JSONObject DBobjectTOJson(DBObject dbobject) {
		JSONObject resultJSON = new JSONObject();
		try {
			Set<String> ads = dbobject.keySet();
			for (String string : ads) {
				resultJSON.put(string, dbobject.get(string));
			}
			
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultJSON;
	}
		
}
