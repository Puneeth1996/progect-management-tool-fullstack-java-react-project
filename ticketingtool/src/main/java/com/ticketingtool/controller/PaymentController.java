package com.ticketingtool.controller;

import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.ticketingtool.modal.PlanType;
import com.ticketingtool.modal.User;
import com.ticketingtool.response.PaymentLinkResponse;
import com.ticketingtool.service.UserService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {


    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String apiSecret;

    @Autowired
    private UserService userService;


    public ResponseEntity<PaymentLinkResponse> createPaymentLink(
            @PathVariable PlanType planType,
            @RequestHeader("Authorization") String jwt
            ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        int amount = 799*100;
        if(planType.equals(PlanType.ANNUALLY)){
            amount = amount *12;
            amount = (int) (amount*0.7);
        }
            RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);

            JSONObject paymentLinkRespose = new JSONObject();
            paymentLinkRespose.put("amount", amount);
            paymentLinkRespose.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name", user.getFullName());
            customer.put("email", user.getEmail());
            paymentLinkRespose.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);
            paymentLinkRespose.put("notify", notify);

            paymentLinkRespose.put("callback_url", "http://localhost:5173/upgrade_plan/success?planType=" + planType);

            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRespose);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            PaymentLinkResponse res = new PaymentLinkResponse();
            res.setPayment_link_id(paymentLinkId);
            res.setPayment_link_url(paymentLinkUrl);

            return new ResponseEntity<>(res, HttpStatus.CREATED);

    }



}
