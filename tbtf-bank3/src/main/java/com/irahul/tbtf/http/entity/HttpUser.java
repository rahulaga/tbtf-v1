package com.irahul.tbtf.http.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.irahul.tbtf.entity.User;

/**
 * Select fields we want exposed to the REST layer. Separation from business/data layer. 
 * 
 * Note "XML" annotation. The resteasy-jettison implementation converts these to JSON depending on
 * the Accept media type
 * 
 * @author rahul
 *
 */
@XmlRootElement(name = "user")
public class HttpUser {
	
	@XmlElement
	public long id;
	
	@XmlElement//(name="foo")
	public String firstName;
	
	@XmlElement
	public String lastName;
	
	@XmlElement
	public String pin;
	
	//required by framework
	protected HttpUser() {}

	public HttpUser(User user) {
		this.id=user.getId();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		//not setting PIN
	}
	
}
