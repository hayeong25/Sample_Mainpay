package com.sample.mainpay.controller;

import com.sample.mainpay.dto.ResponseDto;
import com.sample.mainpay.dto.VirtualAccountCancelRequestDto;
import com.sample.mainpay.dto.VirtualAccountRequestDto;
import com.sample.mainpay.service.VirtualAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v2/api/payments/payment/vacct")
public class VirtualAccountController {
    @Autowired
    VirtualAccountService virtualAccountService;

    @PostMapping("/publish")
    public void virtualAccountPublish(@RequestBody @Valid VirtualAccountRequestDto request) {
        log.info("/v2/api/payments/payment/vacct/publish Request : {}", request);
        ResponseDto response = virtualAccountService.virtualAccountPublish(request);
        log.info("/v2/api/payments/payment/vacct/publish Response : {}", response);
    }

    @PostMapping("/cancel")
    public void virtualAccountCancel(@RequestBody @Valid VirtualAccountCancelRequestDto request) {
        log.info("/v2/api/payments/payment/vacct/cancel Request : {}", request);
        ResponseDto response = virtualAccountService.virtualAccountCancel(request);
        log.info("/v2/api/payments/payment/vacct/cancel Response : {}", response);
    }
}