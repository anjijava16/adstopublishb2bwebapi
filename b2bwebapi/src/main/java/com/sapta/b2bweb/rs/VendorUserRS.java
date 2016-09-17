package com.sapta.b2bweb.rs;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sapta.b2bweb.commons.CommonConstants;
import com.sapta.b2bweb.domainobject.VendorUserDO;
import com.sapta.b2bweb.emailproxy.EmailProxyUtil;
import com.sapta.b2bweb.jwttoken.JwtTokenGenerator;
import com.sapta.b2bweb.service.VendorUserService;
import com.sapta.b2bweb.util.CommonUtil;
import com.sapta.b2bweb.util.CommonWebUtil;
import com.sapta.b2bweb.util.FtpFileUpdate;

@Controller
@RequestMapping(value = "/vendor")
@SessionAttributes("session")
public class VendorUserRS {
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/register/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String  vendorRegister(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
        			List<VendorUserDO> vendorUserDOList = new VendorUserService().vendorLogin(requestObj.getString(CommonConstants.EMAIL), requestObj.getString(CommonConstants.EMAIL));
        			if(vendorUserDOList != null && vendorUserDOList.size() == 0){    	
        		    	VendorUserDO vendoruserDO = new VendorUserDO();
        		    	vendoruserDO.setUsername(requestObj.getString(CommonConstants.NAME));
        		    	vendoruserDO.setEmail(requestObj.getString(CommonConstants.EMAIL));
        		    	vendoruserDO.setMobile(requestObj.getString(CommonConstants.PHONE));
        		    	vendoruserDO.setPassword(requestObj.getString(CommonConstants.PASSWORD));
        		    	vendoruserDO.setAccountstatus(CommonConstants.ACTIVE);
        		    	
        		    	vendoruserDO.setUpdatedby(requestObj.getString(CommonConstants.NAME));
        		    	vendoruserDO.setUpdatedon(new Date());
        		    	
        		    	VendorUserDO registerVendor = new VendorUserService().vendorRegister(vendoruserDO);
        		    	
        		    	//token generate    	
        		    	new JwtTokenGenerator().createJWT(vendoruserDO, response);
        	    	
        		    	//sen mail
        		    	EmailProxyUtil.sendEmail("","","i got it",false,registerVendor.getEmail());
        		    	
        		    	respJSON = CommonWebUtil.buildSuccessResponse();
        	    	}else{
        	    		respJSON = CommonWebUtil.buildErrorResponse("Plz Login ");
        	    	}
        		}else{
        			respJSON = CommonWebUtil.buildErrorResponse("");
        		}
			}else{
				respJSON = CommonWebUtil.buildErrorResponse("");
			}
			
	    }catch (Exception e) {
	    	respJSON = CommonWebUtil.buildErrorResponse("");
		}
		return respJSON != null ? respJSON.toString() : "";
	}

    
    @SuppressWarnings("unused")
	@RequestMapping(value="/login/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String vendorLogin(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) throws JSONException {	
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	JSONObject respJSON = null;
    	try{
    		if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		if(requestObj != null){
        			List<VendorUserDO> vendorUserDOList = new VendorUserService().vendorLogin(requestObj.getString(CommonConstants.USERNAME), requestObj.getString(CommonConstants.PASSWORD));
        			if(vendorUserDOList != null && vendorUserDOList.size() > 0){
        				new JwtTokenGenerator().createJWT(vendorUserDOList.get(0), response);
        				respJSON = CommonWebUtil.buildSuccessResponse();
        			}else{
        				respJSON = CommonWebUtil.buildErrorResponse("user not exits");
        			}
        		}else{
        			respJSON = CommonWebUtil.buildErrorResponse("requestObj");
        		}
        	}else{
        		respJSON = CommonWebUtil.buildErrorResponse("requestParameter");
        	}
    	}catch(Exception exception){
    		respJSON = CommonWebUtil.buildErrorResponse("exception");
    	}
    	FtpFileUpdate.sadsa();
    	return respJSON != null ? respJSON.toString() : "empty";
	}
	
    @SuppressWarnings("unused")
	@RequestMapping(value="/update/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String vendorUpdate(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) {	
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	JSONObject respJSON = null;
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		if(requestObj != null){
        			List<VendorUserDO> vendoruser =  new VendorUserService().retriveByID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
        			if(vendoruser.size() > 0){
        				String email = requestObj.getString(CommonConstants.EMAIL);
        				String password = requestObj.getString(CommonConstants.PASSWORD);
        				String phone = requestObj.getString(CommonConstants.PHONE);
        				char activetype = requestObj.getString(CommonConstants.ACTIVETYPE).charAt(0);
        				String name = requestObj.getString(CommonConstants.NAME);
        				VendorUserDO vendoruserDO = vendoruser.get(0);
    			    	if(email != null)vendoruserDO.setEmail(email);
    			    	if(password != null)vendoruserDO.setPassword(password);
    			    	if(phone != null)vendoruserDO.setMobile(phone);
    			    	vendoruserDO.setAccountstatus(activetype);
    			    	if(name != null)vendoruserDO.setDisplayname(name);
    			    	new VendorUserService().vendorUpdate(vendoruserDO);
    		    	}else
    		    		respJSON = CommonWebUtil.buildErrorResponse("user not exits");
        		}else
		    		respJSON = CommonWebUtil.buildErrorResponse("");
			}else
	    		respJSON = CommonWebUtil.buildErrorResponse("");
		}catch(Exception exception){
    		respJSON = CommonWebUtil.buildErrorResponse("exception");
    	}
		return respJSON != null ? respJSON.toString() : "";
	}
    
    
    @SuppressWarnings("unused")
	@RequestMapping(value="/delete/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String vendorDelete(@PathVariable String requestParameter, HttpServletRequest request,  HttpServletResponse response) {	
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	JSONObject respJSON = null;
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		if(requestObj != null){
        			List<VendorUserDO> vendoruser =  new VendorUserService().retriveByID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
			    	if(vendoruser.size() > 0){
			    		new VendorUserService().vendorDelete(vendoruser.get(0));
			    	}else
			    		respJSON = CommonWebUtil.buildErrorResponse("user not exits");
        		}else
    	    		respJSON = CommonWebUtil.buildErrorResponse("");
			}else
	    		respJSON = CommonWebUtil.buildErrorResponse("");
		}catch(Exception exception){
	    		respJSON = CommonWebUtil.buildErrorResponse("exception");
    	}
		return respJSON != null ? respJSON.toString() : "";
    	
	}
    
    
	@SuppressWarnings("unused")
	@RequestMapping(value="/forgetpass/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String vendorForgetPassword(@PathVariable String requestParameter,  HttpServletResponse response) {
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	JSONObject respJSON = null;
		try {
			if(requestParameter != null ){
        		JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
        		System.out.println(requestObj.getString(CommonConstants.USERNAME));
        		if(requestObj != null){
        			List<VendorUserDO> vendoruser =  new VendorUserService().retriveUserByEmailOrMobile(requestObj.getString(CommonConstants.USERNAME),requestObj.getString(CommonConstants.USERNAME));
					if(vendoruser.size() > 0){
						EmailProxyUtil.sendEmail("","","dont forget password next time ",false,vendoruser.get(0).getEmail());
						respJSON = CommonWebUtil.buildSuccessResponse();
					}else{
						respJSON = CommonWebUtil.buildErrorResponse("user not exits");
					}
        		}else
    	    		respJSON = CommonWebUtil.buildErrorResponse("");
			}else
	    		respJSON = CommonWebUtil.buildErrorResponse("");
		} catch (Exception e) {
			respJSON = CommonWebUtil.buildErrorResponse("exception");
		}
		return respJSON != null ? respJSON.toString() : "";
	}

}
