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
import com.atp.b2bweb.createdbobject.DBAirlineAndAirportsObject;
import com.atp.b2bweb.service.AirlineAndAirportsService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.JsonToDB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.AIRLINE_AND_AIRPORTS)
@SessionAttributes(UrlCommonConstant.SESSION)
public class AirlineAndAirportsRS {

MongoClient mongo;
	
	@RequestMapping(value = UrlCommonConstant.ADD_AIRLINE_AND_AIRPORTS + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addAirlineAndAirports(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		org.json.simple.JSONObject respJSON = null;
		DBObject doc= null;
		boolean result = true;
		List<DBObject> airlineList = new ArrayList<>();
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					if(!requestObj.getString("_id").equalsIgnoreCase("")){
						result = new AirlineAndAirportsService().findOutdoor(requestObj.getString("_id"), mongo);
					}
					if(result){
        				doc = DBAirlineAndAirportsObject.createAirlineAndAirportsDBObject(requestObj);        				
        				airlineList.add(new AirlineAndAirportsService().addAirlineAndAirportsService(doc, mongo));
        				respJSON = CommonResponseUtil.getAllDetailLists(airlineList , 1);
        	    	}else{
        	    		doc = DBAirlineAndAirportsObject.createAirlineAndAirportsDBObject(requestObj);  
        	    		airlineList.add(new AirlineAndAirportsService().updateAirlineAndAirportsService(requestObj.get("_id").toString(), doc , mongo));
        	    		respJSON = CommonResponseUtil.getAllDetailLists(airlineList , 1);
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

	@RequestMapping(value = UrlCommonConstant.GET_AIRLINE_AND_AIRPORTS + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String getAirlineAndAirports(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		 response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		 org.json.simple.JSONObject respJSON = null;
		 mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		 DBObject doc = null;
		 List<DBObject> AirlineAirportsList = new ArrayList<>();
		 try {
				if(requestParameter != null){
					JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
					if(requestObj != null){
						DBCursor dbCursor =  new AirlineAndAirportsService().getAirlineAndAirportsService(requestObj, mongo);
						while(dbCursor.hasNext()){
							 doc = dbCursor.next();
							 AirlineAirportsList.add(doc);
						}
						int count = new AirlineAndAirportsService().getCount(mongo);
						respJSON = CommonResponseUtil.getAllDetailLists(AirlineAirportsList, count);
					}
				}
		}catch (Exception e) {
			System.out.println("exception "+e);
			respJSON = CommonResponseUtil.getErrorResponseObject("Exception");
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	    
	@RequestMapping(value = "/addtodb")
    @ResponseBody
	public void addrecordtodb(){
		new JsonToDB().addAirlineAndAirporttodb();
	}

}
