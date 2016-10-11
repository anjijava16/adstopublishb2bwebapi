package com.atp.b2bweb.service;

import java.util.List;

import org.json.JSONObject;

import com.atp.b2bweb.dao.VendorUserDAO;
import com.atp.b2bweb.domainobject.VendorUserDO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorUserService {
	
	/*public VendorUserDO vendorRegister(VendorUserDO vendoruserDO, MongoClient mongo){
		return new VendorUserDAO(mongo).persist(vendoruserDO);
	}*/
	
	public DBObject vendorRegister(DBObject doc, MongoClient mongo){
		return new VendorUserDAO(mongo).vendorRegister(doc);
	} 
	
	public DBCursor getvendorDetails(JSONObject requestObj, MongoClient mongo) {	
		return new VendorUserDAO(mongo).getvendorDetails(requestObj);
	}
	
	public boolean vendorFind(String email,String mobile, MongoClient mongo) {	
		return new VendorUserDAO(mongo).vendorFind(email,mobile);
	}
	
	public DBObject vendorLogin(String email,String password, MongoClient mongo) {	
		return new VendorUserDAO(mongo).vendorLogin(email,password);
	}
	
	/*public VendorUserDO vendorUpdate(VendorUserDO vendorUserDO, MongoClient mongo){	
		return new VendorUserDAO(mongo).vendorUpdate(vendorUserDO);
	}*/
	
	public String vendorDelete(VendorUserDO vendorUserDO, MongoClient mongo){	
		return new VendorUserDAO(mongo).vendorDelete(vendorUserDO);
	}
	
	public DBObject retriveByID(String vendorID, MongoClient mongo){	
		return new VendorUserDAO(mongo).retriveByID(vendorID);
	}
	
    public List<VendorUserDO> retriveUserByEmailOrMobile(String email,String phone, MongoClient mongo){	
		return new VendorUserDAO().retriveUserByEmailOrMobile(email,phone);
	}


}
