package com.sapta.b2bweb.service;

import java.util.List;

import com.sapta.b2bweb.dao.VendorBusinessDetailsDAO;
import com.sapta.b2bweb.domainobject.VendorBusinessDetailDO;

public class VendorBusinessDetailsService {
	
	public VendorBusinessDetailDO addBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO){
		return new VendorBusinessDetailsDAO().addBusinessDetails(vendorBusinessDetailDO);
	}
	
	public VendorBusinessDetailDO updateBusinessDetails(VendorBusinessDetailDO vendorBusinessDetailDO){	
		return new VendorBusinessDetailsDAO().updateBusinessDetails(vendorBusinessDetailDO);
	}
	
	public List<VendorBusinessDetailDO> retriveByVendorID(Long vendorId){	
		return new VendorBusinessDetailsDAO().retriveByVendorID(vendorId);
	}
}
