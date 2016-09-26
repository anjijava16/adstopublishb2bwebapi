package com.atp.b2bweb.util;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
			
			System.out.println("---------"+jsonArray.size()); 
			
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

}
