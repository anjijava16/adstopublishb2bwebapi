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
import com.atp.b2bweb.createdbobject.DBOrderObject;
import com.atp.b2bweb.db.OrderDB;
import com.atp.b2bweb.service.OrderService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.ORDERS)
@SessionAttributes(UrlCommonConstant.SESSION)
public class OrdersRS {
	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_ORDER + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
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
					int recordCount = new OrderService().getRecordCount(mongo);
					String recordIdCount = String.valueOf(recordCount);
					String orderNumber = mediaTypeChar.concat(mediaNameChar).concat(recordIdCount);
					requestObj.put(OrderDB.ORDERID,orderNumber);
					doc = DBOrderObject.createOrderDBObject(requestObj);
					orderList.add(new OrderService().addOrder(doc, mongo));
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
	
	@RequestMapping(value = UrlCommonConstant.GET_ORDERS + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
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
	}
	
	
}
