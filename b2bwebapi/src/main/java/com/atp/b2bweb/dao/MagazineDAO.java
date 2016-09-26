package com.atp.b2bweb.dao;

import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.util.JsonToDB;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class MagazineDAO {
	
	private DBCollection col;
	
	public MagazineDAO(){ }
	
	public MagazineDAO(MongoClient mongo){
		this.col = mongo.getDB(TableCommonConstant.SCHEMA_NAME).getCollection(TableCommonConstant.MAGAZINE);
	}
	
	public  DBObject addMagazine(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {
		} 
		return doc;
	}

	public DBCursor getMagazine() {
		DBCursor sad = null;
		Object[] params= {}; 
		System.out.println("1111");		
		/*DBObject q1 = new BasicDBObject("createdBy", new BasicDBObject("$eq", "chethan"));
		DBObject q2 = new BasicDBObject("frequency", new BasicDBObject("$eq", "Monthly"));*/
       // BasicDBObject fields = new BasicDBObject("_id", 1);
		//sad.addSpecial("createdBy", "chethan").addSpecial("frequency", "Monthly");
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("createdBy", 1);
		whereQuery.put("attributes.language.value", "English");
		//List<DBObject> obj = collection.find(query).skip(1000).limit(100).toArray();
		
		String sortBy = "_id";
		if(sortBy == "topserch") sortBy= "";
		else if(sortBy == "fullpageprice")	sortBy= "mediaOptions.regularOptions.fullPage.cardRate";
		else if(sortBy == "circulation")	sortBy= "attributes.circulation.value";
		
		
		
		   
		DBCursor data = col.find(whereQuery).sort(new BasicDBObject("_id",1)).skip(0).limit(30);
		System.out.println(data.count());
		return data;
	}

}
