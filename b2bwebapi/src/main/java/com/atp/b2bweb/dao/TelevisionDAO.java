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

public class TelevisionDAO {
	private DBCollection col;

	public TelevisionDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.TELEVISION);
	}
	
	public  DBObject addTelevision(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	
	public  DBObject updateTelevision(String id, DBObject doc){
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
		System.out.println("data getbyid TelevisionDAO  "+data);
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
	
	public DBCursor getTelevision(JSONObject requestObj)  {
		DBCursor dbCursor = null;
		try {
			String sortBy = requestObj.get("sortBy").toString();
			int skip      = Integer.valueOf(requestObj.get("offset").toString());
			JSONObject jsonObject =  (JSONObject) requestObj.get("filters"); 
			
			JSONArray languagesArray = (JSONArray) jsonObject.get("languages");
			JSONArray geographiesArray = (JSONArray) jsonObject.get("geographies");
			JSONArray channelgenreArray = (JSONArray) jsonObject.get("channelgenre");
			
			if(sortBy.equalsIgnoreCase("topserch")) sortBy= "views";
			else if(sortBy.equalsIgnoreCase("fullpageprice"))	sortBy= "mediaOptions.regularOptions.fullPage.cardRate";
			else if(sortBy.equalsIgnoreCase("cardRate"))	sortBy= "cardRate";
			else if(sortBy.equalsIgnoreCase("")) sortBy= "views";
			
				
			BasicDBObject query = new BasicDBObject();
			List<BasicDBObject> criterias = new ArrayList<BasicDBObject>();
				if(geographiesArray != null && geographiesArray.length() > 0){
					List<BasicDBObject> criteria = new ArrayList<BasicDBObject>();
					for (int i = 0;i < geographiesArray.length();i++) {
						criteria.add(new BasicDBObject("state", geographiesArray.get(i))); 						
					} 
					criterias.add(new BasicDBObject().append(TableCommonConstant.OR, criteria));
					query.append(TableCommonConstant.AND, criterias);
				}
				if(channelgenreArray != null && channelgenreArray.length() > 0){
					List<BasicDBObject> criteria1 = new ArrayList<BasicDBObject>();
					for (int i = 0;i < channelgenreArray.length();i++) {
						criteria1.add(new BasicDBObject("channelgenreArray", channelgenreArray.get(i))); 
					}
					criterias.add(new BasicDBObject().append(TableCommonConstant.OR, criteria1));
					query.append(TableCommonConstant.AND, criterias);
				}
				if(languagesArray != null && languagesArray.length() > 0){
					List<BasicDBObject> criteria2 = new ArrayList<BasicDBObject>();
					for (int i = 0;i < languagesArray.length();i++) {
						criteria2.add(new BasicDBObject("attributes.language.value", languagesArray.get(i))); 
					}
					criterias.add(new BasicDBObject().append(TableCommonConstant.OR, criteria2));
					query.append(TableCommonConstant.AND, criterias);
				}
				
				
			System.out.println("criteria.size()   "+query.size() );
			if(query != null && query.size() > 0){
				 dbCursor = col.find(query);
//				 .sort(new BasicDBObject(sortBy,-1)).skip(skip).limit(30);
				}else{
				 dbCursor = col.find().sort(new BasicDBObject(sortBy, -1)).skip(skip).limit(30);
			}
			System.out.println(dbCursor);
		} catch (JSONException e) {	}

		return dbCursor;
	}
}