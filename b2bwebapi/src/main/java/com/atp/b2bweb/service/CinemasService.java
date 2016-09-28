package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.CinemasDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CinemasService {

	public DBObject addCinemas(DBObject doc, MongoClient mongo){
		return new CinemasDAO(mongo).addCinemas(doc);
	}
	
	public DBCursor getCinemas(JSONObject requestObj, MongoClient mongo){
		return new CinemasDAO(mongo).getCinemas(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new CinemasDAO(mongo).getByID(doc);
	}
	
	public DBObject updateCinemas(String id,DBObject doc, MongoClient mongo){
		return new CinemasDAO(mongo).updateCinemas(id, doc);
	}
}
