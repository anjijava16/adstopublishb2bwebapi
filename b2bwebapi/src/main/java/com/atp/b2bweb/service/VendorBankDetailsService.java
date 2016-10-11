package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorBankDetailsDAO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBankDetailsService {
	
	public DBObject addBankDetails(DBObject doc, MongoClient mongo){
		return new VendorBankDetailsDAO(mongo).addBankDetails(doc);
	}
	
	public DBObject updateBankDetails(DBObject doc, String id, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).updateBankDetails(doc, id);
	}
	
	public DBObject retriveByVendorID(String vendorId, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).retriveByVendorID(vendorId);
	}
}
