# Chat Service

Disponibiliza tods los servicios referido a todo lo que tiene que ver con el chat del sistema.


## Endpoints


- [Create Chat](#1-create-chat)
- [Get Chats by User](#2-get-chats-by-user)
- [Get Chat](#3-get-chat-by-id)
- [Create Message](#4-create-message)


## 1. Create Chat

Crea un chat entre dos personas


```http
Method | Endpoint
Post   | /chat-web/rest/chat
```

### 1.1. Body Exmaple
```
{
    "participants": ["User1","User1"]
    "messages": []
}
```

## 2. Get Chats by User

Obtiene todos los chats en los que participa un user.


```http
Method | Endpoint
Get   | /chat-web/rest/chat/list/{user}
```

## 3. Get Chat by Id

Obtiene todos los mensajes (contenido, fecha y creador) enviados en un chat


```http
Method | Endpoint
Get   | /chat-web/rest/chat/{id}
```

## 4. Create Message

Crea un mensaje en un determinado chat


```http
Method | Endpoint
Post   | /chat-web/rest/chat/message
```

### 4.1. Body Exmaple
```
{
    "chat_id": 1,
    "content": "First Message."
    "creator": "User1"
}
```
