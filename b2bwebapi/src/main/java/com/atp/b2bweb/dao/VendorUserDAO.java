package com.atp.b2bweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.domainobject.VendorUserDO;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

public class VendorUserDAO {
	
	private DBCollection col;
	
	public VendorUserDAO(){ }
	
	public VendorUserDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_USER);
	}
	
	public  DBObject vendorRegister(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	
	
	public  DBCursor getvendorDetails(String email, String phone){
		DBCursor dbCursor = null;
		try {
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
			criteria.add(new BasicDBObject(CommonConstants.EMAIL, new BasicDBObject(TableCommonConstant.EQUALS, email)));
			criteria.add(new BasicDBObject(CommonConstants.MOBILE, new BasicDBObject(TableCommonConstant.EQUALS, phone))); 
			dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria));

		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return dbCursor;
	}

	
	
	public  boolean vendorFind(String email, String mobile){
		boolean result = false;
		try {
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
			criteria.add(new BasicDBObject(CommonConstants.EMAIL, new BasicDBObject(TableCommonConstant.EQUALS, email)));
			criteria.add(new BasicDBObject(CommonConstants.MOBILE, new BasicDBObject(TableCommonConstant.EQUALS, mobile))); 
			DBCursor dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria));

			if(dbCursor.size() > 0)	result = true;
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return result;
	}
	
	public  DBObject vendorLogin(String user, String password){
			DBObject query = null;
			DBObject data = null;
		try {
				query = BasicDBObjectBuilder.start().append(CommonConstants.EMAIL, user).append(CommonConstants.PASSWORD, password).get();
				data = col.findOne(query);
				
				if(data == null){
					 query = BasicDBObjectBuilder.start().append(CommonConstants.MOBILE, user).append(CommonConstants.PASSWORD , password).get();
					 data = col.findOne(query);
				}
		} catch (MongoException e) {
			System.out.println("in DAO   "+e);
			// TODO: handle exceptions
		}
		return data;
	}
	
	public  DBObject vendorUpdate(DBObject doc, String id){
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
			System.out.println("result "+doc);
		} catch (Exception e) {
		}
		return doc;
	}
	            
	
	public  String vendorDelete(VendorUserDO vendorUserDO){
		try {
			
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorUserDO.getId())).get();
			col.remove(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return "";
	}
	
	
	public  DBCursor retriveUserByEmailOrMobile(String email, String mobile){
		DBCursor dbCursor = null;
		try {
			
				List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
				criteria.add(new BasicDBObject(CommonConstants.EMAIL, new BasicDBObject(TableCommonConstant.EQUALS, email)));
				criteria.add(new BasicDBObject(CommonConstants.MOBILE, new BasicDBObject(TableCommonConstant.EQUALS, mobile))); 
				System.out.println("criteria  "+criteria);
				dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria));

		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return dbCursor;
	}
	
	
	public  DBObject retriveByID(String vendorID){
		DBObject query = null;
		DBObject data = null;
		try {
			query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorID)).get();
			System.out.println(query);
			data = col.findOne(query);
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return data;
	}
	
}