package com.substring.chat.controllers;

import com.substring.chat.entities.Message;
import com.substring.chat.entities.Room;
import com.substring.chat.playload.MessageRequest;
import com.substring.chat.repositories.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/chat")
@CrossOrigin("http://localhost:5173")
public class ChatController {

    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

//    for sending and receiving message

    @MessageMapping("/sendMessage/{roomId}") // app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}") //subscribe
    public Message sendMesssage(@DestinationVariable String roomId, @RequestBody MessageRequest request){

        Room room = roomRepository.findByRoomId(request.getRoomId());

        Message msg = new Message();
        msg.setContent(request.getContent());
        msg.setSender(request.getSender());
        msg.setTimeStamp(LocalDateTime.now());

        if(room!=null){
            room.getMessages().add(msg);
            roomRepository.save(room);
        }
        else{
            throw new RuntimeException("Room Not Found!");
        }

        return msg;
    }


}
