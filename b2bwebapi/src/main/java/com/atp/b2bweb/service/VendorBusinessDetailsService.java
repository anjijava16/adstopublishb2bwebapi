package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorBusinessDetailsDAO;
import com.atp.b2bweb.domainobject.VendorBusinessDetailDO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBusinessDetailsService {
	
	public VendorBusinessDetailDO addBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO,  MongoClient mongo){
		return new VendorBusinessDetailsDAO(mongo).addBusinessDetails(vendorBusinessDetailDO);
	}
	
	public VendorBusinessDetailDO updateBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO,  MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).updateBusinessDetails(vendorBusinessDetailDO);
	}
	
	public DBObject retriveByVendorID(String vendorId,  MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).retriveByVendorID(vendorId);
	}
}
