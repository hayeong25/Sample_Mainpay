package com.sample.mainpay.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefundResponseDto {
    private String resultCode; // 응답코드 '200' 이면 성공, 이외는 거절
    private String resultMessage; // 응답 메시지
    private Data data;

    @Getter
    @Setter
    public static class Data {
        private String mbrRefNo; // 가맹점 주문번호
        private String refNo; // 거래번호
        private String tranDate; // 거래일자 (거래 취소시 필요)
    }
}