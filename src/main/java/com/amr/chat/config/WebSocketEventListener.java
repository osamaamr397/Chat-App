package com.amr.chat.config;

import com.amr.chat.chat.ChatMessage;
import com.amr.chat.chat.MessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
@Slf4j
//for logging as a i just want to log a few info when a user leaves tha chat
public class WebSocketEventListener {
    /**
     * event listener to listen on all the session disconnect event so this mean each time the
     * user disconnect from our session we need to inform the others so we need to inform all
     * the participants of the chat app , to tell that master x left the chat we need to listen
     * to session disconnect event
     * */
    private final SimpMessageSendingOperations messageTemplate;
    @EventListener
    public void HandleWebSocketDisconnectListener(SessionDisconnectEvent event){
        /*
        * to inform that the users of the chat app that our user or one user has lift the chat
        * to make this we want to @EventListener
        * */

        //i want to wrap the message that i will get from this SessionDisconnectEvent

        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        // i do the casting as this is an object
        String username=(String)headerAccessor.getSessionAttributes().get("username");
        if(username != null){
            log.info("User disconnected : {}",username);

            var chatMessage= ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();
            /*
             * we need to inform the other that specific user has left
             * to do that we need to inject private final-> messageTemplate
             * */
            // /topic/public that the channel or the queue that everyone will be listenning on
            messageTemplate.convertAndSend("/topic/public",chatMessage);
        }
    }

}
