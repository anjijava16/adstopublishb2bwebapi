package com.atp.b2bweb.createdbobject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.domainobject.VendorUserDO;
import com.mongodb.DBObject;

public class MongoToVendorDo {

	public static VendorUserDO jsonToVendoruserDO(DBObject dbObject){
		
		String activestatus = (String) dbObject.get(CommonConstants.ACCOUNTSTATUS);
		
		VendorUserDO vendorUserDO  = new VendorUserDO();
		vendorUserDO.setId(dbObject.get(CommonConstants._ID).toString());
		vendorUserDO.setUsername((String) dbObject.get(CommonConstants.USERNAME));
		vendorUserDO.setEmail((String) dbObject.get(CommonConstants.EMAIL));
		vendorUserDO.setAccountstatus(activestatus.charAt(0));
		vendorUserDO.setDisplayname((String) dbObject.get(CommonConstants.DISPLAYNAME));
		
		return vendorUserDO;
	}
	
	
	
}
