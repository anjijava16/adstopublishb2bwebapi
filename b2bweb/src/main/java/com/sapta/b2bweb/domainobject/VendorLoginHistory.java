package com.sapta.b2bweb.domainobject;

import java.util.Date;

public class VendorLoginHistory {

	private Long id;
    
    private Long agentid;
	
	private Date fromdate;

	private Date thrudate;	

	private String loginwith;

	private String successlogin;
	
	private String updatedby;

	private Date updatedon;

	public Long getId() {
		return id;
	}	

	public void setId(Long id) {
		this.id = id;
	}	

	public Long getAgentid() {
		return agentid;
	}	

	public void setAgentid(Long agentid) {
		this.agentid = agentid;
	}	

	public Date getFromdate() {
		return fromdate;
	}	

	public void setFromdate(Date fromdate) {
		this.fromdate = fromdate;
	}	

	public Date getThrudate() {
		return thrudate;
	}	

	public void setThrudate(Date thrudate) {
		this.thrudate = thrudate;
	}	

	public String getLoginwith() {
		return loginwith;
	}	

	public void setLoginwith(String loginwith) {
		this.loginwith = loginwith;
	}	

	public String getSuccesslogin() {
		return successlogin;
	}	

	public void setSuccesslogin(String successlogin) {
		this.successlogin = successlogin;
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
