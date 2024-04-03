package com.sample.mainpay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.mainpay.dto.*;
import com.sample.mainpay.utils.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RefundService {
    @Autowired
    WebClient webClient;

    public ResponseDto registerRefund(RefundRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("objectPaymethod", request.getPaymentMethod());
        formData.add("objectRefNo", request.getRefNo());
        formData.add("objectTranDate", request.getTranDate());
        formData.add("bankCode", request.getBankCode());
        formData.add("accountNo", request.getAccountNumber());
        formData.add("accountOwner", request.getAccountHolder());
        formData.add("birthDay", request.getRegNo());
        formData.add("amount", request.getAmount());
        formData.add("requestUser", request.getUserId());
        formData.add("requestSystem", "Merchant");
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getRefundSignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v1/api/payments/payment/cms/front/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        RefundResponseDto response = new RefundResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), RefundResponseDto.class);
        } catch (Exception e) {
            log.error("PaymentService ERROR ", e);
        }

        // TODO 원장, 정산 처리
        
        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .build();
    }
    public ResponseDto inquiryRefund(RefundInquiryRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("orgRefNo", request.getRefNo());
        formData.add("requestUser", request.getUserId());
        formData.add("requestSystem", "Merchant");
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getInquirySignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v1/api/payments/payment/cms/front/status")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        RefundResponseDto response = new RefundResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), RefundResponseDto.class);
        } catch (Exception e) {
            log.error("PaymentService ERROR ", e);
        }

        // TODO 원장, 정산 처리

        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .build();
    }

    public ResponseDto cancelRefund(RefundICancelRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("orgRefNo", request.getRefNo());
        formData.add("requestUser", request.getUserId());
        formData.add("requestSystem", "Merchant");
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getRefundCancelSignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v1/api/payments/payment/cms/front/cancel")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        RefundResponseDto response = new RefundResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), RefundResponseDto.class);
        } catch (Exception e) {
            log.error("PaymentService ERROR ", e);
        }

        // TODO 원장, 정산 처리

        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .build();
    }

    private String getRefundSignature(RefundRequestDto request) {
        String apiKey = "";
        String signature = request.getMerchantNo() + "|" + request.getOrderNo() + "|" + request.getAmount() + "|" + apiKey + "|" + request.getRequestDateTime();
        return DigestUtils.sha256Hex(signature);
    }

    private String getInquirySignature(RefundInquiryRequestDto request) {
        String apiKey = "";
        String signature = request.getMerchantNo() + "||0|" + apiKey + "|" + request.getRequestDateTime();
        return DigestUtils.sha256Hex(signature);
    }

    private String getRefundCancelSignature(RefundICancelRequestDto request) {
        String apiKey = "";
        String signature = request.getMerchantNo() + "|" + request.getOrderNo() + "|" + request.getAmount() + "|" + apiKey + "|" + request.getRequestDateTime();
        return DigestUtils.sha256Hex(signature);
    }

    private Status getStatus(String resultCode) {
        if (resultCode.equals("200") || resultCode.equals("0000")) {
            return Status.SUCCESS;
        }

        return Status.FAIL;
    }
}