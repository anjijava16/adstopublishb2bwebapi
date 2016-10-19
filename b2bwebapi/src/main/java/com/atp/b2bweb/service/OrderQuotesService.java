package com.atp.b2bweb.service;






import org.json.JSONObject;

import com.atp.b2bweb.dao.OrderQuotesDAO;
import com.atp.b2bweb.dao.VendorUserDAO;
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
	
	
	
	
	public DBCursor getQuotes(JSONObject requestObj, MongoClient mongo){
		return new OrderQuotesDAO(mongo).getQuotedOrders(requestObj);
	}
	
	 public DBObject getQuotesByQuotID(String quotid, MongoClient mongo) {	
		return new OrderQuotesDAO(mongo).getQuotesByQuotID(quotid);
	 }
	 
	 public DBObject quotesUpdate(DBObject doc,String id, MongoClient mongo){	
			return new OrderQuotesDAO(mongo).quotesUpdate(doc, id);
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
