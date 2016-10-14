package com.atp.b2bweb.dao;

import java.util.ArrayList;
import java.util.List;





import org.json.JSONArray;
import org.json.JSONObject;

import com.atp.b2bweb.common.TableCommonConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class OrderQuotesDAO {
	
	private DBCollection col;
	
	public OrderQuotesDAO(){ }
	
	public OrderQuotesDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.QUOTES);
	}
	
	public  DBObject addQuotes(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}
	
	public int getRecordCount(){
		int count = col.find().count();   
		
		return count;
	} 
	
	/*public DBCursor getQuotedOrders(JSONObject requestObj)  {
		List<DBCursor> dbCursorList = new ArrayList<DBCursor>();
		DBCursor dbCursor = null;
		try {
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>();
			JSONArray a = requestObj.names();
			for(int i = 0 ; i < a.length(); i++){
				criteria.add(new BasicDBObject(a.get(i).toString(), requestObj.get(a.get(i).toString())));
			}
			if(criteria != null && criteria.size() > 0){
				dbCursor = col.find(new BasicDBObject(TableCommonConstant.OR,criteria));
			}
			dbCursorList.add(dbCursor);
		} catch (Exception e) {	
			System.out.println(e);
		}
		
		return dbCursor;
	}*/
}
