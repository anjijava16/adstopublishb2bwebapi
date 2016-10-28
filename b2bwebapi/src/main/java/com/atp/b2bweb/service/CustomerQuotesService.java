package com.atp.b2bweb.service;




import org.json.JSONObject;

import com.atp.b2bweb.dao.CustomerQuotesDAO;
import com.atp.b2bweb.dao.OrderSummaryDAO;
import com.atp.b2bweb.dao.VendorQuotesDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class CustomerQuotesService {
	
	public DBObject addOrder(DBObject doc, MongoClient mongo){
		return new CustomerQuotesDAO(mongo).addOrder(doc);
	}
	
	public int getRecordCount(MongoClient mongo){
		return new CustomerQuotesDAO(mongo).getRecordCount();
	}
	
	public DBCursor getOrders(JSONObject requestObj, MongoClient mongo){
		return new CustomerQuotesDAO(mongo).getOrders(requestObj);
	}
	
	public DBObject addOrderSummary(DBObject doc, MongoClient mongo){
		return new OrderSummaryDAO(mongo).addOrderSummary(doc);
	}
	
	public DBCursor getProccedOrders(JSONObject requestObj, MongoClient mongo){
		return new OrderSummaryDAO(mongo).getProccedOrders(requestObj);
}
	
	
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
