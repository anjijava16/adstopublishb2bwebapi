package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.MagazineDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MagazineService {
	
	public DBObject addMagazine(DBObject doc, MongoClient mongo){
		return new MagazineDAO(mongo).addMagazine(doc);
	}
	
	public DBCursor getMagazine(JSONObject requestObj, MongoClient mongo){
		return new MagazineDAO(mongo).getMagazine(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new MagazineDAO(mongo).getByID(doc);
	}
	
	public DBObject updateMagazine(String id,DBObject doc, MongoClient mongo){
		return new MagazineDAO(mongo).updateMagazine(id, doc);
	}

}
