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
public class VirtualAccountService {
    @Autowired
    WebClient webClient;

    public ResponseDto virtualAccountPublish(VirtualAccountRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("bankCode", request.getBankCode());
        formData.add("amount", request.getAmount());
        formData.add("accountCloseDate", request.getPayLimitDay());
        formData.add("goodsName", request.getProductName());
        formData.add("customerName", request.getCustomerName());
        formData.add("customerTelNo", request.getCustomerTelNo());
        formData.add("customerEmail", request.getCustomerEmail());
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getPublishSignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v2/api/payments/payment/vacct/publish")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        VirtualAccountResponseDto response = new VirtualAccountResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), VirtualAccountResponseDto.class);
        } catch (Exception e) {
            log.error("PaymentService ERROR ", e);
        }

        // TODO 원장, 정산 처리
        
        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .amount(response.getData().getAmount())
                .accountNo(response.getData().getAccountNo())
                .accountCloseDate(response.getData().getAccountCloseDate())
                .build();
    }

    public ResponseDto virtualAccountCancel(VirtualAccountCancelRequestDto request) {
        // 이미 입금된 거래는 가상 계좌 발급 취소 불가
        
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("orgTranDate", request.getApprovalDate());
        formData.add("accountNo", request.getAccountNumber());
        formData.add("orgTranDate", request.getApprovalDate());
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getCancelSignature(request));

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v2/api/payments/payment/vacct/cancel")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        VirtualAccountResponseDto response = new VirtualAccountResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), VirtualAccountResponseDto.class);
        } catch (Exception e) {
            log.error("PaymentService ERROR ", e);
        }

        // TODO 원장, 정산 처리

        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .tranTime(response.getData().getTranTime())
                .amount(response.getData().getAmount())
                .accountNo(response.getData().getAccountNo())
                .build();
    }

    private String getPublishSignature(VirtualAccountRequestDto request) {
        String apiKey = "";
        String signature = request.getMerchantNo() + "|" + request.getOrderNo() + "|" + request.getAmount() + "|" + apiKey + "|" + request.getRequestDateTime().toString();
        return DigestUtils.sha256Hex(signature);
    }

    private String getCancelSignature(VirtualAccountCancelRequestDto request) {
        String apiKey = "";
        String signature = request.getMerchantNo() + "|" + request.getOrderNo() + "|" + request.getAmount() + "|" + apiKey + "|" + request.getRequestDateTime().toString();
        return DigestUtils.sha256Hex(signature);
    }

    private Status getStatus(String resultCode) {
        if (resultCode.equals("200")) {
            return Status.SUCCESS;
        }

        return Status.FAIL;
    }
}