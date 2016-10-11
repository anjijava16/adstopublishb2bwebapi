package com.atp.b2bweb.dao;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.domainobject.VendorBankDetailDO;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class VendorBankDetailsDAO {
	
	private DBCollection col;
	
	public VendorBankDetailsDAO(MongoClient  mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_BANK_DETAIL);
	}
	
	public  VendorBankDetailDO addBankDetails(VendorBankDetailDO vendorBankDetailDO){
		try {
			DBObject doc = VendorDBObject.createVendorBankDetailDBObject(vendorBankDetailDO);
			col.insert(doc);
		} catch (Exception e) {	}
		return vendorBankDetailDO;
	}
	
	public  VendorBankDetailDO updateBankDetails(VendorBankDetailDO vendorBankDetailDO){
		try {
			DBObject doc = VendorDBObject.createVendorBankDetailDBObject(vendorBankDetailDO);
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorBankDetailDO.getId())).get();
			System.out.println("bank detail   -----------"+query);
			 col.update(query, doc);
		} catch (Exception e) {	}
		return vendorBankDetailDO;
	}
	
	public DBObject retriveByVendorID(String vendorid){
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