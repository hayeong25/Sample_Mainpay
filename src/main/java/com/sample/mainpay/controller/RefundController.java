package com.sample.mainpay.controller;

import com.sample.mainpay.dto.RefundICancelRequestDto;
import com.sample.mainpay.dto.RefundInquiryRequestDto;
import com.sample.mainpay.dto.RefundRequestDto;
import com.sample.mainpay.dto.ResponseDto;
import com.sample.mainpay.service.RefundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/api/payments/payment/cms/front")
public class RefundController {
    @Autowired
    RefundService refundService;

    @PostMapping("/register")
    public void registerRefund(@RequestBody @Valid RefundRequestDto request) {
        log.info("/v1/api/payments/payment/cms/front/register Request : {}", request);
        ResponseDto response = refundService.registerRefund(request);
        log.info("/v1/api/payments/payment/cms/front/register Response : {}", response);
    }

    @PostMapping("/status")
    public void inquiryRefund(@RequestBody @Valid RefundInquiryRequestDto request) {
        log.info("/v1/api/payments/payment/cms/front/status Request : {}", request);
        ResponseDto response = refundService.inquiryRefund(request);
        log.info("/v1/api/payments/payment/cms/front/status Response : {}", response);
    }

    @PostMapping("/cancel")
    public void cancelRefund(@RequestBody @Valid RefundICancelRequestDto request) {
        log.info("/v1/api/payments/payment/cms/front/cancel Request : {}", request);
        ResponseDto response = refundService.cancelRefund(request);
        log.info("/v1/api/payments/payment/cms/front/cancel Response : {}", response);
    }
}