package com.atp.b2bweb.createdbobject;

import java.util.Date;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.domainobject.VendorBankDetailDO;
import com.atp.b2bweb.domainobject.VendorBusinessDetailDO;
import com.atp.b2bweb.domainobject.VendorDetailDO;
import com.atp.b2bweb.domainobject.VendorUserDO;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class MongoDBObject {

	
	public static DBObject createRegisterDBObject(VendorUserDO vendorUserDO) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		//docBuilder.append("_id", vendorUserDO.getId());
		docBuilder.append(CommonConstants.USERNAME, vendorUserDO.getUsername());
		docBuilder.append(CommonConstants.EMAIL, vendorUserDO.getEmail());
		docBuilder.append(CommonConstants.PASSWORD, vendorUserDO.getPassword());
		docBuilder.append(CommonConstants.MOBILE, vendorUserDO.getMobile());
		docBuilder.append(CommonConstants.DISPLAYNAME, vendorUserDO.getUsername());
		docBuilder.append(CommonConstants.ACCOUNTSTATUS, CommonConstants.ACTIVE);
		
		docBuilder.append(CommonConstants.UPDATEDBY, vendorUserDO.getUpdatedby());
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		
		return docBuilder.get();
	}
	
	public static DBObject createVendorDetailDBObject(VendorDetailDO vendorDetailDO) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		//docBuilder.append("_id", vendorUserDO.getId());
		docBuilder.append(CommonConstants.VENDORID, vendorDetailDO.getVendorid());
		docBuilder.append(CommonConstants.DISPLAYNAME, vendorDetailDO.getDisplayname());
		docBuilder.append(CommonConstants.BUISENESSDESC, vendorDetailDO.getBusinessDesc());
		
		docBuilder.append(CommonConstants.UPDATEDBY, vendorDetailDO.getUpdatedby());
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		
		return docBuilder.get();
	}
	
	public static DBObject createVendorBankDetailDBObject(VendorBankDetailDO vendorBankDetailDO) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		//docBuilder.append("_id", vendorUserDO.getId());
		docBuilder.append(CommonConstants.VENDORID, vendorBankDetailDO.getVendorid());
		docBuilder.append(CommonConstants.ACCOUNTHOLDERNAME, vendorBankDetailDO.getAccountholdername());
		docBuilder.append(CommonConstants.ACCOUNTNUMBER, vendorBankDetailDO.getAccountnumber());
		docBuilder.append(CommonConstants.IFSC, vendorBankDetailDO.getIfsc());
		docBuilder.append(CommonConstants.BANKNAME, vendorBankDetailDO.getBankname());
		docBuilder.append(CommonConstants.STATE, vendorBankDetailDO.getState());
		docBuilder.append(CommonConstants.CITY, vendorBankDetailDO.getCity());
		docBuilder.append(CommonConstants.BRANCH, vendorBankDetailDO.getBranch());
		docBuilder.append(CommonConstants.ADDRESSPROOFTYPE, vendorBankDetailDO.getAddressprooftype());
		docBuilder.append(CommonConstants.ADDRESSPROOFURL, vendorBankDetailDO.getAddressproofurl());
		docBuilder.append(CommonConstants.CANCELLEDCHEQUEURL, vendorBankDetailDO.getCancelledchequeurl());
		
		docBuilder.append(CommonConstants.UPDATEDBY, vendorBankDetailDO.getUpdatedby());
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		
		return docBuilder.get();
	}
	
	
	public static DBObject createVendorBusinessDetailDBObject(VendorBusinessDetailDO vendorBusinessDetailDO) {
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
								
		//docBuilder.append("_id", vendorUserDO.getId());
		docBuilder.append(CommonConstants.VENDORID, vendorBusinessDetailDO.getVendorid());
		docBuilder.append(CommonConstants.BUSINESSNAME, vendorBusinessDetailDO.getBusinessname());
		docBuilder.append(CommonConstants.BUSSINESSTYPE, vendorBusinessDetailDO.getBusinesstype());
		docBuilder.append(CommonConstants.PAN, vendorBusinessDetailDO.getPan());
		docBuilder.append(CommonConstants.PANURL, vendorBusinessDetailDO.getPanurl());
		docBuilder.append(CommonConstants.TAN, vendorBusinessDetailDO.getTan());
		docBuilder.append(CommonConstants.TANURL, vendorBusinessDetailDO.getTanUrl());
		docBuilder.append(CommonConstants.TINVAT, vendorBusinessDetailDO.getTinvat());
		docBuilder.append(CommonConstants.TINVATURL, vendorBusinessDetailDO.getTanUrl());
		docBuilder.append(CommonConstants.BUSINESSPAN, vendorBusinessDetailDO.getBusinesspan());
		docBuilder.append(CommonConstants.BUSINESSPANURL, vendorBusinessDetailDO.getBusinesspanurl());
		
		docBuilder.append(CommonConstants.UPDATEDBY, vendorBusinessDetailDO.getUpdatedby());
		docBuilder.append(CommonConstants.UPDATEDON, new Date());
		
		return docBuilder.get();
	}
	
}