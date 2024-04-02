package com.sample.mainpay.dto;

import com.sample.mainpay.utils.Status;
import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ResponseDto {
    private Status resultCode;
    private String resultMessage;
    private String refNo;
    private String tranDate;
    private String payType;
    private String applNo;
}