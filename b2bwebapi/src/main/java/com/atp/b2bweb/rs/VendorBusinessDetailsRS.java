package com.atp.b2bweb.rs;

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
import com.atp.b2bweb.domainobject.VendorBusinessDetailDO;
import com.atp.b2bweb.service.VendorBusinessDetailsService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.VENDOR_BUSINESS)
@SessionAttributes(UrlCommonConstant.SESSION)
public class VendorBusinessDetailsRS {
	
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_BUSINESS_DETAIL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addBusinessDetail(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response ){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		String token = request.getHeader("Accept");
		System.out.println(11111+"  "+token);
		try {
			//new JwtEncodeandDecode().decodeMethod(token);    	    
			//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				System.out.println(requestObj.toString());
				if(requestObj != null){
					DBObject dbObject  =  new VendorUserService().retriveByID(requestObj.getString(CommonConstants.VENDORID), mongo);
				    	System.out.println(dbObject);
				    	if(dbObject != null && dbObject.get(CommonConstants.ACCOUNTSTATUS) != CommonConstants.INACTIVE){
				    		DBObject dbBusinessDetailObject =  new VendorBusinessDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
				    	    if(dbBusinessDetailObject == null){		    		
					    		VendorBusinessDetailDO vendorBusinessDetail = new VendorBusinessDetailDO();
					    		vendorBusinessDetail.setVendorid(requestObj.getString(CommonConstants.VENDORID));
					    		vendorBusinessDetail.setBusinessname(requestObj.getString(CommonConstants.BUSINESSNAME));
					    		vendorBusinessDetail.setBusinesstype(requestObj.getString(CommonConstants.BUSSINESSTYPE));
					    		vendorBusinessDetail.setPan(requestObj.getString(CommonConstants.PERSONALPAN));
					    		//vendorBusinessDetail.setPanurl(requestObj.getString(CommonConstants.PANURL));
					    		vendorBusinessDetail.setTinvat(requestObj.getString(CommonConstants.TINVAT));
					    		vendorBusinessDetail.setTan(requestObj.getString(CommonConstants.TAN));
					    		//vendorBusinessDetail.setTinvaturl(requestObj.getString(CommonConstants.TINVATURL));
					    		//vendorBusinessDetail.setServicetax(requestObj.getString(CommonConstants.SERVICETAX));
					    		//vendorBusinessDetail.setServicetaxurl(requestObj.getString(CommonConstants.TINVATURL));
					    		vendorBusinessDetail.setBusinesspan(requestObj.getString(CommonConstants.BUSINESSPAN));
					    		//vendorBusinessDetail.setBusinesspanurl(requestObj.getString("businesspanurl"));
					    		
					    		
					    		//FtpFileUpdate.sadsa(); save image in FTP location 
					    		new VendorBusinessDetailsService().addBusinessDetails(vendorBusinessDetail, mongo);
					    		System.out.println("created");
					    		respJSON = CommonWebUtil.buildSuccessResponse();
					    		
					    	}else{
					    		
					    		VendorBusinessDetailDO vendorBusinessDetailDO = new VendorBusinessDetailDO();
					    		vendorBusinessDetailDO.setId(dbBusinessDetailObject.get("_id").toString());
					    		vendorBusinessDetailDO.setVendorid(requestObj.getString(CommonConstants.VENDORID));
					    		vendorBusinessDetailDO.setBusinessname(requestObj.getString(CommonConstants.BUSINESSNAME));
					    		vendorBusinessDetailDO.setBusinesstype(requestObj.getString(CommonConstants.BUSSINESSTYPE));
					    		vendorBusinessDetailDO.setPan(requestObj.getString(CommonConstants.PERSONALPAN));
					    		//vendorBusinessDetailDO.setPanurl(requestObj.getString(CommonConstants.PANURL));
					    		vendorBusinessDetailDO.setTinvat(requestObj.getString(CommonConstants.TINVAT));
					    		vendorBusinessDetailDO.setTan(requestObj.getString(CommonConstants.TAN));
					    		//vendorBusinessDetailDO.setTinvaturl(requestObj.getString(CommonConstants.TINVATURL));
					    		//vendorBusinessDetailDO.setServicetax(requestObj.getString(CommonConstants.SERVICETAX));
					    		//vendorBusinessDetailDO.setServicetaxurl(requestObj.getString(CommonConstants.SERVICETAXURL));
					    		vendorBusinessDetailDO.setBusinesspan(requestObj.getString(CommonConstants.BUSINESSPAN));
					    	//	vendorBusinessDetailDO.setBusinesspanurl(requestObj.getString("businesspanurl"));
					    		
					    		new VendorBusinessDetailsService().updateBusinessDetails(vendorBusinessDetailDO, mongo);
					    		System.out.println("updated");
			        	    	respJSON = CommonWebUtil.buildSuccessResponse();
					    	}	
				    	}else
				    		 respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.USER_NOT_EXITS);
				}else
	        	    respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
	    	}else
	    		respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
	    }catch (Exception e) {
	    	System.out.println(e);
	    	respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	

	@SuppressWarnings({ "unused"})
	@RequestMapping(value = UrlCommonConstant.GET_BY_VENDOR_DETAIL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String  retriveByVendorID(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		JSONObject respJSON = null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					DBObject dbObject =  new VendorBusinessDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
			       	  if(dbObject != null ){
			       		//respJSON = new VendorBusinessDetailsUtil().getBusinessDetailList(vendorBusinessDetailDO);
			       		 respJSON = (JSONObject) dbObject;
			       		 System.out.println(respJSON);
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

}
