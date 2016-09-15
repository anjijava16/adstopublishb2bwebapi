package com.sapta.b2bweb.service;

import java.util.List;

import com.sapta.b2bweb.dao.VendorDetailDAO;
import com.sapta.b2bweb.domainobject.VendorDetailDO;

public class VendorDetailsService {
	
	public VendorDetailDO addvendorDetails(VendorDetailDO vendorDetailDO){
		return new VendorDetailDAO().addVendorDetails(vendorDetailDO);
	}
	
	public VendorDetailDO updatevendorDetails(VendorDetailDO vendorDetailDO){	
		return new VendorDetailDAO().updateVendorDetails(vendorDetailDO);
	}
	
	public List<VendorDetailDO> retriveByVendorID(Long vendorId){	
		return new VendorDetailDAO().retriveByVendorID(vendorId);
	}

}
