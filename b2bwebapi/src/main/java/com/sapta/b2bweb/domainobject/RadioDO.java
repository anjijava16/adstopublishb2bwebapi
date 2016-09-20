package com.sapta.b2bweb.domainobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "radio")
@TableGenerator(name = "radio", initialValue = 000001, allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "radio.findall", query = "SELECT u FROM RadioDO u")
})
public class RadioDO {
	public static final String FIND_ALL = "radio.findall";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "radio")
    private Long id;
    
	private String name;

	private String type;	

	private String copies;

	private String lang;
	
	private String timetype;
	
	private String cost;

	public Long getId() {
		return id;
	}
	

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public String getType() {
		return type;
	}
	

	public void setType(String type) {
		this.type = type;
	}
	

	public String getCopies() {
		return copies;
	}
	

	public void setCopies(String copies) {
		this.copies = copies;
	}
	

	public String getLang() {
		return lang;
	}
	

	public void setLang(String lang) {
		this.lang = lang;
	}
	

	public String getTimetype() {
		return timetype;
	}
	

	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}
	

	public String getCost() {
		return cost;
	}
	

	public void setCost(String cost) {
		this.cost = cost;
	}
		

}
