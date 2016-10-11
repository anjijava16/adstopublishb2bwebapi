package com.atp.b2bweb.dao;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.domainobject.VendorDetailDO;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorDetailDAO {
	
	private DBCollection col;
	
	
	public VendorDetailDAO( MongoClient  mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_DETAIL);
	}
	
	public  VendorDetailDO addVendorDetails(VendorDetailDO vendorDetailDO){
		try {
			DBObject doc = VendorDBObject.createVendorDetailDBObject(vendorDetailDO);
			col.insert(doc);
		} catch (Exception e) {	}
		return vendorDetailDO;
	}
	
	public  VendorDetailDO updateVendorDetails(VendorDetailDO vendorDetailDO){
		try {
			DBObject doc = VendorDBObject.createVendorDetailDBObject(vendorDetailDO);
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorDetailDO.getId())).get();
			System.out.println(query);
			col.update(query, doc);
		} catch (Exception e) {	}
		return vendorDetailDO;
	}
	
	public DBObject retriveByVendorID(String vendorid){
		DBObject query = null;
		DBObject data = null;
	    try {
			query = BasicDBObjectBuilder.start().append(CommonConstants.VENDORID,vendorid).get();
			data = col.findOne(query);
			System.out.println("vendor detail dao    "+query);
			System.out.println("vendor detail dao    "+data);
	    } catch (Exception e) {
	    	System.out.println("in DAO   "+e);
	    }
		return data;
	}
	
}
