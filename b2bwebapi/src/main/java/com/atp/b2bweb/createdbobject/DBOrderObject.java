package com.atp.b2bweb.createdbobject;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.db.OrderDB;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class DBOrderObject {
	
	
	
	public static DBObject createOrderDBObject(JSONObject requestObj) {
		
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		try {

				for(int i = 0; i < requestObj.length(); i++){
	                JSONArray aaa = requestObj.names();
	                if(requestObj.get(aaa.get(i).toString()) != null){  
	                	if(aaa.get(i).toString() != "_id")
	                	docBuilder.append(aaa.get(i).toString(), requestObj.get(aaa.get(i).toString()));
	                }
                }	
			
			
			
			
	/*	docBuilder.append(CommonConstants.USERNAME, reqObject.get(vendorDetailsDB.NAME));
		docBuilder.append(CommonConstants.MEDIATYPE, requestObj.get(OrderDB.MEDIATYPE));
		
		docBuilder.append(CommonConstants.MEDIANAME, requestObj.get(OrderDB.MEDIANAME));
		
		docBuilder.append(CommonConstants.MEDIAOPTION,  requestObj.get(OrderDB.MEDIAOPTION));
		
		docBuilder.append(CommonConstants.CUSTOMERID, requestObj.get(OrderDB.CUSTOMERID));
		docBuilder.append(CommonConstants.STATUS, requestObj.get(OrderDB.STATUS));
		
		docBuilder.append(CommonConstants.PRICE, requestObj.get(OrderDB.PRICE));
		
		docBuilder.append(CommonConstants.ORDERID, requestObj.get(OrderDB.ORDERID));*/
		




		
        
        
		
		/*docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(vendorDetailsDB.UPDATEDBY));*/
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return docBuilder.get();
	}
	
public static DBObject createQuoteDBObject(JSONObject requestObj) {
		
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		try {

				for(int i = 0; i < requestObj.length(); i++){
	                JSONArray aaa = requestObj.names();
	                if(requestObj.get(aaa.get(i).toString()) != null){  
	                	if(aaa.get(i).toString() != "_id")
	                	docBuilder.append(aaa.get(i).toString(), requestObj.get(aaa.get(i).toString()));
	                }
                }	
			
			
			
			
	/*	docBuilder.append(CommonConstants.USERNAME, reqObject.get(vendorDetailsDB.NAME));
		docBuilder.append(CommonConstants.MEDIATYPE, requestObj.get(OrderDB.MEDIATYPE));
		
		docBuilder.append(CommonConstants.MEDIANAME, requestObj.get(OrderDB.MEDIANAME));
		
		docBuilder.append(CommonConstants.MEDIAOPTION,  requestObj.get(OrderDB.MEDIAOPTION));
		
		docBuilder.append(CommonConstants.CUSTOMERID, requestObj.get(OrderDB.CUSTOMERID));
		docBuilder.append(CommonConstants.STATUS, requestObj.get(OrderDB.STATUS));
		
		docBuilder.append(CommonConstants.PRICE, requestObj.get(OrderDB.PRICE));
		
		docBuilder.append(CommonConstants.ORDERID, requestObj.get(OrderDB.ORDERID));*/
		




		
        
        
		
		/*docBuilder.append(CommonConstants.UPDATEDBY, reqObject.get(vendorDetailsDB.UPDATEDBY));*/
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return docBuilder.get();
	}

}
