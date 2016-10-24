package com.atp.b2bweb.service;

import java.util.List;

import com.atp.b2bweb.dao.VendorBusinessDetailsDAO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class VendorBusinessDetailsService {
	
	public DBObject addBusinessDetails(DBObject doc,  MongoClient mongo){
		return new VendorBusinessDetailsDAO(mongo).addBusinessDetails(doc);
	}
	
	public DBObject updateBusinessDetails(DBObject doc, String id,  MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).updateBusinessDetails(doc, id);
	}
	
	public boolean updateBusinessDetailsImageURL(String key, String value, String vendorid, MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).updateBusinessDetailsImageURL(key, value, vendorid);
	}
	
	public DBObject retriveByVendorID(String vendorId,  MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).retriveByVendorID(vendorId);
	}
	
	public GridFSInputFile addImage (byte[] imageBytes, String filename, MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).addImage(imageBytes, filename);
	}
	
	public List<GridFSDBFile> getImage (String filename, MongoClient mongo){	
		return new VendorBusinessDetailsDAO(mongo).getImage(filename);
	}
}
