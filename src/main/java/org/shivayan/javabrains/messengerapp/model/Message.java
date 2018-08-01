package org.shivayan.javabrains.messengerapp.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

// JAX-B will convert the instances of the below object into xmls
// Below annotation specifies that the below element is the root element 
@XmlRootElement
public class Message {
	private long id;
	private String message;
	private Date created;
	private String author;

	public Message() {

	}

	public Message(long id, String message, Date created, String author) {
		this.id = id;
		this.message = message;
		this.created = created;
		this.author = author;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
