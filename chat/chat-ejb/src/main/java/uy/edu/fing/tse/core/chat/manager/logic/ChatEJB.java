package uy.edu.fing.tse.core.chat.manager.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import uy.edu.fing.tse.core.chat.manager.dto.ChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.CollectionDetailChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.DetailChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.MessageDTO;
import uy.edu.fing.tse.core.chat.manager.persistence.model.ChatEntity;
import uy.edu.fing.tse.core.chat.manager.persistence.model.MessageEntity;

@Stateless
public class ChatEJB implements ChatEJBLocal, ChatEJBRemote{

	@PersistenceContext(name = "TSE-JPA")
	private EntityManager em;
	
	public ChatEJB() {	}
	
	@Override
	public void createChat(ChatDTO chat) {
		ChatEntity chatEntity = new ChatEntity();
		chatEntity.setSender(chat.getParticipants().get(0));
		chatEntity.setReceiver(chat.getParticipants().get(1));
		chatEntity.setMessages(new ArrayList<MessageEntity>());
		
		em.persist(chatEntity);
	}

	@Override
	public CollectionDetailChatDTO getAllCahtsByUser(String user) {
		
    	Query query = em.createQuery("SELECT c FROM ChatEntity c WHERE c.sender = ?1 OR c.receiver = ?2");
		query.setParameter(1, user);
		query.setParameter(2, user);
		List<ChatEntity> chats = query.getResultList();
		
		CollectionDetailChatDTO result = new CollectionDetailChatDTO();
		
		result.setChats(chats.stream()
			.map(chat -> {
				DetailChatDTO chatDetailDTO = new DetailChatDTO(); 
				chatDetailDTO.setId(chat.getId());
				
				if (!chat.getSender().equals(user)) {
					chatDetailDTO.setContact(chat.getSender());
				} else {
					chatDetailDTO.setContact(chat.getReceiver());
				}
				
				return chatDetailDTO;
			})
			.distinct()
			.collect(Collectors.toList()));
		
		
		return result;
	}

	@Override
	public ChatDTO getChat(Long chatId) {
		ChatEntity chatEntity = em.find(ChatEntity.class, chatId);
		
		MessageDTO msgDTO;
		ChatDTO chat = new ChatDTO();
		chat.setParticipants(List.of(chatEntity.getSender(), chatEntity.getReceiver()));
		chat.setMessages(new ArrayList<MessageDTO>());
		
		for (MessageEntity msgEntity : chatEntity.getMessages()) {
			msgDTO = new MessageDTO();
			msgDTO.setContent(msgEntity.getContent());
			msgDTO.setDate(msgEntity.getCreation());
			msgDTO.setCreator(msgEntity.getCreator());
			
			chat.getMessages().add(msgDTO);
		}
		
		return chat;
	}

	@Override
	public void saveMesaage(MessageDTO messageDTO) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setCreator(messageDTO.getCreator());
		messageEntity.setContent(messageDTO.getContent());
		messageEntity.setChat(em.find(ChatEntity.class, messageDTO.getChat_id()));
		em.persist(messageEntity);
		
	}

	@Override
	public String ping() {
		return "Chat service running!!";
	}

}
