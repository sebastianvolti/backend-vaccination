package uy.edu.fing.tse.core.chat.manager.dto;

import java.io.Serializable;
import java.util.Date;

public class MessageDTO implements Serializable{

	private static final long serialVersionUID = -315829626127800580L;
	
	private Long chat_id;
	private String creator;
	private String content;
	private Date date;
	
	public MessageDTO() {	}

	
		
	public Long getChat_id() {
		return chat_id;
	}

	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}