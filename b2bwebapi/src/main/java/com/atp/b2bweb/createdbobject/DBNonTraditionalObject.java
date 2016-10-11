package com.atp.b2bweb.createdbobject;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.NonTraditionalDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBNonTraditionalObject {


	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			String aaaa = jsonObj.get(jsonObj.names().get(i).toString()).toString();
			if(aaaa.contains(":")){
				BasicDBObject attObject = new BasicDBObject();
				JSONObject attJSON = (JSONObject) jsonObj.get(jsonObj.names().get(i).toString());
				for(int j = 0; j < attJSON.length(); j++){
					basicDBObject.append(attJSON.names().get(j).toString(), attJSON.get(attJSON.names().get(j).toString()));
				}
				basicDBObject.append(jsonObj.names().get(i).toString(), attObject);
			 }else{
				basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
			 }
		}
		return basicDBObject;
	}
	
	public static DBObject createNonTraditionalDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
		
			
			document.put(NonTraditionalDB.URL_SIUG, requestObj.get(NonTraditionalDB.URL_SIUG));
			document.put(NonTraditionalDB.LOGO, requestObj.get(NonTraditionalDB.LOGO));
			document.put(NonTraditionalDB.NAME, requestObj.get(NonTraditionalDB.NAME));
			document.put(NonTraditionalDB.NOTE, requestObj.get(NonTraditionalDB.NOTE));
			document.put(NonTraditionalDB.SERVICE_TAX_PERCENTAGE, requestObj.get(NonTraditionalDB.SERVICE_TAX_PERCENTAGE));
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(NonTraditionalDB.MEDIA_OPTIONS);	

				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NonTraditionalDB.REGULAR_OPTION);
				BasicDBObject regularOptions = new BasicDBObject();
				for(int i = 0; i < regularOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
					regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(NonTraditionalDB.REGULAR_OPTION, regularOptions);
				
			BasicDBObject attributes = new BasicDBObject();	
				
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NonTraditionalDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				
			document.append(NonTraditionalDB.ATTRIBUTES, attributes);	
			document.append(NonTraditionalDB.MEDIA_OPTIONS, mediaOptions);
			
			
			BasicDBObject geography = new BasicDBObject();	
			
			JSONObject geographyJSON =  (JSONObject) requestObj.get(NonTraditionalDB.GEOGRAPHY);
			for(int i = 0; i < geographyJSON.length(); i++){  
			//	JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
				geography.append(geographyJSON.names().get(i).toString(), geographyJSON.get(geographyJSON.names().get(i).toString()));
			}
			document.append(NonTraditionalDB.GEOGRAPHY, geography);
			
		}catch(Exception e){ System.out.println(e);	}
		return document;
	}
}
