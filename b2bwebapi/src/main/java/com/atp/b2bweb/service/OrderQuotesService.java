package com.atp.b2bweb.service;




import org.json.JSONObject;

import com.atp.b2bweb.dao.OrderDAO;
import com.atp.b2bweb.dao.OrderQuotesDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class OrderQuotesService {
	
	public DBObject addQuote(DBObject doc, MongoClient mongo){
		return new OrderQuotesDAO(mongo).addQuotes(doc);
	}
	
	public int getRecordCount(MongoClient mongo){
		return new OrderQuotesDAO(mongo).getRecordCount();
	}
	
	
	
	/*
	public DBCursor getOrders(JSONObject requestObj, MongoClient mongo){
		return new OrderDAO(mongo).getOrders(requestObj);
	}*/
	
	
	
	/*public DBObject getByID(DBObject doc, MongoClient mongo){
		return new MagazineDAO(mongo).getByID(doc);
	}
	
	public DBObject updateMagazine(String id,DBObject doc, MongoClient mongo){
		return new MagazineDAO(mongo).updateMagazine(id, doc);
	}
	
	public int getCount(MongoClient mongo){
		return new MagazineDAO(mongo).getCount();
	}
	
	public boolean findOutdoor(String id , MongoClient mongo){
		return new MagazineDAO(mongo).findOutdoor(id);
	}*/
}
