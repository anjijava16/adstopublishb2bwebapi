package com.atp.b2bweb.util;

import java.util.List;

import org.json.simple.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class MzgazineUtil {

		@SuppressWarnings("unchecked")
		public static JSONObject getMzgazineDetailList(DBCursor dbCursor){
			JSONObject responseJSON = new JSONObject();
			JSONObject resultJSON = new JSONObject();
			try {
				resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
				resultJSON.put(CommonConstants.ERRORS, "");
				resultJSON.put(CommonConstants.RESULTS, dbCursor);
				
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
}
