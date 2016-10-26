package com.atp.b2bweb.rs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.atp.b2bweb.common.CommonConstants;
import com.atp.b2bweb.common.ExceptionCommonconstant;
import com.atp.b2bweb.common.TableCommonConstant;
import com.atp.b2bweb.common.UrlCommonConstant;
import com.atp.b2bweb.createdbobject.VendorDBObject;
import com.atp.b2bweb.service.VendorBankDetailsService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFSDBFile;

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
			System.out.println("bank bank ban k ");
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

	
	@RequestMapping(value="/newDocument", method = RequestMethod.POST)
	@ResponseBody
	public String UploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, JSONException { 
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		MultipartFile file = null; 
		JSONObject responseJSON = null;
		 
		if(request.getFile("file") != null && request.getParameter("type") != null){			
			file = request.getFile("file");
			String fileName = request.getParameter("vendorid")+"_"+request.getParameter("typename")+"_"+request.getParameter("type")+"url";
			List<GridFSDBFile> imageFiles = new VendorBankDetailsService().getImage(fileName, mongo);
			byte[] imageBytes = file.getBytes();
			new VendorBankDetailsService().addImage(imageBytes, fileName, mongo);
			new VendorBankDetailsService().updatebankImageURL(request.getParameter("type")+"url", fileName, request.getParameter("vendorid"), mongo);
			responseJSON = CommonWebUtil.buildSuccessImgResponse("Image added success fully");
		}else{
			responseJSON = CommonWebUtil.buildErrorResponse("");
		}
		
		/*
		    FileOutputStream outputImage = new FileOutputStream("C:/Users/SAPTALABS/Documents/GitHub/adstopublishb2bweb/b2bweb/src/main/webapp/pages/ui/images/user/bearCopy.bmp");
		    aaa.writeTo( outputImage );
	        outputImage.close();*/
		 return responseJSON != null ? responseJSON.toString() : CommonConstants.EMPTY;
	}
	
	@RequestMapping(value="/getimage/{requestParameter}", method = RequestMethod.GET)
	@ResponseBody
	public String getImage(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException { 
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		ByteArrayOutputStream out = null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		if(requestParameter != null){
			JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
			if(requestObj != null){
				List<GridFSDBFile> aaa = new VendorBankDetailsService().getImage(requestObj.get("name").toString(), mongo);
				if(aaa != null ){
					InputStream in = aaa.get(0).getInputStream();
				     out = new ByteArrayOutputStream();
				    int data = in.read();
				    while (data >= 0) {
				      out.write((char) data);
				      data = in.read();
				    }
				    out.flush();
				}
			}
		}
		JSONObject respJSON = new JSONObject();
		respJSON.put("image",  DatatypeConverter.printBase64Binary(out.toByteArray()));
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
}
