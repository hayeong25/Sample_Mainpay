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
public class CancelService {
    @Autowired
    WebClient webClient;

    public ResponseDto cardCancel(CancelRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("orgRefNo", request.getRefNo());
        formData.add("orgTranDate", request.getApprovalDate());
        formData.add("payType", request.getPayType());
        formData.add("paymethod", request.getPaymentMethod());
        formData.add("amount", request.getAmount());
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getSignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v1/api/payments/payment/cancel")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        CancelResponseDto response = new CancelResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), CancelResponseDto.class);
        } catch (Exception e) {
            log.error("CancelService ERROR ", e);
        }

        // TODO 원장, 정산 처리
        
        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .build();
    }

    public ResponseDto cardPartCancel(CancelRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("amount", request.getAmount());
        formData.add("orgRefNo", request.getRefNo());
        formData.add("orgTranDate", request.getApprovalDate());
        formData.add("payType", request.getPayType());
        formData.add("paymethod", request.getPaymentMethod());
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getSignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v1/api/payments/payment/part-cancel")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        CancelResponseDto response = new CancelResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), CancelResponseDto.class);
        } catch (Exception e) {
            log.error("CancelService ERROR ", e);
        }

        // TODO 원장, 정산 처리

        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .build();
    }

    private String getSignature(CancelRequestDto request) {
        String apiKey = "";
        String signature = request.getMerchantNo() + "|" + request.getOrderNo() + "|" + request.getAmount() + "|" + apiKey + "|" + request.getRequestDateTime();
        return DigestUtils.sha256Hex(signature);
    }

    private Status getStatus(String resultCode) {
        if (resultCode.equals("200")) {
            return Status.SUCCESS;
        }

        return Status.FAIL;
    }
}