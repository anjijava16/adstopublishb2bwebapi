package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorDetailDAO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorDetailsService {
	
	public DBObject addvendorDetails(DBObject doc, MongoClient mongo){
		return new VendorDetailDAO(mongo).addVendorDetails(doc);
	}
	
	public DBObject updatevendorDetails(DBObject doc, String id,  MongoClient mongo){	
		return new VendorDetailDAO(mongo).updateVendorDetails(doc, id);
	}
	
	public DBObject retriveByVendorID(String vendorId, MongoClient mongo){	
		return new VendorDetailDAO(mongo).retriveByVendorID(vendorId);
	}

}
