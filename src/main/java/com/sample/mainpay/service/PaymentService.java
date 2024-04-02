package com.sample.mainpay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.mainpay.dto.PaymentRequestDto;
import com.sample.mainpay.dto.PaymentResponseDto;
import com.sample.mainpay.dto.ResponseDto;
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
public class PaymentService {
    @Autowired
    WebClient webClient;

    public ResponseDto cardApproval(PaymentRequestDto request) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("mbrNo", request.getMerchantNo());
        formData.add("mbrRefNo", request.getOrderNo());
        formData.add("paymethod", request.getPaymentMethod());
        formData.add("cardNo", request.getCardNumber());
        formData.add("expd", request.getExpiredPeriod());
        formData.add("amount", request.getAmount());
        formData.add("installment", request.getInstallment());
        formData.add("goodsName", request.getProductName());
        formData.add("timestamp", request.getRequestDateTime());
        formData.add("signature", getSignature(request));
        formData.add("keyinAuthType", request.getKeyinAuthType());
        formData.add("authType", request.getAuthType());
        formData.add("regNo", request.getRegNo());
        formData.add("passwd", request.getPassword());
        formData.add("customerName", request.getCustomerName());
        formData.add("customerTelNo", request.getCustomerTelNo());
        formData.add("customerEmail", request.getCustomerEmail());
        formData.add("retailerCode", request.getRetailerCode());

        ResponseEntity<String> responseStr = webClient.post()
                .uri("https://test-relay.mainpay.co.kr/v1/api/payments/payment/card-keyin/trans")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue(formData)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .onErrorResume(error -> Mono.error(new Exception("WebClient ERROR : {}", error)))
                .block();

        PaymentResponseDto response = new PaymentResponseDto();

        try {
            if (ObjectUtils.isEmpty(responseStr) || ObjectUtils.isEmpty(responseStr.getBody())) {
                throw new Exception("WebClient ResponseEntity is Empty");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseStr.getBody(), PaymentResponseDto.class);
        } catch (Exception e) {
            log.error("PaymentService ERROR ", e);
        }

        // TODO 원장, 정산 처리
        
        return ResponseDto.builder()
                .resultCode(getStatus(response.getResultCode()))
                .resultMessage(response.getResultMessage())
                .refNo(response.getData().getRefNo())
                .tranDate(response.getData().getTranDate())
                .applNo(response.getData().getApplNo())
                .payType(response.getData().getPayType())
                .build();
    }

    private String getSignature(PaymentRequestDto request) {
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