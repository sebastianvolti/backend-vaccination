package uy.edu.fing.tse.core.chat.services;


import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import uy.edu.fing.tse.core.chat.manager.dto.ChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.CollectionDetailChatDTO;
import uy.edu.fing.tse.core.chat.manager.dto.MessageDTO;
import uy.edu.fing.tse.core.chat.manager.logic.ChatEJBLocal;

import uy.edu.fing.tse.core.chat.manager.persistence.model.MessageEntity;

@RequestScoped
@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON})
public class ChatRest {
	
	@EJB
	private ChatEJBLocal chatEJBLocal;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ping")
	public Response ping(){
		return Response.ok(this.chatEJBLocal.ping()).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveChat(ChatDTO chat) {
		try {
			this.chatEJBLocal.createChat(chat);
			return Response.ok().entity(String.format("El chat entre %s y %s ha sido creado", chat.getParticipants().get(0), chat.getParticipants().get(1))).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/message")
	public Response saveMessage(MessageDTO message) {
		try {
			this.chatEJBLocal.saveMesaage(message);
			return Response.ok().entity(String.format("El mensaje %s ha sido agreado al chat %s", message.getContent(), message.getChat_id())).build();
		} catch (Exception e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public ChatDTO getChat(@PathParam("id")Long chatId) {
		return this.chatEJBLocal.getChat(chatId);
	}

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list/{user}")
	public CollectionDetailChatDTO getAllChatsByUser(@PathParam("user")String user) {
		return this.chatEJBLocal.getAllCahtsByUser(user);
	}

}
