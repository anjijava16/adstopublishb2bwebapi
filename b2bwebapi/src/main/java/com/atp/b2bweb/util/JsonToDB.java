package com.atp.b2bweb.util;

import java.io.FileReader;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.atp.b2bweb.db.DigitalDB;
import com.atp.b2bweb.db.NonTraditionalDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

public class JsonToDB {
	
	public static void jsontodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("magazine");
			
			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Downloads/Magazine1.txt"));
			JSONObject jsonObject =  (JSONObject) obj;				
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");			
			for (Object object : jsonArray) {
				JSONObject requestObj =  (JSONObject) object;
				BasicDBObject document = new BasicDBObject();
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
			table.insert(document);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void addnespaperrecordtodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("newspaper");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/Newspaper/Newspaper.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");			
			
			for (Object object : jsonArray) {
				JSONObject requestObj =  (JSONObject) object;
				BasicDBObject document = new BasicDBObject();
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
				table.insert(document);
			}

		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public static void addAirlineAndAirporttodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("airlineandairports");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/AirlineAndAirports/AirlineAndAirports.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");			
			for (Object object : jsonArray) {
					JSONObject requestObj =  (JSONObject) object;
					BasicDBObject document = new BasicDBObject();
					if(requestObj != null){
						Object[] allKeys = requestObj.keySet().toArray();
						for(int x = 0; x < allKeys.length; x++){
							if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
								document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
							}
							if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
								JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
								if(mediaOptionsJSON != null){
									document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
								}
							}
							if(allKeys[x].toString().equalsIgnoreCase("attributes")){
								JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
								if(attributesJSON != null){
									document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
								}
							}
						}
					}
				table.insert(document);
			}

		}catch(Exception e){
			System.out.println(e);
		}
		
	}

	public static void addcinematodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("cinemas");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/Cinemas/Cinemas.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");			
			for (Object object : jsonArray) {
				JSONObject requestObj =  (JSONObject) object;
				BasicDBObject document = new BasicDBObject();
	
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
				table.insert(document);
			}
		}catch(Exception e){ System.out.println(e);	}
		
	}
	
	public static void addnontraditionaltodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("nontraditional");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/NonTraditional/Nontraditional.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");			
			for (Object object : jsonArray) {
				JSONObject requestObj =  (JSONObject) object;				
				BasicDBObject document = new BasicDBObject();
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes") && !allKeys[x].toString().equalsIgnoreCase("geography")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("geography")){
							BasicDBObject geography = new BasicDBObject();	
							JSONObject geographyJSON =  (JSONObject) requestObj.get(NonTraditionalDB.GEOGRAPHY);
							if(geographyJSON != null){
								for(int i = 0; i < geographyJSON.size(); i++){
									Object[] aaa = geographyJSON.keySet().toArray();
									geography.append(aaa[i].toString(), geographyJSON.get(aaa[i].toString()));
								}
								document.append(NonTraditionalDB.GEOGRAPHY, geography);
							}
						}
						
					}
				}
				
				
				
					/*document.put(NonTraditionalDB.URL_SIUG, requestObj.get(NonTraditionalDB.URL_SIUG));
					document.put(NonTraditionalDB.LOGO, requestObj.get(NonTraditionalDB.LOGO));
					document.put(NonTraditionalDB.NAME, requestObj.get(NonTraditionalDB.NAME));
					document.put(NonTraditionalDB.NOTE, requestObj.get(NonTraditionalDB.NOTE));
					document.put(NonTraditionalDB.SERVICE_TAX_PERCENTAGE, requestObj.get(NonTraditionalDB.SERVICE_TAX_PERCENTAGE));
							
					BasicDBObject geography = new BasicDBObject();	
					
					JSONObject geographyJSON =  (JSONObject) requestObj.get(NonTraditionalDB.GEOGRAPHY);
					if(geographyJSON != null){
						for(int i = 0; i < geographyJSON.size(); i++){
							Object[] aaa = geographyJSON.keySet().toArray();
							geography.append(aaa[i].toString(), geographyJSON.get(aaa[i].toString()));
						}
					}
					
					document.append(NonTraditionalDB.GEOGRAPHY, geography);*/
					table.insert(document);
				}
				}catch(Exception e){ System.out.println(e);	}
		}
	
	public static void addOutdoortodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("outdoor");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/Outdoor/Outdoor.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");		
			
			for (Object object : jsonArray) {
				JSONObject requestObj =  (JSONObject) object;
				BasicDBObject document = new BasicDBObject();
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
				table.insert(document);
			}
		}catch(Exception e){ System.out.println(e);	}
		
		
		
	}

	public static void addRadiotodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("radio");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/Radio/Radio.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");		
			
			for (Object object : jsonArray) {
				BasicDBObject document = new BasicDBObject();
				JSONObject requestObj =  (JSONObject) object;
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
				table.insert(document);
				}
			}catch(Exception e){ System.out.println(e);	}
		
		
		
	}

	public static void addTVtodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("television");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/Television/Television.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");		
			for (Object object : jsonArray) {   
				BasicDBObject document = new BasicDBObject();
				JSONObject requestObj =  (JSONObject) object;
				
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
				table.insert(document);   
				
			}
		}catch(Exception e){ System.out.println(e);	}
	}
	
	public static void addDigitaltodb(){
		JSONParser parser = new JSONParser();
		try {
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("digital");
			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/Digital/Digital.txt"));
			JSONObject jsonObject =  (JSONObject) obj;
			JSONArray jsonArray = (JSONArray) jsonObject.get("medias");		
		
			for (Object object : jsonArray) {   
				BasicDBObject document = new BasicDBObject();
				JSONObject requestObj =  (JSONObject) object;
				
				if(requestObj != null){
					Object[] allKeys = requestObj.keySet().toArray();
					for(int x = 0; x < allKeys.length; x++){
						if(!allKeys[x].toString().equalsIgnoreCase("_id") &&!allKeys[x].toString().equalsIgnoreCase("mediaOptions") && !allKeys[x].toString().equalsIgnoreCase("attributes")){
							document.append(allKeys[x].toString(), requestObj.get(allKeys[x]));
						}
						if(allKeys[x].toString().equalsIgnoreCase("mediaOptions")){
							JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
							if(mediaOptionsJSON != null){
								document.append(DigitalDB.MEDIA_OPTIONS, getMediaOption(mediaOptionsJSON));
							}
						}
						if(allKeys[x].toString().equalsIgnoreCase("attributes")){
							JSONObject attributesJSON =  (JSONObject) requestObj.get(DigitalDB.ATTRIBUTES);
							if(attributesJSON != null){
								document.append(DigitalDB.ATTRIBUTES, getAttributes(attributesJSON));
							}
						}
					}
				}
				table.insert(document);   
			}
					
				
		}catch(Exception e){ System.out.println(e);	}
	}
	
	public static BasicDBObject getAttributes(JSONObject attributesJSON) throws JSONException{
		BasicDBObject attributes = new BasicDBObject();	
		for(int i = 0; i < attributesJSON.size(); i++){ 
			Object[] aaa = attributesJSON.keySet().toArray();
			JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
			if(jsonObject1 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
		}
		return attributes;
	}
	
	public static BasicDBObject getMediaOption(JSONObject mediaOptionsJSON) throws JSONException{
		BasicDBObject mediaOptions = new BasicDBObject();
		Object[] aaa1 = mediaOptionsJSON.keySet().toArray();
		for(int k = 0; k < aaa1.length; k++){
			BasicDBObject costPerView = new BasicDBObject();
			JSONObject costPerViewJSON =  (JSONObject) mediaOptionsJSON.get(aaa1[k]);
			if(costPerViewJSON != null){
				for(int i = 0; i < costPerViewJSON.size(); i++){  
					Object[] aaa = costPerViewJSON.keySet().toArray();
					JSONObject jsonObject1 =  (JSONObject) costPerViewJSON.get(aaa[i].toString());
					if(jsonObject1 != null) costPerView.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
				}
				mediaOptions.append(aaa1[k].toString(), costPerView);
			}
		}
		
		return mediaOptions;
	}
	
	public static BasicDBObject getBasicDBObject(JSONObject jsonObj) throws JSONException{
		BasicDBObject basicDBObject = new BasicDBObject();	
		if(jsonObj != null){
			for(int i = 0; i < jsonObj.size(); i++){
				Object[] aaa = jsonObj.keySet().toArray();
				if(jsonObj.get(aaa[i].toString()) != null){
					String aaaa = jsonObj.get(aaa[i].toString()).toString();
					if(aaaa.contains("{") || aaaa.contains("[")){
						BasicDBObject attObject = new BasicDBObject();
						if(aaaa.contains("{")){
							JSONObject attJSON = (JSONObject) jsonObj.get(aaa[i].toString());
							if(attJSON != null){
								for(int j = 0; j < attJSON.size(); j++){
									Object[] bbb = attJSON.keySet().toArray();
									attObject.append(bbb[j].toString(), attJSON.get(bbb[j].toString()));
								}
								basicDBObject.append(aaa[i].toString(), attObject);
							}
						}
						if(aaaa.contains("[")){
							JSONArray attJSON =  (JSONArray) jsonObj.get(aaa[i].toString());
							if(attJSON != null) basicDBObject.append(aaa[i].toString(), attJSON);
						}
					 }else{
						basicDBObject.append(aaa[i].toString(), jsonObj.get(aaa[i].toString()));
					 }
				}
			}
		}
		return basicDBObject;
	}

}
