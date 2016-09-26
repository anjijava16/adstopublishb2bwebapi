package com.atp.b2bweb.service;

import com.atp.b2bweb.dao.MagazineDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MagazineService {
	
	public DBObject addMagazine(DBObject doc, MongoClient mongo){
		return new MagazineDAO(mongo).addMagazine(doc);
	}
	
	public DBCursor getMagazine( MongoClient mongo){
		return new MagazineDAO(mongo).getMagazine();
	}

}
