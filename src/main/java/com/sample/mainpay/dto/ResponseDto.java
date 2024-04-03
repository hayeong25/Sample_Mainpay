package com.sample.mainpay.dto;

import com.sample.mainpay.utils.Status;
import lombok.Builder;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@ToString
public class ResponseDto {
    private Status resultCode;
    private String resultMessage;
    private String refNo;
    private String tranDate;
    private LocalDateTime tranTime;
    private String payType;
    private String applNo;
    private int amount;
    private String accountNo; // 가상계좌 한정
    private String accountCloseDate; // 가상계좌 한정
}