package com.ticketingtool.service;

import com.ticketingtool.modal.Chat;
import com.ticketingtool.repository.ChatRepository;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;


    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
}
