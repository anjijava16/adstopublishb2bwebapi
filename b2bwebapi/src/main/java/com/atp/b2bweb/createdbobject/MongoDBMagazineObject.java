package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.db.MagazineDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoDBMagazineObject {
	
	public static DBObject createMagazineDBObject(JSONObject requestObj) {
		
		BasicDBObject document = new BasicDBObject();
		try {
			document.put(MagazineDB.TOOL_ID, requestObj.get(MagazineDB.TOOL_ID));
			document.put(MagazineDB.LOGO, requestObj.get(MagazineDB.LOGO));
			document.put(MagazineDB.THUMBNAIL, requestObj.get(MagazineDB.THUMBNAIL));
			document.put(MagazineDB.VIEWS, requestObj.get(MagazineDB.VIEWS));
			document.put(MagazineDB.CREATED_BY, requestObj.get(MagazineDB.CREATED_BY));
			document.put(MagazineDB.NAME, requestObj.get(MagazineDB.NAME));
			document.put(MagazineDB.URL_SIUG, requestObj.get(MagazineDB.URL_SIUG));
			List<String> categoryId = new ArrayList<>();  
			categoryId.add(requestObj.get(MagazineDB.CATEGORY_ID).toString());
			document.put(MagazineDB.CATEGORY_ID, categoryId);
			document.put(MagazineDB.IRS, requestObj.get(MagazineDB.IRS));
			List<String> keywords = new ArrayList<>();  
			keywords.add(requestObj.get(MagazineDB.KEYWORDS).toString());
			document.put(MagazineDB.KEYWORDS, keywords);
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get(MagazineDB.GEOGRAPHY).toString());
			document.put(MagazineDB.GEOGRAPHY, geography);
			document.put(MagazineDB.SERVICE_TAX_PERCENTAGE, requestObj.get(MagazineDB.SERVICE_TAX_PERCENTAGE));
			document.put(MagazineDB.CATEGORY_NAME, requestObj.get(MagazineDB.CATEGORY_NAME));
			document.put(MagazineDB.CARD_RATE, requestObj.get(MagazineDB.CARD_RATE));
			document.put(MagazineDB.DISCOUNT_RATE, requestObj.get(MagazineDB.DISCOUNT_RATE));
		
			
			BasicDBObject documentDetails = new BasicDBObject();
			
				BasicDBObject documentDetail = new BasicDBObject();			
				documentDetail.put(MagazineDB.VALUE, requestObj.get("attrcategoryName") != null ? requestObj.get("attrcategoryName"): CommonConstants.EMPTY);					
				BasicDBObject documentDetail1 = new BasicDBObject();
				documentDetail1.put(MagazineDB.VALUE, requestObj.get("attrcirculation") != null ? requestObj.get("attrcirculation"): CommonConstants.EMPTY);				
				BasicDBObject documentDetail2 = new BasicDBObject();
				documentDetail2.put(MagazineDB.VALUE, requestObj.get("attrfrequency") != null ? requestObj.get("attrfrequency"): CommonConstants.EMPTY);
				BasicDBObject documentDetail3 = new BasicDBObject();
				documentDetail3.put(MagazineDB.VALUE, requestObj.get("attrlanguage") != null ? requestObj.get("attrlanguage"): CommonConstants.EMPTY);
				
				documentDetails.put(MagazineDB.CATEGORY_NAME,documentDetail);
				documentDetails.put(MagazineDB.CIRCULATION,documentDetail1);
				documentDetails.put(MagazineDB.FREQUENCY,documentDetail2);
				documentDetails.put(MagazineDB.LANGUAGE,documentDetail3);
				
				document.put(MagazineDB.ATTRIBUTES, documentDetails);
		
		
				BasicDBObject mediaDetails = new BasicDBObject();
					BasicDBObject regularOptions = new BasicDBObject();
					
						BasicDBObject fullPage = new BasicDBObject();
							fullPage.put(MagazineDB.CARD_RATE, requestObj.get("fpcardrate") != null ? requestObj.get("fpcardrate"): CommonConstants.EMPTY);
							fullPage.put(MagazineDB.ONE_TO_TWO,  requestObj.get("fp1to2") != null ? requestObj.get("fp1to2"): CommonConstants.EMPTY);
							fullPage.put(MagazineDB.THREE_TO_SIX,  requestObj.get("fp3to6") != null ? requestObj.get("fp3to6"): CommonConstants.EMPTY);
							fullPage.put(MagazineDB.SEVEN_PLUS,  requestObj.get("fp7plus") != null ? requestObj.get("fp7plus"): CommonConstants.EMPTY);
							fullPage.put(MagazineDB.IMAGE_URL,  requestObj.get("fpimageurl") != null ? requestObj.get("fp7plus"): CommonConstants.EMPTY);
							
							BasicDBObject mediaAntMarginPercentage = new BasicDBObject();
								mediaAntMarginPercentage.put(MagazineDB.ONE_TO_TWO, requestObj.get("fppercent1to2") != null ? requestObj.get("fppercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage.put(MagazineDB.THREE_TO_SIX, requestObj.get("fppercent3to6") != null ? requestObj.get("fppercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage.put(MagazineDB.SEVEN_PLUS,  requestObj.get("fppercent7plus") != null ? requestObj.get("fppercent7plus"): CommonConstants.EMPTY);
								fullPage.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage);
							
					   BasicDBObject halfPage = new BasicDBObject();
							halfPage.put(MagazineDB.CARD_RATE, requestObj.get("hpcardrate") != null ? requestObj.get("hpcardrate"): CommonConstants.EMPTY);
							halfPage.put(MagazineDB.ONE_TO_TWO,  requestObj.get("hp1to2") != null ? requestObj.get("hp1to2"): CommonConstants.EMPTY);
							halfPage.put(MagazineDB.THREE_TO_SIX,  requestObj.get("hp3to6") != null ? requestObj.get("hp3to6"): CommonConstants.EMPTY);
							halfPage.put(MagazineDB.SEVEN_PLUS,  requestObj.get("hp7plus") != null ? requestObj.get("hp7plus"): CommonConstants.EMPTY);
							halfPage.put(MagazineDB.IMAGE_URL,  requestObj.get("hpimageurl") != null ? requestObj.get("hpimageurl"): CommonConstants.EMPTY);
						
							BasicDBObject mediaAntMarginPercentage0 = new BasicDBObject();
								mediaAntMarginPercentage0.put(MagazineDB.ONE_TO_TWO, requestObj.get("hppercent1to2") != null ? requestObj.get("hppercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage0.put(MagazineDB.THREE_TO_SIX, requestObj.get("hppercent3to6") != null ? requestObj.get("hppercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage0.put(MagazineDB.SEVEN_PLUS,  requestObj.get("hppercent7plus") != null ? requestObj.get("hppercent7plus"): CommonConstants.EMPTY);
								halfPage.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage0);
							
	                   BasicDBObject insideFrontCover = new BasicDBObject();
							insideFrontCover.put(MagazineDB.CARD_RATE, requestObj.get("ifccardrate") != null ? requestObj.get("ifccardrate"): CommonConstants.EMPTY);
							insideFrontCover.put(MagazineDB.ONE_TO_TWO, requestObj.get("ifc1to2") != null ? requestObj.get("ifc1to2"): CommonConstants.EMPTY);
							insideFrontCover.put(MagazineDB.THREE_TO_SIX, requestObj.get("ifc3to6") != null ? requestObj.get("ifc3to6"): CommonConstants.EMPTY);
							insideFrontCover.put(MagazineDB.SEVEN_PLUS, requestObj.get("ifc7plus") != null ? requestObj.get("ifc7plus"): CommonConstants.EMPTY);
							insideFrontCover.put(MagazineDB.IMAGE_URL, requestObj.get("ifcimageUrl") != null ? requestObj.get("ifcimageUrl"): CommonConstants.EMPTY);
						
							BasicDBObject mediaAntMarginPercentage1 = new BasicDBObject();
								mediaAntMarginPercentage1.put(MagazineDB.ONE_TO_TWO, requestObj.get("ifcpercent1to2") != null ? requestObj.get("ifcpercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage1.put(MagazineDB.THREE_TO_SIX, requestObj.get("ifcpercent3to6") != null ? requestObj.get("ifcpercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage1.put(MagazineDB.SEVEN_PLUS,  requestObj.get("ifcpercent7plus") != null ? requestObj.get("ifcpercent7plus"): CommonConstants.EMPTY);
								insideFrontCover.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage1);
	
								
								
						BasicDBObject insideBackCover = new BasicDBObject();
							insideBackCover.put(MagazineDB.CARD_RATE,  requestObj.get("ibccardrate") != null ? requestObj.get("ibccardrate"): CommonConstants.EMPTY);
							insideBackCover.put(MagazineDB.ONE_TO_TWO, requestObj.get("ibc1to2") != null ? requestObj.get("ibc1to2"): CommonConstants.EMPTY);
							insideBackCover.put(MagazineDB.THREE_TO_SIX, requestObj.get("ibc3to6") != null ? requestObj.get("ibc3to6"): CommonConstants.EMPTY);
							insideBackCover.put(MagazineDB.SEVEN_PLUS, requestObj.get("ibc7plus") != null ? requestObj.get("ibc7plus"): CommonConstants.EMPTY);
							insideBackCover.put(MagazineDB.IMAGE_URL, requestObj.get("ibcimageUrl") != null ? requestObj.get("ibcimageUrl"): CommonConstants.EMPTY);
						
							BasicDBObject mediaAntMarginPercentage2 = new BasicDBObject();
								mediaAntMarginPercentage2.put(MagazineDB.ONE_TO_TWO, requestObj.get("ibcpercent1to2") != null ? requestObj.get("ibcpercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage2.put(MagazineDB.THREE_TO_SIX, requestObj.get("ibcpercent3to6") != null ? requestObj.get("ibcpercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage2.put(MagazineDB.SEVEN_PLUS,  requestObj.get("ibcpercent7plus") != null ? requestObj.get("ibcpercent7plus"): CommonConstants.EMPTY);
								insideBackCover.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage2);
							
						
					
						BasicDBObject backCover = new BasicDBObject();
							backCover.put(MagazineDB.CARD_RATE, requestObj.get("bccardrate") != null ? requestObj.get("bccardrate"): CommonConstants.EMPTY);
							backCover.put(MagazineDB.ONE_TO_TWO, requestObj.get("bc1to2") != null ? requestObj.get("bc1to2"): CommonConstants.EMPTY);
							backCover.put(MagazineDB.THREE_TO_SIX, requestObj.get("bc3to6") != null ? requestObj.get("bc3to6"): CommonConstants.EMPTY);
							backCover.put(MagazineDB.SEVEN_PLUS, requestObj.get("bc7plus") != null ? requestObj.get("bc7plus"): CommonConstants.EMPTY);
							backCover.put(MagazineDB.IMAGE_URL,  requestObj.get("bcimageUrl") != null ? requestObj.get("bcimageUrl"): CommonConstants.EMPTY);
							
							BasicDBObject mediaAntMarginPercentage3 = new BasicDBObject();
								mediaAntMarginPercentage3.put(MagazineDB.ONE_TO_TWO, requestObj.get("bcpercent1to2") != null ? requestObj.get("bcpercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage3.put(MagazineDB.THREE_TO_SIX, requestObj.get("bcpercent3to6") != null ? requestObj.get("bcpercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage3.put(MagazineDB.SEVEN_PLUS,  requestObj.get("bcpercent7plus") != null ? requestObj.get("bcpercent7plus"): CommonConstants.EMPTY);
								backCover.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage3);	
				
						BasicDBObject doubleSpread = new BasicDBObject();
							doubleSpread.put(MagazineDB.CARD_RATE, requestObj.get("dscardrate") != null ? requestObj.get("dscardrate"): CommonConstants.EMPTY);
							doubleSpread.put(MagazineDB.ONE_TO_TWO, requestObj.get("ds1to2") != null ? requestObj.get("ds1to2"): CommonConstants.EMPTY);
							doubleSpread.put(MagazineDB.THREE_TO_SIX, requestObj.get("ds3to6") != null ? requestObj.get("ds3to6"): CommonConstants.EMPTY);
							doubleSpread.put(MagazineDB.SEVEN_PLUS,  requestObj.get("ds7plus") != null ? requestObj.get("ds7plus"): CommonConstants.EMPTY);
							doubleSpread.put(MagazineDB.IMAGE_URL, requestObj.get("dsimageUrl") != null ? requestObj.get("dsimageUrl"): CommonConstants.EMPTY);
							
							BasicDBObject mediaAntMarginPercentage4 = new BasicDBObject();
								mediaAntMarginPercentage4.put(MagazineDB.ONE_TO_TWO, requestObj.get("dspercent1to2") != null ? requestObj.get("dspercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage4.put(MagazineDB.THREE_TO_SIX, requestObj.get("dspercent3to6") != null ? requestObj.get("dspercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage4.put(MagazineDB.SEVEN_PLUS, requestObj.get("dspercent7plus") != null ? requestObj.get("dspercent7plus"): CommonConstants.EMPTY);
								doubleSpread.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage4);
							
						
						BasicDBObject centerDoubleSpread = new BasicDBObject();
							centerDoubleSpread.put(MagazineDB.CARD_RATE, requestObj.get("cdscardrate") != null ? requestObj.get("cdscardrate"): CommonConstants.EMPTY);
							centerDoubleSpread.put(MagazineDB.ONE_TO_TWO, requestObj.get("cds1to2") != null ? requestObj.get("cds1to2"): CommonConstants.EMPTY);
							centerDoubleSpread.put(MagazineDB.THREE_TO_SIX, requestObj.get("cds3to6") != null ? requestObj.get("cds3to6"): CommonConstants.EMPTY);
							centerDoubleSpread.put(MagazineDB.SEVEN_PLUS, requestObj.get("cds7plus") != null ? requestObj.get("cds7plus"): CommonConstants.EMPTY);
							centerDoubleSpread.put(MagazineDB.IMAGE_URL, requestObj.get("cdsimageUrl") != null ? requestObj.get("cdsimageUrl"): CommonConstants.EMPTY);
							
							BasicDBObject mediaAntMarginPercentage5 = new BasicDBObject();
								mediaAntMarginPercentage5.put(MagazineDB.ONE_TO_TWO, requestObj.get("cdspercent1to2") != null ? requestObj.get("cdspercent1to2"): CommonConstants.EMPTY);
								mediaAntMarginPercentage5.put(MagazineDB.THREE_TO_SIX, requestObj.get("cdspercent3to6") != null ? requestObj.get("cdspercent3to6"): CommonConstants.EMPTY);
								mediaAntMarginPercentage5.put(MagazineDB.SEVEN_PLUS,  requestObj.get("cdspercent7plus") != null ? requestObj.get("cdspercent7plus"): CommonConstants.EMPTY);
								centerDoubleSpread.put(MagazineDB.MEDIA_ANT_MARIGING_PERCENTAGE, mediaAntMarginPercentage5);
							
							
						regularOptions.put(MagazineDB.FULL_PAGE, fullPage);
						regularOptions.put(MagazineDB.INSIDE_FRONT_COVER, insideFrontCover);
						regularOptions.put(MagazineDB.INSIDE_BACK_COVER, insideBackCover);
						regularOptions.put(MagazineDB.BACK_COVER, backCover);
						regularOptions.put(MagazineDB.DOUBLE_SPREAD, doubleSpread);
						regularOptions.put(MagazineDB.CENTRAL_DOUBLE_SPREAD, centerDoubleSpread);
						
					mediaDetails.put(MagazineDB.REGULAR_OPTION, regularOptions);	
					
				document.put(MagazineDB.MEDIA_DETAILS, mediaDetails);		
					
				document.put(CommonConstants.UPDATEDBY, requestObj.get(CommonConstants.VENDORID));
				document.put(CommonConstants.UPDATEDON, new Date());	
		} catch (Exception e) {
			System.out.println("exception in mondodbmagazineobject java "+e);
		}
		System.out.println("document----------------------"+document);
		return document;
	}

}
