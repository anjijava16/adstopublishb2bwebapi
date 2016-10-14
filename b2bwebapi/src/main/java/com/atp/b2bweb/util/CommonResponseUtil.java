package com.atp.b2bweb.util;

import java.util.List;

import org.json.simple.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class CommonResponseUtil {

		@SuppressWarnings("unchecked")
		public static JSONObject getDetailList(DBObject dbObject){
			JSONObject responseJSON = new JSONObject();
			JSONObject resultJSON = new JSONObject();
			try {
				resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
				resultJSON.put(CommonConstants.ERRORS, "");
				resultJSON.put(CommonConstants.RESULTS, dbObject);
				
				responseJSON.put(CommonConstants.RESPONSE, resultJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseJSON;
		}
		
		@SuppressWarnings("unchecked")
		public static JSONObject getDetailLists(List<DBObject> List){
			JSONObject responseJSON = new JSONObject();
			JSONObject resultJSON = new JSONObject();
			try {
				resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
				resultJSON.put(CommonConstants.ERRORS, "");
				resultJSON.put(CommonConstants.RESULTS, List.toString());
				
				responseJSON.put(CommonConstants.RESPONSE, resultJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseJSON;
		}
		
		@SuppressWarnings("unchecked")
		public static JSONObject getAllDetailLists(List<DBObject> List, int count){
			JSONObject responseJSON = new JSONObject();
			JSONObject resultJSON = new JSONObject();
			try {
				resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
				resultJSON.put(CommonConstants.ERRORS, "");
				resultJSON.put(CommonConstants.RESULTS, List.toString());
				resultJSON.put(CommonConstants.COUNT, count);
				
				responseJSON.put(CommonConstants.RESPONSE, resultJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseJSON;
		}
		
		
		@SuppressWarnings({ "unchecked"})
		public static JSONObject getResponseObject(String res){
			BasicDBObject basicDBObject = new BasicDBObject();
			JSONObject responseJSON = new JSONObject();
			JSONObject resultJSON = new JSONObject();
			try {   
				basicDBObject.append("MSG", res);
				resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
				resultJSON.put(CommonConstants.ERRORS, "");
				resultJSON.put(CommonConstants.RESULTS, basicDBObject);
				responseJSON.put(CommonConstants.RESPONSE, resultJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseJSON;
		}
		
		@SuppressWarnings({ "unchecked"})
		public static JSONObject getErrorResponseObject(String res){
			BasicDBObject basicDBObject = new BasicDBObject();
			JSONObject responseJSON = new JSONObject();
			JSONObject resultJSON = new JSONObject();
			try {   
				basicDBObject.append("MSG", res);
				resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.FALSE);
				resultJSON.put(CommonConstants.ERRORS, "");
				resultJSON.put(CommonConstants.RESULTS, basicDBObject);
				responseJSON.put(CommonConstants.RESPONSE, resultJSON);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return responseJSON;
		}
}
