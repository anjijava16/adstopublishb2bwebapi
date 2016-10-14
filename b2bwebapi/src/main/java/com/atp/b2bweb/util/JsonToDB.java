package com.atp.b2bweb.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.atp.b2bweb.db.AirlineAndAirportsDB;
import com.atp.b2bweb.db.CinimasDB;
import com.atp.b2bweb.db.DigitalDB;
import com.atp.b2bweb.db.NewspaperDB;
import com.atp.b2bweb.db.NonTraditionalDB;
import com.atp.b2bweb.db.RadionDB;
import com.atp.b2bweb.db.TelevisionDB;
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
				JSONObject jsonObject1 =  (JSONObject) object;
				BasicDBObject document = new BasicDBObject();
				document.put("toolId", "55755d6c66579f76671b1a1d");
				document.put("logo", "/images/medias/55b7d6ab8ead0e48288b4595/55b7d6ab8ead0e48288b4595_logo.jpg");
				document.put("thumbnail", "/images/medias/55b7d6ab8ead0e48288b4595/55b7d6ab8ead0e48288b4595_thumbnail.jpg");
				document.put("views", jsonObject1.get("views"));
				document.put("createdBy", jsonObject1.get("createdBy"));
				document.put("name", jsonObject1.get("name"));
				document.put("urlSlug", jsonObject1.get("urlSlug"));
				document.put("categoryId", jsonObject1.get("categoryId"));
				document.put("IRS", jsonObject1.get("IRS"));
				document.put("keywords", jsonObject1.get("keywords"));
				document.put("geography", jsonObject1.get("geography"));
				document.put("serviceTaxPercentage", jsonObject1.get("serviceTaxPercentage"));
				document.put("categoryName", jsonObject1.get("categoryName"));
				document.put("cardRate", jsonObject1.get("cardRate"));
				document.put("discountedRate", jsonObject1.get("discountedRate"));
				

				BasicDBObject attributes = new BasicDBObject();	
				
				JSONObject attributesJSON =  (JSONObject) jsonObject1.get(NonTraditionalDB.ATTRIBUTES);
				if(attributesJSON != null){
					for(int i = 0; i < attributesJSON.size(); i++){ 
						Object[] aaa = attributesJSON.keySet().toArray();
						JSONObject jsonObject11 =  (JSONObject) attributesJSON.get(aaa[i].toString());
						if(jsonObject11 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject11));
					}
				}
				document.append(NonTraditionalDB.ATTRIBUTES, attributes);
			
				BasicDBObject mediaOptions = new BasicDBObject();
				
				JSONObject mediaOptionsJSON =  (JSONObject) jsonObject1.get(NonTraditionalDB.MEDIA_OPTIONS);	
				JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NonTraditionalDB.REGULAR_OPTION);
				if( regularOptionsJSON != null){
					BasicDBObject regularOptions = new BasicDBObject();
					for(int i = 0; i < regularOptionsJSON.size(); i++){
						Object[] aaa = regularOptionsJSON.keySet().toArray();
						JSONObject jsonObject11 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
						if( jsonObject11 != null) regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject11));
					}
					mediaOptions.append(NonTraditionalDB.REGULAR_OPTION, regularOptions);
				}
				document.append(NonTraditionalDB.MEDIA_OPTIONS, mediaOptions);
					
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
					List<String> categoryId = new ArrayList<>();  
					categoryId.add(requestObj.get(NewspaperDB.CATEGORY_ID) != null ? requestObj.get(NewspaperDB.CATEGORY_ID).toString():"");
					List<String> geography = new ArrayList<>();  
					geography.add(requestObj.get(NewspaperDB.GEOGRAPHY).toString());
					document.put(NewspaperDB.URL_SIUG, requestObj.get(NewspaperDB.URL_SIUG));
					document.put(NewspaperDB.LOGO, requestObj.get(NewspaperDB.LOGO));
					document.put(NewspaperDB.NAME, requestObj.get(NewspaperDB.NAME));
					document.put(NewspaperDB.EDITION_NAME, requestObj.get(NewspaperDB.EDITION_NAME));
					document.put(NewspaperDB.CATEGORYNAME, requestObj.get(NewspaperDB.CATEGORYNAME));

					BasicDBObject mediaOptions = new BasicDBObject();
						JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(NewspaperDB.MEDIA_OPTIONS);	
						if(mediaOptionsJSON != null){
							JSONObject otherOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NewspaperDB.OTHER_OPTIONS);
							if(otherOptionsJSON != null){
								
								BasicDBObject otherOptions = new BasicDBObject();
								if(otherOptionsJSON != null){
									for(int i = 0; i < otherOptionsJSON.size(); i++){  
										Object[] aaa = otherOptionsJSON.keySet().toArray();
										JSONObject jsonObject1 =  (JSONObject) otherOptionsJSON.get(aaa[i].toString());
										if(jsonObject1 != null)
										otherOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
									}
									mediaOptions.append(NewspaperDB.OTHER_OPTIONS, otherOptions);
								}
							}
							JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NewspaperDB.REGULAR_OPTION);
							if(regularOptionsJSON != null){
								BasicDBObject regularOptions = new BasicDBObject();
								for(int i = 0; i < regularOptionsJSON.size(); i++){
									Object[] aaa = regularOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)
									regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
								mediaOptions.append(NewspaperDB.REGULAR_OPTION, regularOptions);
							}
						}
					BasicDBObject attributes = new BasicDBObject();	
						JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
						if(attributesJSON != null){
							for(int i = 0; i < attributesJSON.size(); i++){  
								Object[] aaa = attributesJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
								if(jsonObject1 != null)
								attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
						}
						
					document.append(NewspaperDB.ATTRIBUTES, attributes);	
					document.append(NewspaperDB.MEDIA_OPTIONS, mediaOptions);
					
				
				table.insert(document);
			}

		}catch(Exception e){
			System.out.println(e);
		}
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
					document.put(AirlineAndAirportsDB.CATEGORY, requestObj.get(AirlineAndAirportsDB.CATEGORY));
					document.put(AirlineAndAirportsDB.URL_SIUG, requestObj.get(AirlineAndAirportsDB.URL_SIUG));
					document.put(AirlineAndAirportsDB.NAME, requestObj.get(AirlineAndAirportsDB.NAME));
					List<String> geography = new ArrayList<>();  
					geography.add(requestObj.get(AirlineAndAirportsDB.GEOGRAPHY).toString());
					document.put(AirlineAndAirportsDB.GEOGRAPHY, geography);
					document.put(AirlineAndAirportsDB.LOGO, requestObj.get(AirlineAndAirportsDB.LOGO));
					document.put(AirlineAndAirportsDB.VIEWS, requestObj.get(AirlineAndAirportsDB.VIEWS));
					document.put(AirlineAndAirportsDB.SERVICE_TAX_PERCENTAGE, requestObj.get(AirlineAndAirportsDB.SERVICE_TAX_PERCENTAGE));
					document.put(AirlineAndAirportsDB.CARD_RATE, requestObj.get(AirlineAndAirportsDB.CARD_RATE));
					
					BasicDBObject mediaOptions = new BasicDBObject();
						JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(AirlineAndAirportsDB.MEDIA_OPTIONS);	
						if(mediaOptionsJSON != null){
							JSONObject digitalOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.DIGITAL_OPTIONS);
							BasicDBObject digitalOptions = new BasicDBObject();
							if(digitalOptionsJSON != null){
								for(int i = 0; i < digitalOptionsJSON.size(); i++){ 
									Object[] aaa = digitalOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) digitalOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)	digitalOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}
							mediaOptions.append(AirlineAndAirportsDB.DIGITAL_OPTIONS, digitalOptions);
							JSONObject printOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.PRINT_OPTION);
							BasicDBObject printOptions = new BasicDBObject();
							if(printOptionsJSON != null){
								for(int i = 0; i < printOptionsJSON.size(); i++){
									Object[] aaa = printOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) printOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)	printOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}
							mediaOptions.append(AirlineAndAirportsDB.PRINT_OPTION, printOptions);
							JSONObject aircraftOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.AIRCRAFT_OPTIONS);
							BasicDBObject aircraftOptions = new BasicDBObject();
							if(aircraftOptionsJSON != null){
								for(int i = 0; i < aircraftOptionsJSON.size(); i++){
									Object[] aaa = aircraftOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) aircraftOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)	aircraftOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}
							mediaOptions.append(AirlineAndAirportsDB.AIRCRAFT_OPTIONS, printOptions);
							JSONObject popularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.POPULAR_OPTION);
							BasicDBObject popularOptions = new BasicDBObject();
							if(popularOptionsJSON != null){
								for(int i = 0; i < popularOptionsJSON.size(); i++){
									Object[] aaa = popularOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) popularOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)	popularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}      
							mediaOptions.append(AirlineAndAirportsDB.POPULAR_OPTION, popularOptions);
						}
					BasicDBObject attributes = new BasicDBObject();	
						JSONObject attributesJSON =  (JSONObject) requestObj.get(AirlineAndAirportsDB.ATTRIBUTES);
						if(attributesJSON != null){
							for(int i = 0; i < attributesJSON.size(); i++){
								Object[] aaa = attributesJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
								if(jsonObject1 != null)	attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
						}
					document.append(AirlineAndAirportsDB.ATTRIBUTES, attributes);	
					document.append(AirlineAndAirportsDB.MEDIA_OPTIONS, mediaOptions);
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
	
				document.put(CinimasDB.TYPE, requestObj.get(CinimasDB.TYPE));
				document.put(CinimasDB.IS_SINGLE_SCREEN, requestObj.get(CinimasDB.IS_SINGLE_SCREEN));
				document.put(CinimasDB.THEATRE_NAME, requestObj.get(CinimasDB.THEATRE_NAME));
				document.put(CinimasDB.URL_SIUG, requestObj.get(CinimasDB.URL_SIUG));
				document.put(CinimasDB.VIEWS, requestObj.get(CinimasDB.VIEWS));
				document.put(CinimasDB.UNIQUE_ID, requestObj.get(CinimasDB.UNIQUE_ID));
				document.put(CinimasDB.SERVICE_TAX_PERCENTAGE, requestObj.get(CinimasDB.SERVICE_TAX_PERCENTAGE));
				document.put(CinimasDB.LOGO, requestObj.get(CinimasDB.LOGO));
				document.put(CinimasDB.CINEMA_CHAIN, requestObj.get(CinimasDB.CINEMA_CHAIN));
				document.put(CinimasDB.SEATS, requestObj.get(CinimasDB.SEATS));
				document.put(CinimasDB.MALL_NAME, requestObj.get(CinimasDB.MALL_NAME));
				document.put(CinimasDB.CARD_RATE, requestObj.get(CinimasDB.CARD_RATE));
				document.put(CinimasDB.CITY, requestObj.get(CinimasDB.CITY));
				document.put(CinimasDB.STATE, requestObj.get(CinimasDB.STATE));
				document.put(CinimasDB.LOCALITY, requestObj.get(CinimasDB.LOCALITY));
				document.put(CinimasDB.NAME, requestObj.get(CinimasDB.NAME));
				
						
				BasicDBObject mediaOptions = new BasicDBObject();
					
					JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(CinimasDB.MEDIA_OPTIONS);	
					if(mediaOptionsJSON != null){
						JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(CinimasDB.REGULAR_OPTION);
						BasicDBObject regularOptions = new BasicDBObject();
						if(regularOptionsJSON != null){
							for(int i = 0; i < regularOptionsJSON.size(); i++){
								Object[] aaa = regularOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
								if(jsonObject1 != null) regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
						}
						mediaOptions.append(CinimasDB.REGULAR_OPTION, regularOptions);
					}
						
				BasicDBObject attributes = new BasicDBObject();	
					
					JSONObject attributesJSON =  (JSONObject) requestObj.get(CinimasDB.ATTRIBUTES);
					if(attributesJSON != null){
						for(int i = 0; i < attributesJSON.size(); i++){  
							Object[] aaa = attributesJSON.keySet().toArray();
							JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
							if(jsonObject1 != null)	attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
						}
					}
				document.append(CinimasDB.ATTRIBUTES, attributes);	
				document.append(CinimasDB.MEDIA_OPTIONS, mediaOptions);
				
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
								
					document.put(NonTraditionalDB.URL_SIUG, requestObj.get(NonTraditionalDB.URL_SIUG));
					document.put(NonTraditionalDB.LOGO, requestObj.get(NonTraditionalDB.LOGO));
					document.put(NonTraditionalDB.NAME, requestObj.get(NonTraditionalDB.NAME));
					document.put(NonTraditionalDB.NOTE, requestObj.get(NonTraditionalDB.NOTE));
					document.put(NonTraditionalDB.SERVICE_TAX_PERCENTAGE, requestObj.get(NonTraditionalDB.SERVICE_TAX_PERCENTAGE));
							
					BasicDBObject mediaOptions = new BasicDBObject();
						
						JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(NonTraditionalDB.MEDIA_OPTIONS);	

						JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(NonTraditionalDB.REGULAR_OPTION);
						if( regularOptionsJSON != null){
							BasicDBObject regularOptions = new BasicDBObject();
							for(int i = 0; i < regularOptionsJSON.size(); i++){
								Object[] aaa = regularOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
								if( jsonObject1 != null) regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							mediaOptions.append(NonTraditionalDB.REGULAR_OPTION, regularOptions);
						}
						document.append(NonTraditionalDB.MEDIA_OPTIONS, mediaOptions);
						
						BasicDBObject attributes = new BasicDBObject();	
						
						JSONObject attributesJSON =  (JSONObject) requestObj.get(NonTraditionalDB.ATTRIBUTES);
						if(attributesJSON != null){
							for(int i = 0; i < attributesJSON.size(); i++){ 
								Object[] aaa = attributesJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
								if(jsonObject1 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
						}
						document.append(NonTraditionalDB.ATTRIBUTES, attributes);	
						
						
						
						BasicDBObject geography = new BasicDBObject();	
						
						JSONObject geographyJSON =  (JSONObject) requestObj.get(NonTraditionalDB.GEOGRAPHY);
						if(geographyJSON != null){
							for(int i = 0; i < geographyJSON.size(); i++){
								Object[] aaa = geographyJSON.keySet().toArray();
							//	JSONObject jsonObject =  (JSONObject) attributesJSON.get(attributesJSON.names().get(i).toString());
								geography.append(aaa[i].toString(), geographyJSON.get(aaa[i].toString()));
							}
						}
					
					document.append(NonTraditionalDB.GEOGRAPHY, geography);
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
						if(otherOptionsJSON != null){
							for(int i = 0; i < otherOptionsJSON.size(); i++){  
								Object[] aaa = otherOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) otherOptionsJSON.get(aaa[i].toString());
								if(jsonObject1 != null)  otherOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							mediaOptions.append(RadionDB.OTHER_OPTIONS, otherOptions);
						}
					
						JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.REGULAR_OPTION);
						BasicDBObject regularOptions = new BasicDBObject();
						if(regularOptionsJSON != null){
							for(int i = 0; i < regularOptionsJSON.size(); i++){
								Object[] aaa = regularOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
								if(jsonObject1 != null) regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
						}
						mediaOptions.append(RadionDB.REGULAR_OPTION, regularOptions);
						
						BasicDBObject attributes = new BasicDBObject();	
						JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
						if(attributesJSON != null){
							for(int i = 0; i < attributesJSON.size(); i++){  
								Object[] aaa = attributesJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
								if(jsonObject1 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							document.append(NewspaperDB.ATTRIBUTES, attributes);
						}
						document.append(RadionDB.MEDIA_OPTIONS, mediaOptions);	
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
					if(mediaOptionsJSON != null){
						JSONObject otherOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.OTHER_OPTIONS);
						BasicDBObject otherOptions = new BasicDBObject();
						if(otherOptionsJSON != null){
							for(int i = 0; i < otherOptionsJSON.size(); i++){  
								Object[] aaa = otherOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) otherOptionsJSON.get(aaa[i].toString());
								if(jsonObject1 != null) otherOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							mediaOptions.append(RadionDB.OTHER_OPTIONS, otherOptions);
						}
						JSONObject rjOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.RJ_OPTIONS);
						BasicDBObject rjOptions = new BasicDBObject();
						if(rjOptionsJSON != null){
							for(int i = 0; i < rjOptionsJSON.size(); i++){  
								Object[] aaa = rjOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) rjOptionsJSON.get(aaa[i].toString());
								if(jsonObject1 != null) rjOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							mediaOptions.append(RadionDB.RJ_OPTIONS, rjOptions);
						}
						JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(RadionDB.REGULAR_OPTION);
						BasicDBObject regularOptions = new BasicDBObject();
						if(regularOptionsJSON != null){
							for(int i = 0; i < regularOptionsJSON.size(); i++){
								Object[] aaa = regularOptionsJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
								if(jsonObject1 != null) regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							mediaOptions.append(RadionDB.REGULAR_OPTION, regularOptions);
						}
						document.append(RadionDB.MEDIA_OPTIONS, mediaOptions);	
					}
					BasicDBObject attributes = new BasicDBObject();	
					JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
					if(attributesJSON != null){
						for(int i = 0; i < attributesJSON.size(); i++){  
							Object[] aaa = attributesJSON.keySet().toArray();
							JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
							if(jsonObject1 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
						}
						document.append(NewspaperDB.ATTRIBUTES, attributes);
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
			System.out.println(jsonArray.size());
			for (Object object : jsonArray) {   
				BasicDBObject document = new BasicDBObject();
				JSONObject requestObj =  (JSONObject) object;
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
				if(mediaOptionsJSON != null){
					JSONObject regularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(TelevisionDB.REGULAR_OPTION);
					BasicDBObject regularOptions = new BasicDBObject();
					if(regularOptionsJSON != null){
						for(int i = 0; i < regularOptionsJSON.size(); i++){
							Object[] aaa = regularOptionsJSON.keySet().toArray();
							JSONObject jsonObject1 =  (JSONObject) regularOptionsJSON.get(aaa[i].toString());
							if(jsonObject1 != null) regularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
						}
						mediaOptions.append(TelevisionDB.REGULAR_OPTION, regularOptions);
					}
					JSONObject planningOptionsJSON =  (JSONObject) mediaOptionsJSON.get(TelevisionDB.PLANNING_OPTIONS);
					BasicDBObject planningOptions = new BasicDBObject();
					if(planningOptionsJSON != null){
						for(int i = 0; i < planningOptionsJSON.size(); i++){
							Object[] aaa = planningOptionsJSON.keySet().toArray();
							JSONObject jsonObject1 =  (JSONObject) planningOptionsJSON.get(aaa[i].toString());
							if(jsonObject1 != null) planningOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
						}
						mediaOptions.append(TelevisionDB.PLANNING_OPTIONS, planningOptions);
					}
					document.append(TelevisionDB.MEDIA_OPTIONS, mediaOptions);
				}
				BasicDBObject attributes = new BasicDBObject();	
				JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
				if(attributesJSON != null){
					for(int i = 0; i < attributesJSON.size(); i++){  
						Object[] aaa = attributesJSON.keySet().toArray();
						JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
						if(jsonObject1 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
					}
					document.append(TelevisionDB.ATTRIBUTES, attributes);
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
			System.out.println(jsonArray.size());
				for (Object object : jsonArray) {   
					BasicDBObject document = new BasicDBObject();
					JSONObject requestObj =  (JSONObject) object;
					
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
					
					BasicDBObject attributes = new BasicDBObject();	
					JSONObject attributesJSON =  (JSONObject) requestObj.get(NewspaperDB.ATTRIBUTES);
					if(attributesJSON != null){
						for(int i = 0; i < attributesJSON.size(); i++){ 
							Object[] aaa = attributesJSON.keySet().toArray();
							JSONObject jsonObject1 =  (JSONObject) attributesJSON.get(aaa[i].toString());
							if(jsonObject1 != null) attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
						}
						document.append(NewspaperDB.ATTRIBUTES, attributes);
					}
							
					BasicDBObject mediaOptions = new BasicDBObject();
						
					JSONObject mediaOptionsJSON =  (JSONObject) requestObj.get(DigitalDB.MEDIA_OPTIONS);
					if(mediaOptionsJSON != null){
						Object[] aaa1 = mediaOptionsJSON.keySet().toArray();
						for(int k = 0; k < aaa1.length; k++){
							BasicDBObject costPerView = new BasicDBObject();
							System.out.println("k "+k+"  name   "+aaa1[k]);
							JSONObject costPerViewJSON =  (JSONObject) mediaOptionsJSON.get(aaa1[k]);
							if(costPerViewJSON != null){
								for(int i = 0; i < costPerViewJSON.size(); i++){  
									Object[] aaa = costPerViewJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) costPerViewJSON.get(aaa[i].toString());
									if(jsonObject1 != null) costPerView.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
								System.out.println("costPerView  "+costPerView);
								mediaOptions.append(aaa1[k].toString(), costPerView);
							}
						}
						document.append(DigitalDB.MEDIA_OPTIONS, mediaOptions);
					}
					
					
					
					/*System.out.println("length "+aaa1.length+"  01   "+aaa1[0]);
					
						JSONObject costPerViewJSON =  (JSONObject) mediaOptionsJSON.get(DigitalDB.COST_PER_VIEW);
						BasicDBObject costPerView = new BasicDBObject();
						if(costPerViewJSON != null){
							for(int i = 0; i < costPerViewJSON.size(); i++){  
								Object[] aaa = costPerViewJSON.keySet().toArray();
								JSONObject jsonObject1 =  (JSONObject) costPerViewJSON.get(aaa[i].toString());
								if(jsonObject1 != null) costPerView.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
							mediaOptions.append(DigitalDB.COST_PER_VIEW, costPerView);
						}      
						document.append(DigitalDB.MEDIA_OPTIONS, mediaOptions);	
					}  */ 
					
					
					
					
					table.insert(document);   
				}
					
				
			}catch(Exception e){ System.out.println(e);	}
	}
}
