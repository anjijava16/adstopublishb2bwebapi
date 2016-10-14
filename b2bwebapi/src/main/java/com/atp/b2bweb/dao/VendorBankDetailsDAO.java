package com.atp.b2bweb.dao;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBankDetailsDAO {
	
	private DBCollection col;
	
	public VendorBankDetailsDAO(MongoClient  mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_BANK_DETAIL);
	}
	
	public  DBObject addBankDetails(DBObject doc){
		try {
			//DBObject doc = VendorDBObject.createVendorBankDetailDBObject(vendorBankDetailDO);
			col.insert(doc);
		} catch (Exception e) {	}
		return doc;
	}
	
	public  DBObject updateBankDetails(DBObject doc, String  id){
		try {
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
	
	public DBObject retriveByVendorID(String vendorid){
		DBObject query = null;
		DBObject data = null;
	    try {
			query = BasicDBObjectBuilder.start().append(CommonConstants.VENDORID,vendorid).get();
			data = col.findOne(query);
	    } catch (Exception e) {
	    	System.out.println("in DAO   "+e);
	    }
		return data;
	}
	
}