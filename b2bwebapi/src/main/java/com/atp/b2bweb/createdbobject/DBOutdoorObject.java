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
				if(attributesJSON != null){
					for(int i = 0; i < attributesJSON.length(); i++){  
						JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
						if(jsonObject != null) attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
					}
					document.append(NonTraditionalDB.ATTRIBUTES, attributes);
				}
			
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(OutdoorDB.MEDIA_OPTIONS);	
				if(mediaOptionsJSON != null){
					BasicDBObject mediaOptions = new BasicDBObject();
					JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(OutdoorDB.REGULAR_OPTION);
					if(regularOptionsJSON != null){
						BasicDBObject regularOptions = new BasicDBObject();
						for(int i = 0; i < regularOptionsJSON.length(); i++){
							JSONObject jsonObject =  (JSONObject) regularOptionsJSON.get(regularOptionsJSON.names().get(i).toString());
							if(jsonObject != null) regularOptions.append(regularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
						}
						mediaOptions.append(OutdoorDB.REGULAR_OPTION, regularOptions);
						document.append(OutdoorDB.MEDIA_OPTIONS, mediaOptions);
					}
				}
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}
}

