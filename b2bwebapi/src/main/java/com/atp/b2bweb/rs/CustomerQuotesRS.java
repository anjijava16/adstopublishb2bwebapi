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
import com.atp.b2bweb.createdbobject.DBCustomerQuotesObject;
import com.atp.b2bweb.createdbobject.DBOrderSummaryObject;
import com.atp.b2bweb.db.CustomerQuotesDB;
import com.atp.b2bweb.service.CustomerQuotesService;
import com.atp.b2bweb.service.VendorDetailsService;
import com.atp.b2bweb.service.VendorQuotesService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.CUSTOMERQUOTES)
@SessionAttributes(UrlCommonConstant.SESSION)
public class CustomerQuotesRS {
	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_CUSTOMERQUOTES + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addOrder(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> orderList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					String mediaTypeChar =  (String) requestObj.get("mediaType");
					mediaTypeChar = mediaTypeChar.substring(0, 3);
					String mediaNameChar =  (String) requestObj.get("mediaName");
					mediaNameChar = mediaNameChar.substring(0, 3);
					int recordCount = new CustomerQuotesService().getRecordCount(mongo);
					String recordIdCount = String.valueOf(recordCount);
					String orderNumber = mediaTypeChar.concat(mediaNameChar).concat(recordIdCount);
					requestObj.put(CustomerQuotesDB.ORDERID,orderNumber);
					doc = DBCustomerQuotesObject.createOrderDBObject(requestObj);
					orderList.add(new CustomerQuotesService().addOrder(doc, mongo));
    				respJSON = CommonResponseUtil.getAllDetailLists(orderList , 1); 
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
	
	@RequestMapping(value = UrlCommonConstant.GET_NEWCUSTOMERQUOTES + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getOrders(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> OrderList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					if(requestObj != null){
						DBCursor dbCursor =  new CustomerQuotesService().getOrders(requestObj, mongo);
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
	}
	
	
	@RequestMapping(value = UrlCommonConstant.GET_CUSTOMER_ORDERDQUOTES + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getCustomerOrderQuotes(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		System.out.println("i came here");
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> vendorratequotesList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new VendorQuotesService().getCustomerOrderQuotes(requestObj, mongo);
						System.out.println("ths s result"+dbCursor);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 vendorratequotesList.add(doc);
							 System.out.println("quotesList"+vendorratequotesList);
						}
						respJSON = CommonResponseUtil.getAllDetailLists(vendorratequotesList, vendorratequotesList.size());
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			respJSON = CommonResponseUtil.getErrorResponseObject(e.toString());
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADDORDERSUMMARY + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addOderSummery(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> orderList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					System.out.println("requestObj"+requestObj);
					doc = DBOrderSummaryObject.createOrderSummaryDBObject(requestObj);
					orderList.add(new CustomerQuotesService().addOrderSummary(doc, mongo));
    				respJSON = CommonResponseUtil.getAllDetailLists(orderList , 1);
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
	
	@RequestMapping(value = UrlCommonConstant.GET_PROCCEDORDERS + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getproccedOrders(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		System.out.println("i came here");
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> proccedOrdersList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new CustomerQuotesService().getProccedOrders(requestObj, mongo);
						System.out.println("ths s result"+dbCursor);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 DBObject dbObject=  new VendorUserService().retriveByID(doc.get(CommonConstants.VENDORID).toString(), mongo);
							 System.out.println("vendor detailsssss"+dbObject);
							 doc.put("vendorDetails", dbObject);
							 proccedOrdersList.add(doc);
							 System.out.println("quotesList"+proccedOrdersList);
						}
						
						respJSON = CommonResponseUtil.getAllDetailLists(proccedOrdersList, proccedOrdersList.size());
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			respJSON = CommonResponseUtil.getErrorResponseObject(e.toString());
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
}
