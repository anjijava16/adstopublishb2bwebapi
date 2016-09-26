package com.atp.b2bweb.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.domainobject.VendorDetailDO;

public class VendorDetailsUtil {
	

	public static JSONObject getVendorDetailList(List<VendorDetailDO> VendorDetailList){
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		try {
			resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
			resultJSON.put(CommonConstants.ERRORS, "");
			
			JSONArray resultJSONArray = new JSONArray();
			for (VendorDetailDO vendorDetailDO : VendorDetailList) {
				resultJSONArray.put(getVendorDetailObject(vendorDetailDO));
			}
			resultJSON.put(CommonConstants.RESULTS, resultJSONArray);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJSON;
	}
	
	private static JSONObject getVendorDetailObject(VendorDetailDO vendorDetailDO) throws JSONException {
		JSONObject result = new JSONObject();
		
		result.put(CommonConstants.ID, String.valueOf(vendorDetailDO.getId()));
		result.put(CommonConstants.VENDORID, String.valueOf(vendorDetailDO.getVendorid()));
		result.put(CommonConstants.DISPLAYNAME, String.valueOf(vendorDetailDO.getDisplayname()));
		result.put(CommonConstants.BUISENESSDESC, String.valueOf(vendorDetailDO.getBusinessDesc()));
		result.put(CommonConstants.UPDATEDBY, String.valueOf(vendorDetailDO.getUpdatedby()));
		result.put(CommonConstants.UPDATEDON, String.valueOf(vendorDetailDO.getUpdatedon()));
		
		return result;
	}

}
