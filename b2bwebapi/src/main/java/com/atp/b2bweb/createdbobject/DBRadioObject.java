package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.NewspaperDB;
import com.atp.b2bweb.db.RadionDB;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBRadioObject {
	
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
					BasicDBList basicDBList = new BasicDBList();
					for(int j=0; j< attJSON.length() ;j++){
						basicDBList.add(attJSON.get(j));
					}
					basicDBObject.append(jsonObj.names().get(i).toString(), basicDBList);
				}
			 }else{
				basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
			 }
		}  
		return basicDBObject;
	}
	
	public static DBObject createRadioDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			document.put(RadionDB.STATION, requestObj.get(RadionDB.STATION));
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(RadionDB.GEOGRAPHY).toString());
			document.put(RadionDB.GEOGRAPHY, requestObj.get(RadionDB.GEOGRAPHY).toString());
			document.put(RadionDB.URL_SIUG, requestObj.get(RadionDB.URL_SIUG));
			document.put(RadionDB.LOGO, requestObj.get(RadionDB.LOGO));
			document.put(RadionDB.VIEWS, requestObj.get(RadionDB.VIEWS));
			document.put(RadionDB.TOOL_NAME, requestObj.get(RadionDB.TOOL_NAME));
			document.put(RadionDB.NAME, requestObj.get(RadionDB.NAME));
			
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(RadionDB.MEDIA_OPTIONS);	

				for(int z = 0; z < mediaOptionsJSON.length(); z++){
					JSONObject mediaJSON =  (JSONObject) mediaOptionsJSON.get(mediaOptionsJSON.names().get(z).toString());
					BasicDBObject basicDBObject = new BasicDBObject();
					for(int i = 0; i < mediaJSON.length(); i++){
						JSONObject mediaoptionJSON =  (JSONObject) mediaJSON.get(mediaJSON.names().get(i).toString());
						if(mediaoptionJSON != null) basicDBObject.append(mediaJSON.names().get(i).toString(), getBasicDBObject(mediaoptionJSON));
					}
					mediaOptions.append(mediaOptionsJSON.names().get(z).toString(), basicDBObject);
				}
				document.append(RadionDB.MEDIA_OPTIONS, mediaOptions);	
				
				
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
				if(attributesJSON != null){
					BasicDBObject attributes = new BasicDBObject();
					for(int i = 0; i < attributesJSON.length(); i++){
						JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
						if(jsonObject != null) attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
					}
					document.append(NewspaperDB.ATTRIBUTES, attributes);
				}
				
				
				
				/*
				JSONObject otherOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.OTHER_OPTIONS);
				
				if(otherOptionsJSON != null){
					BasicDBObject otherOptions = new BasicDBObject();
					for(int i = 0; i < otherOptionsJSON.length(); i++){  
						JSONObject jsonObject =  (JSONObject) otherOptionsJSON.get(otherOptionsJSON.names().get(i).toString());
						otherOptions.append(otherOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
					}
					mediaOptions.append(RadionDB.OTHER_OPTIONS, otherOptions);
				}
				
				JSONObject rjOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.RJ_OPTIONS);
				if(rjOptionsJSON != null){
					BasicDBObject rjOptions = new BasicDBObject();
					for(int i = 0; i < rjOptionsJSON.length(); i++){ 
						JSONObject jsonObject =  (JSONObject) rjOptionsJSON.get(rjOptionsJSON.names().get(i).toString());
						rjOptions.append(rjOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
					}
					mediaOptions.append(RadionDB.RJ_OPTIONS, rjOptions);
				}
				
				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.REGULAR_OPTION);
				if(regularOptionsJSON != null){
					BasicDBObject regularOptions = new BasicDBObject();
					for(int i = 0; i < regularOptionsJSON.length(); i++){
						JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
						regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
					}
					mediaOptions.append(RadionDB.REGULAR_OPTION, regularOptions);
				}
			document.append(RadionDB.MEDIA_OPTIONS, mediaOptions);	
			
				
			
			JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
			if(attributesJSON != null){
				BasicDBObject attributes = new BasicDBObject();
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				document.append(NewspaperDB.ATTRIBUTES, attributes);
			}*/
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}
}
