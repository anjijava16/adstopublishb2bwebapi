package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.NewspaperDB;
import com.atp.b2bweb.db.TelevisionDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBTelevisionObject {
	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			String aaaa = jsonObj.get(jsonObj.names().get(i).toString()).toString();
			if(aaaa.contains("{") || aaaa.contains("[")){				
				BasicDBObject attObject = new BasicDBObject();
				if(aaaa.contains("{")){
					JSONObject attJSON = (JSONObject) jsonObj.get(jsonObj.names().get(i).toString());
					for(int j = 0; j < attJSON.length(); j++){
						attObject.append(attJSON.names().get(j).toString(), attJSON.get(attJSON.names().get(j).toString()));
					}
					basicDBObject.append(jsonObj.names().get(i).toString(), attObject);
				}
				if(aaaa.contains("[")){
					JSONArray attJSON =  (JSONArray) jsonObj.get(jsonObj.names().get(i).toString());				
					basicDBObject.append(jsonObj.names().get(i).toString(), attJSON.toString());
				}
			 }else{
				basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
			 }
		}  
		return basicDBObject;
	}
	
	public static DBObject createTelevisionDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(TelevisionDB.GEOGRAPHY).toString());
			document.put(TelevisionDB.NAME, requestObj.get(TelevisionDB.NAME));
			document.put(TelevisionDB.URL_SIUG, requestObj.get(TelevisionDB.URL_SIUG));
			List<String> categoryId = new ArrayList<>();  
			categoryId.add(requestObj.get(TelevisionDB.CATEGORY_ID).toString());
			document.put(TelevisionDB.LOGO, requestObj.get(TelevisionDB.LOGO));
			document.put(TelevisionDB.SERVICE_TAX_PERCENTEGE, requestObj.get(TelevisionDB.SERVICE_TAX_PERCENTEGE));
			document.put(TelevisionDB.WEEKLY_REACH, requestObj.get(TelevisionDB.WEEKLY_REACH));
			document.put(TelevisionDB.RATE, requestObj.get(TelevisionDB.RATE));
			document.put(TelevisionDB.TOOL_NAME, requestObj.get(TelevisionDB.TOOL_NAME));
			document.put(TelevisionDB.CARDRATE, requestObj.get(TelevisionDB.CARDRATE));
			
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(TelevisionDB.MEDIA_OPTIONS);	

				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(TelevisionDB.REGULAR_OPTION);
				BasicDBObject regularOptions = new BasicDBObject();
				for(int i = 0; i < regularOptionsJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
					regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(TelevisionDB.REGULAR_OPTION, regularOptions);
				
								
				JSONObject planningOptionsJSON =  (JSONObject) mediaOptionsJSON.get(TelevisionDB.PLANNING_OPTIONS);
				BasicDBObject planningOptions = new BasicDBObject();
				for(int i = 0; i < planningOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) planningOptionsJSON.get(planningOptionsJSON.names().get(i).toString());
					planningOptions.append(planningOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(TelevisionDB.PLANNING_OPTIONS, planningOptions);
				
				BasicDBObject attributes = new BasicDBObject();	
				
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				document.append(TelevisionDB.ATTRIBUTES, attributes);
			document.append(TelevisionDB.MEDIA_OPTIONS, mediaOptions);	
			System.out.println(document);
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}

}
