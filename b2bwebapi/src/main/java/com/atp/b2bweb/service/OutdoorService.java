package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.OutdoorDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class OutdoorService {
	
	public DBObject addOutdoor(DBObject doc, MongoClient mongo){
		return new OutdoorDAO(mongo).addOutdoor(doc);
	}
	
	public DBCursor getOutdoor(JSONObject requestObj, MongoClient mongo){
		return new OutdoorDAO(mongo).getOutdoor(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new OutdoorDAO(mongo).getByID(doc);
	}
	
	public DBObject updateOutdoor(String id,DBObject doc, MongoClient mongo){
		return new OutdoorDAO(mongo).updateOutdoor(id, doc);
	}

	public int getCount(MongoClient mongo){
		return new OutdoorDAO(mongo).getCount();
	}
}
