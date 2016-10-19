package com.atp.b2bweb.rs;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bson.BSONObject;
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
import com.atp.b2bweb.createdbobject.DBOrderQuotesObject;
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.db.OrderQuotesDB;
import com.atp.b2bweb.service.OrderQuotesService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.QUOTES)
@SessionAttributes(UrlCommonConstant.SESSION)
public class QuotesRS {
	
	MongoClient mongo;
	
	@RequestMapping(value = UrlCommonConstant.GET_QUOTE + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getQuotes(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> quotesList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new OrderQuotesService().getQuotes(requestObj, mongo);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 quotesList.add(doc);
						}
						respJSON = CommonResponseUtil.getAllDetailLists(quotesList, quotesList.size());
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			respJSON = CommonResponseUtil.getErrorResponseObject(e.toString());
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_QUOTE + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addQuotes(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> quotesList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					System.out.println("ths s my req"+requestObj);
					String orderChar =  (String) requestObj.get(CommonConstants.ORDERNO);
					orderChar = orderChar.substring(0, 6);
					String vendorChar =  (String) requestObj.get(CommonConstants.VENDORID);
					int recordCount = new OrderQuotesService().getRecordCount(mongo);
					String recordIdCount = String.valueOf(recordCount);
					String orderNumber = orderChar.concat(vendorChar).concat(recordIdCount);
					requestObj.put(OrderQuotesDB.QUOTEID,orderNumber);
					doc = DBOrderQuotesObject.createquotesDBObject(requestObj);
					quotesList.add(new OrderQuotesService().addQuote(doc, mongo));
    				respJSON = CommonResponseUtil.getAllDetailLists(quotesList , 1); 
        		}else{
        			respJSON = CommonResponseUtil.getErrorResponseObject("request is null");
        		}
			}else{
				respJSON = CommonResponseUtil.getErrorResponseObject("request parameter is null");
			}
	    }catch (Exception e) {
	    	System.out.println(e);
	    	respJSON = CommonResponseUtil.getErrorResponseObject(e.toString());
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
		
	}
	
	 @SuppressWarnings("unused")
		@RequestMapping(value = UrlCommonConstant.UPDATE_QUOTE + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
	    @ResponseBody
		public String updateQuotes(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) {	
	    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
	    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
	    	org.json.simple.JSONObject respJSON = null;
	    	DBObject doc= null;
	    	List<DBObject> quotesList = new ArrayList<>();
			try {
				if(requestParameter != null ){
	        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
	        		if(requestObj != null){   
	        			DBObject dbObject =  new OrderQuotesService().getQuotesByQuotID(requestObj.get("quoteid").toString(), mongo);
	        			if(dbObject !=  null){
	        				String id = dbObject.get("_id").toString();
	        				doc = DBOrderQuotesObject.createquotesDBObject(requestObj);
	        				quotesList.add(new OrderQuotesService().quotesUpdate(doc, id, mongo));
	        				respJSON = CommonResponseUtil.getAllDetailLists(quotesList, 1);
	    		    	}else
	    		    		respJSON = CommonResponseUtil.getErrorResponseObject("Record not found to update");
	        		}else
	        			respJSON = CommonResponseUtil.getErrorResponseObject("Request object null");
				}else
					respJSON = CommonResponseUtil.getErrorResponseObject("Request parameter null");
			}catch(Exception exception){
				System.out.println(exception);
				respJSON = CommonResponseUtil.getErrorResponseObject("Exception occured");
	    	}
			
			return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
		}
	
	
}
