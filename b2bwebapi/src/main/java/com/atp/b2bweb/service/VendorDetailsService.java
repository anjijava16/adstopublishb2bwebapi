package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorDetailDAO;
import com.atp.b2bweb.domainobject.VendorDetailDO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorDetailsService {
	
	public VendorDetailDO addvendorDetails(VendorDetailDO vendorDetailDO, MongoClient mongo){
		return new VendorDetailDAO(mongo).addVendorDetails(vendorDetailDO);
	}
	
	public VendorDetailDO updatevendorDetails(VendorDetailDO vendorDetailDO, MongoClient mongo){	
		return new VendorDetailDAO(mongo).updateVendorDetails(vendorDetailDO);
	}
	
	public DBObject retriveByVendorID(String vendorId, MongoClient mongo){	
		return new VendorDetailDAO(mongo).retriveByVendorID(vendorId);
	}

}
