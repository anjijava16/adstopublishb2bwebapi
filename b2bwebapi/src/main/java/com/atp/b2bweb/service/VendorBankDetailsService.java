package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorBankDetailsDAO;
import com.atp.b2bweb.domainobject.VendorBankDetailDO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBankDetailsService {
	
	public VendorBankDetailDO addBankDetails(VendorBankDetailDO vendorBankDetailDO, MongoClient mongo){
		return new VendorBankDetailsDAO(mongo).addBankDetails(vendorBankDetailDO);
	}
	
	public VendorBankDetailDO updateBankDetails(VendorBankDetailDO vendorBankDetailDO, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).updateBankDetails(vendorBankDetailDO);
	}
	
	public DBObject retriveByVendorID(String vendorId, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).retriveByVendorID(vendorId);
	}
}
