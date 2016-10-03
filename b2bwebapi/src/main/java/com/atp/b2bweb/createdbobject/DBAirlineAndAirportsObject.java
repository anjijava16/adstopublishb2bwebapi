package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.AirlineAndAirportsDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBAirlineAndAirportsObject {
	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
		}
		return basicDBObject;
	}
	
	public static DBObject createAirlineAndAirportsDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			document.put(AirlineAndAirportsDB.CATEGORY, requestObj.get(AirlineAndAirportsDB.CATEGORY));
			document.put(AirlineAndAirportsDB.URL_SIUG, requestObj.get(AirlineAndAirportsDB.URL_SIUG));
			document.put(AirlineAndAirportsDB.NAME, requestObj.get(AirlineAndAirportsDB.NAME));
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(AirlineAndAirportsDB.GEOGRAPHY).toString());
			document.put(AirlineAndAirportsDB.LOGO, requestObj.get(AirlineAndAirportsDB.LOGO));
			document.put(AirlineAndAirportsDB.VIEWS, requestObj.get(AirlineAndAirportsDB.VIEWS));
			document.put(AirlineAndAirportsDB.SERVICE_TAX_PERCENTAGE, requestObj.get(AirlineAndAirportsDB.SERVICE_TAX_PERCENTAGE));
			document.put(AirlineAndAirportsDB.CARD_RATE, requestObj.get(AirlineAndAirportsDB.CARD_RATE));
			
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(AirlineAndAirportsDB.MEDIA_OPTIONS);	

				JSONObject digitalOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.DIGITAL_OPTIONS);
				BasicDBObject digitalOptions = new BasicDBObject();
				for(int i = 0; i < digitalOptionsJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) digitalOptionsJSON.get(digitalOptionsJSON.names().get(i).toString());
					digitalOptions.append(digitalOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(AirlineAndAirportsDB.DIGITAL_OPTIONS, digitalOptions);
				
				
				JSONObject printOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.PRINT_OPTION);
				BasicDBObject printOptions = new BasicDBObject();
				for(int i = 0; i < printOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) printOptionsJSON.get(printOptionsJSON.names().get(i).toString());
					printOptions.append(printOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(AirlineAndAirportsDB.PRINT_OPTION, printOptions);
				
				JSONObject aircraftOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.AIRCRAFT_OPTIONS);
				BasicDBObject aircraftOptions = new BasicDBObject();
				for(int i = 0; i < aircraftOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) aircraftOptionsJSON.get(printOptionsJSON.names().get(i).toString());
					aircraftOptions.append(aircraftOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(AirlineAndAirportsDB.AIRCRAFT_OPTIONS, printOptions);
				
				JSONObject popularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.POPULAR_OPTION);
				BasicDBObject popularOptions = new BasicDBObject();
				for(int i = 0; i < popularOptionsJSON.length(); i++){
					JSONObject jsonObject =  (JSONObject) popularOptionsJSON.get(popularOptionsJSON.names().get(i).toString());
					popularOptions.append(popularOptionsJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(AirlineAndAirportsDB.POPULAR_OPTION, popularOptions);
				
			BasicDBObject attributes = new BasicDBObject();	
				
				JSONObject attributesJSON =  (JSONObject) requestObj.get(AirlineAndAirportsDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				
			document.append(AirlineAndAirportsDB.ATTRIBUTES, attributes);	
			document.append(AirlineAndAirportsDB.MEDIA_OPTIONS, mediaOptions);
			System.out.println(document);
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}

}
