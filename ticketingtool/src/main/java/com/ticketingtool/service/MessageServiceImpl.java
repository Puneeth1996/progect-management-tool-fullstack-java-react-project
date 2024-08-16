package com.ticketingtool.service;

import com.ticketingtool.modal.Chat;
import com.ticketingtool.modal.Message;
import com.ticketingtool.modal.User;
import com.ticketingtool.repository.MessageRespository;
import com.ticketingtool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageRespository messageRespository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ProjectService projectService;



    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender =  userRepository.findById(senderId)
                .orElseThrow(() -> new Exception("User not found with id " + senderId));

        Chat chat = projectService.getProjectbyId(projectId).getChat();
        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);
        Message savedMessage = messageRespository.save(message);

        chat.getMessages().add(savedMessage);
        return savedMessage;

    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);
        List<Message> findChatIdOrderByCreatedAtAsc = messageRespository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findChatIdOrderByCreatedAtAsc;
    }
}
