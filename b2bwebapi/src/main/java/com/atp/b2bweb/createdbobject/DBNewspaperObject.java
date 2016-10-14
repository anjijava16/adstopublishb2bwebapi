package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.NewspaperDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBNewspaperObject {
    
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
	
	public static DBObject createNewspaperDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {  
			System.out.println(requestObj);
			List<String> categoryId = new ArrayList<>();  
			categoryId.add(requestObj.get(NewspaperDB.CATEGORY_ID).toString());
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(NewspaperDB.GEOGRAPHY).toString());
			document.put(NewspaperDB.URL_SIUG, requestObj.get(NewspaperDB.URL_SIUG));
			document.put(NewspaperDB.LOGO, requestObj.get(NewspaperDB.LOGO));
			document.put(NewspaperDB.NAME, requestObj.get(NewspaperDB.NAME));
			document.put(NewspaperDB.EDITION_NAME, requestObj.get(NewspaperDB.EDITION_NAME));
			document.put(NewspaperDB.CATEGORYNAME, requestObj.get(NewspaperDB.CATEGORYNAME));
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(NewspaperDB.MEDIA_OPTIONS);	
				JSONObject otherOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NewspaperDB.OTHER_OPTIONS);
				BasicDBObject otherOptions = new BasicDBObject();
				for(int i = 0; i < otherOptionsJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) otherOptionsJSON.get(otherOptionsJSON.names().get(i).toString());
					otherOptions.append(otherOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(NewspaperDB.OTHER_OPTIONS, otherOptions);
				
				
				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NewspaperDB.REGULAR_OPTION);
				BasicDBObject regularOptions = new BasicDBObject();
				for(int i = 0; i < regularOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
					regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(NewspaperDB.REGULAR_OPTION, regularOptions);
				
			BasicDBObject attributes = new BasicDBObject();	
			
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				
			document.append(NewspaperDB.ATTRIBUTES, attributes);	
			document.append(NewspaperDB.MEDIA_OPTIONS, mediaOptions);
		}catch(Exception e){ System.out.println(e);	}
		return document;
	}
}
