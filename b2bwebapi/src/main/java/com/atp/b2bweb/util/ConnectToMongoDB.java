package com.atp.b2bweb.util;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ConnectToMongoDB {
	
	public static DB connect(){
		MongoClient mongo;
		DB db = null;
		try {
			mongo = new MongoClient( "localhost" , 27017 );
			db = mongo.getDB("adstopublish");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return db;
	}

}
