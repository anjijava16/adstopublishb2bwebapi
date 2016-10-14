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
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.service.VendorBankDetailsService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.VENDOR_BANK)
@SessionAttributes(UrlCommonConstant.SESSION)
public class VendorBankDetailsRS {
	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_BANK_DETAIL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addbankDetail(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response ){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		DBObject doc= null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					DBObject dbObject =  new VendorUserService().retriveByID(requestObj.getString(CommonConstants.VENDORID), mongo);
					if(dbObject != null && dbObject.get(CommonConstants.ACCOUNTSTATUS) != CommonConstants.INACTIVE){
						DBObject dbVendorBankObject =  new VendorBankDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
						
						if(dbVendorBankObject == null){
							doc = VendorDBObject.createVendorBankDetailDBObject(requestObj);
				    		new VendorBankDetailsService().addBankDetails(doc, mongo);
				    		respJSON = CommonWebUtil.buildSuccessResponse();
				    		//new JwtEncodeandDecode().decodeMethod(token);    	    
							//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
				    	}else{
				    		doc = VendorDBObject.createVendorBankDetailDBObject(requestObj);
				    		new VendorBankDetailsService().updateBankDetails(doc, dbVendorBankObject.get("_id").toString() , mongo);
		    	    		respJSON = CommonWebUtil.buildSuccessResponse();
				    	}	
					}else{
						 respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.USER_NOT_EXITS);
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

	@SuppressWarnings({  "unused" })
	@RequestMapping(value = UrlCommonConstant.GET_BY_VENDOR_DETAIL+ UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String  retriveByVendorID(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		org.json.simple.JSONObject respJSON = null;
		List<DBObject> bankList = new ArrayList<>();
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				System.out.println(requestObj.toString());
				if(requestObj != null){
					DBObject dbObject =  new VendorBankDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
		       	    if(dbObject != null){		       	    	
		       	    	bankList.add(dbObject);
		       	    	respJSON = CommonResponseUtil.getAllDetailLists(bankList , 1); 
		       	    }else{
		       	    	respJSON = CommonResponseUtil.getErrorResponseObject("addnew");
		       	    }
				}else{
					respJSON = CommonResponseUtil.getErrorResponseObject("aa");
			    }
			}else{
				respJSON = CommonResponseUtil.getErrorResponseObject("aaa");
			}
		}catch (Exception e) {
			respJSON = CommonResponseUtil.getErrorResponseObject("Exception");
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}

}
