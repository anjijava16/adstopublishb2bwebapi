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
import com.atp.b2bweb.createdbobject.DBRadioObject;
import com.atp.b2bweb.service.RadioService;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.atp.b2bweb.util.JsonToDB;
import com.atp.b2bweb.util.MzgazineUtil;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.RADIO)
@SessionAttributes(UrlCommonConstant.SESSION)
public class RadioRS {
	
MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_RADIO + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addRadio(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> radioList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					if(!requestObj.getString("_id").equalsIgnoreCase("")){
						result = new RadioService().findOutdoor(requestObj.getString("_id"), mongo);
					}
        			if(result){
        				doc = DBRadioObject.createRadioDBObject(requestObj);
        				radioList.add(new RadioService().addRadio(doc, mongo));
        				respJSON = MzgazineUtil.getAllDetailLists(radioList , 1);
        	    	}else{
        	    		doc = DBRadioObject.createRadioDBObject(requestObj);
        	    		new RadioService().updateRadio(requestObj.get("_id").toString(), doc , mongo);
        	    		//respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.ALREADY_REGISTERD);
        	    	}
        		}else{
        			//respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
        		}
			}else{
				//respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}
	    }catch (Exception e) {
	    	System.out.println(e);
	    	//respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}

	@RequestMapping(value = UrlCommonConstant.GET_RADIO + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getRadio(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> radioList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					 System.out.println(requestObj);
					if(requestObj != null){
						DBCursor dbCursor =  new RadioService().getRadio(requestObj, mongo);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 radioList.add(doc);
						}
						int count = new RadioService().getCount(mongo);
						respJSON = MzgazineUtil.getAllDetailLists(radioList, count);
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			//respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		 System.out.println("radioList  "+radioList.size());
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.UPDATE_RADIO + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String updateRadio(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		DBObject doc= null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
        			boolean result = true;/*new VendorUserService().vendorFind(requestObj.getString(CommonConstants.EMAIL), requestObj.getString(CommonConstants.EMAIL), mongo);*/
        			if(result){
        				doc = DBRadioObject.createRadioDBObject(requestObj);
        				new RadioService().updateRadio(requestObj.get("_id").toString(), doc , mongo);
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
		JsonToDB.addRadiotodb();
	}
	

}
