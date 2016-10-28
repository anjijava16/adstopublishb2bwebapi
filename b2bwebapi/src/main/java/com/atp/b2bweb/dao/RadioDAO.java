package com.atp.b2bweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RadioDAO {
	private DBCollection col;
	DB db;
	public RadioDAO(MongoClient mongo){
		db= mongo.getDB(TableCommonConstant.SCHEMA_NAME);
		this.col = db.getCollection(TableCommonConstant.RADIO);
	}
	
	public  DBObject addRadio(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	
	public  DBObject updateRadio(String id, DBObject doc){
		try {
			String[] idString = id.split(":");
			String x = null;
			if(idString.length > 1){
				 x = idString[1].substring(1, idString[1].length() - 2);
			}else{
				 x = idString[0];
			}
			System.out.println(x);
			DBObject query = new BasicDBObject("_id", new ObjectId(x));
			col.update(query, doc);
		} catch (Exception e) {	} 
		return doc;
	}
	
	public  DBObject getByID(DBObject doc){
		DBObject data = null;
		try {
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, doc.get("_id")).get();
			data = col.findOne(query);
		} catch (Exception e) {
		} 
		System.out.println("data getbyid RadioDAO  "+data);
		return data;
	}

	public int getCount(){
		int count = col.find().count();   
		
		return count;
	}
	
	public  boolean findOutdoor(String id){
		boolean result = false;
		try {
			String[] idString = id.split(":");
			String x = null;
			if(idString.length > 1){
				 x = idString[1].substring(1, idString[1].length() - 2);
			}else{
				 x = idString[0];
			}
			System.out.println(x);
			DBObject query = new BasicDBObject("_id", new ObjectId(x));
			DBObject data = col.findOne(query);
			if(data == null) result = true;
		} catch (Exception e) {
		} 
		return result;
	}
	
	public DBCursor getRadio(JSONObject requestObj)  {
		DBCursor dbCursor = null;
		try {
			String sortBy = requestObj.get("sortBy").toString();
			int skip      = Integer.valueOf(requestObj.get("offset").toString());
			JSONObject jsonObject =  (JSONObject) requestObj.get("filters"); 

			JSONArray languagesArray = (JSONArray) jsonObject.get("languages");
			JSONArray geographiesArray = (JSONArray) jsonObject.get("geographies");
			JSONArray stationArray = (JSONArray) jsonObject.get("station");

			if(sortBy.equalsIgnoreCase("topserch")) sortBy= "views";
			else if(sortBy.equalsIgnoreCase("fullpageprice")) sortBy= "mediaOptions.regularOptions.fullPage.cardRate";
			else if(sortBy.equalsIgnoreCase("circulation"))	sortBy= "attributes.circulation.value";
			else if(sortBy.equalsIgnoreCase("")) sortBy= "views";
			
			BasicDBObject query = new BasicDBObject();
			List<BasicDBObject> criterias = new ArrayList<BasicDBObject>();
			BasicDBObject query1 = new BasicDBObject();
			BasicDBObject query2 = new BasicDBObject();
			BasicDBObject query3 = new BasicDBObject();
				if(geographiesArray != null && geographiesArray.length() > 0){
					List<BasicDBObject> criteria = new ArrayList<BasicDBObject>();
					for (int i = 0;i < geographiesArray.length();i++) {
						criteria.add(new BasicDBObject("attributes.city.value", geographiesArray.get(i))); 						
					} 
					query1.append(TableCommonConstant.OR, criteria);
					criterias.add(query1);
					query.append(TableCommonConstant.AND, criterias);
				}
				if(stationArray != null && stationArray.length() > 0){
					List<BasicDBObject> criteria1 = new ArrayList<BasicDBObject>();
					for (int i = 0;i < stationArray.length();i++) {
						criteria1.add(new BasicDBObject("station", stationArray.get(i))); 
					}
					query2.append(TableCommonConstant.OR, criteria1);
					criterias.add(query2);
					query.append(TableCommonConstant.AND, criterias);
				}
				if(languagesArray != null && languagesArray.length() > 0){
					List<BasicDBObject> criteria2 = new ArrayList<BasicDBObject>();
					for (int i = 0;i < languagesArray.length();i++) {
						criteria2.add(new BasicDBObject("attributes.language.value", languagesArray.get(i))); 
					}
					query3.append(TableCommonConstant.OR, criteria2);
					criterias.add(query3);
					query.append(TableCommonConstant.AND, criterias);
				}
				
			if(query != null && query.size() > 0){
				 dbCursor = col.find(query);
//				 .sort(new BasicDBObject(sortBy,-1)).skip(skip).limit(30)
				}else{
				 dbCursor = col.find().sort(new BasicDBObject("sortBy",1)).sort(new BasicDBObject(sortBy,1)).skip(skip).limit(30);
			}
			
		} catch (JSONException e) {	}

		return dbCursor;
	}
}
