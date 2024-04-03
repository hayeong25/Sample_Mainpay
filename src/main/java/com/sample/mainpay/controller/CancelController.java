package com.sample.mainpay.controller;

import com.sample.mainpay.dto.CancelRequestDto;
import com.sample.mainpay.dto.ResponseDto;
import com.sample.mainpay.service.CancelService;
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
public class CancelController {
    @Autowired
    CancelService cancelService;

    @PostMapping("/cancel")
    public void cardCancel(@RequestBody @Valid CancelRequestDto request) {
        log.info("/v1/api/payments/payment/cancel Request : {}", request);
        ResponseDto response = cancelService.cardCancel(request);
        log.info("/v1/api/payments/payment/cancel Response : {}", response);
    }

    @PostMapping("/part-cancel")
    public void cardPartCancel(@RequestBody @Valid CancelRequestDto request) {
        log.info("/v1/api/payments/payment/part-cancel Request : {}", request);
        ResponseDto response = cancelService.cardPartCancel(request);
        log.info("/v1/api/payments/payment/part-cancel Response : {}", response);
    }
}