package com.atp.b2bweb.createdbobject;

import java.util.Date;

import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.db.vendorDetailsDB;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class VendorDBObject {

	public static DBObject vendorRegisterDBObject(JSONObject reqObject) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		try {
		/*docBuilder.append(CommonConstants.USERNAME, reqObject.get(vendorDetailsDB.NAME));*/
		docBuilder.append(CommonConstants.EMAIL, reqObject.get(vendorDetailsDB.EMAIL));
		
		docBuilder.append(CommonConstants.MOBILE, reqObject.get(vendorDetailsDB.PHONENUMBER));
		
		docBuilder.append(CommonConstants.TOKEN,  reqObject.get(vendorDetailsDB.REGISTERTOKEN));
		docBuilder.append(CommonConstants.STATUS, reqObject.get(vendorDetailsDB.STATUS));
		
		
		/*docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(vendorDetailsDB.UPDATEDBY));*/
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return docBuilder.get();
	}
	
	
	public static DBObject createRegisterDBObject(JSONObject reqObject) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		try {
		/*docBuilder.append(CommonConstants.USERNAME, reqObject.get(vendorDetailsDB.NAME));*/
		docBuilder.append(CommonConstants.EMAIL, reqObject.get(vendorDetailsDB.EMAIL));
		
		docBuilder.append(CommonConstants.MOBILE, reqObject.get(vendorDetailsDB.PHONENUMBER));
		
		
		docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(vendorDetailsDB.UPDATEDBY));
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return docBuilder.get();
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
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		//docBuilder.append("_id", vendorUserDO.getId());
		try {	
			docBuilder.append(CommonConstants.VENDORID, reqObject.get(CommonConstants.VENDORID));
			docBuilder.append(CommonConstants.BUSINESSNAME, reqObject.get(CommonConstants.BUSINESSNAME));
			docBuilder.append(CommonConstants.BUSSINESSTYPE, reqObject.get(CommonConstants.BUSSINESSTYPE));
			docBuilder.append(CommonConstants.PAN, reqObject.get(CommonConstants.PAN));
			docBuilder.append(CommonConstants.PANURL, reqObject.get(CommonConstants.PANURL));
			docBuilder.append(CommonConstants.TAN, reqObject.get(CommonConstants.TAN));
			docBuilder.append(CommonConstants.TANURL, reqObject.get(CommonConstants.TANURL));
			docBuilder.append(CommonConstants.TINVAT, reqObject.get(CommonConstants.TINVAT));
			docBuilder.append(CommonConstants.TINVATURL, reqObject.get(CommonConstants.TINVATURL));
			docBuilder.append(CommonConstants.BUSINESSPAN, reqObject.get(CommonConstants.BUSINESSPAN));
			docBuilder.append(CommonConstants.BUSINESSPANURL, reqObject.get(CommonConstants.BUSINESSPANURL));
			
			docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(CommonConstants.UPDATEDBY));
			docBuilder.append(CommonConstants.UPDATEDON, new Date());
		}catch(Exception e){
			
		}
		return docBuilder.get();
	}
	
}
