package com.api.models;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement 
public class Course implements Serializable {
	private static final long serialVersionUID = 6048843879910302161L;

	private String code;
	private String title;
	private String description;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public int hashCode()
	{
		return code != null ? code.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj)
	{
	    return ((obj instanceof Course) && code.equals(((Course) obj).getCode()));
	}
}
