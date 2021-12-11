package uy.edu.fing.tse.core.chat.manager.dto;
import java.io.Serializable;
import java.util.List;

import uy.edu.fing.tse.core.chat.manager.dto.DetailChatDTO;

public class CollectionDetailChatDTO implements Serializable{

	
	private static final long serialVersionUID = 8687807281146339282L;
	
	private List<DetailChatDTO> chats;
	
	public CollectionDetailChatDTO() {}

	public List<DetailChatDTO> getChats() {
		return chats;
	}

	public void setChats(List<DetailChatDTO> chats) {
		this.chats = chats;
	}
}
