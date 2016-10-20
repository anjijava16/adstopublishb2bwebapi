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
				if(!key.equalsIgnoreCase("uniqueKey") && !key.equalsIgnoreCase("_id") && !key.equalsIgnoreCase("updatedon")){
					basicDBObject.append(key, reqObject.get(key));
				}
			}  
			basicDBObject.append(CommonConstants.UPDATEDON, new Date());
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
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		try {							
		//docBuilder.append("_id", vendorUserDO.getId());
			docBuilder.append(CommonConstants.VENDORID, reqObject.get(CommonConstants.VENDORID));
			docBuilder.append(CommonConstants.ACCOUNTHOLDERNAME, reqObject.get(CommonConstants.ACCOUNTHOLDERNAME));
			docBuilder.append(CommonConstants.ACCOUNTNUMBER, reqObject.get(CommonConstants.ACCOUNTNUMBER));
			docBuilder.append(CommonConstants.IFSC, reqObject.get(CommonConstants.IFSC));
			docBuilder.append(CommonConstants.BANKNAME, reqObject.get(CommonConstants.BANKNAME));
			docBuilder.append(CommonConstants.STATE, reqObject.get(CommonConstants.STATE));
			docBuilder.append(CommonConstants.CITY, reqObject.get(CommonConstants.CITY));
			docBuilder.append(CommonConstants.BRANCH, reqObject.get(CommonConstants.BRANCH));
			docBuilder.append(CommonConstants.ADDRESSPROOFTYPE, reqObject.get(CommonConstants.ADDRESSPROOFTYPE));
			docBuilder.append(CommonConstants.ADDRESSPROOFURL, reqObject.get(CommonConstants.ADDRESSPROOFURL));
			docBuilder.append(CommonConstants.CANCELLEDCHEQUEURL, reqObject.get(CommonConstants.CANCELLEDCHEQUEURL));
			
			docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(CommonConstants.UPDATEDBY));
			docBuilder.append(CommonConstants.UPDATEDON, new Date());
		}catch(Exception e){
			
		}
		return docBuilder.get();
	}
	
	
	public static DBObject createVendorBusinessDetailDBObject(JSONObject reqObject) {
		BasicDBObject basicDBObject = new BasicDBObject();	
		try {
			for(int i = 0; i < reqObject.length(); i++){
				String key = reqObject.names().get(i).toString();
				String aaaa = reqObject.get(key).toString();
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
			return basicDBObject;
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return basicDBObject;
	}
	
}
