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

public class AirlineAndAirportsDAO {
	
	private DBCollection col;
	
	public AirlineAndAirportsDAO(){ }
	
	public AirlineAndAirportsDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.AIRLINE_AND_AIRPORTS);
	}
	   
	public  DBObject addAirlineAndAirports(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	    
	public  DBObject updateAirlineAndAirports(String id, DBObject doc){
		try {
			String[] idString = id.split(":");
			String x = null;
			if(idString.length > 1){
				 x = idString[1].substring(1, idString[1].length() - 2);
			}else{
				 x = idString[0];
			}
			
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
		System.out.println("data getbyid AirlineAndAirportsDAO  "+data);
		return data;
	}

	public int getCount(){
		int count = col.find().count();   
		System.out.println(count);
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
			DBObject query = new BasicDBObject("_id", new ObjectId(x));
			
			DBObject data = col.findOne(query);
			if(data == null) result = true;
		} catch (Exception e) {
		} 
		return result;
	}
	
	public DBCursor getAirlineAndAirports(JSONObject requestObj)  {
		DBCursor dbCursor = null;
		try {
			String sortByParm = requestObj.get("sortBy").toString();
			String sortBy = "";
			int skip      = Integer.valueOf(requestObj.get("offset").toString());
			JSONObject jsonObject =  (JSONObject) requestObj.get("filters"); 
			JSONArray geographiesArray = (JSONArray) jsonObject.get("geographies");
			JSONArray categoriesArray = (JSONArray) jsonObject.get("categories");
			
			if(sortByParm.equalsIgnoreCase("topserch"))		sortBy= "views";
			else if(sortByParm.equalsIgnoreCase("price"))	sortBy= "cardRate";
			else if(sortByParm.equalsIgnoreCase("reach"))	sortBy= "attributes.reach.value";
			else if(sortByParm.equalsIgnoreCase(""))		sortBy= "views";
			   
			BasicDBObject query = new BasicDBObject();
			List<BasicDBObject> criterias = new ArrayList<BasicDBObject>();
			if(geographiesArray != null && geographiesArray.length() > 0){
				List<BasicDBObject> criteria = new ArrayList<BasicDBObject>();
				for (int i = 0;i < geographiesArray.length();i++) {
					criteria.add(new BasicDBObject("attributes.city.value", geographiesArray.get(i))); 						
				} 
				criterias.add(new BasicDBObject().append(TableCommonConstant.OR, criteria));
				query.append(TableCommonConstant.AND, criterias);
			}
			if(categoriesArray != null && categoriesArray.length() > 0){
				List<BasicDBObject> criteria1 = new ArrayList<BasicDBObject>();
				for (int i = 0;i < categoriesArray.length();i++) {
					criteria1.add(new BasicDBObject("category", categoriesArray.get(i))); 
				}
				criterias.add(new BasicDBObject().append(TableCommonConstant.OR, criteria1));
				query.append(TableCommonConstant.AND, criterias);
			}
				
			if(query != null && query.size() > 0){
				 dbCursor = col.find(query);
				 /*sort(new BasicDBObject(sortBy, -1)).skip(skip).limit(30);*/
				}else{
				 dbCursor = col.find().sort(new BasicDBObject(sortBy, -1)).skip(skip).limit(30);
			}
			
		} catch (JSONException e) {	}

		return dbCursor;
	}

}