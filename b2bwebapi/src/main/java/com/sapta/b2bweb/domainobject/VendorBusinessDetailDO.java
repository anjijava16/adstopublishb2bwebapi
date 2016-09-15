package com.sapta.b2bweb.domainobject;

import java.util.Date;

import javax.persistence.*;

import com.sapta.b2bweb.commons.CommonConstants;

@Entity
@Table(name = CommonConstants.VENDOR_BUSSINESS_DETAILS)
@TableGenerator(name = CommonConstants.VENDOR_BUSSINESS_DETAILS, initialValue = 000001, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = CommonConstants.FIND_BY_VENDOR_ID_BUSINESS, query = "SELECT c FROM VendorBusinessDetailDO c WHERE c.vendorid =:vendorid")
})
public class VendorBusinessDetailDO {
	
	public static final String FIND_BY_VENDOR_ID = CommonConstants.FIND_BY_VENDOR_ID_BUSINESS;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = CommonConstants.VENDOR_BUSSINESS_DETAILS)
    private Long id;
    
    private Long vendorid;
	
	private String businessname;

	private String businesstype;
	
	private String pan;

	private String panurl;
	
	private String tan;
	
	private String tanUrl;
	
	private String tinvat;
	
	private String tinvaturl;
	
	private String servicetax;

	private String servicetaxurl;
	
	private String businesspan ;

	private String businesspanurl;	

	private String updatedby;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedon;

	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}	

	public String getTan() {
		return tan;
	}
	

	public void setTan(String tan) {
		this.tan = tan;
	}
	

	public String getTanUrl() {
		return tanUrl;
	}
	

	public void setTanUrl(String tanUrl) {
		this.tanUrl = tanUrl;
	}
	

	public Long getVendorid() {
		return vendorid;
	}

	public void setVendorid(Long vendorid) {
		this.vendorid = vendorid;
	}

	public String getBusinessname() {
		return businessname;
	}	

	public void setBusinessname(String businessname) {
		this.businessname = businessname;
	}	

	public String getBusinesstype() {
		return businesstype;
	}	

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}	

	
	public String getBusinesspan() {
		return businesspan;
	}
	

	public void setBusinesspan(String businesspan) {
		this.businesspan = businesspan;
	}
	

	public String getBusinesspanurl() {
		return businesspanurl;
	}
	

	public void setBusinesspanurl(String businesspanurl) {
		this.businesspanurl = businesspanurl;
	}
	

	public String getServicetax() {
		return servicetax;
	}
	

	public void setServicetax(String servicetax) {
		this.servicetax = servicetax;
	}


	public String getTinvat() {
		return tinvat;
	}	

	public void setTinvat(String tinvat) {
		this.tinvat = tinvat;
	}	

	public String getTinvaturl() {
		return tinvaturl;
	}	

	public void setTinvaturl(String tinvaturl) {
		this.tinvaturl = tinvaturl;
	}	


	

	public String getServicetaxurl() {
		return servicetaxurl;
	}
	

	public void setServicetaxurl(String servicetaxurl) {
		this.servicetaxurl = servicetaxurl;
	}
	

	

	public String getPan() {
		return pan;
	}
	

	public void setPan(String pan) {
		this.pan = pan;
	}
	

	public String getPanurl() {
		return panurl;
	}
	

	public void setPanurl(String panurl) {
		this.panurl = panurl;
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
