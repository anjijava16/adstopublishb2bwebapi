package com.atp.b2bweb.dao;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.createdbobject.MongoDBObject;
import com.atp.b2bweb.domainobject.VendorBusinessDetailDO;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBusinessDetailsDAO {
	
	private DBCollection col;
	
	public VendorBusinessDetailsDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_BUINESS_DETAIL);
	}
	
	public  VendorBusinessDetailDO addBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO){
		try {
			//WriteResult result = 
			DBObject doc = MongoDBObject.createVendorBusinessDetailDBObject(vendorBusinessDetailDO);
			col.insert(doc);
		} catch (Exception e) {	}
		return vendorBusinessDetailDO;
	}
	
	public  VendorBusinessDetailDO updateBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO){
		try {
			DBObject doc = MongoDBObject.createVendorBusinessDetailDBObject(vendorBusinessDetailDO);
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorBusinessDetailDO.getId())).get();
			System.out.println("business detail   -----------"+query);
			 col.update(query, doc);
		} catch (Exception e) {	}
		return vendorBusinessDetailDO;
	}
	
	public  DBObject  retriveByVendorID(String vendorid){
		DBObject query = null;
		DBObject data = null;
	    try {
			query = BasicDBObjectBuilder.start().append(CommonConstants.VENDORID,vendorid).get();
			data = col.findOne(query);
			
			System.out.println("vendor bank detail dao    "+query);
			System.out.println("vendor bank detail dao    "+data);
	    } catch (Exception e) {
	    	System.out.println("in DAO   "+e);
	    }
		return data;
	}
	
}