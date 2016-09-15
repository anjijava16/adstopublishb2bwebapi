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
		response.addHeader("Access-Control-Allow-Origin", "*");
		JSONObject respJSON = null;
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				
				if(requestObj != null){
					List<VendorUserDO> vendoruser =  new VendorUserService().retriveByID(Long.valueOf(requestObj.getString("vendorid")));
	    	    
			    	if(vendoruser.size() > 0 && vendoruser.get(0).getAccountstatus() != 'I'){    	
			    		VendorBankDetailDO vendorBankDetailDO = new VendorBankDetailDO();
			    		vendorBankDetailDO.setVendorid(Long.valueOf(requestObj.getString("vendorid")));
			    		vendorBankDetailDO.setAccountholdername(requestObj.getString("accountholdername"));
			    		vendorBankDetailDO.setAccountnumber(Long.valueOf(requestObj.getString("accountnumber")));
			    		vendorBankDetailDO.setIfsc(requestObj.getString("ifsc"));
			    		vendorBankDetailDO.setBankname(requestObj.getString("bankname"));
			    		vendorBankDetailDO.setState(requestObj.getString("state"));
			    		vendorBankDetailDO.setCity(requestObj.getString("city"));
			    		vendorBankDetailDO.setBranch(requestObj.getString("branch"));
			    		vendorBankDetailDO.setAddressprooftype(requestObj.getString("addressprooftype"));
			    		//vendorBankDetailDO.setAddressproofurl(requestObj.getString("addressproofurl"));
			    		//vendorBankDetailDO.setCancelledchequeurl(requestObj.getString("cancelledchequeurl"));
			    		
			    		new VendorBankDetailsService().addBankDetails(vendorBankDetailDO);
			    		//new JwtEncodeandDecode().decodeMethod(token);    	    
						//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
			    	}else{
	    	    		respJSON = CommonWebUtil.buildErrorResponse("");
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
	
	@RequestMapping(value="/updatebankdetail/{vendorid}/{accountholdername}/{accountnumber}/{Ifsc}/{bankname}/{state}/{city}/{branch}/{addressprooftype}/{addressproofurl}/{cancelledchequeurl}", method = RequestMethod.GET)
    @ResponseBody
	public String  updatebankdetail(@PathVariable String vendorid,@PathVariable String accountholdername, @PathVariable String accountnumber, @PathVariable String Ifsc, @PathVariable String bankname, @PathVariable String state, @PathVariable String city, @PathVariable String branch, @PathVariable String addressprooftype, @PathVariable String addressproofurl, @PathVariable String cancelledchequeurl){
	try {
    		VendorBankDetailDO vendorBankDetailDO=  new VendorBankDetailsService().retriveByVendorID(Long.valueOf(vendorid));
    	    System.out.println(vendorBankDetailDO);
			
	    	if(vendorBankDetailDO == null){  
	    		VendorBankDetailDO  vendorBankDetail = new VendorBankDetailDO();
	    		vendorBankDetail.setVendorid(Long.valueOf(vendorid));
	    		vendorBankDetail.setAccountholdername(accountholdername);
				vendorBankDetail.setAccountnumber(Long.valueOf(accountnumber));
				vendorBankDetail.setIfsc(Ifsc);
				vendorBankDetail.setBankname(bankname);
				vendorBankDetail.setState(state);
				vendorBankDetail.setCity(city);
				vendorBankDetail.setBranch(branch);
				vendorBankDetail.setAddressprooftype(addressprooftype);
				vendorBankDetail.setAddressproofurl(addressproofurl);
				vendorBankDetail.setCancelledchequeurl(cancelledchequeurl);
	    		new VendorBankDetailsService().addBankDetails(vendorBankDetailDO);
				return "vendor Bank Detail  added";
	    	}else{
	    		vendorBankDetailDO.setVendorid(Long.valueOf(vendorid));
	    		vendorBankDetailDO.setAccountholdername(accountholdername);
	    		vendorBankDetailDO.setAccountnumber(Long.valueOf(accountnumber));
	    		vendorBankDetailDO.setIfsc(Ifsc);
	    		vendorBankDetailDO.setBankname(bankname);
	    		vendorBankDetailDO.setState(state);
	    		vendorBankDetailDO.setCity(city);
	    		vendorBankDetailDO.setBranch(branch);
	    		vendorBankDetailDO.setAddressprooftype(addressprooftype);
	    		vendorBankDetailDO.setAddressproofurl(addressproofurl);
	    		vendorBankDetailDO.setCancelledchequeurl(cancelledchequeurl);
	    		new VendorBankDetailsService().updateBankDetails(vendorBankDetailDO);
	    		return "vendor Bank Detail  updateded";
	    	}
			    //new JwtEncodeandDecode().decodeMethod(token);    	    
				//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
	    }catch (Exception e) {
			return "error";
		}
    
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping(value="/getbyvendorid/{vendorid}", method = RequestMethod.GET)
    @ResponseBody
   	public String  retriveByVendorID(@PathVariable String vendorid){
		JSONObject respJSON = null;
       try {
    	   VendorBankDetailDO vendorBankDetailDO =  new VendorBankDetailsService().retriveByVendorID(Long.valueOf(vendorid));
       	  if(vendorBankDetailDO != null ){
       		respJSON = new VendorBankDetailsUtil().getBankDetailList((List<VendorBankDetailDO>) vendorBankDetailDO);
       	  }
   	    	
   	    }catch (Exception e) {
   			return "error";
   		}
       return respJSON != null ? respJSON.toString() : "";
       
   	}
    

}
