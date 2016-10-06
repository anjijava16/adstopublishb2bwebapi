package com.atp.b2bweb.common;

public class CommonConstants {
	
	//mail body
	
	public static final String REGISTER_MAIL_BODY = "hi hello, done done done";
	
	public static final String FORGETPASSWORD_MAIL_BODY = "hi hello, done done done";
	
	// Access-Control-Allow-Origin
	
	public static final String RESPONSE_HEADER = "Access-Control-Allow-Origin";
	
	public static final String STAR = "*";
	
	public static final String EMPTY = "";
	
	
	public static final String SUCCESS_FLAG = "success";
	
	public static final String TRUE = "true";
	
	public static final String FALSE = "false";
	
	public static final String ERRORS = "errors";
	
	public static final String RESULTS = "results";
	
	public static final String RESPONSE = "response";
	
	public static final String COUNT = "count";
	
	//variables
	// vendor bank detais
	
	public static final String ACCOUNTHOLDERNAME = "accountholdername";
	
	public static final String ACCOUNTNUMBER = "accountnumber";
	
	public static final String BANKNAME = "bankname";
	
	public static final String BRANCH = "branch";
	
	public static final String CITY = "city";
	
	public static final String STATE = "state";
	
	public static final String IFSC = "ifsc";
	
	public static final String ADDRESSPROOFTYPE = "addressprooftype";
	
	public static final String ADDRESSPROOFURL = "addressproofurl";
	
	public static final String CANCELLEDCHEQUEURL = "cancelledchequeurl";
	
	public static final String UPDATEDBY = "updatedby";
	
	public static final String UPDATEDON = "updatedon";
	
	//business details
	
	public static final String BUSINESSNAME = "businessname";
	
	public static final String BUSSINESSTYPE = "businesstype";
	
	public static final String PAN = "pan";
	
	public static final String PERSONALPAN = "personalpan";
	
	public static final String PANURL = "panurl";
	
	public static final String TINVAT = "tinvat";
	
	public static final String TAN = "tan";
	
	public static final String TANURL = "tanurl";
	
	public static final String TINVATURL = "tinvaturl";
	
	public static final String SERVICETAX = "servicetax";
	
	public static final String SERVICETAXURL = "servicetaxurl";
	
	public static final String BUSINESSPAN = "businesspan";
	
	public static final String BUSINESSPANURL = "businesspanurl";
	
	//vendor details
	
	public static final String DISPLAYNAME = "displayname";
	
	public static final String BUISENESSDESC = "businessdesc";
	
	//Register
	public static final String ACTIVE = "A";
	
	public static final String  INACTIVE = "I";
	
	public static final String _ID = "_id";
	
	public static final String ID = "id";
	
	public static final String USERNAME = "username";
	
	public static final String EMAIL = "email";
	
	public static final String NAME = "name";
	
	public static final String  PHONE = "phone";

	public static final String  ACTIVETYPE = "activetype";
	
	
	public static final String MOBILE = "mobile";
	
	public static final String PASSWORD = "password";
	
	public static final String VENDORID = "vendorid";
	
	public static final String ACCOUNTSTATUS = "accountstatus";
	
	
	//table names
	
	public static final String VENDOR_USER = "vendoruser";
	
	public static final String VENDOR_BUSSINESS_DETAILS = "vendorbusinessdetail";
	
	public static final String VENDOR_BANK_DETAILS = "vendorbankdetail";
	
	public static final String VENDOR_DETAILS = "vendordetail";
	
	//Query key
	
	public static final String VENDORUSERDO_FIND_BY_EMAIL_OR_MOBILE= "VendorUserDO.findbyemailormobile";
	
	public static final String VENDOR_LOGIN= "VendorUserDO.vendorlogin";
	
	public static final String RETRIEVE_BY_ID= "VendorUserDO.retrievebyid";
	
	public static final String FIND_BY_VENDOR_ID_BUSINESS= "VendorBusinessDetailDO.retrievebyvendorid";
	
	public static final String FIND_BY_VENDOR_ID_BANK= "VendorBankDetailDO.retrievebyvendorid";
	
	public static final String FIND_BY_VENDOR_ID_DETAILS= "VendorDetailDO.retrievebyvendorid";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
