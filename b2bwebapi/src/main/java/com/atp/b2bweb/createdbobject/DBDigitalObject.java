package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.db.DigitalDB;
import com.atp.b2bweb.db.NewspaperDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DBDigitalObject {
	
	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		for(int i = 0; i < jsonObj.length(); i++){
			basicDBObject.append(jsonObj.names().get(i).toString(), jsonObj.get(jsonObj.names().get(i).toString()));
		}
		return basicDBObject;
	}
	
	public static DBObject createDigitalDBObject(JSONObject requestObj) {
		BasicDBObject document = new BasicDBObject();
		try {
			document.put(DigitalDB.LOGO, requestObj.get(DigitalDB.LOGO));
			document.put(DigitalDB.URL_SIUG, requestObj.get(DigitalDB.URL_SIUG));
			document.put(DigitalDB.NAME, requestObj.get(DigitalDB.NAME));			
			document.put(DigitalDB.MEDIUM, requestObj.get(DigitalDB.MEDIUM));
			document.put(DigitalDB.SERVICE_TAX_PERCENTAGE, requestObj.get(DigitalDB.SERVICE_TAX_PERCENTAGE));
			document.put(DigitalDB.MINIMUM_BILLING, requestObj.get(DigitalDB.MINIMUM_BILLING));
			document.put(DigitalDB.CATEGORY_NAME, requestObj.get(DigitalDB.CATEGORY_NAME));
			document.put(DigitalDB.CARD_RATE, requestObj.get(DigitalDB.CARD_RATE));
			List<String> categoryId = new ArrayList<>();  
			categoryId.add(requestObj.get(DigitalDB.CATEGORY_ID).toString());
			
					
			BasicDBObject mediaOptions = new BasicDBObject();
				
			JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);	

				JSONObject costPerViewJSON =  (JSONObject) mediaOptionsJSON.get(DigitalDB.COST_PER_VIEW);
				BasicDBObject costPerView = new BasicDBObject();
				for(int i = 0; i < costPerViewJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) costPerViewJSON.get(costPerViewJSON.names().get(i).toString());
					costPerView.append(costPerViewJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
				mediaOptions.append(DigitalDB.COST_PER_VIEW, costPerView);
				document.append(DigitalDB.MEDIA_OPTIONS, mediaOptions);	
				
			BasicDBObject attributes = new BasicDBObject();	
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
				for(int i = 0; i < attributesJSON.length(); i++){  
					JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
					attributes.append(attributesJSON.names().get(i).toString(), getBasicDBObject(jsonObject));
				}
			document.append(NewspaperDB.ATTRIBUTES, attributes);	
				
			
			System.out.println(document);
		}catch(Exception e){ System.out.println(e);	}
		
		return document;
	}
}
