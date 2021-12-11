package uy.edu.fing.tse.core.chat.manager.logic;

import javax.ejb.Remote;

import uy.edu.fing.tse.core.chat.manager.dto.ChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.CollectionDetailChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.MessageDTO;

@Remote
public interface ChatEJBRemote {
	
	public String ping();
	public void createChat(ChatDTO chat);
	public void saveMesaage(MessageDTO message);
	public CollectionDetailChatDTO getAllCahtsByUser(String userId);
	public ChatDTO getChat(Long id);

}
