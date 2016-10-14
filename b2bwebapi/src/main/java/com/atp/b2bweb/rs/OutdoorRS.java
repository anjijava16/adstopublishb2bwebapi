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
import com.atp.b2bweb.createdbobject.DBOutdoorObject;
import com.atp.b2bweb.service.OutdoorService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.JsonToDB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;


@Controller
@RequestMapping(value = UrlCommonConstant.OUTDOOR)
@SessionAttributes(UrlCommonConstant.SESSION)
public class OutdoorRS {

	MongoClient mongo;
	
	@RequestMapping(value = UrlCommonConstant.ADD_OUTDOOR + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addOutdoor(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> outdoorList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {  
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){					
					if(!requestObj.getString("_id").equalsIgnoreCase("")){
						result = new OutdoorService().findOutdoor(requestObj.getString("_id"), mongo);
					}        			
        			if(result){
        				doc = DBOutdoorObject.createOutdoorDBObject(requestObj);
        				outdoorList.add(new OutdoorService().addOutdoor(doc, mongo));						
        				respJSON = CommonResponseUtil.getAllDetailLists(outdoorList , 1);  
        	    	}else{  
        	    		doc = DBOutdoorObject.createOutdoorDBObject(requestObj);
        	    		outdoorList.add(new OutdoorService().updateOutdoor(requestObj.get("_id").toString(), doc , mongo));
        	    		respJSON = CommonResponseUtil.getAllDetailLists(outdoorList , 1);
        	    	}
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

	@RequestMapping(value = UrlCommonConstant.GET_OUTDOOR + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getOutdoor(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> outdoorList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new OutdoorService().getOutdoor(requestObj, mongo);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 outdoorList.add(doc);
						}
						int count = new OutdoorService().getCount(mongo);
						respJSON = CommonResponseUtil.getAllDetailLists(outdoorList, count);
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			respJSON = CommonResponseUtil.getErrorResponseObject("exception");
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	

	@RequestMapping(value = "/addtodb")
    @ResponseBody
	public void addrecordtodb(){
		JsonToDB.addOutdoortodb();
	}
}
