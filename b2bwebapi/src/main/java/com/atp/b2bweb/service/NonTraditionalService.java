package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.NonTraditionalDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class NonTraditionalService {

	public DBObject addNonTraditional(DBObject doc, MongoClient mongo){
		return new NonTraditionalDAO(mongo).addNonTraditional(doc);
	}
	
	public DBCursor getNonTraditional(JSONObject requestObj, MongoClient mongo){
		return new NonTraditionalDAO(mongo).getNonTraditional(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new NonTraditionalDAO(mongo).getByID(doc);
	}
	
	public DBObject updateNonTraditional(String id,DBObject doc, MongoClient mongo){
		return new NonTraditionalDAO(mongo).updateNonTraditional(id, doc);
	}
	
	public int getCount(MongoClient mongo){
		return new NonTraditionalDAO(mongo).getCount();
	}
	
	public boolean findOutdoor(String id , MongoClient mongo){
		return new NonTraditionalDAO(mongo).findOutdoor(id);
	}
}
