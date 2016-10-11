package com.atp.b2bweb.createdbobject;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.CinimasDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBCinimasObject {
	
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
	
	public static DBObject createCinimasDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			document.put(CinimasDB.TYPE, requestObj.get(CinimasDB.TYPE));
			document.put(CinimasDB.IS_SINGLE_SCREEN, requestObj.get(CinimasDB.IS_SINGLE_SCREEN));
			document.put(CinimasDB.THEATRE_NAME, requestObj.get(CinimasDB.THEATRE_NAME));
			document.put(CinimasDB.URL_SIUG, requestObj.get(CinimasDB.URL_SIUG));
			document.put(CinimasDB.VIEWS, requestObj.get(CinimasDB.VIEWS));
			document.put(CinimasDB.UNIQUE_ID, requestObj.get(CinimasDB.UNIQUE_ID));
			document.put(CinimasDB.SERVICE_TAX_PERCENTAGE, requestObj.get(CinimasDB.SERVICE_TAX_PERCENTAGE));
			document.put(CinimasDB.LOGO, requestObj.get(CinimasDB.LOGO));
			document.put(CinimasDB.CINEMA_CHAIN, requestObj.get(CinimasDB.CINEMA_CHAIN));
			document.put(CinimasDB.SEATS, requestObj.get(CinimasDB.SEATS));
			document.put(CinimasDB.MALL_NAME, requestObj.get(CinimasDB.MALL_NAME));
			document.put(CinimasDB.CARD_RATE, requestObj.get(CinimasDB.CARD_RATE));
			document.put(CinimasDB.CITY, requestObj.get(CinimasDB.CITY));
			document.put(CinimasDB.STATE, requestObj.get(CinimasDB.STATE));
			document.put(CinimasDB.LOCALITY, requestObj.get(CinimasDB.LOCALITY));
			document.put(CinimasDB.NAME, requestObj.get(CinimasDB.NAME));
			
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(CinimasDB.MEDIA_OPTIONS);	

				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(CinimasDB.REGULAR_OPTION);
				BasicDBObject regularOptions = new BasicDBObject();
				for(int i = 0; i < regularOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
					regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(CinimasDB.REGULAR_OPTION, regularOptions);
				
			BasicDBObject attributes = new BasicDBObject();	
				
				JSONObject attributesJSON =  (JSONObject) requestObj.get(CinimasDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				
			document.append(CinimasDB.ATTRIBUTES, attributes);	
			document.append(CinimasDB.MEDIA_OPTIONS, mediaOptions);
			System.out.println(document);
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}

}
