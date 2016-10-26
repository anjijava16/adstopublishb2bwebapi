package com.atp.b2bweb.createdbobject;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class DBVendorQuotesObject {
	
	
	
	public static DBObject createquotesDBObject(JSONObject requestObj) {
		
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		try {

			for(int i = 0; i < requestObj.length(); i++){
                JSONArray quoteKeys = requestObj.names();
                if(requestObj.get(quoteKeys.get(i).toString()) != null){  
                	if(quoteKeys.get(i).toString() != "_id")
                	docBuilder.append(quoteKeys.get(i).toString(), requestObj.get(quoteKeys.get(i).toString()));
                }
            }	
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		} catch (Exception e) {
			System.out.println("exception in vendordb object java "+e);
		}
		return docBuilder.get();
	}

}
