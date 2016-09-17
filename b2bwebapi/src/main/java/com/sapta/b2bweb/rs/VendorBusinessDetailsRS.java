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
import com.sapta.b2bweb.domainobject.VendorBusinessDetailDO;
import com.sapta.b2bweb.domainobject.VendorUserDO;
import com.sapta.b2bweb.service.VendorBusinessDetailsService;
import com.sapta.b2bweb.service.VendorUserService;
import com.sapta.b2bweb.util.CommonUtil;
import com.sapta.b2bweb.util.CommonWebUtil;
import com.sapta.b2bweb.util.VendorBusinessDetailsUtil;

@Controller
@RequestMapping(value = "/vendorbusiness")
@SessionAttributes("session")
public class VendorBusinessDetailsRS {
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/addbusinessdetail/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String  addBusinessDetail(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response ){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		
		String token = request.getHeader("Accept");
		System.out.println(11111+"  "+token);
		try {
			//new JwtEncodeandDecode().decodeMethod(token);    	    
			//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				System.out.println(requestObj.toString());
				if(requestObj != null){
			    	    List<VendorUserDO> vendoruser =  new VendorUserService().retriveByID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
				    	System.out.println(vendoruser.size());
				    	
				    	List<VendorBusinessDetailDO> vendorBusinessDetailList =  new VendorBusinessDetailsService().retriveByVendorID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
				    	
			    	    if(vendoruser.size() > 0 && vendoruser.get(0).getAccountstatus() != CommonConstants.INACTIVE && vendorBusinessDetailList.size() == 0){				    		
				    		VendorBusinessDetailDO vendorBusinessDetail = new VendorBusinessDetailDO();
				    		vendorBusinessDetail.setVendorid(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
				    		vendorBusinessDetail.setBusinessname(requestObj.getString(CommonConstants.BUSINESSNAME));
				    		vendorBusinessDetail.setBusinesstype(requestObj.getString(CommonConstants.BUSSINESSTYPE));
				    		vendorBusinessDetail.setPan(requestObj.getString(CommonConstants.PERSONALPAN));
				    		//vendorBusinessDetail.setPanurl(requestObj.getString("panurl"));
				    		vendorBusinessDetail.setTinvat(requestObj.getString(CommonConstants.TINVAT));
				    		vendorBusinessDetail.setTan(requestObj.getString(CommonConstants.TAN));
				    		/*vendorBusinessDetail.setTinvaturl(requestObj.getString("tinvaturl"));
				    		vendorBusinessDetail.setServicetax(requestObj.getString("servicetaxurl"));
				    		vendorBusinessDetail.setServicetaxurl(requestObj.getString("servicetaxurl"));*/
				    		vendorBusinessDetail.setBusinesspan(requestObj.getString(CommonConstants.BUSINESSPAN));
				    		//vendorBusinessDetail.setBusinesspanurl(requestObj.getString("businesspanurl"));
				    		
				    		
				    		//FtpFileUpdate.sadsa(); save image in FTP location 
				    		new VendorBusinessDetailsService().addBusinessDetails(vendorBusinessDetail);
				    		System.out.println("created");
				    		respJSON = CommonWebUtil.buildSuccessResponse();
				    	}else{
				    		VendorBusinessDetailDO vendorBusinessDetailDO = vendorBusinessDetailList.get(0);
				    		vendorBusinessDetailDO.setVendorid(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
				    		vendorBusinessDetailDO.setBusinessname(requestObj.getString(CommonConstants.BUSINESSNAME));
				    		vendorBusinessDetailDO.setBusinesstype(requestObj.getString(CommonConstants.BUSSINESSTYPE));
				    		vendorBusinessDetailDO.setPan(requestObj.getString(CommonConstants.PERSONALPAN));
				    		//vendorBusinessDetailDO.setPanurl(requestObj.getString("panurl"));
				    		vendorBusinessDetailDO.setTinvat(requestObj.getString(CommonConstants.TINVAT));
				    		vendorBusinessDetailDO.setTan(requestObj.getString(CommonConstants.TAN));
				    		/*vendorBusinessDetailDO.setTinvaturl(requestObj.getString("tinvaturl"));
				    		vendorBusinessDetailDO.setServicetax(requestObj.getString("servicetaxurl"));
				    		vendorBusinessDetailDO.setServicetaxurl(requestObj.getString("servicetaxurl"))*/;
				    		vendorBusinessDetailDO.setBusinesspan(requestObj.getString(CommonConstants.BUSINESSPAN));
				    	//	vendorBusinessDetailDO.setBusinesspanurl(requestObj.getString("businesspanurl"));
				    		
				    		new VendorBusinessDetailsService().updateBusinessDetails(vendorBusinessDetailDO);
				    		System.out.println("updated");
		        	    	respJSON = CommonWebUtil.buildSuccessResponse();
				    	}	
				}else{
	        	    respJSON = CommonWebUtil.buildErrorResponse(" ");
			    }
	    	}else{
	    		respJSON = CommonWebUtil.buildErrorResponse(" ");
	    	}
	    }catch (Exception e) {
	    	respJSON = CommonWebUtil.buildErrorResponse(" Exception ");
		}
		return respJSON != null ? respJSON.toString() : "";
	}
	

	@SuppressWarnings({ "unused", "static-access" })
	@RequestMapping(value="/getbyvendorid/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
   	public String  retriveByVendorID(@PathVariable String requestParameter, HttpServletResponse response){
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		System.out.println(11111);
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				System.out.println(requestObj.toString());
				if(requestObj != null){
		    	   List<VendorBusinessDetailDO> vendorBusinessDetailDO =  new VendorBusinessDetailsService().retriveByVendorID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
			       	  if(vendorBusinessDetailDO != null ){
			       		respJSON = new VendorBusinessDetailsUtil().getBusinessDetailList(vendorBusinessDetailDO);
			       	  }else{
				    	respJSON = CommonWebUtil.buildErrorResponse(" ");
				      }
				}else{
					respJSON = CommonWebUtil.buildErrorResponse(" ");
			    }
			}else{
				respJSON = CommonWebUtil.buildErrorResponse(" ");
		    }
   	    }catch (Exception e) {
   	    	respJSON = CommonWebUtil.buildErrorResponse("exception");
   		}
       return respJSON != null ? respJSON.toString() : "";
   	}

}
