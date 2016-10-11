package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.RadioDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class RadioService {

	public DBObject addRadio(DBObject doc, MongoClient mongo){
		return new RadioDAO(mongo).addRadio(doc);
	}
	
	public DBCursor getRadio(JSONObject requestObj, MongoClient mongo){
		return new RadioDAO(mongo).getRadio(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new RadioDAO(mongo).getByID(doc);
	}
	
	public DBObject updateRadio(String id,DBObject doc, MongoClient mongo){
		return new RadioDAO(mongo).updateRadio(id, doc);
	}

	public int getCount(MongoClient mongo){
		return new RadioDAO(mongo).getCount();
	}
	
	public boolean findOutdoor(String id , MongoClient mongo){
		return new RadioDAO(mongo).findOutdoor(id);
	}
}
