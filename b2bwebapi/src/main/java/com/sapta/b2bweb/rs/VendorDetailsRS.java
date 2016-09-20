package com.sapta.b2bweb.rs;

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

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.domainobject.VendorDetailDO;
import com.sapta.b2bweb.domainobject.VendorUserDO;
import com.sapta.b2bweb.service.VendorDetailsService;
import com.sapta.b2bweb.service.VendorUserService;
import com.sapta.b2bweb.util.CommonUtil;
import com.sapta.b2bweb.util.CommonWebUtil;
import com.sapta.b2bweb.util.VendorDetailsUtil;

@Controller
@RequestMapping(value = "/vendordetail")
@SessionAttributes("session")
public class VendorDetailsRS {
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/addvendordetail/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String  addvendorDetail(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					List<VendorUserDO> vendoruser =  new VendorUserService().retriveByID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
					if(vendoruser.size() > 0 && vendoruser.get(0).getAccountstatus() != 'I'){
						List<VendorDetailDO> vendorDetailList =  new VendorDetailsService().retriveByVendorID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
						if(vendorDetailList.size() == 0){    	
							VendorDetailDO vendorDetailDO = new VendorDetailDO();
				    		vendorDetailDO.setVendorid(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
				    		vendorDetailDO.setDisplayname(requestObj.getString(CommonConstants.DISPLAYNAME));
				    		vendorDetailDO.setBusinessDesc(requestObj.getString(CommonConstants.BUISENESSDESC));
				    		new VendorDetailsService().addvendorDetails(vendorDetailDO);
				    		respJSON = CommonWebUtil.buildSuccessResponse();
						}else{
							VendorDetailDO vendorDetailDO = vendorDetailList.get(0);	
							vendorDetailDO.setVendorid(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
				    		vendorDetailDO.setDisplayname(requestObj.getString(CommonConstants.DISPLAYNAME));
				    		vendorDetailDO.setBusinessDesc(requestObj.getString(CommonConstants.BUISENESSDESC));
				    		new VendorDetailsService().updatevendorDetails(vendorDetailDO);
				    		respJSON = CommonWebUtil.buildSuccessResponse();
						}
					}else
						respJSON = CommonWebUtil.buildErrorResponse("vendor not exits register first");
	    	}else
	    		respJSON = CommonWebUtil.buildErrorResponse("");
			}else
				respJSON = CommonWebUtil.buildErrorResponse("");
			    //new JwtEncodeandDecode().decodeMethod(token);    	    
				//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
	    }catch (Exception e) {
	    	respJSON = CommonWebUtil.buildErrorResponse(" Exception ");
		}
		return respJSON != null ? respJSON.toString() : "";
	}

    @SuppressWarnings({ "static-access", "unused" })
	@RequestMapping(value="/getbyvendorid/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String  retriveByVendorID(@PathVariable String requestParameter,  HttpServletRequest request, HttpServletResponse response){
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		System.out.println(11111);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				System.out.println(requestObj.toString());
				if(requestObj != null){
			    	  List<VendorDetailDO> vendorDetailList=  new VendorDetailsService().retriveByVendorID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
			    	  if(vendorDetailList != null && vendorDetailList.size() > 0){
			    		  respJSON = new VendorDetailsUtil().getVendorDetailList(vendorDetailList);
			    		  respJSON = CommonWebUtil.buildSuccessResponse();
			    	  }else{
			    		  respJSON = CommonWebUtil.buildErrorResponse("");
			    	  }
				}else{
					respJSON = CommonWebUtil.buildErrorResponse("");
				}
			}else{
				respJSON = CommonWebUtil.buildErrorResponse("");
			}
	    }catch (Exception e) {
	    	respJSON = CommonWebUtil.buildErrorResponse("exception");
		}
    	return respJSON != null ? respJSON.toString() : "";
    
	}

}
