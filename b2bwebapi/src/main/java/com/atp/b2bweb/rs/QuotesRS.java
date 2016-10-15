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
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.common.UrlCommonConstant;
import com.atp.b2bweb.createdbobject.DBOrderQuotesObject;
import com.atp.b2bweb.db.OrderQuotesDB;
import com.atp.b2bweb.service.OrderQuotesService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.QUOTES)
@SessionAttributes(UrlCommonConstant.SESSION)
public class QuotesRS {
	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	
	/*@RequestMapping(value = UrlCommonConstant.GET_ORDERS + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getCinemas(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> OrderList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new OrderService().getOrders(requestObj, mongo);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 OrderList.add(doc);
						}
						respJSON = CommonResponseUtil.getAllDetailLists(OrderList, OrderList.size());
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			respJSON = CommonResponseUtil.getErrorResponseObject(e.toString());
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}*/
	
	
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
					String orderChar =  (String) requestObj.get(CommonConstants.ORDERNO);
					orderChar = orderChar.substring(0, 6);
					String vendorChar =  (String) requestObj.get(CommonConstants.VENDORID);
					int recordCount = new OrderQuotesService().getRecordCount(mongo);
					String recordIdCount = String.valueOf(recordCount);
					String orderNumber = orderChar.concat(vendorChar).concat(recordIdCount);
					requestObj.put(OrderQuotesDB.VENDORID,orderNumber);
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
	
	
}
