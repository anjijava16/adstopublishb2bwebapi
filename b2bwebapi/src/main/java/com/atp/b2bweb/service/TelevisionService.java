package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.TelevisionDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TelevisionService {
	public DBObject addTelevision(DBObject doc, MongoClient mongo){
		return new TelevisionDAO(mongo).addTelevision(doc);
	}
	
	public DBCursor getTelevision(JSONObject requestObj, MongoClient mongo){
		return new TelevisionDAO(mongo).getTelevision(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new TelevisionDAO(mongo).getByID(doc);
	}
	
	public DBObject updateTelevision(String id,DBObject doc, MongoClient mongo){
		return new TelevisionDAO(mongo).updateTelevision(id, doc);
	}
	
	public int getCount(MongoClient mongo){
		return new TelevisionDAO(mongo).getCount();
	}

}
