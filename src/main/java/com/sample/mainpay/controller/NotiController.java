package com.sample.mainpay.controller;

import com.sample.mainpay.dto.CardNotiRequestDto;
import com.sample.mainpay.dto.CardNotiResponseDto;
import com.sample.mainpay.dto.VirtualAccountNotiRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/noti")
public class NotiController {
    @PostMapping("/card")
    public CardNotiResponseDto callbackCard(@RequestBody @Valid CardNotiRequestDto request) {
        log.info("Card Notification : {}", request);

        // TODO 원장 비교 및 처리

        return CardNotiResponseDto.builder()
                .resultCode("0000")
                .message("SUCCESS")
                .mbrRefNo(request.getMbrRefNo())
                .mbrNo(request.getMbrNo())
                .refNo(request.getRefNo())
                .build();
    }

    @PostMapping("/virtualAccount")
    public ResponseEntity<String> callbackVirtualAccount(@RequestBody @Valid VirtualAccountNotiRequestDto request) {
        log.info("Virtual Account Notification : {}", request);

        // TODO 원장 비교 및 처리

        return ResponseEntity.ok("OK");
    }
}