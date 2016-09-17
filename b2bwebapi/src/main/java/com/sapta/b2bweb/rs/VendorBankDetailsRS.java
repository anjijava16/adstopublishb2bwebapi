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
import com.sapta.b2bweb.domainobject.VendorBankDetailDO;
import com.sapta.b2bweb.domainobject.VendorUserDO;
import com.sapta.b2bweb.service.VendorBankDetailsService;
import com.sapta.b2bweb.service.VendorUserService;
import com.sapta.b2bweb.util.CommonUtil;
import com.sapta.b2bweb.util.CommonWebUtil;
import com.sapta.b2bweb.util.VendorBankDetailsUtil;

@Controller
@RequestMapping(value = "/vendorbank")
@SessionAttributes("session")
public class VendorBankDetailsRS {
	
	@SuppressWarnings("unused")
	@RequestMapping(value="/addbankdetail/{requestParameter}", method = RequestMethod.GET)
    @ResponseBody
	public String  addbankDetail(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response ){
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		JSONObject respJSON = null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				
				if(requestObj != null){
					List<VendorUserDO> vendoruser =  new VendorUserService().retriveByID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
					List<VendorBankDetailDO> vendorBankDetailList =  new VendorBankDetailsService().retriveByVendorID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
			    	if(vendoruser.size() > 0 && vendoruser.get(0).getAccountstatus() != 'I' && vendorBankDetailList.size() == 0){    	
			    		VendorBankDetailDO vendorBankDetailDO = new VendorBankDetailDO();
			    		vendorBankDetailDO.setVendorid(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
			    		vendorBankDetailDO.setAccountholdername(requestObj.getString(CommonConstants.ACCOUNTHOLDERNAME));
			    		vendorBankDetailDO.setAccountnumber(Long.valueOf(requestObj.getString(CommonConstants.ACCOUNTNUMBER)));
			    		vendorBankDetailDO.setIfsc(requestObj.getString(CommonConstants.IFSC));
			    		vendorBankDetailDO.setBankname(requestObj.getString(CommonConstants.BANKNAME));
			    		vendorBankDetailDO.setState(requestObj.getString(CommonConstants.STATE));
			    		vendorBankDetailDO.setCity(requestObj.getString(CommonConstants.CITY));
			    		vendorBankDetailDO.setBranch(requestObj.getString(CommonConstants.BRANCH));
			    		vendorBankDetailDO.setAddressprooftype(requestObj.getString(CommonConstants.ADDRESSPROOFTYPE));
			    		//vendorBankDetailDO.setAddressproofurl(requestObj.getString("addressproofurl"));
			    		//vendorBankDetailDO.setCancelledchequeurl(requestObj.getString("cancelledchequeurl"));
			    		new VendorBankDetailsService().addBankDetails(vendorBankDetailDO);
			    		respJSON = CommonWebUtil.buildSuccessResponse();
			    		//new JwtEncodeandDecode().decodeMethod(token);    	    
						//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
			    	}else{
			    		VendorBankDetailDO vendorBankDetailDO = vendorBankDetailList.get(0);
			    		vendorBankDetailDO.setVendorid(Long.valueOf(CommonConstants.VENDORID));
			    		vendorBankDetailDO.setAccountholdername(CommonConstants.ACCOUNTHOLDERNAME);
			    		vendorBankDetailDO.setAccountnumber(Long.valueOf(CommonConstants.ACCOUNTNUMBER));
			    		vendorBankDetailDO.setIfsc(CommonConstants.IFSC);
			    		vendorBankDetailDO.setBankname(CommonConstants.BANKNAME);
			    		vendorBankDetailDO.setState(CommonConstants.STATE);
			    		vendorBankDetailDO.setCity(CommonConstants.STATE);
			    		vendorBankDetailDO.setBranch(CommonConstants.BRANCH);
			    		vendorBankDetailDO.setAddressprooftype(CommonConstants.ADDRESSPROOFTYPE);
			    		//vendorBankDetailDO.setAddressproofurl(addressproofurl);
			    		//vendorBankDetailDO.setCancelledchequeurl(cancelledchequeurl);
			    		new VendorBankDetailsService().updateBankDetails(vendorBankDetailDO);
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

	@SuppressWarnings({ "static-access",  "unused" })
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
		    	    List<VendorBankDetailDO> vendorBankDetailList =  new VendorBankDetailsService().retriveByVendorID(Long.valueOf(requestObj.getString(CommonConstants.VENDORID)));
		       	    if(vendorBankDetailList != null  && vendorBankDetailList.size() > 0){
		       	    	respJSON = new VendorBankDetailsUtil().getBankDetailList(vendorBankDetailList);
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
