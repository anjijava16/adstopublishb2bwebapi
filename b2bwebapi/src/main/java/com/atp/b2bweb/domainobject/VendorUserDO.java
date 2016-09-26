package com.atp.b2bweb.domainobject;

import java.util.Date;

import javax.persistence.*;

import com.atp.b2bweb.common.CommonConstants;

/*@Entity
@Table(name = CommonConstants.VENDOR_USER)
@TableGenerator(name = CommonConstants.VENDOR_USER, initialValue = 000001, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = CommonConstants.VENDORUSERDO_FIND_BY_EMAIL_OR_MOBILE, query = "SELECT c FROM VendorUserDO c WHERE c.email =:email or c.mobile =:mobile"),
	@NamedQuery(name = CommonConstants.RETRIEVE_BY_ID, query = "SELECT c FROM VendorUserDO c WHERE c.id =:vendorid"),
	@NamedQuery(name = CommonConstants.VENDOR_LOGIN, query = "SELECT c FROM VendorUserDO c WHERE (c.email =:email OR c.mobile =:mobile) AND c.password =:password")
})*/
public class VendorUserDO {
	
	public static final String FIND_BY_EMAIL_OR_MOBILE = CommonConstants.VENDORUSERDO_FIND_BY_EMAIL_OR_MOBILE;
	
	public static final String VENDOR_LOGIN = CommonConstants.VENDOR_LOGIN;
	
	public static final String RETRIEVE_BY_ID = CommonConstants.RETRIEVE_BY_ID;
	
/*	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = CommonConstants.VENDOR_USER)*/
	private String id;
	
	private String username;

	private String email;	

	private String mobile;

	private String password;
	
	private char accountstatus;
	
	private String displayname;
	
	private String updatedby;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedon;
	
	
	public VendorUserDO(String id, String username, String email ,String mobile){
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
    }
	public VendorUserDO(){
		
	}
	
	
	public String getId() {
		return id;
	}	

	public void setId(String id) {
		this.id = id;
	}	

	public String getUsername() {
		return username;
	}	

	public void setUsername(String username) {
		this.username = username;
	}	

	public String getEmail() {
		return email;
	}	

	public void setEmail(String email) {
		this.email = email;
	}	

	public String getMobile() {
		return mobile;
	}	

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	

	public String getPassword() {
		return password;
	}	

	public void setPassword(String password) {
		this.password = password;
	}	

	public char getAccountstatus() {
		return accountstatus;
	}	

	public void setAccountstatus(char accountstatus) {
		this.accountstatus = accountstatus;
	}	

	public String getDisplayname() {
		return displayname;
	}	

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
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
