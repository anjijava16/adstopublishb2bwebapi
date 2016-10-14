package com.atp.b2bweb.rs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.ExceptionCommonconstant;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.common.UrlCommonConstant;
import com.atp.b2bweb.createdbobject.MongoToVendorDo;
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.db.vendorDetailsDB;
import com.atp.b2bweb.domainobject.VendorUserDO;
import com.atp.b2bweb.emailproxy.EmailProxyUtil;
import com.atp.b2bweb.jwttoken.JwtTokenGenerator;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value="/vendor")
@SessionAttributes(UrlCommonConstant.SESSION)
public class VendorUserRS {
	
	List<String> ccEmailList = null ;
	List<String> bccEmailList = null ;
	List<String> emailList = null;	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/register/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String  vendorRegister(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		DBObject doc= null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					if(requestObj.get(vendorDetailsDB.EMAIL).toString() != "" && requestObj.get(vendorDetailsDB.PHONE).toString() != ""){
						DBCursor dbCursor =  new VendorUserService().getvendorDetails(requestObj.get(vendorDetailsDB.EMAIL).toString(), requestObj.get(vendorDetailsDB.PHONE).toString(),mongo);
						
						if(dbCursor.size() == 0){
							UUID uniqueKey = UUID.randomUUID();
							requestObj.put(vendorDetailsDB.REGISTERTOKEN, uniqueKey);
							doc = VendorDBObject.vendorRegisterDBObject(requestObj);
							DBObject newRegisterdUserInfo = new VendorUserService().vendorRegister(doc, mongo);
							//EmailProxyUtil.sendEmail(ccEmailList, bccEmailList, CommonConstants.REGISTER_MAIL_BODY, false, Arrays.asList(newRegisterdUserInfo.get("email").toString()), uniqueKey);
							respJSON = CommonWebUtil.buildSuccessResponseMsg("register");
						}else{
							System.out.println("user already registerd");
							respJSON = CommonWebUtil.buildErrorResponse("already registerd");
						}
					}
        		}else{
        			respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
        		}
			}else{
				respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}
			
	   }catch (Exception e) {
	   	System.out.println(e);
	   	respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
    
	@SuppressWarnings("unused")
	@RequestMapping(value="/login/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String vendorLogin(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
    	JSONObject respJSON = null;
		//JsonToDB.jsontodb();
    	try{       
    		if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		if(requestObj != null){
        			DBObject dbObject = new VendorUserService().vendorLogin(requestObj.getString(CommonConstants.USERNAME), requestObj.getString(CommonConstants.PASSWORD), mongo);
        			if(dbObject != null){
        				new JwtTokenGenerator().createJWT(MongoToVendorDo.jsonToVendoruserDO(dbObject), response);
        				respJSON = CommonWebUtil.buildSuccessResponse();
        			}else
        				respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.USER_NOT_EXITS);
        		}else
        			respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
        	}else
        		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
    	}catch(Exception exception){
    		System.out.println(exception);
    		respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
    	}
    	return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	@RequestMapping(value = UrlCommonConstant.GET_VENDOR + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String getVendor(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) {	
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
    	org.json.simple.JSONObject respJSON = null;
    	List<DBObject> vendorList = new ArrayList<>();
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		if(requestObj != null){
        			DBObject dbObject =  new VendorUserService().retriveByID(requestObj.getString(CommonConstants.VENDORID), mongo);
        			if(dbObject !=  null){
        				vendorList.add(dbObject);
        				respJSON = CommonResponseUtil.getAllDetailLists(vendorList , 1);
    		    	}else
    		    		respJSON = CommonResponseUtil.getErrorResponseObject("not Exits");
        		}
			}else
				respJSON = CommonResponseUtil.getErrorResponseObject("");
		}catch(Exception exception){
			System.out.println(exception);
			respJSON = CommonResponseUtil.getErrorResponseObject("exception");
    	}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	
	
    @SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.UPDATE + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String vendorUpdate(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) {	
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
    	JSONObject respJSON = null;
    	DBObject doc= null;
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		System.out.println("requestObj"+requestObj);
        		if(requestObj != null){   
        			DBCursor dBCursor =  new VendorUserService().retriveUserByEmailOrMobile(requestObj.getString(CommonConstants.EMAIL),requestObj.getString(CommonConstants.PHONE), mongo);
        			System.out.println("dBCursor  "+dBCursor);
        			if(dBCursor !=  null){
        				String id = null;
        				while(dBCursor.hasNext()){
        					DBObject dBObject = dBCursor.next();
        					id = dBObject.get("_id").toString();
        				}
        				System.out.println("id   "+id);
        				doc = VendorDBObject.createUpdateDBObject(requestObj);
        				System.out.println("doc  "+doc);
        				new VendorUserService().vendorUpdate(doc, id, mongo);
    			    	respJSON = CommonWebUtil.buildSuccessResponse();
    		    	}else
    		    		respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.USER_NOT_EXITS);
        		}else
		    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}else
	    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
		}catch(Exception exception){
			System.out.println(exception);
    		respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
    	}
		
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
    
    
    @SuppressWarnings("unused")
	@RequestMapping(value= UrlCommonConstant.DELETE + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String vendorDelete(@PathVariable String requestParameter, HttpServletRequest request,  HttpServletResponse response) {	
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
    	JSONObject respJSON = null;
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		if(requestObj != null){
        			DBObject dbObject =  new VendorUserService().retriveByID(requestObj.getString(CommonConstants.VENDORID), mongo);
			    	if(dbObject != null){
			    		VendorUserDO vendoruserDO = new VendorUserDO();
        				vendoruserDO.setId(dbObject.get(CommonConstants._ID).toString());
			    		new VendorUserService().vendorDelete(vendoruserDO, mongo);
			    	}else
			    		respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.USER_NOT_EXITS);
        		}else
    	    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}else
	    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
		}catch(Exception exception){
	    		respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
    	}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
    	
	}
    
    
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.FORGET_PASSWORD + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String vendorForgetPassword(@PathVariable String requestParameter,  HttpServletRequest request, HttpServletResponse response) {
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	JSONObject respJSON = null;
    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		System.out.println(requestObj.getString(CommonConstants.USERNAME));
        		if(requestObj != null){
        			DBCursor dBCursor  =  new VendorUserService().retriveUserByEmailOrMobile(requestObj.getString(CommonConstants.USERNAME),requestObj.getString(CommonConstants.USERNAME), mongo);
					if(dBCursor != null && dBCursor.size() >  0){
						EmailProxyUtil.sendEmail(ccEmailList, bccEmailList, CommonConstants.FORGETPASSWORD_MAIL_BODY, false, Arrays.asList(dBCursor.next().get("email").toString()), null);
						respJSON = CommonWebUtil.buildSuccessResponse();
					}else{
						respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.USER_NOT_EXITS);
					}
        		}else
    	    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}else
	    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
		} catch (Exception e) {
			respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}

}
