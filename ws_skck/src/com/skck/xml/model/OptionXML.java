package com.skck.xml.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OptionXML {

	String autostart;
	int id;

	public String getAutostart() {
		return autostart;
	}

	@XmlElement
	public void setAutostart(String autostart) {
		this.autostart = autostart;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

}