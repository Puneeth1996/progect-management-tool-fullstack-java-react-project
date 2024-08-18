package com.ticketingtool.service;

import com.ticketingtool.modal.PlanType;
import com.ticketingtool.modal.Subscription;
import com.ticketingtool.modal.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgrageSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);


}
