package com.atp.b2bweb.util;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.atp.b2bweb.db.AirlineAndAirportsDB;
import com.atp.b2bweb.db.NewspaperDB;
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
				document.put("name1", "times of india");
				document.put("state", "ka");
				document.put("res", "ban");
				BasicDBObject documentDetails = new BasicDBObject();				
				    JSONObject jsonObject2 =  (JSONObject) jsonObject1.get("attributes");				
					BasicDBObject documentDetail = new BasicDBObject();
					JSONObject jsonObject3 = (JSONObject) jsonObject2.get("categoryName");
					documentDetail.put("value", jsonObject3 != null ? jsonObject3.get("value"): "");					
					BasicDBObject documentDetail1 = new BasicDBObject();
					JSONObject jsonObject4 = (JSONObject) jsonObject2.get("circulation");
					documentDetail1.put("value", jsonObject4 != null ? jsonObject4.get("value"):"" );					
					BasicDBObject documentDetail2 = new BasicDBObject();
					JSONObject jsonObject5 = (JSONObject) jsonObject2.get("frequency");
					documentDetail2.put("value", jsonObject5 != null ? jsonObject5.get("value") : "");
					BasicDBObject documentDetail3 = new BasicDBObject();
					JSONObject jsonObject6 = (JSONObject) jsonObject2.get("language");
					documentDetail3.put("value", jsonObject6 != null ? jsonObject6.get("value") : "");
					
					documentDetails.put("categoryName",documentDetail);
					documentDetails.put("circulation",documentDetail1);
					documentDetails.put("frequency",documentDetail2);
					documentDetails.put("language",documentDetail3);
					
					document.put("attributes", documentDetails);
			
			
			BasicDBObject mediaDetails = new BasicDBObject();
				BasicDBObject regularOptions = new BasicDBObject();
				    JSONObject jsonObject7 = (JSONObject) jsonObject1.get("mediaOptions");
				    JSONObject jsonObject8 =  (JSONObject) jsonObject7.get("regularOptions");
				  
				    JSONObject jsonObject9 =  (JSONObject) jsonObject8.get("fullPage");
					BasicDBObject fullPage = new BasicDBObject();
					if(jsonObject9 != null){
						fullPage.put("cardRate", jsonObject9.get("cardRate"));
						fullPage.put("1-2",  jsonObject9.get("1-2"));
						fullPage.put("3-6",  jsonObject9.get("3-6"));
						fullPage.put("7+",  jsonObject9.get("7+"));
						fullPage.put("imageUrl",  jsonObject9.get("imageUrl"));
						
						BasicDBObject mediaAntMarginPercentage = new BasicDBObject();
						JSONObject jsonObject10 =  (JSONObject) jsonObject9.get("mediaAntMarginPercentage");
						if(jsonObject10 != null){
							mediaAntMarginPercentage.put("1-2", jsonObject10.get("1-2"));
							mediaAntMarginPercentage.put("3-6",  jsonObject10.get("3-6"));
							mediaAntMarginPercentage.put("7+",  jsonObject10.get("7+"));
							fullPage.put("mediaAntMarginPercentage", mediaAntMarginPercentage);
						}
					}
						
					BasicDBObject halfPage = new BasicDBObject();
					JSONObject halfPageJSON =  (JSONObject) jsonObject8.get("halfPage");
					if(halfPageJSON != null){
						halfPage.put("cardRate", halfPageJSON.get("cardRate"));
						halfPage.put("1-2",  halfPageJSON.get("1-2"));
						halfPage.put("3-6",  halfPageJSON.get("3-6"));
						halfPage.put("7+",  halfPageJSON.get("7+"));
						halfPage.put("imageUrl",  halfPageJSON.get("imageUrl"));
						
						BasicDBObject mediaAntMarginPercentage0 = new BasicDBObject();
						JSONObject halfPagemediaAntJSON =  (JSONObject) halfPageJSON.get("mediaAntMarginPercentage");
						if(halfPagemediaAntJSON != null){
							mediaAntMarginPercentage0.put("1-2", halfPagemediaAntJSON.get("1-2"));
							mediaAntMarginPercentage0.put("3-6",  halfPagemediaAntJSON.get("3-6"));
							mediaAntMarginPercentage0.put("7+",  halfPagemediaAntJSON.get("7+"));
							halfPage.put("mediaAntMarginPercentage", mediaAntMarginPercentage0);
						}
					}	
						
					BasicDBObject insideFrontCover = new BasicDBObject();
					JSONObject insideFrontCoverJSON =  (JSONObject) jsonObject8.get("insideFrontCover");
					if(insideFrontCoverJSON != null){
						insideFrontCover.put("cardRate", insideFrontCoverJSON.get("cardRate"));
						insideFrontCover.put("1-2", insideFrontCoverJSON.get("1-2"));
						insideFrontCover.put("3-6", insideFrontCoverJSON.get("3-6"));
						insideFrontCover.put("7+", insideFrontCoverJSON.get("7+"));
						insideFrontCover.put("imageUrl", insideFrontCoverJSON.get("imageUrl"));
						
						BasicDBObject mediaAntMarginPercentage1 = new BasicDBObject();
						JSONObject insideFrontCovermediaAntJSON =  (JSONObject) insideFrontCoverJSON.get("mediaAntMarginPercentage");
						if(insideFrontCovermediaAntJSON != null){
							mediaAntMarginPercentage1.put("1-2", insideFrontCovermediaAntJSON.get("1-2"));
							mediaAntMarginPercentage1.put("3-6",  insideFrontCovermediaAntJSON.get("3-6"));
							mediaAntMarginPercentage1.put("7+",  insideFrontCovermediaAntJSON.get("7+"));
							insideFrontCover.put("mediaAntMarginPercentage", mediaAntMarginPercentage1);
						}
					}	
					
					BasicDBObject insideBackCover = new BasicDBObject();
					JSONObject insideBackCoverJSON =  (JSONObject) jsonObject8.get("insideBackCover");
					if(insideBackCoverJSON != null){
						insideBackCover.put("cardRate", insideBackCoverJSON.get("cardRate"));
						insideBackCover.put("1-2", insideBackCoverJSON.get("1-2"));
						insideBackCover.put("3-6", insideBackCoverJSON.get("3-6"));
						insideBackCover.put("7+", insideBackCoverJSON.get("7+"));
						insideBackCover.put("imageUrl", insideBackCoverJSON.get("cardRate"));
					
						BasicDBObject mediaAntMarginPercentage2 = new BasicDBObject();
						JSONObject insideBackCovermediaAntJSON =  (JSONObject) insideBackCoverJSON.get("mediaAntMarginPercentage");
						if(insideBackCovermediaAntJSON != null){
							mediaAntMarginPercentage2.put("1-2", insideBackCovermediaAntJSON.get("1-2"));
							mediaAntMarginPercentage2.put("3-6", insideBackCovermediaAntJSON.get("3-6"));
							mediaAntMarginPercentage2.put("7+",  insideBackCovermediaAntJSON.get("7+"));
							insideBackCover.put("mediaAntMarginPercentage", mediaAntMarginPercentage2);
						}
					
						
					}
					
				
					BasicDBObject backCover = new BasicDBObject();
					JSONObject backCoverJSON =  (JSONObject) jsonObject8.get("backCover");
					if(backCoverJSON != null){
						backCover.put("cardRate", backCoverJSON.get("cardRate"));
						backCover.put("1-2", backCoverJSON.get("1-2"));
						backCover.put("3-6", backCoverJSON.get("3-6"));
						backCover.put("7+", backCoverJSON.get("7+"));
						backCover.put("imageUrl", backCoverJSON.get("imageUrl"));
						
						BasicDBObject mediaAntMarginPercentage3 = new BasicDBObject();
						JSONObject backCovermediaantJSON =  (JSONObject) backCoverJSON.get("mediaAntMarginPercentage");
						if(backCovermediaantJSON != null){
							mediaAntMarginPercentage3.put("1-2", backCovermediaantJSON.get("1-2"));
							mediaAntMarginPercentage3.put("3-6",  backCovermediaantJSON.get("3-6"));
							mediaAntMarginPercentage3.put("7+",  backCovermediaantJSON.get("7+"));
							backCover.put("mediaAntMarginPercentage", mediaAntMarginPercentage3);	
						}
			
					}
					
					
					BasicDBObject doubleSpread = new BasicDBObject();
					JSONObject doubleSpreadJSON =  (JSONObject) jsonObject8.get("doubleSpread");
					if(doubleSpreadJSON != null){
						doubleSpread.put("cardRate", doubleSpreadJSON.get("cardRate"));
						doubleSpread.put("1-2", doubleSpreadJSON.get("1-2"));
						doubleSpread.put("3-6", doubleSpreadJSON.get("3-6"));
						doubleSpread.put("7+", doubleSpreadJSON.get("7+"));
						doubleSpread.put("imageUrl", doubleSpreadJSON.get("imageUrl"));
						
						BasicDBObject mediaAntMarginPercentage4 = new BasicDBObject();
						JSONObject doubleSpreadmediaantJSON =  (JSONObject) doubleSpreadJSON.get("mediaAntMarginPercentage");
						if(doubleSpreadmediaantJSON != null){
							mediaAntMarginPercentage4.put("1-2", doubleSpreadmediaantJSON.get("1-2"));
							mediaAntMarginPercentage4.put("3-6",  doubleSpreadmediaantJSON.get("3-6"));
							mediaAntMarginPercentage4.put("7+",  doubleSpreadmediaantJSON.get("7+"));
							doubleSpread.put("mediaAntMarginPercentage", mediaAntMarginPercentage4);
						}
			
					}
						
					
					BasicDBObject centerDoubleSpread = new BasicDBObject();
					JSONObject centerDoubleSpreadJSON =  (JSONObject) jsonObject8.get("centerDoubleSpread");
					if(centerDoubleSpreadJSON != null){
						centerDoubleSpread.put("cardRate", centerDoubleSpreadJSON.get("cardRate"));
						centerDoubleSpread.put("1-2", centerDoubleSpreadJSON.get("1-2"));
						centerDoubleSpread.put("3-6", centerDoubleSpreadJSON.get("3-6"));
						centerDoubleSpread.put("7+", centerDoubleSpreadJSON.get("7+"));
						centerDoubleSpread.put("imageUrl", centerDoubleSpreadJSON.get("imageUrl"));
						
						BasicDBObject mediaAntMarginPercentage5 = new BasicDBObject();
						JSONObject centerDoubleSpreadmediaantJSON =  (JSONObject) centerDoubleSpreadJSON.get("mediaAntMarginPercentage");
						if(centerDoubleSpreadmediaantJSON != null){
							mediaAntMarginPercentage5.put("1-2", centerDoubleSpreadmediaantJSON.get("1-2"));
							mediaAntMarginPercentage5.put("3-6",  centerDoubleSpreadmediaantJSON.get("3-6"));
							mediaAntMarginPercentage5.put("7+",  centerDoubleSpreadmediaantJSON.get("7+"));
							centerDoubleSpread.put("mediaAntMarginPercentage", mediaAntMarginPercentage5);
						}
					}
						
						
					regularOptions.put("fullPage", fullPage);
					regularOptions.put("insideFrontCover", insideFrontCover);
					regularOptions.put("insideBackCover", insideBackCover);
					regularOptions.put("backCover", backCover);
					regularOptions.put("doubleSpread", doubleSpread);
					regularOptions.put("centerDoubleSpread", centerDoubleSpread);
					
				mediaDetails.put("regularOptions", regularOptions);	
				
			document.put("mediaDetails", mediaDetails);			
		
						
			table.insert(document);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
				basicDBObject.append(aaa[i].toString(), jsonObj.get(aaa[i].toString()) != null ? jsonObj.get(aaa[i].toString()) : "");
			}
		}
		return basicDBObject;
	}
	
	public static void addAirlineAndAirporttodb(){
		JSONParser parser = new JSONParser();
		try {
			
			DB db = ConnectToMongoDB.connect();
			DBCollection table = db.getCollection("airlineandairports");

			Object obj = parser.parse(new FileReader("C:/Users/SAPTALABS/Documents/GitHub/adstopublish/Master Data/AirlineAndAirports/AirlineAndAirports .txt"));
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
									if(jsonObject1 != null)
									digitalOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}
							mediaOptions.append(AirlineAndAirportsDB.DIGITAL_OPTIONS, digitalOptions);
							
							JSONObject printOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.PRINT_OPTION);
							BasicDBObject printOptions = new BasicDBObject();
							if(printOptionsJSON != null){
								for(int i = 0; i < printOptionsJSON.size(); i++){
									Object[] aaa = printOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) printOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)
									printOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}
							mediaOptions.append(AirlineAndAirportsDB.PRINT_OPTION, printOptions);
							JSONObject aircraftOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.AIRCRAFT_OPTIONS);
							BasicDBObject aircraftOptions = new BasicDBObject();
							if(aircraftOptionsJSON != null){
								for(int i = 0; i < aircraftOptionsJSON.size(); i++){
									Object[] aaa = aircraftOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) aircraftOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)
									aircraftOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
								}
							}
							mediaOptions.append(AirlineAndAirportsDB.AIRCRAFT_OPTIONS, printOptions);
							JSONObject popularOptionsJSON =  (JSONObject) mediaOptionsJSON.get(AirlineAndAirportsDB.POPULAR_OPTION);
							BasicDBObject popularOptions = new BasicDBObject();
							if(popularOptionsJSON != null){
								for(int i = 0; i < popularOptionsJSON.size(); i++){
									Object[] aaa = popularOptionsJSON.keySet().toArray();
									JSONObject jsonObject1 =  (JSONObject) popularOptionsJSON.get(aaa[i].toString());
									if(jsonObject1 != null)
									popularOptions.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
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
								if(jsonObject1 != null)
								attributes.append(aaa[i].toString(), getBasicDBObject(jsonObject1));
							}
						}
					document.append(AirlineAndAirportsDB.ATTRIBUTES, attributes);	
					document.append(AirlineAndAirportsDB.MEDIA_OPTIONS, mediaOptions);
					
				//table.insert(document);
			}

		}catch(Exception e){
			System.out.println(e);
		}
		
	}
	
	
}
