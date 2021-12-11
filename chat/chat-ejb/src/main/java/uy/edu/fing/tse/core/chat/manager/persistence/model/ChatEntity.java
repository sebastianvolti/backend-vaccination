package uy.edu.fing.tse.core.chat.manager.persistence.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Chat")
public class ChatEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6435388615420593119L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String sender;
	private String receiver;
	
	@OneToMany(mappedBy = "chat")
	private List<MessageEntity> messages;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public List<MessageEntity> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageEntity> messages) {
		this.messages = messages;
	}		
}