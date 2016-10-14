package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.MagazineDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBMagazineObject {
	
	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			String aaaa = jsonObj.get(jsonObj.names().get(i).toString()).toString();
			if(aaaa.contains(":")){
				BasicDBObject attObject = new BasicDBObject();
				JSONObject attJSON = (JSONObject) jsonObj.get(jsonObj.names().get(i).toString());
				for(int j = 0; j < attJSON.length(); j++){
					attObject.append(attJSON.names().get(j).toString(), attJSON.get(attJSON.names().get(j).toString()));
				}
				basicDBObject.append(jsonObj.names().get(i).toString(), attObject);
			 }else{
				basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
			 }
		}  
		return basicDBObject;
	}
	
	public static DBObject createMagazineDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			System.out.println("requestObj  "+requestObj);
			document.put(MagazineDB.TOOL_ID, requestObj.get(MagazineDB.TOOL_ID));
			document.put(MagazineDB.LOGO, requestObj.get(MagazineDB.LOGO));
			document.put(MagazineDB.THUMBNAIL, requestObj.get(MagazineDB.THUMBNAIL));
			document.put(MagazineDB.VIEWS, requestObj.get(MagazineDB.VIEWS));
			document.put(MagazineDB.CREATED_BY, requestObj.get(MagazineDB.CREATED_BY));
			document.put(MagazineDB.NAME, requestObj.get(MagazineDB.NAME));
			document.put(MagazineDB.URL_SIUG, requestObj.get(MagazineDB.URL_SIUG));
			List<String> categoryId = new ArrayList<>();  
			categoryId.add(requestObj.get(MagazineDB.CATEGORY_ID).toString());
			document.put(MagazineDB.CATEGORY_ID, requestObj.get(MagazineDB.CATEGORY_ID).toString());
			document.put(MagazineDB.IRS, requestObj.get(MagazineDB.IRS));
			List<String> keywords = new ArrayList<>();  
			keywords.add(requestObj.get(MagazineDB.KEYWORDS).toString());
			document.put(MagazineDB.KEYWORDS, keywords);
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(MagazineDB.GEOGRAPHY).toString());
			document.put(MagazineDB.GEOGRAPHY, requestObj.get(MagazineDB.GEOGRAPHY).toString());
			document.put(MagazineDB.SERVICE_TAX_PERCENTAGE, requestObj.get(MagazineDB.SERVICE_TAX_PERCENTAGE));
			document.put(MagazineDB.CATEGORY_NAME, requestObj.get(MagazineDB.CATEGORY_NAME));
			document.put(MagazineDB.CARD_RATE, requestObj.get(MagazineDB.CARD_RATE));
			document.put(MagazineDB.DISCOUNT_RATE, requestObj.get(MagazineDB.DISCOUNT_RATE));
			
			
			
			BasicDBObject mediaOptions = new BasicDBObject();
			
			JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(MagazineDB.MEDIA_OPTIONS);	
			
			JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(MagazineDB.REGULAR_OPTION);
			
			BasicDBObject regularOptions = new BasicDBObject();
			for(int i = 0; i < regularOptionsJSON.length(); i++){
				JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
				regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
			}
			mediaOptions.append(MagazineDB.REGULAR_OPTION, regularOptions);
			
			BasicDBObject attributes = new BasicDBObject();	
		
			JSONObject attributesJSON =  (JSONObject) requestObj.get(MagazineDB.ATTRIBUTES);
			for(int i = 0; i < attributesJSON.length(); i++){  
				JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
				attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
			}
			document.put(MagazineDB.ATTRIBUTES, attributes);	
			document.put(MagazineDB.MEDIA_OPTIONS, mediaOptions);	
			
		} catch (Exception e) {
			System.out.println("exception in mondodbmagazineobject java "+e);
		}
		return document;
	}

}
