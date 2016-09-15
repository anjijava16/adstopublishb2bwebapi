package com.sapta.b2bweb.service;

import java.util.List;

import com.sapta.b2bweb.dao.VendorUserDAO;
import com.sapta.b2bweb.domainobject.VendorUserDO;

public class VendorUserService {
	public VendorUserDO vendorRegister(VendorUserDO vendoruserDO){
		return new VendorUserDAO().retriveVendor(vendoruserDO);
	}
	
	public List<VendorUserDO> vendorLogin(String email,String password){	
		return new VendorUserDAO().vendorLogin(email,password);
	}
	
	public VendorUserDO vendorUpdate(VendorUserDO vendorUserDO){	
		return new VendorUserDAO().vendorUpdate(vendorUserDO);
	}
	
	public String vendorDelete(VendorUserDO vendorUserDO){	
		return new VendorUserDAO().vendorDelete(vendorUserDO);
	}
	
	public List<VendorUserDO> retriveByID(Long vendorID){	
		return new VendorUserDAO().retriveByID(vendorID);
	}
	
    public List<VendorUserDO> retriveUserByEmailOrMobile(String email,String phone){	
		return new VendorUserDAO().retriveUserByEmailOrMobile(email,phone);
	}


}
