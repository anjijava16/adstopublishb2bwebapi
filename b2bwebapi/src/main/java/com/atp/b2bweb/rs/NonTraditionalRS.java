package com.atp.b2bweb.rs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.atp.b2bweb.createdbobject.DBNonTraditionalObject;
import com.atp.b2bweb.service.NonTraditionalService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.atp.b2bweb.util.JsonToDB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.NON_TRADITIONAL)
@SessionAttributes(UrlCommonConstant.SESSION)
public class NonTraditionalRS {
	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_NON_TRADITIONAL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addNonTraditional(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> nonTraditionalList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
        			if(!requestObj.getString("_id").equalsIgnoreCase("")){
						result = new NonTraditionalService().findOutdoor(requestObj.getString("_id"), mongo);
					}
        			if(result){
        				doc = DBNonTraditionalObject.createNonTraditionalDBObject(requestObj);
        				nonTraditionalList.add(new NonTraditionalService().addNonTraditional(doc, mongo));
        				respJSON = CommonResponseUtil.getAllDetailLists(nonTraditionalList , 1);
        	    	}else{
        	    		doc = DBNonTraditionalObject.createNonTraditionalDBObject(requestObj);
        	    		nonTraditionalList.add(new NonTraditionalService().updateNonTraditional(requestObj.get("_id").toString(), doc , mongo));
        				respJSON = CommonResponseUtil.getAllDetailLists(nonTraditionalList , 1);
        	    	}
        		}else{
        			respJSON = CommonResponseUtil.getErrorResponseObject("");
        		}
			}else{
				respJSON = CommonResponseUtil.getErrorResponseObject("");
			}
	    }catch (Exception e) {
	    	System.out.println(e);
	    	respJSON = CommonResponseUtil.getErrorResponseObject("Exception");
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}

	@RequestMapping(value = UrlCommonConstant.GET_NON_TRADITIONAL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getNonTraditional(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> nonTraditionalList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new NonTraditionalService().getNonTraditional(requestObj, mongo);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 nonTraditionalList.add(doc);
						}
						int count = new NonTraditionalService().getCount(mongo);
						respJSON = CommonResponseUtil.getAllDetailLists(nonTraditionalList, count);
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			//respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		 System.out.println("nonTraditionalList  "+nonTraditionalList.size());
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.UPDATE_NON_TRADITIONAL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String updateNonTraditional(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		DBObject doc= null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
        			boolean result = true; /*new VendorUserService().vendorFind(requestObj.getString(CommonConstants.EMAIL), requestObj.getString(CommonConstants.EMAIL), mongo);*/
        			if(result){
        				doc = DBNonTraditionalObject.createNonTraditionalDBObject(requestObj);
        				new NonTraditionalService().updateNonTraditional(requestObj.get("_id").toString(), doc , mongo);
        		    	respJSON = CommonWebUtil.buildSuccessResponse();
        	    	}else{
        	    		respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.ALREADY_REGISTERD);
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
		//return doc;
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}

	@RequestMapping(value = "/addtodb")
    @ResponseBody
	public void addrecordtodb(){  
		new JsonToDB().addnontraditionaltodb();
	}
}



