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
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CinemasDAO {
	
	private DBCollection col;
	
	public CinemasDAO(){ }
	
	public CinemasDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.CINEMAS);
	}
	
	public  DBObject addCinemas(DBObject doc){
		try {
			col.insert(doc);
			System.out.println("222");
		} catch (Exception e) {
		} 
		return doc;
	}
	
	public  DBObject updateCinemas(String id, DBObject doc){
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
		System.out.println("data getbyid CinemasDAO  "+data);
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
	
	
	public DBCursor getCinemas(JSONObject requestObj)  {
		DBCursor dbCursor = null;
		try {
			String sortBy = requestObj.get("sortBy").toString();
			int skip      = Integer.valueOf(requestObj.get("offset").toString());
			JSONObject jsonObject =  (JSONObject) requestObj.get("filters"); 
			
			JSONArray mallNameArray = (JSONArray) jsonObject.get("mallName");
			JSONArray geographiesArray = (JSONArray) jsonObject.get("geographies");
			JSONArray cinemaChainArray = (JSONArray) jsonObject.get("cinemaChain");
			JSONArray mediaOptionsArray = (JSONArray) jsonObject.get("mediaOptions");
			JSONArray screentypeArray = (JSONArray) jsonObject.get("screentype");
			
			if(sortBy.equalsIgnoreCase("topserch")) sortBy= "views";
			else if(sortBy.equalsIgnoreCase("fullpageprice"))	sortBy= "mediaOptions.regularOptions.fullPage.cardRate";
			else if(sortBy.equalsIgnoreCase("circulation"))	sortBy= "attributes.circulation.value";
			else if(sortBy.equalsIgnoreCase("")) sortBy= "views";
			
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
				for (int i = 0;i < geographiesArray.length();i++) {
					criteria.add(new BasicDBObject("city", geographiesArray.get(i))); 
				}   
				for (int i = 0;i < mallNameArray.length();i++) {
					criteria.add(new BasicDBObject("mallName", mallNameArray.get(i))); 
				}
				for (int i = 0;i < cinemaChainArray.length();i++) {
					System.out.println( cinemaChainArray.get(i));
					criteria.add(new BasicDBObject("cinemaChain", cinemaChainArray.get(i))); 
				}
				for (int i = 0; i < mediaOptionsArray.length();i++) {
					criteria.add(new BasicDBObject("mediaOptions", mediaOptionsArray.get(i))); 
				}
				for (int i = 0; i < screentypeArray.length();i++) {
					criteria.add(new BasicDBObject("attributes.frequency.value", screentypeArray.get(i))); 
				}
			
			if(criteria != null && criteria.size() > 0){
				 dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria)).sort(new BasicDBObject(sortBy,-1)).skip(skip).limit(30);
				}else{
				 dbCursor = col.find().sort(new BasicDBObject("sortBy",1)).sort(new BasicDBObject(sortBy,1)).skip(skip).limit(30);
			}
			
		} catch (JSONException e) {	}

		return dbCursor;
	}

}
