package br.com.xareu.lift.Controller;

import br.com.xareu.lift.DTO.Mensagem.MensagemRequestDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {


    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public MensagemRequestDTO sendMessage(@Payload MensagemRequestDTO chatMessage) {
        return chatMessage;
    }
}