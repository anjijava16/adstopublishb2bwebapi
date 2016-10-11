package com.atp.b2bweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.createdbobject.MongoToVendorDo;
import com.atp.b2bweb.db.vendorDetailsDB;
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
		/* try { 
			 DB db = (new MongoClient("localhost", 27017)).getDB("admin");
			//DB db = mongo.getDB("admin");
			
			char[] password = new char[] {'a', 'd', 'm', 'i', 'n'};
			System.out.println(db.authenticate("prasad",password));
			//if (auth) {
				this.col = db.getCollection(TableCommonConstant.VENDOR_USER);
			//}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
         // Now connect to your databases
*/         
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDOR_USER);
		
	}
	
	public  DBObject vendorRegister(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	
	
	public  DBCursor getvendorDetails(JSONObject requestObj){
		DBCursor result = null;
		try {
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
			criteria.add(new BasicDBObject(CommonConstants.EMAIL, new BasicDBObject(TableCommonConstant.EQUALS, requestObj.get(vendorDetailsDB.EMAIL).toString())));
			criteria.add(new BasicDBObject(CommonConstants.MOBILE, new BasicDBObject(TableCommonConstant.EQUALS, requestObj.get(vendorDetailsDB.PHONENUMBER).toString()))); 
			DBCursor dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria));

			if(dbCursor.size() > 0)	result = dbCursor;
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return result;
	}
	
	
	
	/*public  VendorUserDO persist(VendorUserDO vendorUserDO){
		try {
			DBObject doc = VendorDBObject.createRegisterDBObject(vendorUserDO);
			col.insert(doc);
		} catch (Exception e) {
		} 
		return vendorUserDO;
	}*/
	
	
	
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
	
	/*public  VendorUserDO vendorUpdate(VendorUserDO vendorUserDO){
		try {
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorUserDO.getId())).get();
			col.update(query, VendorDBObject.createRegisterDBObject(vendorUserDO));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return vendorUserDO;
	}*/
	
	public  String vendorDelete(VendorUserDO vendorUserDO){
		try {
			
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(vendorUserDO.getId())).get();
			col.remove(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		} 
		return "";
	}
	
	
	public  List<VendorUserDO> retriveUserByEmailOrMobile(String email, String mobile){
		List<VendorUserDO> data = null;
		try {
				data = new ArrayList<VendorUserDO>();
				List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
				criteria.add(new BasicDBObject(CommonConstants.EMAIL, new BasicDBObject(TableCommonConstant.EQUALS, email)));
				criteria.add(new BasicDBObject(CommonConstants.PASSWORD, new BasicDBObject(TableCommonConstant.EQUALS, mobile))); 
				DBCursor dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria));

				while(dbCursor.hasNext())	{
					VendorUserDO vendorUserDO = MongoToVendorDo.jsonToVendoruserDO(dbCursor.next());
							data.add(vendorUserDO);
				}
				
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return data;
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