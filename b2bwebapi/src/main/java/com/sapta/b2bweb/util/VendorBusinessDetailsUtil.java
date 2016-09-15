package com.sapta.b2bweb.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.domainobject.VendorBusinessDetailDO;

public class VendorBusinessDetailsUtil {
	

	public static JSONObject getBusinessDetailList(List<VendorBusinessDetailDO> vendorBusinessDetailList){
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		try {
			resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
			resultJSON.put(CommonConstants.ERRORS, "");
			
			JSONArray resultJSONArray = new JSONArray();
			for (VendorBusinessDetailDO vendorBusinessDetailDO : vendorBusinessDetailList) {
				resultJSONArray.put(getBusinessDetailObject(vendorBusinessDetailDO));
			}
			resultJSON.put(CommonConstants.RESULTS, resultJSONArray);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJSON;
	}
	
	private static JSONObject getBusinessDetailObject(VendorBusinessDetailDO vendorBusinessDetailDO) throws JSONException {
		JSONObject result = new JSONObject();
		
		result.put(CommonConstants.ID, String.valueOf(vendorBusinessDetailDO.getId()));
		result.put(CommonConstants.VENDORID, String.valueOf(vendorBusinessDetailDO.getVendorid()));
		result.put(CommonConstants.BUSINESSNAME, String.valueOf(vendorBusinessDetailDO.getBusinessname()));
		result.put(CommonConstants.BUSSINESSTYPE, String.valueOf(vendorBusinessDetailDO.getBusinesstype()));
		result.put(CommonConstants.PAN, String.valueOf(vendorBusinessDetailDO.getPan()));
		result.put(CommonConstants.PANURL, String.valueOf(vendorBusinessDetailDO.getPanurl()));
		result.put(CommonConstants.TINVAT, String.valueOf(vendorBusinessDetailDO.getTinvat()));
		result.put(CommonConstants.TINVATURL, String.valueOf(vendorBusinessDetailDO.getTinvaturl()));
		result.put(CommonConstants.SERVICETAX, String.valueOf(vendorBusinessDetailDO.getServicetax()));
		result.put(CommonConstants.SERVICETAXURL, String.valueOf(vendorBusinessDetailDO.getServicetaxurl()));
		result.put(CommonConstants.BUSINESSPAN, String.valueOf(vendorBusinessDetailDO.getBusinesspan()));
		result.put(CommonConstants.BUSINESSPANURL, String.valueOf(vendorBusinessDetailDO.getBusinesspanurl()));
		result.put(CommonConstants.UPDATEDBY, String.valueOf(vendorBusinessDetailDO.getUpdatedby()));
		result.put(CommonConstants.UPDATEDON, String.valueOf(vendorBusinessDetailDO.getUpdatedon()));
		
		return result;
	}

}
