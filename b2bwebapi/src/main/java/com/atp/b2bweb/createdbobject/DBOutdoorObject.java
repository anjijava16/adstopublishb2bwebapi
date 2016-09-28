package com.atp.b2bweb.createdbobject;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.NonTraditionalDB;
import com.atp.b2bweb.db.OutdoorDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBOutdoorObject {

	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
		}
		return basicDBObject;
	}
	
	public static DBObject createOutdoorDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		
		try {
			document.put(OutdoorDB.URL_SIUG, requestObj.get(OutdoorDB.URL_SIUG));
			document.put(OutdoorDB.NAME, requestObj.get(OutdoorDB.NAME));
			document.put(OutdoorDB.GEOGRAPHY, requestObj.get(OutdoorDB.GEOGRAPHY).toString());
			document.put(OutdoorDB.LOGO, requestObj.get(OutdoorDB.LOGO));
			document.put(OutdoorDB.UNIQUEID, requestObj.get(OutdoorDB.UNIQUEID));
			document.put(OutdoorDB.TOOL_NAME, requestObj.get(OutdoorDB.TOOL_NAME));
			document.put(OutdoorDB.LOCALITY, requestObj.get(OutdoorDB.LOCALITY));
			document.put(OutdoorDB.CITY, requestObj.get(OutdoorDB.CITY));
			document.put(OutdoorDB.CARD_RATE, requestObj.get(OutdoorDB.CARD_RATE));
			
			BasicDBObject attributes = new BasicDBObject();	
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NonTraditionalDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				document.append(NonTraditionalDB.ATTRIBUTES, attributes);
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(OutdoorDB.MEDIA_OPTIONS);	

				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(OutdoorDB.REGULAR_OPTION);
				BasicDBObject regularOptions = new BasicDBObject();
				for(int i = 0; i < regularOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
					regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(OutdoorDB.REGULAR_OPTION, regularOptions);
			document.append(OutdoorDB.MEDIA_OPTIONS, mediaOptions);	
			System.out.println(document);
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}
}

