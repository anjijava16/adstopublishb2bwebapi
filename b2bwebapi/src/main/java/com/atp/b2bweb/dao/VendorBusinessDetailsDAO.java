package com.atp.b2bweb.dao;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBusinessDetailsDAO {
	
	private DBCollection col;
	
	public VendorBusinessDetailsDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_BUINESS_DETAIL);
	}
	
	public  DBObject addBusinessDetails(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {	}
		return doc;
	}
	
	public  DBObject updateBusinessDetails(DBObject doc, String id){
		try {
			//DBObject doc = VendorDBObject.createVendorBusinessDetailDBObject(vendorBusinessDetailDO);
			String[] idString = id.split(":");
			String x = null;
			if(idString.length > 1){
				 x = idString[1].substring(1, idString[1].length() - 2);
			}else{
				 x = idString[0];
			}
			DBObject query = new BasicDBObject("_id", new ObjectId(x));
			 col.update(query, doc);
		} catch (Exception e) {	}
		return doc;
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