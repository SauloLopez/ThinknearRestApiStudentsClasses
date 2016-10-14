package com.api.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Student implements Serializable {
	private static final long serialVersionUID = 299214675635989588L;
	
	private String id;
	private String firstName;
	private String lastName;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Override
	public int hashCode()
	{
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj)
	{
	    return ((obj instanceof Student) && id.equals(((Student) obj).getId()));
	}
}

