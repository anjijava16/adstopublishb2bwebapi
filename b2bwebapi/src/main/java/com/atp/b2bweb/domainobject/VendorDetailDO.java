package com.atp.b2bweb.domainobject;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.atp.b2bweb.common.CommonConstants;

/*@Entity
@Table(name = CommonConstants.VENDOR_DETAILS)
@TableGenerator(name = CommonConstants.VENDOR_DETAILS, initialValue = 000001, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = CommonConstants.FIND_BY_VENDOR_ID_DETAILS, query = "SELECT c FROM VendorDetailDO c WHERE c.vendorid =:vendorid")
})*/
public class VendorDetailDO {
	
	public static final String FIND_BY_VENDOR_ID = CommonConstants.FIND_BY_VENDOR_ID_DETAILS;
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = CommonConstants.VENDOR_DETAILS)*/
    private String id;
    
    private String vendorid;
	
	private String displayname;
	
	private String businessDesc;
	
	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedon;

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getVendorid() {
		return vendorid;
	}

	public void setVendorid(String vendorid) {
		this.vendorid = vendorid;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}
	
	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public String getUpdatedby() {
		return updatedby;
	}
	

	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	

	public Date getUpdatedon() {
		return updatedon;
	}

	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}
	
}
