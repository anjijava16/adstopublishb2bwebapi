package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.DigitalDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class DigitalService {

	public DBObject addDigital(DBObject doc, MongoClient mongo){
		return new DigitalDAO(mongo).addDigital(doc);
	}
	
	public DBCursor getDigital(JSONObject requestObj, MongoClient mongo){
		return new DigitalDAO(mongo).getDigital(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new DigitalDAO(mongo).getByID(doc);
	}
	
	public DBObject updateDigital(String id,DBObject doc, MongoClient mongo){
		return new DigitalDAO(mongo).updateDigital(id, doc);
	}
}
