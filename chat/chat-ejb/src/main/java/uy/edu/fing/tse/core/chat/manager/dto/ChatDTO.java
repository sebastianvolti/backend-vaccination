package uy.edu.fing.tse.core.chat.manager.dto;

import java.io.Serializable;
import java.util.List;

public class ChatDTO implements Serializable{


	private static final long serialVersionUID = -5150976769639517436L;
	
	private Long chat_id;
	private List<String> participants;
	private List<MessageDTO> messages;
	
	public ChatDTO() {}
	

	public Long getChat_id() {
		return chat_id;
	}


	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}


	public List<String> getParticipants() {
		return participants;
	}


	public void setParticipants(List<String> participants) {
		this.participants = participants;
	}


	public List<MessageDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageDTO> messages) {
		this.messages = messages;
	}
}