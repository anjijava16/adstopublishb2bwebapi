package com.atp.b2bweb.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONObject;

import com.atp.b2bweb.common.TableCommonConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
public class VendorQuotesDAO {
	
	private DBCollection col;
	
	public VendorQuotesDAO(){ }
	
	public VendorQuotesDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.VENDORQUOTES);
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
	
	public DBCursor getQuotedOrders(JSONObject requestObj)  {
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
	}
	
	public  DBObject getQuotesByQuotID(String quoteId){
		DBObject data = null;
		try {
			DBObject query = BasicDBObjectBuilder.start().append("quoteid", quoteId).get();
			data = col.findOne(query);
		} catch (Exception e) {
			System.out.println("in DAO   "+e);
		}
		return data;
	}
	
	public  DBObject quotesUpdate(DBObject doc, String id){
		try {
			String[] idString = id.split(":");
			String x = null;
			if(idString.length > 1){
				 x = idString[1].substring(1, idString[1].length() - 2);
			}else{
				 x = idString[0];
			}
			System.out.println("id  "+x);
			DBObject query = new BasicDBObject("_id", new ObjectId(x));
			BasicDBObject newDocument = new BasicDBObject();
			newDocument.append("$set", doc);
			
			col.update(query, newDocument);
			System.out.println("result "+doc);
		} catch (Exception e) {
		}
		return doc;
	}
	
	public DBCursor getCustomerOrderQuotes(JSONObject requestObj)  {
		List<DBCursor> dbCursorList = new ArrayList<DBCursor>();
		DBCursor dbCursor = null;
		try {
			List<BasicDBObject> criteria = new ArrayList<BasicDBObject>();
			JSONArray a = requestObj.names();
			for(int i = 0 ; i < a.length(); i++){
				criteria.add(new BasicDBObject(a.get(i).toString(), requestObj.get(a.get(i).toString())));
			}
			if(criteria != null && criteria.size() > 0){
				dbCursor = col.find(new BasicDBObject(TableCommonConstant.AND,criteria));
			}
			dbCursorList.add(dbCursor);
		} catch (Exception e) {	
			System.out.println(e);
		}
		
		return dbCursor;
	}
}
