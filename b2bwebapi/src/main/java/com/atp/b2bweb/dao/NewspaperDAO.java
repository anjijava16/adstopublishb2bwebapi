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

public class NewspaperDAO {

	private DBCollection col;
	
	public NewspaperDAO(){ }
	
	public NewspaperDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.NEWSPAPER);
	}
	
	public  DBObject addNewspaper(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	
	public  DBObject updateNewspaper(String id, DBObject doc){
		try {
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants._ID, new ObjectId(id)).get();
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
		System.out.println("data getbyid newspaperDAO  "+data);
		return data;
	}
	
	public int getCount(){
		int count = col.find().count();   
		
		return count;
	}

	public DBCursor getNewspaper(JSONObject requestObj)  {
		DBCursor dbCursor = null;
		try {
			String sortByparm = requestObj.get("sortBy").toString();
			String sortBy = "";
			int skip      = Integer.valueOf(requestObj.get("offset").toString());
			System.out.println(skip);
			JSONObject jsonObject =  (JSONObject) requestObj.get("filters"); 
			
			JSONArray languagesArray = (JSONArray) jsonObject.get("languages");
			JSONArray geographiesArray = (JSONArray) jsonObject.get("geographies");
			/*JSONArray targetGroupsArray = (JSONArray) jsonObject.get("targetGroups");
			JSONArray mediaOptionsArray = (JSONArray) jsonObject.get("mediaOptions");*/
			JSONArray frequenciesArray = (JSONArray) jsonObject.get("frequencies");
			JSONArray categoriesArray = (JSONArray) jsonObject.get("categories");
			
			if(sortByparm.equalsIgnoreCase("topserch")) sortBy= "views";
			else if(sortByparm.equalsIgnoreCase("fullpageprice"))	sortBy= "mediaOptions.regularOptions.fullPage.cardRate";
			else if(sortByparm.equalsIgnoreCase("circulation"))	sortBy= "attributes.circulation.value";
			else if(sortByparm.equalsIgnoreCase("")) sortBy= "views";
			
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>(); 
				for (int i = 0;i < geographiesArray.length();i++) {
					criteria.add(new BasicDBObject("attributes.areaCovered.value", geographiesArray.get(i))); 
				}   
				for (int i = 0;i < categoriesArray.length();i++) {
					criteria.add(new BasicDBObject("categoryName", categoriesArray.get(i))); 
				}
				for (int i = 0;i < languagesArray.length();i++) {
					criteria.add(new BasicDBObject("attributes.language.value", languagesArray.get(i))); 
				}
				for (int i = 0; i < frequenciesArray.length();i++) {
					criteria.add(new BasicDBObject("attributes.frequency.value", frequenciesArray.get(i))); 
				}
			
			System.out.println("criteria.size()   "+criteria.size() );
			System.out.println("sortBy   "+sortBy);
		
			
			if(criteria != null && criteria.size() > 0){
				 dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR, criteria)).sort(new BasicDBObject(sortBy, -1)).skip(skip).limit(30);
				}else{
				 dbCursor = col.find().sort(new BasicDBObject(sortBy,1)).sort(new BasicDBObject(sortBy, -1)).skip(skip).limit(30);
			}
			System.out.println("sortBy   "+sortBy);
		} catch (JSONException e) {	}

		return dbCursor;
	}

}
