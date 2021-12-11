package uy.edu.fing.tse.core.chat.manager.dto;

import java.io.Serializable;

public class DetailChatDTO implements Serializable{

	
	private static final long serialVersionUID = 5584421515269686174L;
	
	public DetailChatDTO() {}
	
	private Long id;
	private String contact;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
