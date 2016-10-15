package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.VendorUserDAO;
import com.atp.b2bweb.domainobject.VendorUserDO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorUserService {
	
	public DBObject vendorRegister(DBObject doc, MongoClient mongo){
		return new VendorUserDAO(mongo).vendorRegister(doc);
	} 
	
	public DBCursor getvendorDetails(String email,String phone, MongoClient mongo) {	
		return new VendorUserDAO(mongo).getvendorDetails(email, phone);
	}
	
	public boolean vendorFind(String email,String mobile, MongoClient mongo) {	
		return new VendorUserDAO(mongo).vendorFind(email,mobile);
	}
	
	public DBObject vendorLogin(String email,String password, MongoClient mongo) {	
		return new VendorUserDAO(mongo).vendorLogin(email,password);
	}
	
	public DBObject vendorUpdate(DBObject doc,String id, MongoClient mongo){	
		return new VendorUserDAO(mongo).vendorUpdate(doc, id);
	}
	
	public String vendorDelete(VendorUserDO vendorUserDO, MongoClient mongo){	
		return new VendorUserDAO(mongo).vendorDelete(vendorUserDO);
	}
	
	public DBObject retriveByID(String vendorID, MongoClient mongo){	
		return new VendorUserDAO(mongo).retriveByID(vendorID);
	}
	
    public DBCursor retriveUserByEmailOrMobile(String email, String phone, MongoClient mongo){	
		return new VendorUserDAO(mongo).retriveUserByEmailOrMobile(email, phone);
	}
    
    public DBObject getvendorByUUID(String token, MongoClient mongo) {	
		return new VendorUserDAO(mongo).getvendorByUUID(token);
	}

}
