package uy.edu.fing.tse.core.chat.manager.persistence.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Table(name="Message")
public class MessageEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4464278490487410188L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String creator;
	private String content;
	
	@CreationTimestamp
	private Date creation;

	@ManyToOne(optional = false)
	private ChatEntity chat;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public ChatEntity getChat() {
		return chat;
	}

	public void setChat(ChatEntity chat) {
		this.chat = chat;
	}
	
}