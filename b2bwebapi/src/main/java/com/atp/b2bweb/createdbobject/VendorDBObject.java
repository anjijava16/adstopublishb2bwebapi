package com.atp.b2bweb.createdbobject;

import java.util.Date;

import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class VendorDBObject {

	public static DBObject vendorRegisterDBObject(JSONObject reqObject) {
		BasicDBObject basicDBObject = new BasicDBObject();
		try {
			System.out.println("reqObject    "+reqObject);
			for(int i = 0; i < reqObject.length(); i++){
				String key = reqObject.names().get(i).toString();
				basicDBObject.append(key, reqObject.get(key));
			}  
			basicDBObject.append("name","username");
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return basicDBObject;
	}
	
	
	public static DBObject createUpdateDBObject(JSONObject reqObject) {
		System.out.println("1111111111111111111");
		BasicDBObject basicDBObject = new BasicDBObject();	
		try {
			for(int i = 0; i < reqObject.length(); i++){
				String key = reqObject.names().get(i).toString();
				if(!key.equalsIgnoreCase("_id") && !key.equalsIgnoreCase("updatedon")){
					basicDBObject.append(key, reqObject.get(key));
				}
			}   
			if(basicDBObject.get("name").toString() == null || basicDBObject.get("name").toString() == "undefined" )
			basicDBObject.append(CommonConstants.NAME,"username");
			basicDBObject.append(CommonConstants.UPDATEDON, new Date());
			basicDBObject.append(CommonConstants.PASSWORD, "123123123");
			return basicDBObject;
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return basicDBObject;
	}
	
	public static DBObject createVendorDetailDBObject(JSONObject reqObject) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		try {	
			docBuilder.append(CommonConstants.VENDORID, reqObject.get(CommonConstants.VENDORID));
			docBuilder.append(CommonConstants.DISPLAYNAME, reqObject.get(CommonConstants.DISPLAYNAME));
			docBuilder.append(CommonConstants.BUISENESSDESC, reqObject.get(CommonConstants.BUISENESSDESC));
			docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(CommonConstants.UPDATEDBY));
			docBuilder.append(CommonConstants.UPDATEDON, new Date());
		
		} catch(Exception e){
			System.out.println(e);
		}
		return docBuilder.get();
	}
	
	public static DBObject createVendorBankDetailDBObject(JSONObject reqObject) {
		BasicDBObject basicDBObject = new BasicDBObject();	
		try {
			for(int i = 0; i < reqObject.length(); i++){
				String key = reqObject.names().get(i).toString();
				String aaaa = reqObject.get(key).toString();
				if(!key.equalsIgnoreCase("_id")){
					if(aaaa.contains(":")){
						BasicDBObject attObject = new BasicDBObject();
						JSONObject attJSON = (JSONObject) reqObject.get(key);
						for(int j = 0; j < attJSON.length(); j++){
							attObject.append(attJSON.names().get(j).toString(), attJSON.get(attJSON.names().get(j).toString()));
						}
						basicDBObject.append(key, attObject);
					 }else{
						basicDBObject.append(key, reqObject.get(key));
					 }
				}
			}  
			
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return basicDBObject;
	}
	
	
	public static DBObject createVendorBusinessDetailDBObject(JSONObject reqObject) {
		BasicDBObject basicDBObject = new BasicDBObject();	
		try {
			for(int i = 0; i < reqObject.length(); i++){
				String key = reqObject.names().get(i).toString();
				String aaaa = reqObject.get(key).toString();
				if(!key.equalsIgnoreCase("_id")){
					if(aaaa.contains(":")){
						BasicDBObject attObject = new BasicDBObject();
						JSONObject attJSON = (JSONObject) reqObject.get(key);
						for(int j = 0; j < attJSON.length(); j++){
							attObject.append(attJSON.names().get(j).toString(), attJSON.get(attJSON.names().get(j).toString()));
						}
						basicDBObject.append(key, attObject);
					 }else{
						basicDBObject.append(key, reqObject.get(key));
					 }
				}
			}  
			System.out.println("basicDBObject "+basicDBObject);
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return basicDBObject;
	}
	
}
