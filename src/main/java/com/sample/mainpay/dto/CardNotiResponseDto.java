package com.sample.mainpay.dto;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class CardNotiResponseDto {
    private String resultCode;
    private String message;
    private String mbrRefNo;
    private String mbrNo;
    private String refNo;
}