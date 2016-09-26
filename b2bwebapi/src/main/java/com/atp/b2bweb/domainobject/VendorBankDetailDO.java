package com.atp.b2bweb.domainobject;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.atp.b2bweb.common.CommonConstants;

/*@Entity
@Table(name = CommonConstants.VENDOR_BANK_DETAILS)
@TableGenerator(name = CommonConstants.VENDOR_BANK_DETAILS, initialValue = 000001, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = CommonConstants.FIND_BY_VENDOR_ID_BANK, query = "SELECT c FROM VendorBankDetailDO c WHERE c.vendorid =:vendorid")
})*/
public class VendorBankDetailDO {
	
	public static final String FIND_BY_VENDOR_ID = CommonConstants.FIND_BY_VENDOR_ID_BANK;
	
	/*@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = CommonConstants.VENDOR_BANK_DETAILS)*/
    private String id;
    
    private String vendorid;
	
	private String accountholdername;

	private Long accountnumber;	

	private String Ifsc;

	private String bankname;
	
	private String state;
	
	private String city;	

	private String branch;	

	private String addressprooftype;

	private String addressproofurl;
	
	private String cancelledchequeurl;	
	
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

	public String getAccountholdername() {
		return accountholdername;
	}	

	public void setAccountholdername(String accountholdername) {
		this.accountholdername = accountholdername;
	}	

	public Long getAccountnumber() {
		return accountnumber;
	}	

	public void setAccountnumber(Long accountnumber) {
		this.accountnumber = accountnumber;
	}	

	public String getBankname() {
		return bankname;
	}	

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}	

	public String getState() {
		return state;
	}	

	public void setState(String state) {
		this.state = state;
	}
	
	public String getCity() {
		return city;
	}	

	public void setCity(String city) {
		this.city = city;
	}	

	public String getBranch() {
		return branch;
	}	

	public void setBranch(String branch) {
		this.branch = branch;
	}	

	public String getAddressprooftype() {
		return addressprooftype;
	}	

	public void setAddressprooftype(String addressprooftype) {
		this.addressprooftype = addressprooftype;
	}	

	public String getIfsc() {
		return Ifsc;
	}

	public void setIfsc(String ifsc) {
		Ifsc = ifsc;
	}

	public String getAddressproofurl() {
		return addressproofurl;
	}
	
	public void setAddressproofurl(String addressproofurl) {
		this.addressproofurl = addressproofurl;
	}

	public String getCancelledchequeurl() {
		return cancelledchequeurl;
	}

	public void setCancelledchequeurl(String cancelledchequeurl) {
		this.cancelledchequeurl = cancelledchequeurl;
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
