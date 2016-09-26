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
import com.atp.b2bweb.domainobject.VendorDetailDO;
import com.atp.b2bweb.service.VendorDetailsService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

@Controller
@RequestMapping(value = UrlCommonConstant.VENDOR_DETAIL)
@SessionAttributes(UrlCommonConstant.SESSION)
public class VendorDetailsRS {
	MongoClient mongo;
	
	@SuppressWarnings("unused")
	@RequestMapping(value = UrlCommonConstant.ADD_VENDOR_DETAIL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  addvendorDetail(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		JSONObject respJSON = null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					System.out.println("  "+requestObj.getString(CommonConstants.VENDORID));
					DBObject dbObject =  new VendorUserService().retriveByID(requestObj.getString(CommonConstants.VENDORID), mongo);
					if(dbObject != null && dbObject.get(CommonConstants.ACCOUNTSTATUS) != CommonConstants.INACTIVE){
						DBObject dbObjectVendor =  new VendorDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
						System.out.println("asd1 "+dbObjectVendor );
						if(dbObjectVendor ==  null){    	
							VendorDetailDO vendorDetailDO = new VendorDetailDO();
				    		vendorDetailDO.setVendorid(requestObj.getString(CommonConstants.VENDORID));
				    		vendorDetailDO.setDisplayname(requestObj.getString(CommonConstants.DISPLAYNAME));
				    		vendorDetailDO.setBusinessDesc(requestObj.getString(CommonConstants.BUISENESSDESC));
				    		vendorDetailDO.setUpdatedby(requestObj.getString(CommonConstants.VENDORID));
				    		new VendorDetailsService().addvendorDetails(vendorDetailDO, mongo);
				    		respJSON = CommonWebUtil.buildSuccessResponse();
						}else{
							VendorDetailDO vendorDetailDO = new VendorDetailDO();
							vendorDetailDO.setId(dbObjectVendor.get(CommonConstants._ID).toString());
							vendorDetailDO.setVendorid(requestObj.getString(CommonConstants.VENDORID));
				    		vendorDetailDO.setDisplayname(requestObj.getString(CommonConstants.DISPLAYNAME));
				    		vendorDetailDO.setBusinessDesc(requestObj.getString(CommonConstants.BUISENESSDESC));
				    		vendorDetailDO.setUpdatedby(requestObj.getString(CommonConstants.VENDORID));
				    		new VendorDetailsService().updatevendorDetails(vendorDetailDO, mongo);
				    		respJSON = CommonWebUtil.buildSuccessResponse();
						}
					}else
						respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.NOT_REGISTERD);
				}else
					respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}else
				respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			    //new JwtEncodeandDecode().decodeMethod(token);    	    
				//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
	    }catch (Exception e) {
	    	System.out.println(e);
	    	respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}

    @SuppressWarnings({  "unused" })
	@RequestMapping(value=UrlCommonConstant.GET_BY_VENDOR_DETAIL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
	public String  retriveByVendorID(@PathVariable String requestParameter,  HttpServletRequest request, HttpServletResponse response){
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				System.out.println(requestObj.toString());
				if(requestObj != null){
					DBObject dbObject=  new VendorDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
			    	  if(dbObject != null){
			    		  respJSON = (JSONObject) dbObject;
			    	  }else{
			    		  respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			    	  }
				}else{
					respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
				}
			}else{
				respJSON = CommonWebUtil.buildErrorResponse(CommonConstants.EMPTY);
			}
	    }catch (Exception e) {
	    	respJSON = CommonWebUtil.buildErrorResponse(ExceptionCommonconstant.EXCEPTION);
		}
    	return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
    
	}
}
