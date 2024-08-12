package com.ticketingtool.service;

import com.ticketingtool.modal.User;

public interface UserService {


    User findUserProfileByJwt(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;

    User findUserById(Long userId) throws  Exception;

    User updateUsesProjectSize(User user, int number);



}
