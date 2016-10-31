package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.NewspaperDB;
import com.atp.b2bweb.db.RadionDB;
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
			document.put(NewspaperDB.VIEWS, requestObj.get(NewspaperDB.VIEWS));
		/*	document.put(NewspaperDB.GEOGRAPHY, requestObj.get(NewspaperDB.GEOGRAPHY));
			document.put(NewspaperDB.CATEGORY_ID, requestObj.get(NewspaperDB.CATEGORY_ID));*/
					
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
			System.out.println("document  "+document);
		}catch(Exception e){ System.out.println(e);	}
		return document;
	}
}
