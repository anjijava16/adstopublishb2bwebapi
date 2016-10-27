package com.atp.b2bweb.dao;

import java.util.List;

import org.bson.types.ObjectId;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.TableCommonConstant;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class VendorBusinessDetailsDAO {
	
	private DBCollection col;
	DB db;
	
	public VendorBusinessDetailsDAO(MongoClient mongo){
		db = mongo.getDB(TableCommonConstant.SCHEMA_NAME);
		this.col = db.getCollection(TableCommonConstant.VENDOR_BUINESS_DETAIL);
	}
	
	public  DBObject addBusinessDetails(DBObject doc){
		try {
			col.insert(doc);
		} catch (Exception e) {	}
		return doc;
	}
	
	public  DBObject updateBusinessDetails(DBObject doc, String id){
		try {
			String[] idString = id.split(":");
			String x = null;
			if(idString.length > 1){
				 x = idString[1].substring(1, idString[1].length() - 2);
			}else{
				 x = idString[0];
			}
			System.out.println("DAO DOC "+doc);
			System.out.println("_id "+x);
			DBObject query = new BasicDBObject("_id", new ObjectId(x));
			 col.update(query, doc);
		} catch (Exception e) {	}
		return doc;
	}
	
	
	public  boolean updateBusinessDetailsImageURL(String key, String value, String vendorId){
		boolean result = false;
		System.out.println("--------key--"+key+"  value "+value+"  vendorId  "+vendorId);
		try {
			BasicDBObject updateQuery = new BasicDBObject();
			updateQuery.append("$set", new BasicDBObject().append(key, value));

			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.append("vendorid", vendorId);

			WriteResult doc = col.update(searchQuery, updateQuery);
			
			if(doc != null ) result = true;
			System.out.println("result   "+result+"  doc   "+doc);
		} catch (Exception e) {	}
		return result;
	}
	
	
	public  DBObject  retriveByVendorID(String vendorid){
		DBObject data = null;
	    try {
			DBObject query = BasicDBObjectBuilder.start().append(CommonConstants.VENDORID, vendorid).get();
			data = col.findOne(query);
	    } catch (Exception e) {
	    	System.out.println("in DAO   "+e);
	    }
		return data;
	}
	
	public  GridFSInputFile addImage(byte[] imageBytes, String filename){
	
		GridFS fs = new GridFS( db , "photo");
        //Save image into database
        GridFSInputFile in = fs.createFile( imageBytes );
        in.setFilename(filename);
        in.save();
        
		return in;
            
	}
	
	public List<GridFSDBFile> getImage(String filename){
		GridFS gfsPhoto = new GridFS(db, "photo");
        return gfsPhoto.find(filename);
	}
	
}