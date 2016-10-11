package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorBusinessDetailsDAO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBusinessDetailsService {
	
	public DBObject addBusinessDetails(DBObject doc,  MongoClient mongo){
		return new VendorBusinessDetailsDAO(mongo).addBusinessDetails(doc);
	}
	
	public DBObject updateBusinessDetails(DBObject doc, String id,  MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).updateBusinessDetails(doc, id);
	}
	
	public DBObject retriveByVendorID(String vendorId,  MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).retriveByVendorID(vendorId);
	}
}
