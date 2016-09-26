package com.atp.b2bweb.util;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.domainobject.VendorBankDetailDO;

public class VendorBankDetailsUtil {
	

	public static JSONObject getBankDetailList(List<VendorBankDetailDO> vendorBankDetailList){
		JSONObject responseJSON = new JSONObject();
		JSONObject resultJSON = new JSONObject();
		try {
			resultJSON.put(CommonConstants.SUCCESS_FLAG, CommonConstants.TRUE);
			resultJSON.put(CommonConstants.ERRORS, "");
			
			JSONArray resultJSONArray = new JSONArray();
			for (VendorBankDetailDO vendorBankDetailDO : vendorBankDetailList) {
				resultJSONArray.put(getBankDetailObject(vendorBankDetailDO));
			}
			resultJSON.put(CommonConstants.RESULTS, resultJSONArray);
			
			responseJSON.put(CommonConstants.RESPONSE, resultJSON);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return responseJSON;
	}
	
	private static JSONObject getBankDetailObject(VendorBankDetailDO vendorBankDetailDO) throws JSONException {
		JSONObject result = new JSONObject();
		
		result.put(CommonConstants.ID, String.valueOf(vendorBankDetailDO.getId()));
		result.put(CommonConstants.VENDORID, String.valueOf(vendorBankDetailDO.getVendorid()));
		result.put(CommonConstants.ACCOUNTHOLDERNAME, String.valueOf(vendorBankDetailDO.getAccountholdername()));
		result.put(CommonConstants.ACCOUNTNUMBER, String.valueOf(vendorBankDetailDO.getAccountnumber()));
		result.put(CommonConstants.BANKNAME, String.valueOf(vendorBankDetailDO.getBankname()));
		result.put(CommonConstants.BRANCH, String.valueOf(vendorBankDetailDO.getBranch()));
		result.put(CommonConstants.CITY, String.valueOf(vendorBankDetailDO.getCity()));
		result.put(CommonConstants.IFSC, String.valueOf(vendorBankDetailDO.getIfsc()));
		result.put(CommonConstants.STATE, String.valueOf(vendorBankDetailDO.getState()));
		result.put(CommonConstants.ADDRESSPROOFTYPE, String.valueOf(vendorBankDetailDO.getAddressprooftype()));
		result.put(CommonConstants.ADDRESSPROOFURL, String.valueOf(vendorBankDetailDO.getAddressproofurl()));
		result.put(CommonConstants.CANCELLEDCHEQUEURL, String.valueOf(vendorBankDetailDO.getCancelledchequeurl()));
		result.put(CommonConstants.UPDATEDBY, String.valueOf(vendorBankDetailDO.getUpdatedby()));
		result.put(CommonConstants.UPDATEDON, String.valueOf(vendorBankDetailDO.getUpdatedon()));
		
		return result;
	}

}
