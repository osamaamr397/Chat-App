package com.amr.chat.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    //TODO --
    //we need to add two endpoints
    // to add a user to chat app
    /*
    * when a new user connect to our chat application so we need to hit this endpoint
    * and inform the other users that we have a new user added to the chat
    * */
    // to send message by any user

    @MessageMapping("/chat.sendMessage")
    /*
      this message mapping tells what the url that i want to use to invoke
      this method

     */
    @SendTo("/topic/public") //topic here which in webSocketConfig
    /*
    this @ where to send this message to which topic or to which queue
    you want to send this one
     */
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        // @RequestBody in normal app but in socket we use @Payload
        return chatMessage;
    }
    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public") //topic here which in webSocketConfig

    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor){
        //this will allow us to establish a conn between the user and websocket

        //add username in websocket session
        headerAccessor.getSessionAttributes().put("username",
                chatMessage.getSender());
        return chatMessage;
    }
}
