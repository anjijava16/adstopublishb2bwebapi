package com.atp.b2bweb.service;

import org.json.JSONObject;

import com.atp.b2bweb.dao.AirlineAndAirportsDAO;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class AirlineAndAirportsService {
	
	public DBObject addAirlineAndAirportsService(DBObject doc, MongoClient mongo){
		return new AirlineAndAirportsDAO(mongo).addAirlineAndAirports(doc);
	}
	
	public DBCursor getAirlineAndAirportsService(JSONObject requestObj, MongoClient mongo){
		return new AirlineAndAirportsDAO(mongo).getAirlineAndAirports(requestObj);
	}
	
	public DBObject getByID(DBObject doc, MongoClient mongo){
		return new AirlineAndAirportsDAO(mongo).getByID(doc);
	}
	
	public DBObject updateAirlineAndAirportsService(String id,DBObject doc, MongoClient mongo){
		return new AirlineAndAirportsDAO(mongo).updateAirlineAndAirports(id, doc);
	}

}
