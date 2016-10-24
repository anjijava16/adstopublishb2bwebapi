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
import com.atp.b2bweb.service.VendorBusinessDetailsService;
import com.atp.b2bweb.service.VendorUserService;
import com.atp.b2bweb.util.CommonResponseUtil;
import com.atp.b2bweb.util.CommonUtil;
import com.atp.b2bweb.util.CommonWebUtil;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.codec.binary.Base64;


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
		DBObject doc= null;
		try {
			//new JwtEncodeandDecode().decodeMethod(token);    	    
			//new JwtTokenDecoder().parseJWT(new JwtTokenGenerator().createJWT("100101", name, email, 9000000));
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					DBObject dbObject  =  new VendorUserService().retriveByID(requestObj.getString(CommonConstants.VENDORID), mongo);
				    	if(dbObject != null && dbObject.get(CommonConstants.ACCOUNTSTATUS) != CommonConstants.INACTIVE){
				    		DBObject dbBusinessDetailObject =  new VendorBusinessDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
				    		if(dbBusinessDetailObject == null){		
				    	    	doc = VendorDBObject.createVendorBusinessDetailDBObject(requestObj);				    	    	
					    		//FtpFileUpdate.sadsa(); save image in FTP location 
					    		new VendorBusinessDetailsService().addBusinessDetails(doc, mongo);
					    		respJSON = CommonWebUtil.buildSuccessResponse();
					    	}else{
					    		doc = VendorDBObject.createVendorBusinessDetailDBObject(requestObj);
					    		new VendorBusinessDetailsService().updateBusinessDetails(doc, dbBusinessDetailObject.get("_id").toString(), mongo);
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
	
	@RequestMapping(value = UrlCommonConstant.GET_BY_VENDOR_DETAIL + UrlCommonConstant.REQUEST_PARAMETER, method = RequestMethod.GET)
    @ResponseBody
   	public String  retriveByVendorID(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response){
    	response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
    	mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		org.json.simple.JSONObject respJSON = null;
		List<DBObject> businessList = new ArrayList<>();
		try {
			if(requestParameter != null){
				JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
				if(requestObj != null){
					DBObject dbObject =  new VendorBusinessDetailsService().retriveByVendorID(requestObj.getString(CommonConstants.VENDORID), mongo);
			       	  if(dbObject != null ){
			       		businessList.add(dbObject);
		       	    	respJSON = CommonResponseUtil.getAllDetailLists(businessList , 1);
			       	  }else{
			       		respJSON = CommonResponseUtil.getErrorResponseObject("not found");
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

	@RequestMapping(value="/newDocument", method = RequestMethod.POST)
	@ResponseBody
	public String UploadFile(MultipartHttpServletRequest request, HttpServletResponse response) throws IOException, JSONException { 
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		MultipartFile file = null; 
		JSONObject responseJSON = null;
		 System.out.println("asdsadsadsa");
		if(request.getFile("file") != null && request.getParameter("type") != null){			
			file = request.getFile("file");
			String fileName = request.getParameter("vendorid")+"_"+request.getParameter("typename")+"_"+request.getParameter("type")+"url";
			List<GridFSDBFile> imageFiles = new VendorBusinessDetailsService().getImage(fileName, mongo);
			byte[] imageBytes = file.getBytes();
			
			new VendorBusinessDetailsService().addImage(imageBytes, fileName, mongo);
			new VendorBusinessDetailsService().updateBusinessDetailsImageURL(request.getParameter("type")+"url", fileName, request.getParameter("vendorid"), mongo);
			responseJSON = CommonWebUtil.buildSuccessImgResponse("Image added success fully");
		}else{
			responseJSON = CommonWebUtil.buildErrorResponse("");
		}
		System.out.println("888888888888");
		
		/*
		    FileOutputStream outputImage = new FileOutputStream("C:/Users/SAPTALABS/Documents/GitHub/adstopublishb2bweb/b2bweb/src/main/webapp/pages/ui/images/user/bearCopy.bmp");
		    aaa.writeTo( outputImage );
	        outputImage.close();*/
		 return responseJSON != null ? responseJSON.toString() : CommonConstants.EMPTY;
	}
	
	@RequestMapping(value="/getimage/{requestParameter}", method = RequestMethod.GET)
	@ResponseBody
	public String getImage(@PathVariable String requestParameter, HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException { 
	System.out.println("ininininnini");
		response.setHeader(CommonConstants.RESPONSE_HEADER, CommonConstants.STAR);
		ByteArrayOutputStream out = null;
		mongo = (MongoClient) request.getServletContext().getAttribute(TableCommonConstant.MONGO_CLIENT);
		if(requestParameter != null){
			JSONObject requestObj = new JSONObject(CommonUtil.decode(requestParameter));
			if(requestObj != null){
				List<GridFSDBFile> aaa = new VendorBusinessDetailsService().getImage(requestObj.get("name").toString(), mongo);
				System.out.println(aaa.size());
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
		//System.out.println(DatatypeConverter.printBase64Binary(out.toByteArray()));
		JSONObject respJSON = new JSONObject();
		respJSON.put("image",  DatatypeConverter.printBase64Binary(out.toByteArray()));
		return respJSON != null ? respJSON.toString() : CommonConstants.EMPTY;
	}
	
	
}
