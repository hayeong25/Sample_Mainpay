package com.sample.mainpay.controller;

import com.sample.mainpay.dto.PaymentRequestDto;
import com.sample.mainpay.dto.ResponseDto;
import com.sample.mainpay.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/api/payments/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping("/card-keyin/trans")
    public void cardApproval(@RequestBody @Valid PaymentRequestDto request) {
        log.info("/v1/api/payments/payment/card-keyin/trans Request : {}", request);
        ResponseDto response = paymentService.cardApproval(request);
    }
}