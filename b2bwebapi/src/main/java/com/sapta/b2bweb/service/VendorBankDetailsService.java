package com.sapta.b2bweb.service;

import java.util.List;

import com.sapta.b2bweb.dao.VendorBankDetailsDAO;
import com.sapta.b2bweb.domainobject.VendorBankDetailDO;

public class VendorBankDetailsService {
	
	public VendorBankDetailDO addBankDetails(VendorBankDetailDO vendorBankDetailDO){
		return new VendorBankDetailsDAO().addBankDetails(vendorBankDetailDO);
	}
	
	public VendorBankDetailDO updateBankDetails(VendorBankDetailDO vendorBankDetailDO){	
		return new VendorBankDetailsDAO().updateBankDetails(vendorBankDetailDO);
	}
	
	public List<VendorBankDetailDO> retriveByVendorID(Long vendorId){	
		return new VendorBankDetailsDAO().retriveByVendorID(vendorId);
	}
}
