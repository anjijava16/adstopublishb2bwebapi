package com.atp.b2bweb.service;

import java.util.List;

import com.atp.b2bweb.dao.VendorBankDetailsDAO;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class VendorBankDetailsService {
	
	public DBObject addBankDetails(DBObject doc, MongoClient mongo){
		return new VendorBankDetailsDAO(mongo).addBankDetails(doc);
	}
	
	public DBObject updateBankDetails(DBObject doc, String id, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).updateBankDetails(doc, id);
	}
	
	public DBObject retriveByVendorID(String vendorId, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).retriveByVendorID(vendorId);
	}
	
	public boolean updatebankImageURL(String key, String value, String vendorid, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).updateBankDetailsImageURL(key, value, vendorid);
	}
	
	public GridFSInputFile addImage (byte[] imageBytes, String filename, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).addImage(imageBytes, filename);
	}
	
	public List<GridFSDBFile> getImage (String filename, MongoClient mongo){	
		return new VendorBankDetailsDAO(mongo).getImage(filename);
	}
}
