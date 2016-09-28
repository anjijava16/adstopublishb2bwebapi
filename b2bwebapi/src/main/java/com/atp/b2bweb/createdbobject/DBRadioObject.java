package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.RadionDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBRadioObject {
	
	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
		}
		return basicDBObject;
	}
	
	public static DBObject createRadioDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			document.put(RadionDB.STATION, requestObj.get(RadionDB.STATION));
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(RadionDB.GEOGRAPHY).toString());
			document.put(RadionDB.URL_SIUG, requestObj.get(RadionDB.URL_SIUG));
			document.put(RadionDB.LOGO, requestObj.get(RadionDB.LOGO));
			document.put(RadionDB.VIEWS, requestObj.get(RadionDB.VIEWS));
			document.put(RadionDB.TOOL_NAME, requestObj.get(RadionDB.TOOL_NAME));
			document.put(RadionDB.NAME, requestObj.get(RadionDB.NAME));
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(RadionDB.MEDIA_OPTIONS);	

				JSONObject otherOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.OTHER_OPTIONS);
				BasicDBObject otherOptions = new BasicDBObject();
				for(int i = 0; i < otherOptionsJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) otherOptionsJSON.get(otherOptionsJSON.names().get(i).toString());
					otherOptions.append(otherOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(RadionDB.OTHER_OPTIONS, otherOptions);
				
				
				JSONObject rjOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.RJ_OPTIONS);
				BasicDBObject rjOptions = new BasicDBObject();
				for(int i = 0; i < rjOptionsJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) rjOptionsJSON.get(rjOptionsJSON.names().get(i).toString());
					rjOptions.append(rjOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(RadionDB.RJ_OPTIONS, rjOptions);
				
				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.REGULAR_OPTION);
				BasicDBObject regularOptions = new BasicDBObject();
				for(int i = 0; i < regularOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
					regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(RadionDB.REGULAR_OPTION, regularOptions);
				
			document.append(RadionDB.MEDIA_OPTIONS, mediaOptions);	
			System.out.println(document);
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}
}
