package org.shivayan.javabrains.messengerapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

// JAX-B will convert the instances of the below object into xmls
// Below annotation specifies that the below element is the root element 
@XmlRootElement
public class Message {
	private long id;
	private String message;
	private Date created;
	private String author;
	
	// For Comment sub resource
	private Map<Long, Comment> comments = new HashMap<>();
	
	// For implementation of HATEOAS
	private List<Link> links = new ArrayList<>();

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

	// Marks the parameter as ignore for XML & JSON Conversion. Exclude comments
	// from both JSON and XML representations
	@XmlTransient
	public Map<Long, Comment> getComments() {
		return comments;
	}

	public void setComments(Map<Long, Comment> comments) {
		this.comments = comments;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public void addLink(String url, String rel) {
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);
	}

}
