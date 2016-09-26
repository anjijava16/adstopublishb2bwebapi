package com.atp.b2bweb.util;

import org.json.simple.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.DBCursor;

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
}
