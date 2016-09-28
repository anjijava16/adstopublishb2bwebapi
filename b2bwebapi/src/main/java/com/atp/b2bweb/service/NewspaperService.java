package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.NewspaperDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class NewspaperService {

	public DBObject addNewspaper(DBObject doc, MongoClient mongo){
		return new NewspaperDAO(mongo).addNewspaper(doc);
	}
	
	public DBCursor getNewspaper(JSONObject requestObj, MongoClient mongo){
		return new NewspaperDAO(mongo).getNewspaper(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new NewspaperDAO(mongo).getByID(doc);
	}
	
	public DBObject updateNewspaper(String id,DBObject doc, MongoClient mongo){
		return new NewspaperDAO(mongo).updateNewspaper(id, doc);
	}
}
