package com.ticketingtool.controller;


import com.ticketingtool.modal.Chat;
import com.ticketingtool.modal.Message;
import com.ticketingtool.modal.User;
import com.ticketingtool.request.CreateMessageRequest;
import com.ticketingtool.service.MessageService;
import com.ticketingtool.service.ProjectService;
import com.ticketingtool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;


    @PostMapping("/semd")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception {

        User user  = userService.findUserById(request.getProjectId());

        Chat chats = projectService.getProjectbyId(request.getProjectId()).getChat();

        if(chats==null) throw  new Exception("Chats nto found");

        Message sentMessage = messageService.sendMessage(request.getSenderId(),
                request.getProjectId(), request.getContent());

        return ResponseEntity.ok(sentMessage);

    }


    @GetMapping("/chat/{projectId")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception {
        List<Message> messages = messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }


}
