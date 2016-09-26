package com.atp.b2bweb.createdbobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MongoDBMagazineObject {
	
	public static DBObject createMagazineDBObject(JSONObject requestObj) {
		
		BasicDBObject document = new BasicDBObject();
		try {
			document.put("toolId", requestObj.get("toolId"));
			document.put("logo", requestObj.get("logo"));
			document.put("thumbnail", requestObj.get("thumbnail"));
			document.put("views", requestObj.get("views"));
			document.put("createdBy", requestObj.get("createdBy"));
			document.put("name", requestObj.get("name"));
			document.put("urlSlug", requestObj.get("urlSlug"));
			List<String> categoryId = new ArrayList<>();  
			categoryId.add(requestObj.get("categoryId").toString());
			document.put("categoryId", categoryId);
			document.put("IRS", requestObj.get("IRS"));
			List<String> keywords = new ArrayList<>();  
			keywords.add(requestObj.get("keywords").toString());
			document.put("keywords", keywords);
			List<String> geography = new ArrayList<>();  
			geography.add(requestObj.get("geography").toString());
			document.put("geography", geography);
			document.put("serviceTaxPercentage", requestObj.get("serviceTaxPercentage"));
			document.put("categoryName", requestObj.get("categoryName"));
			document.put("cardRate", requestObj.get("cardRate"));
			document.put("discountedRate", requestObj.get("discountedRate"));
		
			
			BasicDBObject documentDetails = new BasicDBObject();
			
				BasicDBObject documentDetail = new BasicDBObject();			
				documentDetail.put("value", requestObj.get("attrcategoryName") != null ? requestObj.get("attrcategoryName"): "");					
				BasicDBObject documentDetail1 = new BasicDBObject();
				documentDetail1.put("value", requestObj.get("attrcirculation") != null ? requestObj.get("attrcirculation"): "");				
				BasicDBObject documentDetail2 = new BasicDBObject();
				documentDetail2.put("value", requestObj.get("attrfrequency") != null ? requestObj.get("attrfrequency"): "");
				BasicDBObject documentDetail3 = new BasicDBObject();
				documentDetail3.put("value", requestObj.get("attrlanguage") != null ? requestObj.get("attrlanguage"): "");
				
				documentDetails.put("categoryname",documentDetail);
				documentDetails.put("circulation",documentDetail1);
				documentDetails.put("frequency",documentDetail2);
				documentDetails.put("language",documentDetail3);
				
				document.put("attributes", documentDetails);
		
		
				BasicDBObject mediaDetails = new BasicDBObject();
					BasicDBObject regularOptions = new BasicDBObject();
					
						BasicDBObject fullPage = new BasicDBObject();
							fullPage.put("cardRate", requestObj.get("fpcardrate") != null ? requestObj.get("fpcardrate"): "");
							fullPage.put("1-2",  requestObj.get("fp1to2") != null ? requestObj.get("fp1to2"): "");
							fullPage.put("3-6",  requestObj.get("fp3to6") != null ? requestObj.get("fp3to6"): "");
							fullPage.put("7+",  requestObj.get("fp7plus") != null ? requestObj.get("fp7plus"): "");
							fullPage.put("imageUrl",  requestObj.get("fpimageurl") != null ? requestObj.get("fp7plus"): "");
							
							BasicDBObject mediaAntMarginPercentage = new BasicDBObject();
								mediaAntMarginPercentage.put("1-2", requestObj.get("fppercent1to2") != null ? requestObj.get("fppercent1to2"): "");
								mediaAntMarginPercentage.put("3-6", requestObj.get("fppercent3to6") != null ? requestObj.get("fppercent3to6"): "");
								mediaAntMarginPercentage.put("7+",  requestObj.get("fppercent7plus") != null ? requestObj.get("fppercent7plus"): "");
								fullPage.put("mediaAntMarginPercentage", mediaAntMarginPercentage);
							
					   BasicDBObject halfPage = new BasicDBObject();
							halfPage.put("cardRate", requestObj.get("hpcardrate") != null ? requestObj.get("hpcardrate"): "");
							halfPage.put("1-2",  requestObj.get("hp1to2") != null ? requestObj.get("hp1to2"): "");
							halfPage.put("3-6",  requestObj.get("hp3to6") != null ? requestObj.get("hp3to6"): "");
							halfPage.put("7+",  requestObj.get("hp7plus") != null ? requestObj.get("hp7plus"): "");
							halfPage.put("imageUrl",  requestObj.get("hpimageurl") != null ? requestObj.get("hpimageurl"): "");
						
							BasicDBObject mediaAntMarginPercentage0 = new BasicDBObject();
								mediaAntMarginPercentage0.put("1-2", requestObj.get("hppercent1to2") != null ? requestObj.get("hppercent1to2"): "");
								mediaAntMarginPercentage0.put("3-6", requestObj.get("hppercent3to6") != null ? requestObj.get("hppercent3to6"): "");
								mediaAntMarginPercentage0.put("7+",  requestObj.get("hppercent7plus") != null ? requestObj.get("hppercent7plus"): "");
								halfPage.put("mediaAntMarginPercentage", mediaAntMarginPercentage0);
							
	                   BasicDBObject insideFrontCover = new BasicDBObject();
							insideFrontCover.put("cardRate", requestObj.get("ifccardrate") != null ? requestObj.get("ifccardrate"): "");
							insideFrontCover.put("1-2", requestObj.get("ifc1to2") != null ? requestObj.get("ifc1to2"): "");
							insideFrontCover.put("3-6", requestObj.get("ifc3to6") != null ? requestObj.get("ifc3to6"): "");
							insideFrontCover.put("7+", requestObj.get("ifc7plus") != null ? requestObj.get("ifc7plus"): "");
							insideFrontCover.put("imageUrl", requestObj.get("ifcimageUrl") != null ? requestObj.get("ifcimageUrl"): "");
						
							BasicDBObject mediaAntMarginPercentage1 = new BasicDBObject();
								mediaAntMarginPercentage1.put("1-2", requestObj.get("ifcpercent1to2") != null ? requestObj.get("ifcpercent1to2"): "");
								mediaAntMarginPercentage1.put("3-6", requestObj.get("ifcpercent3to6") != null ? requestObj.get("ifcpercent3to6"): "");
								mediaAntMarginPercentage1.put("7+",  requestObj.get("ifcpercent7plus") != null ? requestObj.get("ifcpercent7plus"): "");
								insideFrontCover.put("mediaAntMarginPercentage", mediaAntMarginPercentage1);
	
								
								
						BasicDBObject insideBackCover = new BasicDBObject();
							insideBackCover.put("cardRate",  requestObj.get("ibccardrate") != null ? requestObj.get("ibccardrate"): "");
							insideBackCover.put("1-2", requestObj.get("ibc1to2") != null ? requestObj.get("ibc1to2"): "");
							insideBackCover.put("3-6", requestObj.get("ibc3to6") != null ? requestObj.get("ibc3to6"): "");
							insideBackCover.put("7+", requestObj.get("ibc7plus") != null ? requestObj.get("ibc7plus"): "");
							insideBackCover.put("imageUrl", requestObj.get("ibcimageUrl") != null ? requestObj.get("ibcimageUrl"): "");
						
							BasicDBObject mediaAntMarginPercentage2 = new BasicDBObject();
								mediaAntMarginPercentage2.put("1-2", requestObj.get("ibcpercent1to2") != null ? requestObj.get("ibcpercent1to2"): "");
								mediaAntMarginPercentage2.put("3-6", requestObj.get("ibcpercent3to6") != null ? requestObj.get("ibcpercent3to6"): "");
								mediaAntMarginPercentage2.put("7+",  requestObj.get("ibcpercent7plus") != null ? requestObj.get("ibcpercent7plus"): "");
								insideBackCover.put("mediaAntMarginPercentage", mediaAntMarginPercentage2);
							
						
					
						BasicDBObject backCover = new BasicDBObject();
							backCover.put("cardRate", requestObj.get("bccardrate") != null ? requestObj.get("bccardrate"): "");
							backCover.put("1-2", requestObj.get("bc1to2") != null ? requestObj.get("bc1to2"): "");
							backCover.put("3-6", requestObj.get("bc3to6") != null ? requestObj.get("bc3to6"): "");
							backCover.put("7+", requestObj.get("bc7plus") != null ? requestObj.get("bc7plus"): "");
							backCover.put("imageUrl",  requestObj.get("bcimageUrl") != null ? requestObj.get("bcimageUrl"): "");
							
							BasicDBObject mediaAntMarginPercentage3 = new BasicDBObject();
								mediaAntMarginPercentage3.put("1-2", requestObj.get("bcpercent1to2") != null ? requestObj.get("bcpercent1to2"): "");
								mediaAntMarginPercentage3.put("3-6", requestObj.get("bcpercent3to6") != null ? requestObj.get("bcpercent3to6"): "");
								mediaAntMarginPercentage3.put("7+",  requestObj.get("bcpercent7plus") != null ? requestObj.get("bcpercent7plus"): "");
								backCover.put("mediaAntMarginPercentage", mediaAntMarginPercentage3);	
				
						BasicDBObject doubleSpread = new BasicDBObject();
							doubleSpread.put("cardRate", requestObj.get("dscardrate") != null ? requestObj.get("dscardrate"): "");
							doubleSpread.put("1-2", requestObj.get("ds1to2") != null ? requestObj.get("ds1to2"): "");
							doubleSpread.put("3-6", requestObj.get("ds3to6") != null ? requestObj.get("ds3to6"): "");
							doubleSpread.put("7+",  requestObj.get("ds7plus") != null ? requestObj.get("ds7plus"): "");
							doubleSpread.put("imageUrl", requestObj.get("dsimageUrl") != null ? requestObj.get("dsimageUrl"): "");
							
							BasicDBObject mediaAntMarginPercentage4 = new BasicDBObject();
								mediaAntMarginPercentage4.put("1-2", requestObj.get("dspercent1to2") != null ? requestObj.get("dspercent1to2"): "");
								mediaAntMarginPercentage4.put("3-6", requestObj.get("dspercent3to6") != null ? requestObj.get("dspercent3to6"): "");
								mediaAntMarginPercentage4.put("7+", requestObj.get("dspercent7plus") != null ? requestObj.get("dspercent7plus"): "");
								doubleSpread.put("mediaAntMarginPercentage", mediaAntMarginPercentage4);
							
						
						BasicDBObject centerDoubleSpread = new BasicDBObject();
							centerDoubleSpread.put("cardRate", requestObj.get("cdscardrate") != null ? requestObj.get("cdscardrate"): "");
							centerDoubleSpread.put("1-2", requestObj.get("cds1to2") != null ? requestObj.get("cds1to2"): "");
							centerDoubleSpread.put("3-6", requestObj.get("cds3to6") != null ? requestObj.get("cds3to6"): "");
							centerDoubleSpread.put("7+", requestObj.get("cds7plus") != null ? requestObj.get("cds7plus"): "");
							centerDoubleSpread.put("imageUrl", requestObj.get("cdsimageUrl") != null ? requestObj.get("cdsimageUrl"): "");
							
							BasicDBObject mediaAntMarginPercentage5 = new BasicDBObject();
								mediaAntMarginPercentage5.put("1-2", requestObj.get("cdspercent1to2") != null ? requestObj.get("cdspercent1to2"): "");
								mediaAntMarginPercentage5.put("3-6", requestObj.get("cdspercent3to6") != null ? requestObj.get("cdspercent3to6"): "");
								mediaAntMarginPercentage5.put("7+",  requestObj.get("cdspercent7plus") != null ? requestObj.get("cdspercent7plus"): "");
								centerDoubleSpread.put("mediaAntMarginPercentage", mediaAntMarginPercentage5);
							
							
						regularOptions.put("fullPage", fullPage);
						regularOptions.put("insideFrontCover", insideFrontCover);
						regularOptions.put("insideBackCover", insideBackCover);
						regularOptions.put("backCover", backCover);
						regularOptions.put("doubleSpread", doubleSpread);
						regularOptions.put("centerDoubleSpread", centerDoubleSpread);
						
					mediaDetails.put("regularOptions", regularOptions);	
					
				document.put("mediaDetails", mediaDetails);		
					
				document.put(CommonConstants.UPDATEDBY, requestObj.get("vendorid"));
				document.put(CommonConstants.UPDATEDON, new Date());	
		} catch (Exception e) {
			System.out.println("exception in mondodbmagazineobject java "+e);
		}
		System.out.println("document----------------------"+document);
		return document;
	}

}
